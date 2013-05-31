package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.junit.Test;
import org.springframework.mock.web.MockPageContext;

import com.spd.cq.searspartsdirect.common.tags.ShoppingCartTag;

public class ShoppingCartTagTest {

	@Test
	public void testShoppingCartCount() throws JspException {
		ShoppingCartTag tag = new ShoppingCartTag();
		PageContext context = new MockPageContext();
		tag.setPageContext(context);
		tag.doStartTag();
	}

}
