package com.spd.cq.searspartsdirect.common.tags;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetMultifieldArticlesTagFixture;

public class GetMultifieldArticlesTagTest  extends MocksTag {
	
	GetMultifieldArticlesTag tag;
	GetMultifieldArticlesTagFixture fixture;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		fixture = new GetMultifieldArticlesTagFixture(properties, pageManager, resourceResolver);
		tag = new GetMultifieldArticlesTag();
	}

	@Test
	public void testTag() {
		assertTrue(true);
	}
	
//	@Test
//	public void testDoStartTagNoPages() throws JspException {
//		runsSkipsBodyEvalsPage();
//		@SuppressWarnings("unchecked")
//		List<Object> result = (List<Object>)pageContext.getAttribute("categories");
//		assertNull(result);
////			assertThat(result,isA(List.class));
////			assertThat(result,hasSize(3));			
//	}
//	
//	@Test
//	public void testDoStartTagWithPages() throws JspException, RepositoryException {
//		fixture.setUpTestPages();
//		runsSkipsBodyEvalsPage();
//		@SuppressWarnings("unchecked")
//		List<Object> result = (List<Object>)pageContext.getAttribute("categories");
//		assertThat(result,isA(List.class));
//		assertThat(result,hasSize(5));			
//	}
//
//	private void runsSkipsBodyEvalsPage() throws JspException {
//		tag.setPageContext(pageContext);
//		int startResult = tag.doStartTag();
//		assertThat(startResult,is(TagSupport.SKIP_BODY));
//		int endResult = tag.doEndTag();
//		assertThat(endResult,is(TagSupport.EVAL_PAGE));
//	}
}
