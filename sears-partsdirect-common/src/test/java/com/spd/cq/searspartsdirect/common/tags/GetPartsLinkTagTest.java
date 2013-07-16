package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetModelHeaderTagFixture;
import com.spd.cq.searspartsdirect.common.fixture.GetPartsLinkTagFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

public class GetPartsLinkTagTest extends MocksTag {
	
	private GetPartsLinkTagFixture fixture;
	private GetPartsLinkTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetPartsLinkTagFixture(slingRequest, pageContext);
		tag = new GetPartsLinkTag();
	}

	@Test
	public void testDoStart() throws JspException {
		tag.setBrandName("Kenmore");
		tag.setCategoryName("Dishwasher");
		tag.setModelNumber("66513593K600");
		tag.setPageContext(pageContext);
		int startResult  = tag.doStartTag();
		String findPartsUrl = (String) pageContext.getAttribute("findPartUrl");
		Assert.assertNotNull(findPartsUrl);
		assertThat(startResult,is(TagSupport.SKIP_BODY));
	}
}
