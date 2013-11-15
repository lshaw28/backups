package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.api.resource.ValueMap;
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
		ValueMap pageProperties = mock(ValueMap.class);
		when(currentPage.getProperties()).thenReturn(pageProperties);
		when(pageProperties.get("cq:template", "")).thenReturn("someTemplate");
	}

	@Test
	public void testWithEmptyCookies() throws JspException {
		fixture.setUpEmptyCookies();
		runTagShouldSkipBodyAndEvalPage();
	}
	
	@Test
	public void testWithNoCookies() throws JspException {
		fixture.setUpNoCookies();
		runTagShouldSkipBodyAndEvalPage();
	}
	
	@Test
	public void testWithMyProfileModelCookie() throws JspException {
		fixture.setUpPopulatedModelCookie();
		runTagShouldSkipBodyAndEvalPage();
	}
	
	@Test
	public void testWithShoppingCartCookie() throws JspException {
		fixture.setUpPopulatedShoppingCartCookie();
		runTagShouldSkipBodyAndEvalPage();
	}
	
	@Test
	public void testWithAllCookies() throws JspException {
		fixture.setUpPopulatedModelCookie();
		fixture.setUpPopulatedShoppingCartCookie();
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
