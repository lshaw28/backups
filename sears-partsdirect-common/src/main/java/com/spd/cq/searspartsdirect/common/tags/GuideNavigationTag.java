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
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GuideNavigationTag extends CQBaseTag {
	protected static Logger log = LoggerFactory
			.getLogger(GuideNavigationTag.class);

	// We need a collection from somewhere which is what components we try to link to
	// Do we always look in the same parsys? Same as we are, or fixed, or configd?
	// Do we ever look in more than one?
	private static abstract class LabelGenerator {
		public abstract String generateLabel(Node beingLabelled);
	}
	private static class SubheadLabelGenerator extends LabelGenerator {
		public String generateLabel(Node subheadBeingLabelled) {
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
		public String generateLabel(Node commentsBeingLabelled) {
			StringBuilder label = new StringBuilder("Comments");
			
			return label.toString();
		}
	}
	private final static Map<String,LabelGenerator> specialLabelGenerators = initSpecialLabelGenerators();
	private final static Map<String,LabelGenerator> initSpecialLabelGenerators() {
		Map<String,LabelGenerator> generators = new HashMap<String,LabelGenerator>();
		generators.put("searspartsdirect/components/content/subhead", new SubheadLabelGenerator());
		return generators;
	}
	
	private Map<String,String> getConfiguredTypeLabels() {
		List<List<String>> typesAndLabels = this.getMenuItems("sections", currentNode);
		Map<String,String> typeToLabel = new HashMap<String,String>();
		for (List<String> aType : typesAndLabels) {
			typeToLabel.put(aType.get(0).trim(), aType.get(1));
		}
		return typeToLabel;
	}
	
	private Node getParsys() throws RepositoryException {
		Node parsysParent = currentNode.getParent();
		if (log.isDebugEnabled()) {
			log.debug("parsysParent is "+parsysParent);
			log.debug("parsysParent name is "+parsysParent.getName());
		}
		//TODO maybe check to be sure this is a parsys.
		return parsysParent;
	}
	
	@Override
	public int doStartTag() throws JspException {
		// list containing lists [sectionresourcetype,sectionlabel]
		// Really we want a Map<String:sectionresourcetype,String:sectionlabel>
		Map<String,String> typeToLabel = getConfiguredTypeLabels();
		
		if (log.isDebugEnabled()) log.debug("typeToLabel is "+typeToLabel);
		// list containing lists [sectionlabel,sectionlink]
		List<List<String>> sections = new ArrayList<List<String>>();
		try {
			if (log.isDebugEnabled()) log.debug("currentNode is "+currentNode);
			Node parsysParent = getParsys();
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
							labelFound = specialGenerator.generateLabel(parSibling);
						}
					}
					List<String> item = new ArrayList<String>();
					item.add(labelFound);
					item.add(parsysParent.getName()+"_"+parSibling.getName());
					sections.add(item);
				}
			}
		} catch (RepositoryException re) {
			log.error("exception getting sections",re);
		}
		pageContext.setAttribute("sections", sections);
		return EVAL_BODY_INCLUDE;
	}
	
	protected List<String> getMenuItemConfig(String jsonString) {

		List<String> item = new ArrayList<String>();

		try {
			JSONObject menuItemConfig = new JSONObject(jsonString);
			item.add(menuItemConfig.getString("name"));
			item.add(menuItemConfig.getString("link"));
		} catch (Exception e) {
			log.error("JSON error", e);
		}

		return item;
	}

	protected List<List<String>> getMenuItems(String propertyName, Node node) {
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

}
