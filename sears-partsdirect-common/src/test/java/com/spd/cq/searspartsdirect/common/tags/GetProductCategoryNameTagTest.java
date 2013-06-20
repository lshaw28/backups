package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetProductCategoryNameTagFixture;

public class GetProductCategoryNameTagTest extends MocksTag{
	
	private GetProductCategoryNameTagFixture fixture;
	private GetProductCategoryNameTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetProductCategoryNameTagFixture(pageContext);
		tag = new GetProductCategoryNameTag();
		tag.setPageContext(pageContext);
	}

	@Test
	public void testGetProductCategoryNameTag() {
		try {
			int startResult = tag.doStartTag();
			assertThat(startResult, is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult, is(TagSupport.EVAL_PAGE));
			String pageTitleTest = (String) pageContext.getAttribute("productCategoryName");
			assertThat(pageTitleTest, is("some title"));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
