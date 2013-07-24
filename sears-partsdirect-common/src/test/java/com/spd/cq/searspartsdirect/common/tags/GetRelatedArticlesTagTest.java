package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

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
	public void testNothing() throws JspException {
		runsSkipsBodyEvalsPage();
		
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>)pageContext.getAttribute("articles");
		assertThat(result,isA(List.class));
		assertThat(result,is(empty()));
	}

	@Test
	public void testNoHits() throws RepositoryException, JspException {
		
		fixture.makeNHits(0, resourceResolver);
		
		tag.setCategoryPath("/content/searspartsdirect/en/somepage");
		
		runsSkipsBodyEvalsPage();
		
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>)pageContext.getAttribute("articles");
		assertThat(result,isA(List.class));
		assertThat(result,is(empty()));
	}
	
	@Test
	public void testTwoHits() throws RepositoryException, JspException {
		
		fixture.makeNHits(2, resourceResolver);

		tag.setCategoryPath("/content/searspartsdirect/en/somepage");
		
		runsSkipsBodyEvalsPage();
		
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>)pageContext.getAttribute("articles");
		assertThat(result,isA(List.class));
		assertThat(result,hasSize(2));
	}

	@Test
	public void testFiveHits() throws RepositoryException, JspException {
		
		fixture.makeNHits(5, resourceResolver);

		tag.setCategoryPath("/content/searspartsdirect/en/somepage");
		
		runsSkipsBodyEvalsPage();
		
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>)pageContext.getAttribute("articles");
		assertThat(result,isA(List.class));
		assertThat(result,hasSize(4)); // not 5.
	} 

	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
