package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetModelSymptomsTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	private String categoryPath;
	public static final Logger log = LoggerFactory.getLogger(GetModelSymptomsTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		
		return SKIP_BODY;	
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}
}
