package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import junit.framework.Assert;

import org.junit.Test;


import com.spd.cq.searspartsdirect.common.fixture.ErrorCodesFixture;
import com.spd.cq.searspartsdirect.common.model.ErrorCodeTableModel;

public class ErrorCodeTableTagTest extends MocksTag {
	
	ErrorCodesFixture fixture;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ErrorCodesFixture();
	}
	
	@Test
	public void testDoStartTag() throws JspException {
		fixture.setUpProperties(properties);
		ErrorCodeTableTag tag = new ErrorCodeTableTag();
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.doEndTag();
		
		Assert.assertNotNull(pageContext.getAttribute("errorCodeTableData"));
		ErrorCodeTableModel model = (ErrorCodeTableModel) pageContext.getAttribute("errorCodeTableData");
		
		Assert.assertNotNull(model.getErrorCodeType());
		
		Assert.assertNotNull(model);
		Assert.assertTrue(model.getErrorCodes().size() > 0);
	}
	
	@Test
	public void testDoStartTagNoProperties() throws JspException {
		ErrorCodeTableTag tag = new ErrorCodeTableTag();
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.doEndTag();
	}
}
