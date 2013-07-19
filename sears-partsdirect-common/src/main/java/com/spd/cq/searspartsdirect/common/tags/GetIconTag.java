package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetIconTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetIconTag.class);
	protected String category;

	@Override
	public int doStartTag() throws JspException {
		
		String iconName = null;
		Page page;
		page = pageManager.getPage(Constants.CATEGORIES_ROOT + "/" + category + Constants.MODELNO_SFX);
//		iconName = page.getProperties().get("iconImages").toString();
//		if(iconName.isEmpty()){
//			iconName = "svg-icon-er";
//		}
//		
		// would like to return the actual icon svg name but page.getProperties().get("iconImages") yields a NPE
		// returning category page for the time being
		pageContext.setAttribute("iconName", page);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
