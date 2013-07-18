package com.spd.cq.searspartsdirect.common.tags;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

@SuppressWarnings("serial")
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

		String resultText = placeholder;
		String resultImage = "";
		
		ChosenSettings chosen = maybeRetrieveChosenSettings();
		String chosenText = chosen.getText();
		if (!isTextBlank(chosenText)) {
			resultText = chosenText;
		}
		String chosenImage = chosen.getImage();
		if (!isTextBlank(chosenImage)) {
			resultImage = chosenImage;
		}
			
		String adhocText = maybeRetrieveAdhocText();
		if (!isTextBlank(adhocText)) {
			resultText = adhocText;
		}
		
		pageContext.setAttribute("htwText", resultText);
		pageContext.setAttribute("htwImage",resultImage);
		
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
				try {
					if (chosenNode.hasProperty(Constants.ASSETS_TITLE_REL_PATH)) {
						settings.setText(chosenNode.getProperty(Constants.ASSETS_TITLE_REL_PATH).getString());
					}
					// restore Constants.ASSETS_IMAGE_REL_PATH or alt.
					if (chosenNode.hasNode(Constants.ASSETS_IMAGE_REL_PATH)) {
						settings.setImage(chosenNode.getPath()+Constants.ASSETS_IMAGE_PATH); // +Constants.ASSETS_IMAGE_PATH
					} else {
						log.info("No such child as "+Constants.ASSETS_IMAGE_REL_PATH+" under "+chosenNode);
					}
				} catch (RepositoryException re) {
					log.warn("Could not retrieve title, ",re);
				}
				
			} else {
				log.warn("Could not resolve "+chosenPath);
			}
		}

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
