package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCartTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(ShoppingCartTag.class);
	
	
	@Override
	public int doStartTag() throws JspException {
	 int cartItemCount = 0;	
		try {
			JspWriter out = pageContext.getOut();
			
			//Make the API call to see if user is logged in 
			 if (cartItemCount > 0) {
				 out.print("show shopping cart item count along with cart item info");
			 } else {
				 out.print("show the shopping cart with 0 items in it and default message");
			 }

		} catch (IOException e) {
			log.error("Error in getting shopping cart API call" + e.getStackTrace());
		}
		return SKIP_BODY;
	}
	

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
