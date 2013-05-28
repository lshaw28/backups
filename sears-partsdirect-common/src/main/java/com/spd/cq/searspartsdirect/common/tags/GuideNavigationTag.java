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

public class GuideNavigationTag extends CQBaseTag {
	protected static Logger log = LoggerFactory
			.getLogger(GuideNavigationTag.class);

	// We need a collection from somewhere which is what components we try to link to
	// Do we always look in the same parsys? Same as we are, or fixed, or configd?
	// Do we ever look in more than one?
	
	
	@Override
	public int doStartTag() throws JspException {
		// list containing lists [sectionresourcetype,sectionlabel]
		// Really we want a Map<String:sectionresourcetype,String:sectionlabel>
		List<List<String>> typesAndLabels = this.getMenuItems("sections", currentNode);
		Map<String,String> typeToLabel = new HashMap<String,String>();
		for (List<String> aType : typesAndLabels) {
			typeToLabel.put(aType.get(0).trim(), aType.get(1));
		}
		if (log.isDebugEnabled()) log.debug("typeToLabel is "+typeToLabel);
		// list containing lists [sectionlabel,sectionlink]
		List<List<String>> sections = new ArrayList<List<String>>();
		try {
			if (log.isDebugEnabled()) log.debug("currentNode is "+currentNode);
			Node parsysParent = currentNode.getParent();
			if (log.isDebugEnabled()) {
				log.debug("parsysParent is "+parsysParent);
				log.debug("parsysParent name is "+parsysParent.getName());
			}
			//TODO maybe check to be sure this is a parsys.
			NodeIterator parsysChildren = parsysParent.getNodes();
			while (parsysChildren.hasNext()) {
				Node parSibling = parsysChildren.nextNode();
				if (parSibling.getName().equals(currentNode.getName())) {
					continue; // We skip ourselves.
				}
				String siblingResourceType = parSibling.getProperty("sling:resourceType").getString();
				if (log.isDebugEnabled()) {
					log.debug("a child is "+parSibling);
					log.debug("parSibling name is "+parSibling.getName());
					log.debug("parSibling resourceType is "+siblingResourceType);
				}
				String labelFound = typeToLabel.get(siblingResourceType);
				if (labelFound != null) {
					// Will need special treatment for "Comments" to get the count in..
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
