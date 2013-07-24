package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetHeaderHelperTagFixture;

public class GetHeaderHelperTagTest extends MocksTag {

	private GetHeaderHelperTagFixture fixture;
	private GetHeaderHelperTag tag;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		fixture = new GetHeaderHelperTagFixture(request);
		tag = new GetHeaderHelperTag();
	}

	@Test
	public void testWithEmptyCookies() throws JspException {
		fixture.setUpEmptyCookies();
		runTagShouldSkipBodyAndEvalPage();
	}
	
	@Test
	public void testWithMyProfileModelCookie() throws JspException {
		fixture.setUpPopulatedModelCookie();
		runTagShouldSkipBodyAndEvalPage();
	}
	
	private void runTagShouldSkipBodyAndEvalPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		int endResult = tag.doEndTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}

}
