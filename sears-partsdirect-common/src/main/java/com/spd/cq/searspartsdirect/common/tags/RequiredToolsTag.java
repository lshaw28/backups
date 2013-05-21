package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.ToolModel;

/**
 * Custom tag to draw out a list of Tag objects when given a Page path
 * Defaults to current page
 * @author Joseph
 *
 */
public class RequiredToolsTag extends CQBaseTag {

	protected static Logger log = LoggerFactory.getLogger(RequiredToolsTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<ToolModel> tools = new ArrayList<ToolModel>();
		try {
			String[]  multiJsons = properties.get("multi",new String[0]);
			for (String json : multiJsons) {
				JSONObject jsob = new JSONObject(json);
				ToolModel tool = new ToolModel(jsob.getString("text"),jsob.getString("id"));
				tools.add(tool);
			}
			pageContext.setAttribute("tools", tools);
		}
		catch (Exception e) {
			
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
