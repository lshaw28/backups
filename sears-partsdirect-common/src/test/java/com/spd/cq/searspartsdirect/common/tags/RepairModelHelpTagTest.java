package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.day.cq.wcm.api.Page;

public class RepairModelHelpTagTest extends MocksTag {

	@Test
	public void testWhenErrorCodeListNotFound() throws JspException {
		RepairModelHelpTag tag = new RepairModelHelpTag();
		tag.setPageContext(pageContext);
		tag.doStartTag();
		Assert.assertEquals(pageContext.getAttribute("errorCodesExist"), false);
	}
	
	/*@Test
	public void testWhenErrorCodeListFound() throws JspException {
		RepairModelHelpTag tag = new RepairModelHelpTag();
		tag.setPageContext(pageContext);
		
		Mockito.when(pageManager.getPage(Mockito.anyString())).thenReturn(currentPage);
		Mockito.when(currentPage.isValid()).thenReturn(true);
		tag.doStartTag();
		Assert.assertEquals(pageContext.getAttribute("errorCodesExist"), true);
	}*/

}
