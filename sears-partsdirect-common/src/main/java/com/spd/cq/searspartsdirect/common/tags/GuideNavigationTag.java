package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Value;
import javax.servlet.jsp.JspException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuideNavigationTag extends CQBaseTag {
	protected static Logger log = LoggerFactory
			.getLogger(GuideNavigationTag.class);

	@Override
	public int doStartTag() throws JspException {

		pageContext.setAttribute("sections",
				this.getMenuItems("sections", currentNode));

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
