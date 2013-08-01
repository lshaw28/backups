package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;

public class GetPartsDirectUrlTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetPartsDirectUrlTag.class);

	@Override
	public int doStartTag() throws JspException {
		log.debug("PDUrl is "+EnvironmentSettings.getPDUrl());
		pageContext.setAttribute("nonSecurePDUrl", "http://"+ EnvironmentSettings.getPDUrl());
		pageContext.setAttribute("securePDUrl", "https://"+ EnvironmentSettings.getPDUrl());
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
