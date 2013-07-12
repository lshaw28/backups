package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.UniqueIDTagFixture;

public class UniqueIDTagTest extends MocksTag {

	UniqueIDTagFixture fixture;
	UniqueIDTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new UniqueIDTagFixture(currentPage,resource);
		tag = new UniqueIDTag();
	}

	@Test
	public void testWithPathIntoContent() throws JspException {
		fixture.setUpPathIntoContent();
		runTagSkipsBodyEvalsPage();
		String uniqueId = (String)pageContext.getAttribute("uniqueId");
		assertThat(uniqueId,not(nullValue()));
		assertThat(uniqueId,is(fixture.getTestIdentifierInContent()));
	}
	
	@Test
	public void testWithPathAboveContent() throws JspException {
		fixture.setUpPathAboveContent();
		runTagSkipsBodyEvalsPage();
		String uniqueId = (String)pageContext.getAttribute("uniqueId");
		assertThat(uniqueId,not(nullValue()));
		assertThat(uniqueId,is(fixture.getTestIdentifierAboveContent()));
	}
	
	private void runTagSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
