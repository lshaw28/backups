package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.RequiredToolsPartsTagFixture;

public class RequiredToolsTagTest extends MocksTag {

	private RequiredToolsPartsTagFixture fixture;
	private RequiredToolsTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new RequiredToolsPartsTagFixture(properties);
		tag = new RequiredToolsTag();
	}

	@Test
	public void testRequiredTools() throws JspException {
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testRequiredToolsWithTools() throws JspException {
		fixture.setUpSomeTools();
		runsSkipsBodyEvalsPage();
	}
	
	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		int endResult = tag.doEndTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
