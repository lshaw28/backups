package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.CategoryModel;


public class Category101Tag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(RequiredToolsTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<CategoryModel> categories = new ArrayList<CategoryModel>();
		try {
			String[]  multiJsons = properties.get("multicat",new String[0]);
			for (String json : multiJsons) {
				JSONObject jsob = new JSONObject(json);
				CategoryModel category = new CategoryModel(jsob.getString("url"), jsob.getString("title"));
				categories.add(category);
			}
			pageContext.setAttribute("categories", categories);
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
