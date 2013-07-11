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
	public void testWithSelectors() throws JspException {
		fixture.setUpWithSelectors();
		tag.setBrand(new BrandModel("a","b","c","d"));
		tag.setProductCategory(new ProductCategoryModel("a","b","c", "d", "e", "f", "g", "h"));
		tag.setModel("A");
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithoutSelectors() throws JspException {
		fixture.setUpWithoutSelectors();
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testExceptions() throws JspException, IOException {
		fixture.setUpWithSelectors();
		fixture.setUpExceptions();
		runTagShouldSkipBodyEvalPage();
	}
	
	/*
	@Test
	public void testGetJsonFromApi() throws IOException {
		PartsDirectAPIHelper api = new PartsDirectAPIHelper();
		String json = api.readJsonData(fixture.getTestUrl());
		assertThat(json,nullValue());
	}
	*/ // purpose served..
	
	private void runTagShouldSkipBodyEvalPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
