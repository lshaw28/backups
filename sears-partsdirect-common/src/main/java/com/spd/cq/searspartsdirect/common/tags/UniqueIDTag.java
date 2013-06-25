package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UniqueIDTag  extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(UniqueIDTag.class);

	@Override
	public int doStartTag() throws JspException {
		String pagePath = currentPage.getPath();
		String pageResPath = currentPage.getContentResource().getPath();
		String path = resource.getPath();
		if (path.startsWith(pageResPath)) {
			path = path.substring(pageResPath.length()+1);
		} else {
			path = path.substring(pagePath.length()+1);
		}
		pageContext.setAttribute("uniqueId", path.replaceAll("[^A-Za-z0-9_]", "_"));
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
