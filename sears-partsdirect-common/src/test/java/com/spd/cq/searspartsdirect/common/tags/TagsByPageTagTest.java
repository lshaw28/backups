package com.spd.cq.searspartsdirect.common.tags;


import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.day.cq.tagging.Tag;
import com.spd.cq.searspartsdirect.common.fixture.TagsByPageTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TagsByPageTagTest extends MocksTag {

	private TagsByPageTagFixture fixture;
	private TagsByPageTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new TagsByPageTagFixture(pageContext,pageManager,currentPage);
		tag = new TagsByPageTag();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAllWithNoPagePathNoTagType() {
		try {
			tag.setPageContext(pageContext);
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			assertThat((List<Tag>)pageContext.getAttribute(fixture.getTestTagType()+"List"),nullValue());
			assertThat((List<Tag>)pageContext.getAttribute("tagList"),isA(List.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAllWithPagePathNoTagType() {
		try {
			tag.setPageContext(pageContext);
			tag.setPagepath(fixture.getTestPagePath());
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			assertThat((List<Tag>)pageContext.getAttribute(fixture.getTestTagType()+"List"),nullValue());
			assertThat((List<Tag>)pageContext.getAttribute("tagList"),isA(List.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAllWithNoPagePathWithTagType() {
		try {
			tag.setPageContext(pageContext);
			tag.setTagType(fixture.getTestTagType());
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			assertThat((List<Tag>)pageContext.getAttribute(fixture.getTestTagType()+"List"),isA(List.class));
			assertThat((List<Tag>)pageContext.getAttribute("tagList"),nullValue());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAllWithPagePathWithTagType() {
		try {
			tag.setPageContext(pageContext);
			tag.setPagepath(fixture.getTestPagePath());
			tag.setTagType(fixture.getTestTagType());
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			assertThat((List<Tag>)pageContext.getAttribute(fixture.getTestTagType()+"List"),isA(List.class));
			assertThat((List<Tag>)pageContext.getAttribute("tagList"),nullValue());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
