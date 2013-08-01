package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.fixture.EnvironmentSettingsFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GetLocalUrlTagTest extends MocksTag {

	private GetLocalUrlTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		new EnvironmentSettingsFixture().setUpRealDefaults(new EnvironmentSettings());
		tag = new GetLocalUrlTag();
	}

	@Test
	public void testTag() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat((String)pageContext.getAttribute("secureLocalUrl"),is("https://"+EnvironmentSettings.getLocalHttpsAndPort()));
		assertThat((String)pageContext.getAttribute("nonSecureLocalUrl"),is("http://"+EnvironmentSettings.getLocalHttpAndPort()));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}

}
