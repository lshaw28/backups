package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetUrlRelationTagFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;

public class GetUrlRelationTagTest extends MocksTag {

	private GetUrlRelationTagFixture fixture;
	private GetUrlRelationTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetUrlRelationTagFixture(slingRequest,pageManager,resourceResolver);
		tag = new GetUrlRelationTag();
	}

	@Test
	public void testInvalidRelationTypeNoSelectors() throws JspException {
		tag.setRelationType("invalid");
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testInvalidRelationTypeWithSelectors() throws JspException {
		fixture.setUpBCMSelectors();
		tag.setRelationType("invalid");
		runsSkipsBodyEvalsPage();	
	}
	
	@Test
	public void testGetProductCategory() throws JspException {
		fixture.setUpBCMSelectors();
		fixture.setUpProductCategory();
		tag.setRelationType("productCategory");
		runsSkipsBodyEvalsPage();
		Object pcr = pageContext.getAttribute("productCategoryRelation");
		assertThat(pcr,not(nullValue()));
		assertTrue(pcr.getClass().equals(ProductCategoryModel.class));
	}
	
	@Test
	public void testGetProductCategoryFromUri() throws JspException {
		fixture.setUpProductCategoryUri();
		fixture.setUpProductCategory();
		tag.setRelationType("productCategory");
		runsSkipsBodyEvalsPage();
		Object pcr = pageContext.getAttribute("productCategoryRelation");
		assertThat(pcr,not(nullValue()));
		assertTrue(pcr.getClass().equals(ProductCategoryModel.class));
	}
	
	@Test
	public void testGetProductCategoryFromUriNonePresent() throws JspException {
		fixture.setUpJRandomUri();
		fixture.setUpProductCategory();
		tag.setRelationType("productCategory");
		runsSkipsBodyEvalsPage();
		Object pcr = pageContext.getAttribute("productCategoryRelation");
		assertThat(pcr,is(nullValue()));
	}
	
	@Test
	public void testGetBrand() throws JspException {
		fixture.setUpBCMSelectors();
		fixture.setUpBrand();
		tag.setRelationType("brand");
		runsSkipsBodyEvalsPage();
		Object br = pageContext.getAttribute("brandRelation");
		assertThat(br,not(nullValue()));
		assertTrue(br.getClass().equals(BrandModel.class));
	}
	
	@Test
	public void testGetNonexistentBrand() throws JspException {
		fixture.setUpBCMSelectors();
		tag.setRelationType("brand");
		runsSkipsBodyEvalsPage();
		Object br = pageContext.getAttribute("brandRelation");
		assertThat(br,nullValue());
	}
	
	@Test
	public void testGetModel() throws JspException {
		fixture.setUpBCMSelectors();
		tag.setRelationType("model");
		runsSkipsBodyEvalsPage();
		Object mr = pageContext.getAttribute("modelRelation");
		assertThat(mr,not(nullValue()));
		assertTrue(mr.getClass().equals(String.class));
		assertTrue(fixture.getModel().equals(mr));
	}
	
	@Test
	public void testGetAllRelations() throws JspException, RepositoryException {
		fixture.setUpBCMSSelectors();
		fixture.setUpProductCategory();
		fixture.setUpBrand();
		fixture.setUpSymptom();
		
		runsSkipsBodyEvalsPage();
		
		productCategoryIsModel();
		brandIsModel();
		modelIsString();
		symptomIsModel();
	}
	
	@Test
	public void testGetAllTwoRelations() throws JspException, RepositoryException {
		fixture.setUpBCSelectors();
		fixture.setUpProductCategory();
		fixture.setUpBrand();
		fixture.setUpSymptom();
		
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testGetAllWithoutSymptom() throws JspException {
		fixture.setUpBCMSelectors();
		fixture.setUpProductCategory();
		fixture.setUpBrand();
		
		runsSkipsBodyEvalsPage();
		
		productCategoryIsModel();
		brandIsModel();
		modelIsString();
	}
	
	@Test
	public void testGetOnlySymptom() throws JspException, RepositoryException {
		fixture.setUpSSelector();
		fixture.setUpSymptom();
		
		runsSkipsBodyEvalsPage();
		
		symptomIsModel();
	}
	
	@Test
	public void testGetOnlySymptomSpecifically() throws JspException, RepositoryException {
		fixture.setUpSSelector();
		fixture.setUpSymptom();
		tag.setRelationType("symptom");
		
		runsSkipsBodyEvalsPage();
		
		symptomIsModel();
	}
	
	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(), is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(), is(TagSupport.EVAL_PAGE));
	}

	private void productCategoryIsModel() {
		Object pcr = pageContext.getAttribute("productCategoryRelation");
		assertThat(pcr,not(nullValue()));
		assertTrue(pcr.getClass().equals(ProductCategoryModel.class));
	}
	
	private void brandIsModel() {
		Object br = pageContext.getAttribute("brandRelation");
		assertThat(br,not(nullValue()));
		assertTrue(br.getClass().equals(BrandModel.class));
	}
	
	private void modelIsString() {
		Object mr = pageContext.getAttribute("modelRelation");
		assertThat(mr,not(nullValue()));
		assertTrue(mr.getClass().equals(String.class));
		assertTrue(fixture.getModel().equals(mr));
	}
	
	private void symptomIsModel() {
		Object sr = pageContext.getAttribute("symptomRelation");
		assertThat(sr,not(nullValue()));
		assertTrue(sr.getClass().equals(SymptomModel.class));
	}
}
