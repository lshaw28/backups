package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetModelHeaderTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

public class GetModelHeaderTagTest extends MocksTag {
	
	private GetModelHeaderTagFixture fixture;
	private GetModelHeaderTag tag; // tag
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetModelHeaderTagFixture(slingRequest, pageContext);
		tag = new GetModelHeaderTag();
	}
	
	@Test
	public void testWithArguments() throws JspException {
		tag.setBrand(new BrandModel("a","Kenmore","c","d"));
		tag.setProductCategory(new ProductCategoryModel("a","b","Dishwasher", "d", "e", "f", "g", "h"));
		tag.setModel("66513593K600");
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testNoArguments() throws JspException {
		runTagShouldSkipBodyEvalPage();
	}
	
	private void runTagShouldSkipBodyEvalPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
