package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.social.commons.*;


import com.spd.cq.searspartsdirect.common.helpers.Constants;

// This component is being reworked, please hold any comments until rework is completed.
public class GuideNavigationTag extends CQBaseTag {
	protected static Logger log = LoggerFactory
			.getLogger(GuideNavigationTag.class);

	private static abstract class LabelGenerator {
		public abstract String generateLabel(Node beingLabelled, ResourceResolver rr);
	}
	private static class SubheadLabelGenerator extends LabelGenerator {
		public String generateLabel(Node subheadBeingLabelled, ResourceResolver rr) {
			String label = Constants.EMPTY;
			try {
				label = subheadBeingLabelled.getProperty("textvalue").getString();
			} catch (Exception e) {
				log.error("retrieving subhead label: ",e);
			}
			return label;
		}
	}
	private static class CommentsLabelGenerator extends LabelGenerator {
		public String generateLabel(Node commentsBeingLabelled, ResourceResolver rr) {
			StringBuilder label = new StringBuilder(Constants.COMMENTS_GUIDE_NAV_LINK_PREFIX);
			try {
				Resource thoseComments = rr.resolve(commentsBeingLabelled.getPath());
				CommentSystem thatCs = thoseComments.adaptTo(CommentSystem.class);
				if (log.isDebugEnabled()) {
					log.debug("comments node is "+commentsBeingLabelled);
					log.debug("thoseComments is "+thoseComments);
					log.debug("thatCs is "+thatCs)
;				}
				label.append(" (").append(thatCs.countComments()).append(")");
			} catch (RepositoryException re) {
				log.warn("finding out about comments, ",re);
			}
			return label.toString();
		}
	}
	private final static Map<String,LabelGenerator> specialLabelGenerators = initSpecialLabelGenerators();
	private final static Map<String,LabelGenerator> initSpecialLabelGenerators() {
		Map<String,LabelGenerator> generators = new HashMap<String,LabelGenerator>();
		generators.put(Constants.SUBHEAD_COMPONENT, new SubheadLabelGenerator());
		generators.put(Constants.COMMENTS_COMPONENT,new CommentsLabelGenerator());
		return generators;
	}
	
	// We need to abstract out link generation. function pattern not quite applicable, diff info needed
	
	@Override
	public int doStartTag() throws JspException {
		// Really we want a Map<String:sectionresourcetype,String:linktext>
		Map<String,String> typeToLabel = getConfiguredTypeLabels();
		
		if (log.isDebugEnabled()) log.debug("typeToLabel is "+typeToLabel);
		// output list containing lists [linktext,sectionlink]
		List<List<String>> sections = new ArrayList<List<String>>();
		try {
			if (log.isDebugEnabled()) log.debug("currentNode is "+currentNode);
			Node parsysParent = getParsys();
			if (parsysParent != null) { //TODO PATCH - actually fix.
				NodeIterator parsysChildren = parsysParent.getNodes();
				while (parsysChildren.hasNext()) {
					Node parSibling = parsysChildren.nextNode();
					if (parSibling.getName().equals(currentNode.getName())) {
						continue; // We skip ourselves.
					}
					String siblingResourceType = parSibling.getProperty("sling:resourceType").getString();
					if (log.isDebugEnabled()) {
						log.debug("parSibling is "+parSibling);
						log.debug("parSibling name is "+parSibling.getName());
						log.debug("parSibling resourceType is "+siblingResourceType);
					}
					String labelFound = typeToLabel.get(siblingResourceType);
					if (labelFound != null) {
						if (labelFound.matches(" *")) {
							// look for and use special label generator
							LabelGenerator specialGenerator = specialLabelGenerators.get(siblingResourceType);
							if (specialGenerator != null) {
								labelFound = specialGenerator.generateLabel(parSibling,resourceResolver);
							}
						}
						List<String> item = new ArrayList<String>();
						item.add(labelFound);
						// refactor...
						item.add(parsysParent.getName()+"_"+parSibling.getName());
						sections.add(item);
					}
				}
			}
		} catch (RepositoryException re) {
			log.error("exception getting sections",re);
		}
		pageContext.setAttribute("sections", sections);
		
		String jumpToString = Constants.GUIDE_NAV_DEF_JUMPTO_TEXT;
		try {
			jumpToString = currentPage.getContentResource().adaptTo(Node.class).getProperty(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR).getString();
		} catch (RepositoryException re) {
			log.error("exception getting jump to text",re);
		}
		pageContext.setAttribute(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR, jumpToString);
		
		return EVAL_BODY_INCLUDE;
	}
	
	/**
	 * Reads config and builds the map of types to link text
	 * @return The map of component types to their link text
	 */
	private Map<String,String> getConfiguredTypeLabels() {
		List<List<String>> typesAndLabels = this.getMenuItems("sections", currentNode);
		Map<String,String> typeToLabel = new HashMap<String,String>();
		for (List<String> aType : typesAndLabels) {
			typeToLabel.put(aType.get(0).trim(), aType.get(1));
		}
		return typeToLabel;
	}
	
	
	@Deprecated // due to inefficiency
	private List<String> getMenuItemConfig(String jsonString) {

		List<String> item = new ArrayList<String>();

		try {
			JSONObject menuItemConfig = new JSONObject(jsonString);
			item.add(menuItemConfig.getString("resType"));
			item.add(menuItemConfig.getString("link"));
		} catch (Exception e) {
			log.error("JSON error", e);
		}

		return item;
	}

	@Deprecated // due to inefficiency - we could populate the map directly
	private List<List<String>> getMenuItems(String propertyName, Node node) {
		// config list containing lists [sectionresourcetype,linktext]
		List<List<String>> items = new ArrayList<List<String>>();
		try {
			if (node.hasProperty(propertyName) == true) {
				if (node.getProperty(propertyName).getDefinition().isMultiple() == true) {
					Value[] menuItems = node.getProperty(propertyName)
							.getValues();

					for (Value menuItem : menuItems) {
						items.add(this.getMenuItemConfig(menuItem.getString()));
					}
				} else {
					String menuItem = node.getProperty(propertyName)
							.getString();
					items.add(this.getMenuItemConfig(menuItem));
				}
			}
		} catch (Exception e) {
			log.error("Retrieving multi-list error", e);
		}
		return items;
	}

	@Deprecated
	private Node getParsys() throws RepositoryException {
		
		Node parsysParent = null;
		if (currentNode != null) //TODO PATCH, need actual fix
			parsysParent = currentNode.getParent();
		if (parsysParent != null) //TODO PATCH, need actual fix
			if (log.isDebugEnabled()) {
				log.debug("parsysParent is "+parsysParent);
				log.debug("parsysParent name is "+parsysParent.getName());
			}
		return parsysParent;
	}
}
