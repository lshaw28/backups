package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

/**
 * Custom Tag to display a model header spoofed onto another page based on a request parameter
 * @author Joseph
 *
 */
public class DisplayModelHeaderTag extends CQBaseTag {
	
	@Override
	public int doStartTag() throws JspException {
		/*
		 * THIS IS PLACEHOLDER MARKUP
		 * This will be replaced with realistic markup when it is provided,
		 * as well as links based on API calls/site hierarchy of SPD base site
		 */
		JspWriter out = pageContext.getOut();
		String[] selectors = slingRequest.getRequestPathInfo().getSelectors();
		if (selectors.length > 2) {
			String brand = selectors[Constants.BRAND_SELECTOR];
			String category = selectors[Constants.CATEGORY_SELECTOR];
			String model = selectors[Constants.MODEL_SELECTOR];
			StringBuilder sb = new StringBuilder();
			sb.append("<h1>Header for Model #" + model + ", " + brand + " " + category + "</h1>");
			sb.append("<span>Parts</span> | ");
			sb.append("<span>Manuals(2)</span> | ");
			sb.append("<span>Repair Help</span> | ");
			sb.append("<span>Expert Q&A</span>");
			try {
				out.write(sb.toString());
				out.flush();
			}
			catch (Exception e) {
				log.error(ExceptionUtils.getFullStackTrace(e));
			}
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
