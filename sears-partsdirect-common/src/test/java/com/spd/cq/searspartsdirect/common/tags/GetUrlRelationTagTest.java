package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetUrlRelationTagFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetUrlRelationTagTest extends MocksTag {

	private GetUrlRelationTagFixture fixture;
	private GetUrlRelationTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetUrlRelationTagFixture(slingRequest,pageManager);
		tag = new GetUrlRelationTag();
	}

	@Test
	public void testInvalidRelationTypeNoSelectors() throws JspException {
		tag.setRelationType("invalid");
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testInvalidRelationTypeWithSelectors() throws JspException {
		fixture.setUpSelectors();
		tag.setRelationType("invalid");
		Exception thrown = null;
		try {
			runsSkipsBodyEvalsPage();
		} catch (Exception e) {
			thrown = e;
		}
		assertThat(thrown,not(nullValue()));
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
	}
	
	@Test
	public void testGetProductCategory() throws JspException {
		fixture.setUpSelectors();
		fixture.setUpProductCategory();
		tag.setRelationType("productCategory");
		runsSkipsBodyEvalsPage();
		Object pcr = pageContext.getAttribute("productCategoryRelation");
		assertThat(pcr,not(nullValue()));
		assertTrue(pcr.getClass().equals(ProductCategoryModel.class));
	}
	
	@Test
	public void testGetBrand() throws JspException {
		fixture.setUpSelectors();
		fixture.setUpBrand();
		tag.setRelationType("brand");
		runsSkipsBodyEvalsPage();
		Object br = pageContext.getAttribute("brandRelation");
		assertThat(br,not(nullValue()));
		assertTrue(br.getClass().equals(BrandModel.class));
	}
	
	@Test
	public void testGetModel() throws JspException {
		fixture.setUpSelectors();
		tag.setRelationType("model");
		runsSkipsBodyEvalsPage();
		Object mr = pageContext.getAttribute("modelRelation");
		assertThat(mr,not(nullValue()));
		assertTrue(mr.getClass().equals(String.class));
		assertTrue(fixture.getModel().equals(mr));
	}
	
	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(), is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(), is(TagSupport.EVAL_PAGE));
	}

}
