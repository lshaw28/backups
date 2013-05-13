package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteMeTag extends CQBaseTag {

	protected static Logger log = LoggerFactory.getLogger(DeleteMeTag.class);


	@Override
	public int doStartTag() throws JspException {

		try {
			JspWriter out = pageContext.getOut();
			out.print("hey hey hey");
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
