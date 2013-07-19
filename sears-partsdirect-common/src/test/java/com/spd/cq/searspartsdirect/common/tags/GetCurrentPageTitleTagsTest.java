package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetCurrentPageTitleTagsFixture;

/**
 * test cases  and GetBrandCategoryModelNoTag
 */
public class GetCurrentPageTitleTagsTest extends MocksTag{
	
	private GetCurrentPageTitleTagsFixture fixture;
	private GetBrandCategoryModelNoTag brandTag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetCurrentPageTitleTagsFixture(pageContext);
		brandTag = new GetBrandCategoryModelNoTag();		
		brandTag.setPageContext(pageContext);
	}

	@Test
	public void testGetBrandCategoryModelNoTag() {
		fixture.setBrandTitle();
		try {
			int startResult = brandTag.doStartTag();
			assertThat(startResult, is(TagSupport.SKIP_BODY));
			int endResult = brandTag.doEndTag();
			assertThat(endResult, is(TagSupport.EVAL_PAGE));
			String pageTitleTest = (String) pageContext.getAttribute("brandCategoryModelNo");
			assertThat(pageTitleTest, is("brandTitle"));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
