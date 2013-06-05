package com.spd.cq.searspartsdirect.common.tags;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class ResolveHazardTipWarningTag extends CQBaseTag {
	protected static Logger log = LoggerFactory
			.getLogger(ResolveHazardTipWarningTag.class);
	
	private String adhocField;
	private String choiceField;
	private String placeholder;
	
	/**
	 * Sets pageContext attributes htwText and htwImage.
	 */
	public int doStartTag() {
		if (log.isDebugEnabled()) {
			log.debug("adhocField is "+adhocField+", choiceField is "+choiceField);
		}
		String resultText = placeholder;
		String resultImage = "";
		
		String adhocText = maybeRetrieveAdhocText();
		
		if (!isTextBlank(adhocText)) {
			resultText = adhocText;
		} else {
			ChosenSettings chosen = maybeRetrieveChosenSettings();
			String chosenText = chosen.getText();
			if (!isTextBlank(chosenText)) {
				resultText = chosenText;
			}
			String chosenImage = chosen.getImage();
			if (!isTextBlank(chosenImage)) {
				resultImage = chosenImage;
			}
		}
		
		pageContext.setAttribute("htwText", resultText);
		pageContext.setAttribute("htwImage",resultImage);
		// We might also need to retrieve the image.. scope from nowhere?
		
		return SKIP_BODY;
	}
	
	private static final class ChosenSettings {
		private String text;
		private String image;
		
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		@Override
		public String toString() {
			return "text="+text+", image="+image;
		}
	}
	
	private ChosenSettings maybeRetrieveChosenSettings() {
		ChosenSettings settings = new ChosenSettings();
		
		String chosenPath = null;
		try {
			if (currentNode.hasProperty(choiceField)) {
				chosenPath = currentNode.getProperty(choiceField).getString();
			}
		} catch (RepositoryException re) {
			log.warn("Retrieving "+choiceField+", ",re);
		}
		
		if (!isTextBlank(chosenPath)) {
			Resource chosenResource = resourceResolver.resolve(chosenPath);
			if (chosenResource != null) {
				Node chosenNode = chosenResource.adaptTo(Node.class);
				if (log.isDebugEnabled()) log.debug("chosenNode is "+chosenNode);
				try {
					if (chosenNode.hasProperty(Constants.ASSETS_TITLE_REL_PATH)) {
						settings.setText(chosenNode.getProperty(Constants.ASSETS_TITLE_REL_PATH).getString());
					}
					// restore Constants.ASSETS_IMAGE_REL_PATH or alt.
					if (chosenNode.hasNode("image")) {
						settings.setImage(chosenNode.getPath()+"/image"); // +Constants.ASSETS_IMAGE_PATH
					} else {
						if (log.isDebugEnabled()) log.debug("No such child as "+"image"+" under "+chosenNode);
					}
				} catch (RepositoryException re) {
					log.warn("Could not retrieve title, ",re);
				}
				
			} else {
				log.warn("Could not resolve "+chosenPath);
			}
		}
		if (log.isDebugEnabled()) log.debug("at end, settings is "+settings);
		return settings;
	}
	
	private String maybeRetrieveAdhocText() {
		String adhocText = null;
		try {
			if (currentNode.hasProperty(adhocField)) {
				adhocText = currentNode.getProperty(adhocField).getString();
			}
		} catch (RepositoryException re) {
			log.warn("Retrieving "+adhocField+", ",re);
		}
		return adhocText;
	}
	
	private static final boolean isTextBlank(final String text) {
		return text == null || text.matches("[ \t]*");
	}

	public String getAdhocField() {
		return adhocField;
	}

	public void setAdhocField(String adhocField) {
		this.adhocField = adhocField;
	}

	public String getChoiceField() {
		return choiceField;
	}

	public void setChoiceField(String choiceField) {
		this.choiceField = choiceField;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	
}
