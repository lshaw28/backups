package com.spd.cq.searspartsdirect.common.tags;

import com.day.cq.wcm.api.WCMMode;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import javax.servlet.jsp.JspException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefineObjectsTag extends CQBaseTag {
	protected static Logger log = LoggerFactory.getLogger(DefineObjectsTag.class);

	@Override
	public int doStartTag() throws JspException {
		try {
			if (pageContext.getAttribute("ObjectsDefined") == null) {
				pageContext.setAttribute("WCMMode", WCMMode.fromRequest(slingRequest));
				pageContext.setAttribute("isDesignMode", WCMMode.fromRequest(slingRequest) == WCMMode.DESIGN);
				pageContext.setAttribute("isDisabledMode", WCMMode.fromRequest(slingRequest) == WCMMode.DISABLED);
				pageContext.setAttribute("isEditMode", WCMMode.fromRequest(slingRequest) == WCMMode.EDIT);
				pageContext.setAttribute("isPreviewMode", WCMMode.fromRequest(slingRequest) == WCMMode.PREVIEW);
				pageContext.setAttribute("isReadOnlyMode", WCMMode.fromRequest(slingRequest) == WCMMode.READ_ONLY);
				pageContext.setAttribute("contextPath",contextPath);
				pageContext.setAttribute("publicRootDir", contextPath + currentDesign.getPath());
				pageContext.setAttribute("publicImageDir", contextPath + currentDesign.getPath() + "/images");
				pageContext.setAttribute("pageLocale", currentPage.getLanguage(true));
				pageContext.setAttribute("currentPagePath", currentPage.getPath());
				pageContext.setAttribute("ObjectsDefined", "true");
			}
		} catch (Exception e) {
			log.error("Error setting object definitions", e);
		}

		return super.doStartTag();
	}
}