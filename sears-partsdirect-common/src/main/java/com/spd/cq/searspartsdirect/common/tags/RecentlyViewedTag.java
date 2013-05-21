package com.spd.cq.searspartsdirect.common.tags;

import java.util.LinkedList;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;
import com.spd.cq.searspartsdirect.common.model.CookieModelItem;
import com.spd.cq.searspartsdirect.common.model.CookiePartItem;

public class RecentlyViewedTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(RecentlyViewedTag.class);

	@Override
	public int doStartTag() throws JspException {
		Cookie recentlyViewedModelsCookie = null;
		Cookie recentlyViewedPartsCookies = null;

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			recentlyViewedModelsCookie = PartsDirectCookieHelper.getCookieInfo(cookies, "recentlyViewedModels");
			if (recentlyViewedModelsCookie != null && recentlyViewedModelsCookie.getValue() != null) {
				LinkedList<CookieModelItem> modelList = PartsDirectCookieHelper.parseRecentltyViewedModelItems(recentlyViewedModelsCookie.getValue());
				pageContext.setAttribute("rvModelList", modelList);
			}
			
			recentlyViewedPartsCookies = PartsDirectCookieHelper.getCookieInfo(cookies, "recentlyViewedParts");
			if (recentlyViewedPartsCookies != null && recentlyViewedPartsCookies.getValue() != null) {
				LinkedList<CookiePartItem> partList =  PartsDirectCookieHelper.parseRecentltyViewedPartItems(recentlyViewedPartsCookies.getValue());
				pageContext.setAttribute("rvPartList", partList);
			}
		}

		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
