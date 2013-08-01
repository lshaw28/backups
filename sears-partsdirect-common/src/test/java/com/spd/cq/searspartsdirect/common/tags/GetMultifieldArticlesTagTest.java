package com.spd.cq.searspartsdirect.common.tags;

import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetMultifieldArticlesTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetMultifieldArticlesTagTest  extends MocksTag {
	
	GetMultifieldArticlesTag tag;
	GetMultifieldArticlesTagFixture fixture;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		fixture = new GetMultifieldArticlesTagFixture(properties, pageManager, resourceResolver, currentPage);
		tag = new GetMultifieldArticlesTag();
	}

	@Test
	public void testTagNoSetup() throws JspException {
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testDoStartTagNoPages() throws JspException {
		tag.setCategory(fixture.getCategory());
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>)pageContext.getAttribute("articles");
		assertTrue(result.isEmpty());			
	}
	
	@Test
	public void testDoStartTagWithPages() throws JspException, RepositoryException {
		fixture.setUpTestPages();
		fixture.setupAuxPages();
		fixture.setCurrentPagePath();
		tag.setCategory(fixture.getCategory());
		runsSkipsBodyEvalsPage();
		
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>)pageContext.getAttribute("articles");
		assertTrue(result.size() > 0);
		assertEquals(result.size(), 8);
		String cat101Header = (String)pageContext.getAttribute("category101header");
		assertThat(cat101Header,is(fixture.getHeader()));
		String cat101ViewAllLinkText = (String)pageContext.getAttribute("category101linkText");
		assertThat(cat101ViewAllLinkText,is(fixture.getViewAllLinkText()));
	}

	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext); 
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
