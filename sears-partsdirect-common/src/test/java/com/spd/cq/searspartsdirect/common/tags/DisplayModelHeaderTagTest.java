package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.DisplayModelHeaderTagFixture;

public class DisplayModelHeaderTagTest extends MocksTag {
	
	private DisplayModelHeaderTagFixture fixture;
	private GetModelHeaderTag tag; // tag
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new DisplayModelHeaderTagFixture(slingRequest, pageContext);
		tag = new GetModelHeaderTag();
	}
	
	@Test
	public void testWithSelectors() throws JspException {
		fixture.setUpWithSelectors();
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithoutSelectors() throws JspException {
		fixture.setUpWithoutSelectors();
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testExceptions() throws JspException, IOException {
		fixture.setUpWithSelectors();
		fixture.setUpExceptions();
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
