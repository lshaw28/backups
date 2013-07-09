package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.spd.cq.searspartsdirect.common.fixture.ErrorCodesFixture;
import com.spd.cq.searspartsdirect.common.model.ErrorCodeTableModel;

public class ErrorCodeTableTagTest extends MocksTag {
	
	ErrorCodesFixture fixture;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ErrorCodesFixture();
		Mockito.when(properties.get("errorCodeTable",new String[0])).thenReturn(fixture.getJSONData());
		Mockito.when(properties.get("codeType", String.class)).thenReturn(fixture.getCodeType());
	}
	
	@Test
	public void testDoStartTag() throws JspException {
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
}
