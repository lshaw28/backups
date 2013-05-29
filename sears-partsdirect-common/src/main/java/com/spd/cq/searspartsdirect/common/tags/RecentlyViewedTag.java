package com.spd.cq.searspartsdirect.common.tags;

import java.util.LinkedList;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;
import com.spd.cq.searspartsdirect.common.model.ModelCookieModel;
import com.spd.cq.searspartsdirect.common.model.PartCookieModel;

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
				LinkedList<ModelCookieModel> modelList = PartsDirectCookieHelper.parseRecentltyViewedModelItems(recentlyViewedModelsCookie.getValue());
				pageContext.setAttribute("rvModelList", modelList);
				log.error("modelList" + modelList);
				for (ModelCookieModel cookieModelItem : modelList) {
					log.error("name="+cookieModelItem.getItemName()+",desc="+cookieModelItem.getItemDescription()+",url="+cookieModelItem.getItemURL());
				}
			}
			
			recentlyViewedPartsCookies = PartsDirectCookieHelper.getCookieInfo(cookies, "recentlyViewedParts");
			if (recentlyViewedPartsCookies != null && recentlyViewedPartsCookies.getValue() != null) {
				LinkedList<PartCookieModel> partList =  PartsDirectCookieHelper.parseRecentltyViewedPartItems(recentlyViewedPartsCookies.getValue());
				pageContext.setAttribute("rvPartList", partList);
				log.error("partList" +partList);
				
				for (PartCookieModel cookiePartItem : partList) {
					log.error("item name"+cookiePartItem.getItemName()+",desc="+cookiePartItem.getItemDescription()+","+cookiePartItem.getItemImageURL());
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
