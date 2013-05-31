package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockPageContext;
import com.spd.cq.searspartsdirect.common.tags.GetProductCategoryNameTag;

@RunWith(PowerMockRunner.class)
public class GetProductCategoryNameTagTest {
	
	PageContext pageContext;
	
	@Before
	  public void setUp() {
	    pageContext = new MockPageContext();
	  }
	
	@Test
	public void testGetProductType() throws JspException {
		GetProductCategoryNameTag getProductCategoryNameTag = new GetProductCategoryNameTag();
		getProductCategoryNameTag.setPageContext(pageContext);
		getProductCategoryNameTag.doStartTag();
	}

}
