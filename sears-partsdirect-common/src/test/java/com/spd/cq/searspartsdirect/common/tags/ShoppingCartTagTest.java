package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ShoppingCartTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ShoppingCartTagTest extends MocksTag {

	ShoppingCartTagFixture fixture;
	ShoppingCartTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ShoppingCartTagFixture(request);
		tag = new ShoppingCartTag();
	}

	@Test
	public void testDoStartTag() {
		try {
			tag.setPageContext(pageContext);
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			String cartItemCount = (String)pageContext.getAttribute("cartItemCount");
			assertThat(cartItemCount,isA(String.class));
			assertThat(cartItemCount,not(nullValue()));
			assertThat(cartItemCount,is("23"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
