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

import com.spd.cq.searspartsdirect.common.fixture.GetMultifieldCategoriesTagFixture;

public class GetMultifieldCategoriesTagTest  extends MocksTag {
	
	GetMultifieldCategoriesTag tag;
	GetMultifieldCategoriesTagFixture fixture;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		fixture = new GetMultifieldCategoriesTagFixture(properties, pageManager, resourceResolver);
		tag = new GetMultifieldCategoriesTag();
	}

	@Test
	public void testDoStartTagNoPages() throws JspException {
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>)pageContext.getAttribute("categories");
		assertNull(result);
//			assertThat(result,isA(List.class));
//			assertThat(result,hasSize(3));			
	}
	
	@Test
	public void testDoStartTagWithPages() throws JspException, RepositoryException {
		fixture.setUpTestPages();
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>)pageContext.getAttribute("categories");
		assertThat(result,isA(List.class));
		assertThat(result,hasSize(5));			
	}

	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
