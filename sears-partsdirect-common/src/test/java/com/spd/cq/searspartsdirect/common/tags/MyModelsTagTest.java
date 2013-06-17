package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.MyModelsTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MyModelsTagTest extends MocksTag {

	private MyModelsTag tag;
	private MyModelsTagFixture fixture;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new MyModelsTagFixture(request);
		tag = new MyModelsTag();
	}

	private void doContextStartAndEnd() throws Exception {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
	
	@Test
	public void testWithNoCookies() {
		// This will become a "verify fails" case when API is hooked in
		try {
			fixture.setUpNoCookies();
			doContextStartAndEnd();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testWithoutOurCookie() {
		// This will become a "verify fails" case when API is hooked in
		try {
			fixture.setUpWithoutOurCookie();
			doContextStartAndEnd();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testWithInvalidCookie() {
		// This will become a "verify fails" case when API is hooked in
		try {
			fixture.setUpWithOurCookieInvalid();
			doContextStartAndEnd();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testWithValidCookie() {
		// THIS will become the "verify success" case when the API is hooked in
		try {
			fixture.setUpWithOurCookieValid();
			doContextStartAndEnd();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
