package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;

public class GetLocalUrlTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetLocalUrlTag.class);

	@Override
	public int doStartTag() throws JspException {
		pageContext.setAttribute("nonSecureLocalUrl", "http://"+ EnvironmentSettings.getLocalHttpAndPort());
		pageContext.setAttribute("secureLocalUrl", "https://"+ EnvironmentSettings.getLocalHttpsAndPort());
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
