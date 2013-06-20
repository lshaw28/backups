package com.spd.cq.searspartsdirect.common.tags;


import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetCategoryArticleListFixture;
import com.spd.cq.searspartsdirect.common.model.RelatedArticleModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GetCategoryArticleListTagTest extends MocksTag {

	GetCategoryArticleListFixture fixture;
	GetCategoryArticleListTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetCategoryArticleListFixture(pageContext,resourceResolver,pageManager);
		tag = new GetCategoryArticleListTag();
	}

	@Test
	public void testDoStartTag() {
		runTheTag();
		@SuppressWarnings("unchecked")
		List<RelatedArticleModel> articles = (List<RelatedArticleModel>)pageContext.getAttribute("articles");
		assertThat(articles,is(instanceOf(List.class)));
		assertThat(articles,hasSize(4));
		RelatedArticleModel first = articles.get(0);
		assertThat(first.getUrl(),is("/baz.html"));
		RelatedArticleModel last = articles.get(3);
		assertThat(last.getUrl(),is("/foo.html"));
	}
	
	@Test
	public void testEncountersException() {
		fixture.setUpToThrow();
		runTheTag();
		@SuppressWarnings("unchecked")
		List<RelatedArticleModel> articles = (List<RelatedArticleModel>)pageContext.getAttribute("articles");
		assertThat(articles,is(instanceOf(List.class)));
		assertThat(articles,hasSize(0));
	}
	
	private void runTheTag() {
		int startResult = Integer.MIN_VALUE;
		int endResult = Integer.MIN_VALUE;
		try {
			tag.setPageContext(pageContext);
			tag.setCategoryPath("/category");
			startResult = tag.doStartTag();
			endResult = tag.doEndTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}

}
