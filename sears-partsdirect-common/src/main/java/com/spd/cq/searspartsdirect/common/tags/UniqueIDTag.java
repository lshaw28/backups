package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;

public class UniqueIDTag  extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(UniqueIDTag.class);

	@Override
	public int doStartTag() throws JspException {

		String path = resource.getPath();
		try {
			path = path.substring(path.indexOf(JcrConstants.JCR_CONTENT) + JcrConstants.JCR_CONTENT.length() + 1);
		}
		catch (Exception e) {

		}
		pageContext.setAttribute("uniqueId", path.replace("/", "_").replace(":", "_"));
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
