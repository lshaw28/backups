package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetCategoryArticleListFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.ArticleModel;


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
		Map<String,List<ArticleModel>> articles = (Map<String,List<ArticleModel>>)pageContext.getAttribute("articles");
		assertThat(articles,is(instanceOf(Map.class)));
		List<ArticleModel> articleList = articles.get(Constants.SUBCATEGORY_TAG+"/hasFour");
		assertThat(articleList,hasSize(5));
		ArticleModel first = articleList.get(0);
		assertThat(first.getUrl(),is("/baz.html"));
		ArticleModel last = articleList.get(3);
		assertThat(last.getUrl(),is("/foo.html"));
	}
	
	@Test
	public void testAuxPagesBroken() {
		fixture.breakAuxPages();
		runTheTag();
		@SuppressWarnings("unchecked")
		Map<String,List<ArticleModel>> articles = (Map<String,List<ArticleModel>>)pageContext.getAttribute("articles");
		assertThat(articles,is(instanceOf(Map.class)));
		List<ArticleModel> articleList = articles.get(Constants.SUBCATEGORY_TAG+"/hasFour");
		assertThat(articleList,hasSize(4));
		ArticleModel first = articleList.get(0);
		assertThat(first.getUrl(),is("/baz.html"));
		ArticleModel last = articleList.get(3);
		assertThat(last.getUrl(),is("/foo.html"));
	}
	
	@Test
	public void testEncountersException() {
		fixture.setUpToThrow();
		runTheTag();
		@SuppressWarnings("unchecked")
		Map<String,List<ArticleModel>> articles = (Map<String,List<ArticleModel>>)pageContext.getAttribute("articles");
		assertThat(articles,is(instanceOf(Map.class)));
		assertThat(articles.entrySet(),hasSize(0));
	}
	
	private void runTheTag() {
		int startResult = Integer.MIN_VALUE;
		int endResult = Integer.MIN_VALUE;
		try {
			tag.setPageContext(pageContext);
			tag.setCategory(fixture.getCategory());
			startResult = tag.doStartTag();
			endResult = tag.doEndTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}

}
