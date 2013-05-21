package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetBrandCategoryModelNoTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetBrandCategoryModelNoTag.class);
	
	@Override
	public int doStartTag() throws JspException {
			//Make use of API call here
			//out.print("Refrigerator");
			pageContext.setAttribute("brandCategoryModelNo", currentPage.getTitle());
		return SKIP_BODY;
	}
	

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
