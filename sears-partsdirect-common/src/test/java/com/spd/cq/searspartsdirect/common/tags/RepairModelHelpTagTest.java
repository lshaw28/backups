package com.spd.cq.searspartsdirect.common.tags;

import static org.mockito.Mockito.mock;

import javax.servlet.jsp.JspException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.Page;

public class RepairModelHelpTagTest extends MocksTag {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void testWhenErrorCodeListNotFound() throws JspException {
		RepairModelHelpTag tag = new RepairModelHelpTag();
		tag.setPageContext(pageContext);
		tag.doStartTag();
		Assert.assertEquals(false, pageContext.getAttribute("errorCodesExist"));
	}
	
	/*@Test
	public void testWhenErrorCodeListFound() throws JspException {
		RepairModelHelpTag tag = new RepairModelHelpTag();
		final Page errorCodePage = mock(Page.class);

		Mockito.when(pageManager.getPage(Mockito.anyString())).thenAnswer(new Answer<Page>() {
			public Page answer(InvocationOnMock invocation) throws Throwable {
				Mockito.when(errorCodePage.getPath()).thenReturn((String)invocation.getArguments()[0]);
				Mockito.when(errorCodePage.isValid()).thenReturn(true);
				return errorCodePage;
			}
		});
		
		//Mockito.when(errorCodePage.isValid()).thenReturn(true);
		tag.setPageContext(pageContext);
		tag.doStartTag();
		Assert.assertEquals(true, pageContext.getAttribute("errorCodesExist"));
	}*/
	
	/*@Test
	public void testWhenErrorCodeListFound2() throws JspException {
		RepairModelHelpTag tag = new RepairModelHelpTag();
		Page errorCodePage = mock(Page.class);
		Mockito.when(pageManager.getPage(currentPage+"/error_codes.html")).thenReturn(errorCodePage);
		Mockito.when(errorCodePage.getPath()).thenReturn("any string");
		Mockito.when(errorCodePage.isValid()).thenReturn(true);
		tag.setPageContext(pageContext);
		tag.doStartTag();
		Assert.assertEquals(true, pageContext.getAttribute("errorCodesExist"));
	}*/

}
