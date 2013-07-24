package com.spd.cq.searspartsdirect.common.tags;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.CleanTextTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class CleanTextTagTest extends MocksTag {

	private CleanTextTagFixture fixture;
	private CleanTextTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new CleanTextTagFixture(pageContext);
		tag = new CleanTextTag();
	}

	@Test
	public void testMissingRequiredArguments() {
		Exception thrown = null;
		try {
			runsSkipsBodyEvalsPage();
		} catch (Exception e) {
			thrown = e;
		}
		assertThat(thrown,is(not(nullValue())));
		assertEquals(thrown.getClass(),NullPointerException.class);
	}
	
	@Test
	public void testNoAction() throws JspException {
		tag.setText(fixture.getCleanText());
		runsSkipsBodyEvalsPage();
		String output = fixture.getOutput();
		assertThat(output,is(fixture.getCleanText()));
	}
	
	@Test
	public void testDoesCleaning() throws JspException {
		tag.setText(fixture.getDirtyText());
		runsSkipsBodyEvalsPage();
		String output = fixture.getOutput();
		assertThat(output,is(fixture.getCleanText()));
	}
	
	@Test
	public void testWhenIOFails() throws JspException, IOException {
		tag.setText(fixture.getDirtyText());
		fixture.breakWriter();
		runsSkipsBodyEvalsPage();
		String output = fixture.getOutput();
		assertThat(output,is(Constants.EMPTY));
	}

	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
