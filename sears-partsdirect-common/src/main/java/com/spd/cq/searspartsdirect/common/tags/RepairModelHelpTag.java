package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

public class RepairModelHelpTag extends CQBaseTag {
	
	protected static final Logger log = LoggerFactory.getLogger(RepairModelHelpTag.class);
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
