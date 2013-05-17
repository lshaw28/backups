package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.sun.mail.mbox.NewlineOutputStream;

public class BackButtonTag extends CQBaseTag {
	protected static Logger log = LoggerFactory.getLogger(TagsByPageTag.class);
	protected String url;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		try {
			
			url = currentPage.getParent().getPath();
			
			// need to account for case where parent page = null
			out.print(url);

		}
		catch (Exception e) {
			log.error(e.toString());
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
