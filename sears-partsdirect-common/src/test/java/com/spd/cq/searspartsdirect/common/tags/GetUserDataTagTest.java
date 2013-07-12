package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetUserDataTagFixture;

public class GetUserDataTagTest extends MocksTag {

	private GetUserDataTagFixture fixture;
	private GetUserDataTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetUserDataTagFixture(request);
		tag = new GetUserDataTag();
	}

	@Test
	public void testWithNothingSetUp() throws JspException {
		runTagShouldSkipBodyAndEvalPage();
	}
	
	@Test
	public void testWithEmptyCookies() throws JspException {
		fixture.setUpEmptyCookies();
		runTagShouldSkipBodyAndEvalPage();
	}
	
	@Test
	public void testWithEmptyUserCookie() throws JspException {
		fixture.setUpEmptyUserCookie();
		runTagShouldSkipBodyAndEvalPage();
	}
	
	@Test
	public void testWithPopulatedUserCookie() throws JspException {
		fixture.setUpPopulatedUserCookie();
		runTagShouldSkipBodyAndEvalPage();
	}
	
	@Test
	public void testWithEmptyModelAndCart() throws JspException {
		fixture.setUpEmptyModelCookie();
		fixture.setUpEmptyCartCookie();
		runTagShouldSkipBodyAndEvalPage();
	}
	
	@Test
	public void testWithPopulatedModelAndCart() throws JspException {
		fixture.setUpPopulatedModelCookie();
		fixture.setUpPopulatedCartCookie();
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
