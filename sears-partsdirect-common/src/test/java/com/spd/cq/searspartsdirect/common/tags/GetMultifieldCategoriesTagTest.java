package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

import java.util.List;

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
		fixture = new GetMultifieldCategoriesTagFixture(properties, pageManager);
		tag = new GetMultifieldCategoriesTag();
	}

	@Test
	public void testDoStartTag() {
		try {
			tag.setPageContext(pageContext);
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			@SuppressWarnings("unchecked")
			List<Object> result = (List<Object>)pageContext.getAttribute("categories");
			assertNull(result);
//			assertThat(result,isA(List.class));
//			assertThat(result,hasSize(3));			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
