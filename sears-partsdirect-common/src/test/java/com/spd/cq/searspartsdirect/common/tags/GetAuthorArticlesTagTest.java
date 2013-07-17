package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;

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
		fixture = new GetAuthorArticlesTagFixture(currentPage, resourceResolver, pageManager);
		tag = new GetAuthorArticlesTag();
	}

	@Test
	public void testDoStartTag() throws JspException, RepositoryException {
		
		fixture.setUpComplete();
		
		tag.setPageContext(pageContext);
		fixture.setCurrentPagePath();
		tag.doStartTag();
		tag.doEndTag();
		

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
	
	@Test
	public void testDoStartTagExplodes() throws JspException, RepositoryException {
		
		fixture.setUpExplodes();
		
		tag.setPageContext(pageContext);
		fixture.setCurrentPagePath();
		tag.doStartTag();
		tag.doEndTag();
		

		@SuppressWarnings("unchecked")
		ArrayList<ArticleModel> articles = (ArrayList<ArticleModel>) pageContext.getAttribute("articles");
		assertNull(articles);
		
	}

}
