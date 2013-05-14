package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PagesByTagTag extends CQBaseTag {
	
	protected static Logger log = LoggerFactory.getLogger(PagesByTagTag.class);
	

	@Override
	public int doStartTag() throws JspException {

		try {
			JspWriter out = pageContext.getOut();
			out.print("Test Output");
		}
        catch (Exception ioe) {
            throw new JspException("Error: " + ioe.getMessage());
        }
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
