package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyModelTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(MyModelTag.class);
	
	
	@Override
	public int doStartTag() throws JspException {
	 int myModelTotal = 0;	
		try {
			JspWriter out = pageContext.getOut();
			
			//Make the API call to get the recently viewed model and parts 
			 if (myModelTotal > 0) {
				 out.print("Show the Users's model here");
			 } else {
				 out.print("Show the Users's model here");
			 }

		} catch (IOException e) {
			log.error("Error in getting User Model API call" + e.getStackTrace());
		}
		return SKIP_BODY;
	}
	

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
