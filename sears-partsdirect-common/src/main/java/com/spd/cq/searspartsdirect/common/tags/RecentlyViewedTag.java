package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;

public class RecentlyViewedTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory			.getLogger(RecentlyViewedTag.class);

	@Override
	public int doStartTag() throws JspException {
		Cookie recentlyViewedModels = null;
		Cookie recentlyViewedParts = null;

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			PartsDirectCookieHelper cookieHelper = new PartsDirectCookieHelper();
			recentlyViewedModels = cookieHelper.getCookieInfo(cookies, "recentlyViewedModels");
			recentlyViewedModels = cookieHelper.getCookieInfo(cookies, "recentlyViewedParts");

			pageContext.setAttribute("recentlyViewedParts", recentlyViewedParts);
			pageContext.setAttribute("recentlyViewedModels", recentlyViewedModels);
		}

		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
