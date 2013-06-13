package com.spd.cq.searspartsdirect.common.tags;


import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;
import org.osgi.service.component.ComponentContext;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.fixture.Four04TagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Four04TagTest extends MocksTag {

	private Four04TagFixture fixture;
	private Four04Tag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		configureTestEnvironment();
		fixture = new Four04TagFixture(pageContext,bindings);
		tag = new Four04Tag();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void configureTestEnvironment() throws Exception {
		ComponentContext osgiContext = mock(ComponentContext.class);
		Dictionary d = new Hashtable();
		d.put(EnvironmentSettings.HANDLE_404_URL, "/404.html");
		when(osgiContext.getProperties()).thenReturn(d);
		EnvironmentSettings env = new EnvironmentSettings();
		env.externalActivateForTesting(osgiContext);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testWithPlebeianPage() {
		fixture.setupToDoUsualRedirect();
		tag.setPageContext(pageContext);
		int startOut = tag.doStartTag();
		assertThat(startOut,is(TagSupport.SKIP_BODY));
		int endOut = tag.doEndTag();
		assertThat(endOut,is(TagSupport.EVAL_PAGE));
		assertThat((String)pageContext.getAttribute("mustRedirect"),is(EnvironmentSettings.get404HandlerURL()));
		assertThat((String)pageContext.getAttribute("mustInclude"),anyOf(nullValue(),is("")));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testWithSystemPage() {
		fixture.setupToRequireAuthorization();
		tag.setPageContext(pageContext);
		int startOut = tag.doStartTag();
		assertThat(startOut,is(TagSupport.SKIP_BODY));
		int endOut = tag.doEndTag();
		assertThat(endOut,is(TagSupport.SKIP_PAGE));
		assertThat((String)pageContext.getAttribute("mustRedirect"),anyOf(nullValue(),is("")));
		assertThat((String)pageContext.getAttribute("mustInclude"),anyOf(nullValue(),is("")));
	}
}
