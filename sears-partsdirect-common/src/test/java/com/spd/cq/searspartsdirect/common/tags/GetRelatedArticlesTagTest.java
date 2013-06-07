package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;

import com.spd.cq.searspartsdirect.common.model.RelatedArticleModel;

public class GetRelatedArticlesTagTest extends TestCase {

	GetRelatedArticlesTag tag;
	ArrayList<RelatedArticleModel> articles;
	PageContext pageContext;
	
	@Override
	protected void setUp() {
		pageContext = Mockito.mock(PageContext.class);
		tag = new GetRelatedArticlesTag();
		articles = new ArrayList<RelatedArticleModel>();

		 // dummy data
		RelatedArticleModel article = new RelatedArticleModel("url", "imagePath", "title", "description");
		RelatedArticleModel article2 = new RelatedArticleModel("url2", "imagePath2", "title2", "description2");
		articles.add(article);
		articles.add(article2);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRelatedArticles() throws JspException {
		ArrayList<RelatedArticleModel> articlesCheck;
		Mockito.when(pageContext.getAttribute("articles")).thenReturn(articles);
		
		tag.doStartTag();
		articlesCheck = (ArrayList<RelatedArticleModel>) pageContext.getAttribute("articles");
		for (int i=0; i < articlesCheck.size(); i++) {
			assertEquals(articlesCheck.get(i).getDescription(),articles.get(i).getDescription());
			assertEquals(articlesCheck.get(i).getUrl(),articles.get(i).getUrl());
			assertEquals(articlesCheck.get(i).getImagePath(),articles.get(i).getImagePath());
			assertEquals(articlesCheck.get(i).getTitle(),articles.get(i).getTitle());
		}
	}
}
