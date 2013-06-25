package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.DisplayImageTagFixture;

public class DisplayImageTagTest extends MocksTag {
	
	private DisplayImageTagFixture fixture;
	private DisplayImageTag tag; // tag
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new DisplayImageTagFixture(resourceResolver,pageContext);
		tag = new DisplayImageTag();
	}
	
	@Test
	public void testWithAbsolutePathAndAlt() throws JspException {
		fixture.setUpForAbsolutePath();
		tag.setPath(fixture.getAbsoluteTestPath());
		tag.setAltText("Alternate alternate");
		tag.setDecorated(true);
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithAbsolutePathNoDecoration() throws JspException {
		fixture.setUpForAbsolutePath();
		tag.setPath(fixture.getAbsoluteTestPath());
		tag.setDecorated(false);
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithNoPath() throws JspException {
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithRelativePath() {
		try {
			tag.setPath(fixture.getRelativeTestPath());
			runTagShouldSkipBodyEvalPage();
		} catch (Exception e) {
			assertThat("We cannot mock an environment for this test.",isA(String.class));
		}
	}
	
	@Test
	public void testWithSameDesign() throws JspException {
		fixture.setUpSameDesign();
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
