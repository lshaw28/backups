package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;

public class MyModelsTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(MyModelsTag.class);
	
	@Override
	public int doStartTag() throws JspException {
	 Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			Cookie myModelsCookie= PartsDirectCookieHelper.getCookieInfo(cookies, "myProfileModels");
			if (myModelsCookie != null) {
				if (myModelsCookie.getValue() != null) {
					//Make the PD API call by passing the cookie hash value and/or userid and store the information in an object
				}
			}
		}	
		return SKIP_BODY;
	}
	

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
