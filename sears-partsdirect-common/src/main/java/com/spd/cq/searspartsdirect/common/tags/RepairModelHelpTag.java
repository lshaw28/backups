package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import com.day.cq.wcm.api.Page;

public class RepairModelHelpTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		pageContext.setAttribute("currentPagePath", currentPage.getPath());
		Page errorListPage = pageManager.getPage(currentPage.getPath()+"/error_codes.html");
		if (errorListPage != null && errorListPage.isValid()) {
			log.debug(errorListPage.getPath() + "page is valid");
			pageContext.setAttribute("errorCodesExist", true);
			pageContext.setAttribute("errorCodePagePath", errorListPage.getPath());
		} else {
			pageContext.setAttribute("errorCodesExist", false);
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
