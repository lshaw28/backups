package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.fixture.Four04TagFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class Four04TagTest extends MocksTag {

	private Four04TagFixture fixture;
	private Four04Tag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new Four04TagFixture(pageContext,bindings);
		fixture.configureTestEnvironment();
		tag = new Four04Tag();
	}
	
	@Test
	public void testNeedsAuthentication() {
		fixture.setupRequested404Page();
		assertThat(tag.needsAuthentication(fixture.getRequest()),is(false));
		fixture.setupToRequireAuthorization();
		assertThat(tag.needsAuthentication(fixture.getRequest()),is(true));
		fixture.setupToDoUsualRedirect();
		assertThat(tag.needsAuthentication(fixture.getRequest()),is(false));
	}

	@Test
	public void testWithPlebeianPage() {
		fixture.setupToDoUsualRedirect();
		fixture.setup404ErrorCode();
		runTagAndShouldContinue();
		shouldNotAttemptAuth();
		shouldBeRedirecting();
	}
	
	@Test
	public void test404Is404() {
		fixture.setupRequested404Page();
		fixture.setup404ErrorCode();
		runTagAndShouldContinue();
		shouldNotAttemptAuth();
		shouldBeIncludingDefaultError();
	}
	
	@Test
	public void testWithSystemPage() {
		fixture.setupToRequireAuthorization();
		runTagAndShouldSkipPage();
		shouldAttemptAuth();
		jspShouldDoNothingFurther();
	}
	
	@Test
	public void testWithSystemPageAlreadyLoggedIn() {
		fixture.setupToRequireAuthorization();
		fixture.setupAlreadyLoggedIn();
		runTagAndShouldContinue();
		shouldNotAttemptAuth();
		shouldBeIncludingDefaultError();
	}
	
	@Test
	public void testWithSystemPageButNotBrowser() {
		fixture.setupToRequireAuthorization();
		fixture.setupNotBrowser();
		runTagAndShouldContinue();
		shouldNotAttemptAuth();
		shouldBeIncludingDefaultError();
	}
	
	@Test
	public void testWithSystemPageUncooperativeAuthenticator() {
		fixture.irritateAuthenticator();
		fixture.setupToRequireAuthorization();
		runTagAndShouldContinue();
		shouldAttemptAuth();
		shouldBeIncludingDefaultError();
	}
	
	@Test
	public void testWithSystemPageNoAuthenticator() {
		fixture.removeAuthenticator();
		fixture.setupToRequireAuthorization();
		runTagAndShouldContinue();
		shouldBeIncludingDefaultError();
	}
	
	private void shouldAttemptAuth() {
		verify(fixture.getAuthenticator()).login(fixture.getRequest(), fixture.getResponse());
	}
	
	private void shouldNotAttemptAuth() {
		verify(fixture.getAuthenticator(),times(0)).login(fixture.getRequest(),fixture.getResponse());
	}
	
	private void runTagAndShouldContinue() {
		tag.setPageContext(pageContext);
		int startOut = tag.doStartTag();
		assertThat(startOut,is(TagSupport.SKIP_BODY));
		int endOut = tag.doEndTag();
		assertThat(endOut,is(TagSupport.EVAL_PAGE));
	}
	
	private void runTagAndShouldSkipPage() {
		tag.setPageContext(pageContext);
		int startOut = tag.doStartTag();
		assertThat(startOut,is(TagSupport.SKIP_BODY));
		int endOut = tag.doEndTag();
		assertThat(endOut,is(TagSupport.SKIP_PAGE));
	}
	
	@SuppressWarnings("unchecked")
	private void shouldBeRedirecting() {
		assertThat((String)pageContext.getAttribute(Four04Tag.REDIRECT_VAR),is(EnvironmentSettings.get404HandlerURL()));
		assertThat((String)pageContext.getAttribute(Four04Tag.INCLUDE_VAR),anyOf(nullValue(),is(Constants.EMPTY)));
	}
	
	@SuppressWarnings("unchecked")
	private void shouldBeIncludingDefaultError() {
		assertThat((String)pageContext.getAttribute(Four04Tag.REDIRECT_VAR),anyOf(nullValue(),is(Constants.EMPTY)));
		assertThat((String)pageContext.getAttribute(Four04Tag.INCLUDE_VAR),anyOf(nullValue(),is(Constants.CQ_DEFAULT_ERROR_PAGE)));
	}
	
	@SuppressWarnings("unchecked")
	private void jspShouldDoNothingFurther() {
		assertThat((String)pageContext.getAttribute(Four04Tag.REDIRECT_VAR),anyOf(nullValue(),is(Constants.EMPTY)));
		assertThat((String)pageContext.getAttribute(Four04Tag.INCLUDE_VAR),anyOf(nullValue(),is(Constants.EMPTY)));
	}
}
