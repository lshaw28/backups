package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetRegisterStatusTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetRegisterStatusTag.class);
	
	
	@Override
	public int doStartTag() throws JspException {
	 boolean loggedIn = false;	
		try {
			JspWriter out = pageContext.getOut();
			
			//Make the API call to see if user is logged in 
			 if (loggedIn) {
				 out.print("My Profile");
			 } else {
				 out.print("Register");
			 }

		} catch (IOException e) {
			log.error("Error in getting login status API call" + e.getStackTrace());
		}
		return SKIP_BODY;
	}
	

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
