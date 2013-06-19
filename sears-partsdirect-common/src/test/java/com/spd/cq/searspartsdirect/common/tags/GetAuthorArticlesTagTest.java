package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetAuthorArticlesTagFixture;
import com.spd.cq.searspartsdirect.common.model.RelatedArticleModel;

public class GetAuthorArticlesTagTest extends MocksTag{
	GetAuthorArticlesTag tag;
	GetAuthorArticlesTagFixture fixture; 
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		fixture = new GetAuthorArticlesTagFixture(pageContext,resourceResolver,pageManager);
		tag = new GetAuthorArticlesTag();
		tag.setPageContext(pageContext);
	}
	
	@Test
	public void testDoStartTag() {
		try {
			tag.setAuthorPath("/author");
			tag.doStartTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		ArrayList<RelatedArticleModel> articles = (ArrayList<RelatedArticleModel>)pageContext.getAttribute("articles");
		assertThat(articles,is(instanceOf(ArrayList.class)));
		//assertThat(articles,hasSize(3));
		RelatedArticleModel first = articles.get(0);
		assertThat(first.getUrl(),is("/foo.html"));
		RelatedArticleModel last = articles.get(2);
		assertThat(last.getUrl(),is("/baz.html"));

	}

}
