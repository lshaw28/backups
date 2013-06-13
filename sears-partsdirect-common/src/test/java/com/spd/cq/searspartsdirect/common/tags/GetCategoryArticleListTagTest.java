package com.spd.cq.searspartsdirect.common.tags;


import java.util.List;

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
		tag.setPageContext(pageContext);
	}

	@Test
	public void testDoStartTag() {
		try {
			tag.setCategoryPath("/category");
			tag.doStartTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		@SuppressWarnings("unchecked")
		List<RelatedArticleModel> articles = (List<RelatedArticleModel>)pageContext.getAttribute("articles");
		assertThat(articles,is(instanceOf(List.class)));
		assertThat(articles,hasSize(3));
		RelatedArticleModel first = articles.get(0);
		assertThat(first.getUrl(),is("/baz.html"));
		RelatedArticleModel last = articles.get(2);
		assertThat(last.getUrl(),is("/foo.html"));
	}

}
