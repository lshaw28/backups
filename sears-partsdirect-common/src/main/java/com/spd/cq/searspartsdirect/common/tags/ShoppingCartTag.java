package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;

public class ShoppingCartTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(ShoppingCartTag.class);
	
	
	@Override
	public int doStartTag() throws JspException {
		//try {
			Cookie[] cookies = request.getCookies();
			
			if (cookies != null) {
				Cookie cartItemCountCookie= PartsDirectCookieHelper.getCookieInfo(cookies, "cartSize");
				Cookie cartItemCookie = PartsDirectCookieHelper.getCookieInfo(cookies, "cid");
				if (cartItemCountCookie != null) {
					if (cartItemCountCookie.getValue() != null) {
						pageContext.setAttribute("cartItemCount", cartItemCountCookie.getValue());
					} else {
						pageContext.setAttribute("cartItemCount", "0");
					}
				} 
				
				//now read the shopping cart cookie and make the API call for guest user
				if (cartItemCookie != null && cartItemCookie.getValue() != null) {
					//make the PD API call
				}
			} 
		return SKIP_BODY;
	}
	

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
