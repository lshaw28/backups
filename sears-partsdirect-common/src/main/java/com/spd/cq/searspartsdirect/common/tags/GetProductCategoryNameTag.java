package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetProductCategoryNameTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetProductCategoryNameTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		
		try {
			JspWriter out = pageContext.getOut();

			//Make use of API call here
			//out.print("Refrigerator");
			out.print(currentPage.getTitle());
		} catch (IOException e) {
			log.error("Error in getting the product category name API call" + e.getStackTrace());
		}
		return SKIP_BODY;
	}
	

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
