package com.spd.cq.searspartsdirect.common.tags;


import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetMostPopularGuideTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetMostPopularGuideTagTest extends MocksTag {

	private GetMostPopularGuideTagFixture fixture;
	private GetMostPopularGuideTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetMostPopularGuideTagFixture();
		tag = new GetMostPopularGuideTag();
	}

	@Test
	public void testDoStartTagNoSetup() throws JspException {
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testDoStartTagWithResults() throws JspException, RepositoryException {
		fixture.setUpResults(resourceResolver,pageManager);
		runsSkipsBodyEvalsPage();
	}

	@Test
	public void testSetCategoryPath() {
		tag.setCategoryPath("foo!");
	}

	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
