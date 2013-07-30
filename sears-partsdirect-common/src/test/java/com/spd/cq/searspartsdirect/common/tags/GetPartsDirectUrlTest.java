package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import junit.framework.Assert;

import org.junit.Test;

public class GetPartsDirectUrlTest  extends MocksTag{

	@Test
	public void testDoStartTag() throws JspException {
		GetPartsDirectUrl getPartsDirectUrl = new GetPartsDirectUrl();
		getPartsDirectUrl.setPageContext(pageContext);
		getPartsDirectUrl.doStartTag();
		getPartsDirectUrl.doEndTag();
		Assert.assertNotNull(pageContext.getAttribute("nonSecurePDUrl"));
		Assert.assertNotNull(pageContext.getAttribute("securePDUrl"));
	}

}
