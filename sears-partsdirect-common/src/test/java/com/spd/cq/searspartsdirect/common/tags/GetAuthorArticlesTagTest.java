package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetAuthorArticlesTagFixture;
import com.spd.cq.searspartsdirect.common.model.ArticleModel;

public class GetAuthorArticlesTagTest extends MocksTag {
	GetAuthorArticlesTag tag;
	GetAuthorArticlesTagFixture fixture;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		fixture = new GetAuthorArticlesTagFixture(pageContext, resourceResolver, pageManager);
		tag = new GetAuthorArticlesTag();
		tag.setPageContext(pageContext);
	}

	@Test
	public void testDoStartTag() {
		try {
			fixture.setCurrentPagePath();
			tag.doStartTag();
			tag.doEndTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		@SuppressWarnings("unchecked")
		ArrayList<ArticleModel> articles = (ArrayList<ArticleModel>) pageContext.getAttribute("articles");
		assertNotNull(articles);
		if (CollectionUtils.isNotEmpty(articles)) {
			assertThat(articles, is(instanceOf(ArrayList.class)));
			assertEquals(articles.size(), 3);
			ArticleModel first = articles.get(0);
			assertThat(first.getUrl(), is("/foo.html"));
			ArticleModel last = articles.get(2);
			assertThat(last.getUrl(), is("/baz.html"));
		}
	}

}
