package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.DisplayModelHeaderTagFixture;

public class DisplayModelHeaderTagTest extends MocksTag {
	
	private DisplayModelHeaderTagFixture fixture;
	private DisplayModelHeaderTag tag; // tag
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tag = new DisplayModelHeaderTag();
	}
	
	@Test
	public void testWithSelectors() throws JspException {
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithoutSelectors() throws JspException {
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testExceptions() throws JspException {
		
		runTagShouldSkipBodyEvalPage();
	}
	
	private void runTagShouldSkipBodyEvalPage() throws JspException {
		/*tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));*/
	}
}
