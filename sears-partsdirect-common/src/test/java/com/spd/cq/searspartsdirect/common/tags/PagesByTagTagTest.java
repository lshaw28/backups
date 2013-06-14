package com.spd.cq.searspartsdirect.common.tags;


import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.fixture.PagesByTagTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PagesByTagTagTest extends MocksTag {

	private PagesByTagTagFixture fixture;
	private PagesByTagTag tag; // tag
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new PagesByTagTagFixture(resourceResolver);
		tag = new PagesByTagTag();
	}

	@Test
	public void testTagUsage() {
		try {
			tag.setPageContext(pageContext);
			tag.setTag(fixture.getTestTag());
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			@SuppressWarnings("unchecked")
			List<Page> pages = (List<Page>)pageContext.getAttribute("taggedPages");
			assertThat(pages,not(nullValue()));
			assertThat(pages,isA(List.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
