package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

public class GetIconTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetIconTag.class);
	protected ProductCategoryModel category;
	protected String pagePath;

	@Override
	public int doStartTag() throws JspException {
		
		String iconName = null;
		if (category != null) {
			iconName = category.getIconClass();
		}
		
		Page overridePage = currentPage;
		log.debug("pagePath is "+pagePath);
		if (StringUtils.isNotEmpty(pagePath)) {
			Page checkPage = pageManager.getPage(pagePath);
			if (checkPage != null) {
				overridePage = checkPage;
			}
		}
		iconName = overridePage.getProperties().get(Constants.ASSETS_ICON_ATTR,iconName);

		if(StringUtils.isEmpty(iconName)){
			iconName = "svg-icon-er";
		}
		pageContext.setAttribute("iconName", iconName);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void setCategory(ProductCategoryModel category) {
		this.category = category;
	}
	
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
}
