package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.RepairModelHelpTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RepairModelHelpTagTest extends MocksTag {

	private RepairModelHelpTagFixture fixture;
	private RepairModelHelpTag tag;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		fixture = new RepairModelHelpTagFixture(pageManager);
		tag = new RepairModelHelpTag();
	}
	
	@Test
	public void testWhenErrorCodeListNotFound() throws JspException {
		runsSkipsBodyEvalsPage();
		Assert.assertEquals(false, pageContext.getAttribute("errorCodesExist"));
	}
	
	@Test
	public void testWhenErrorCodeListFound() throws JspException {
		fixture.setUpErrorCodesExist();
		runsSkipsBodyEvalsPage();
		Assert.assertEquals(true, pageContext.getAttribute("errorCodesExist"));
	}
	
	@Test
	public void testWhenErrorCodeListInvalid() throws JspException {
		fixture.setUpErrorCodesInvalid();
		runsSkipsBodyEvalsPage();
		Assert.assertEquals(false, pageContext.getAttribute("errorCodesExist"));
	}
	/*
	@Test
	public void testWhenErrorCodeListFound2() throws JspException {
		Page errorCodePage = mock(Page.class);
		Mockito.when(pageManager.getPage(currentPage+"/error_codes.html")).thenReturn(errorCodePage);
		Mockito.when(errorCodePage.getPath()).thenReturn("any string");
		Mockito.when(errorCodePage.isValid()).thenReturn(true);
		tag.setPageContext(pageContext);
		tag.doStartTag();
		Assert.assertEquals(true, pageContext.getAttribute("errorCodesExist"));
	}
	*/
	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
