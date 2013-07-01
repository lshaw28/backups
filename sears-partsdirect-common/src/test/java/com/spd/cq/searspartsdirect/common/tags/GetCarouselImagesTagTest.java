package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetCarouselImagesTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetCarouselImagesTagTest extends MocksTag {

	GetCarouselImagesTagFixture fixture;
	GetCarouselImagesTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetCarouselImagesTagFixture(pageContext);
		tag = new GetCarouselImagesTag();
	}

	@Test
	public void testWithNothingSetUp() throws JspException {
		runsTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testWithLessThanNothingSetUp() throws JspException {
		fixture.removeCurrentNode();
		runsTagSkipsBodyEvalsPage();
	}
	
	private void runsTagSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}

}
