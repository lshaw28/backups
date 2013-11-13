package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import junit.framework.Assert;

import org.junit.Test;

public class GetPartsDirectEnvDetailsTagTest  extends MocksTag{

	@Test
	public void testDoStartTag() throws JspException {
		GetPartsDirectEnvDetailsTag getPartsDirectUrl = new GetPartsDirectEnvDetailsTag();
		getPartsDirectUrl.setPageContext(pageContext);
		getPartsDirectUrl.doStartTag();
		getPartsDirectUrl.doEndTag();
		Assert.assertNotNull(pageContext.getAttribute("nonSecurePDUrl"));
		Assert.assertNotNull(pageContext.getAttribute("securePDUrl"));
	}

}
