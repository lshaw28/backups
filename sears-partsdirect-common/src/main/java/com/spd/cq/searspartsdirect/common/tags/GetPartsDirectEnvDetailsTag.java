package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;

public class GetPartsDirectEnvDetailsTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetPartsDirectEnvDetailsTag.class);

	@Override
	public int doStartTag() throws JspException {
		log.debug("PDUrl is "+EnvironmentSettings.getPDUrl());
		pageContext.setAttribute("nonSecurePDUrl", "http://"+ EnvironmentSettings.getPDUrl());
		pageContext.setAttribute("securePDUrl", "https://"+ EnvironmentSettings.getPDUrl());
		
		pageContext.setAttribute("nonSecureCommercialUrl", "http://"+ EnvironmentSettings.getCommercialSiteUrl());
		pageContext.setAttribute("secureCommercialUrl", "https://"+ EnvironmentSettings.getCommercialSiteUrl());
		
		pageContext.setAttribute("ssoServerUrl", EnvironmentSettings.getSSOServerURL());
		pageContext.setAttribute("pdServicePath", EnvironmentSettings.getPDSecurityPath());
		
		pageContext.setAttribute("pd247ChatFlag", EnvironmentSettings.getPD247ChatFlag());
		
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
