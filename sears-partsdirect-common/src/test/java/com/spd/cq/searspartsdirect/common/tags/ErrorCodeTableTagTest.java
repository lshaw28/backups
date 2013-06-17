package com.spd.cq.searspartsdirect.common.tags;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.spd.cq.searspartsdirect.common.fixture.ErrorCodesFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.ErrorCodeModel;

public class ErrorCodeTableTagTest extends MocksTag {
	
	ErrorCodesFixture fixture;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ErrorCodesFixture();
		Mockito.when(properties.get("errorCodeTable",new String[0])).thenReturn(fixture.getJSONData());
	}
	
	@Test
	public void testDoStartTag() throws JspException {
		ErrorCodeTableTag tag = new ErrorCodeTableTag();
		tag.setPageContext(pageContext);
		tag.doStartTag();
		
		Assert.assertNotNull(pageContext.getAttribute("errorCodeTableData"));
		Map<String, List<ErrorCodeModel>> errorCodeTableData = (Map<String, List<ErrorCodeModel>>) pageContext.getAttribute("errorCodeTableData");
		Assert.assertTrue(errorCodeTableData.size() > 0);
		Assert.assertNotNull(errorCodeTableData.get("Type1"));
		List<ErrorCodeModel> models = errorCodeTableData.get("Type1");
		Assert.assertTrue(models.size() > 0);
		Assert.assertEquals("code 101", models.get(0).getCode());
	}
}
