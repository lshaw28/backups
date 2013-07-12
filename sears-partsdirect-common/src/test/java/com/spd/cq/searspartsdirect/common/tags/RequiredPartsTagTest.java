package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.RequiredToolsPartsTagFixture;

public class RequiredPartsTagTest extends MocksTag {

	private RequiredToolsPartsTagFixture fixture;
	private RequiredPartsTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new RequiredToolsPartsTagFixture(properties);
		tag = new RequiredPartsTag();
	}

	@Test
	public void testRequiredParts() throws JspException {
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testRequiredPartsWithParts() throws JspException {
		fixture.setUpSomeParts();
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
