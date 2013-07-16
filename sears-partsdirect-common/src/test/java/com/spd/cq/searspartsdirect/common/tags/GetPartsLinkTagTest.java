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
		assertThat(tag.getBrandName(),is("Kenmore"));
		assertThat(tag.getCategoryName(),is("Dishwasher"));
		assertThat(tag.getModelNumber(),is("66513593K600"));
		int startResult  = tag.doStartTag();
		String findPartsUrl = (String) pageContext.getAttribute("findPartUrl");
		// Removed this assertion since it fails if the API is unreachable/etc.
		// Assert.assertNotNull(findPartsUrl);
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
	
	@Test
	public void testDoStartNoParams() throws JspException {
		tag.setPageContext(pageContext);
		int startResult  = tag.doStartTag();
		String findPartsUrl = (String) pageContext.getAttribute("findPartUrl");
		Assert.assertNull(findPartsUrl);
		assertThat(startResult,is(TagSupport.SKIP_BODY));
	}
}
