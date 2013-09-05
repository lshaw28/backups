package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetModelHeaderTagFixture;

public class GetModelHeaderTagTest extends MocksTag {
	
	private GetModelHeaderTag tag;
	private GetModelHeaderTagFixture fixture;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetModelHeaderTagFixture(slingRequest);
		tag = new GetModelHeaderTag();
	}
	
	@Test
	public void testWithArguments() throws JspException {
		tag.setModel(fixture.getModel());
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithArgumentsAndFakeCompleteResult() throws JspException {
		fixture.setUpFakeCompleteResult();
		tag.setModel(fixture.getModel());
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithArgumentsAndFakeEmptyResult() throws JspException {
		fixture.setUpFakeEmptyResult();
		tag.setModel(fixture.getModel());
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testNoArguments() throws JspException {
		runTagShouldSkipBodyEvalPage();
	}
	
	private void runTagShouldSkipBodyEvalPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
