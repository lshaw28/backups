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

	@SuppressWarnings("unchecked")
	@Test
	public void testWithPlebeianPage() {
		fixture.setupToDoUsualRedirect();
		tag.setPageContext(pageContext);
		int startOut = tag.doStartTag();
		assertThat(startOut,is(TagSupport.SKIP_BODY));
		int endOut = tag.doEndTag();
		verify(fixture.getAuthenticator(),times(0)).login(fixture.getRequest(),fixture.getResponse());
		assertThat(endOut,is(TagSupport.EVAL_PAGE));
		assertThat((String)pageContext.getAttribute(Four04Tag.REDIRECT_VAR),is(EnvironmentSettings.get404HandlerURL()));
		assertThat((String)pageContext.getAttribute(Four04Tag.INCLUDE_VAR),anyOf(nullValue(),is(Constants.EMPTY)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testWithSystemPage() {
		fixture.setupToRequireAuthorization();
		tag.setPageContext(pageContext);
		int startOut = tag.doStartTag();
		assertThat(startOut,is(TagSupport.SKIP_BODY));
		int endOut = tag.doEndTag();
		verify(fixture.getAuthenticator()).login(fixture.getRequest(), fixture.getResponse());
		assertThat(endOut,is(TagSupport.SKIP_PAGE));
		assertThat((String)pageContext.getAttribute(Four04Tag.REDIRECT_VAR),anyOf(nullValue(),is(Constants.EMPTY)));
		assertThat((String)pageContext.getAttribute(Four04Tag.INCLUDE_VAR),anyOf(nullValue(),is(Constants.EMPTY)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testWithSystemPageNoAuthenticator() {
		fixture.removeAuthenticator();
		fixture.setupToRequireAuthorization();
		tag.setPageContext(pageContext);
		int startOut = tag.doStartTag();
		assertThat(startOut,is(TagSupport.SKIP_BODY));
		int endOut = tag.doEndTag();
		// auth.login(jspRequest, jspResponse) will not have been called - and auth is null, so no verify
		assertThat(endOut,is(TagSupport.EVAL_PAGE));
		assertThat((String)pageContext.getAttribute(Four04Tag.REDIRECT_VAR),anyOf(nullValue(),is(Constants.EMPTY)));
		assertThat((String)pageContext.getAttribute(Four04Tag.INCLUDE_VAR),anyOf(nullValue(),is(Constants.CQ_DEFAULT_ERROR_PAGE)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testWithSystemPageUncooperativeAuthenticator() {
		fixture.irritateAuthenticator();
		fixture.setupToRequireAuthorization();
		tag.setPageContext(pageContext);
		int startOut = tag.doStartTag();
		assertThat(startOut,is(TagSupport.SKIP_BODY));
		int endOut = tag.doEndTag();
		// auth.login(jspRequest, jspResponse) will not have been called - and auth is null, so no verify
		assertThat(endOut,is(TagSupport.EVAL_PAGE));
		assertThat((String)pageContext.getAttribute(Four04Tag.REDIRECT_VAR),anyOf(nullValue(),is(Constants.EMPTY)));
		assertThat((String)pageContext.getAttribute(Four04Tag.INCLUDE_VAR),anyOf(nullValue(),is(Constants.CQ_DEFAULT_ERROR_PAGE)));
	}
}
