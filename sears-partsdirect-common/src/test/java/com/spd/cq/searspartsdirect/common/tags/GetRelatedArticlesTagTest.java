package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.fixture.GetRelatedItemsFixture;

public class GetRelatedArticlesTagTest extends MocksTag {

	private GetRelatedItemsFixture fixture;
	private GetRelatedArticlesTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetRelatedItemsFixture(pageManager);
		tag = new GetRelatedArticlesTag();
	}

	@Test
	public void testNoHits() {
		try {
				fixture.makeNHits(0, resourceResolver);
				tag.setPageContext(pageContext);
				tag.setCategoryPath("/content/searspartsdirect/en/somepage");
				
				int startResult = tag.doStartTag();
				assertThat(startResult,is(TagSupport.SKIP_BODY));
				int endResult = tag.doEndTag();
				assertThat(endResult,is(TagSupport.EVAL_PAGE));
				
				List<Object> result = (List<Object>)pageContext.getAttribute("articles");
				assertThat(result,isA(List.class));
				assertThat(result,is(empty()));
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testTwoHits() {
		try {
				fixture.makeNHits(2, resourceResolver);

				tag.setPageContext(pageContext);
				tag.setCategoryPath("/content/searspartsdirect/en/somepage");
				
				int startResult = tag.doStartTag();
				assertThat(startResult,is(TagSupport.SKIP_BODY));
				int endResult = tag.doEndTag();
				assertThat(endResult,is(TagSupport.EVAL_PAGE));
				
				@SuppressWarnings("unchecked")
				List<Object> result = (List<Object>)pageContext.getAttribute("articles");
				assertThat(result,isA(List.class));
				assertThat(result,hasSize(2));
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testFiveHits() {
		try {
			
				fixture.makeNHits(5, resourceResolver);

				tag.setPageContext(pageContext);
				tag.setCategoryPath("/content/searspartsdirect/en/somepage");
				
				int startResult = tag.doStartTag();
				assertThat(startResult,is(TagSupport.SKIP_BODY));
				int endResult = tag.doEndTag();
				assertThat(endResult,is(TagSupport.EVAL_PAGE));
				
				@SuppressWarnings("unchecked")
				List<Object> result = (List<Object>)pageContext.getAttribute("articles");
				assertThat(result,isA(List.class));
				assertThat(result,hasSize(4));
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
