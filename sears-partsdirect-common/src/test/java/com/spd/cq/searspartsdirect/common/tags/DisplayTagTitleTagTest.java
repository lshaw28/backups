package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.DisplayTagTitleTagFixture;

public class DisplayTagTitleTagTest extends MocksTag {

	private DisplayTagTitleTagFixture fixture;
	private DisplayTagTitleTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new DisplayTagTitleTagFixture(resourceResolver,pageContext);
		tag = new DisplayTagTitleTag();
	}

	@Test
	public void testResolves() throws JspException {
		fixture.setUpResolvingTag();
		runsTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testExplodes() throws JspException {
		fixture.setUpExplodingTag();
		runsTagSkipsBodyEvalsPage();
	}
	
	private void runsTagSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		tag.setTagId(fixture.getTagId());
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}

}
