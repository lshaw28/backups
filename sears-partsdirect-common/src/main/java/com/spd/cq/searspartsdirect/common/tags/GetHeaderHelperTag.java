package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;

public class GetHeaderHelperTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		Cookie[] cookies = request.getCookies();
		Cookie myProfileModelCookie = null;
		Cookie shoppingCartCookie = null;
		Cookie jSessionId = null;

		if (cookies != null) {
			myProfileModelCookie = PartsDirectCookieHelper.getCookieInfo(cookies,
					Constants.MY_MODEL_COOKIE);
			shoppingCartCookie = PartsDirectCookieHelper.getCookieInfo(cookies,
					Constants.SHOPPING_CART_COOKIE);
			
			jSessionId = PartsDirectCookieHelper.getCookieInfo(cookies, "JSESSIONID");

			if (myProfileModelCookie != null) {
				pageContext.setAttribute("myProfileModelCookie", myProfileModelCookie.getValue());
			} else {
				pageContext.setAttribute("myProfileModelCookie", "");
			}

			if (shoppingCartCookie != null) {
				pageContext.setAttribute("shoppingCartCookieId", shoppingCartCookie.getValue());
			} else {
				pageContext.setAttribute("shoppingCartCookieId", "");
			}
			
			if (jSessionId != null) {
				pageContext.setAttribute("jSessionId", jSessionId.getValue());
			} else {
				pageContext.setAttribute("jSessionId", "");
			}
			
			
		}

		pageContext.setAttribute("PdApiRoot", EnvironmentSettings.getPDApiRoot());
		pageContext.setAttribute("PdApiRootSecure", EnvironmentSettings.getPDApiRootSecure());
		return SKIP_BODY;
	}

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
