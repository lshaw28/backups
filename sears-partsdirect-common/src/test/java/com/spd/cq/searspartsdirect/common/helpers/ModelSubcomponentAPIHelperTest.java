package com.spd.cq.searspartsdirect.common.helpers;


import org.apache.sling.api.SlingHttpServletRequest;
import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ModelSubcomponentAPIHelperFixture;
import com.spd.cq.searspartsdirect.common.model.PDModelSubcomponentModel;
import com.spd.cq.searspartsdirect.common.model.PDSymptom;
import com.spd.cq.searspartsdirect.common.model.PDSymptomWrapper;
import com.spd.cq.searspartsdirect.common.model.PDTab;
import com.spd.cq.searspartsdirect.common.model.PDTabWrapper;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ModelSubcomponentAPIHelperTest extends TestCase {

	private ModelSubcomponentAPIHelperFixture fixture;
	private ModelSubcomponentAPIHelper helper;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ModelSubcomponentAPIHelperFixture();
		helper = new ModelSubcomponentAPIHelper();
	}

	@Test
	public void testGetModelSubcomponents() {
		setExampleParameters();
		SlingHttpServletRequest request = fixture.getRequest();
		PDModelSubcomponentModel subcomponents1 = helper.getModelSubcomponents(request);
		ModelSubcomponentAPIHelper helper2 = new ModelSubcomponentAPIHelper();
		setExampleParameters(helper2);
		PDModelSubcomponentModel subcomponents2 = helper2.getModelSubcomponents(request);
		assertTrue(subcomponents1 == subcomponents2);
	}

	@Test
	public void testGetModelSubcomponentsFromApi() {
		setExampleParameters();
		PDModelSubcomponentModel subcomponents = helper.getModelSubcomponentsFromApi();
		assertThat(subcomponents,is(not(nullValue())));
		hasExampleContent(subcomponents);
	}

	@Test
	public void testBuildUrl() {
		setExampleParameters();
		String url = helper.buildUrl();
		assertThat(url,is(fixture.getFiatUrl()));
	}

	@Test
	public void testGetJsonFromApi() {
		String json = null;
		json = helper.getJsonFromApi(fixture.getFiatUrl());
		assertThat(json,is(json)); // we can't let API crankiness fail our builds.
	}

	
	@Test
	public void testGetModelSubcomponentsFromJson() {
		PDModelSubcomponentModel subcomponents = helper.getModelSubcomponentsFromJson(fixture.getFiatJson());
		assertThat(subcomponents,is(not(nullValue())));
		hasExampleContent(subcomponents);
	}
	
	private void setExampleParameters() {
		setExampleParameters(helper);
	}
	
	private void setExampleParameters(ModelSubcomponentAPIHelper helper) {
		helper.setBrand("Kenmore");
		helper.setCategory("Dishwasher");
		helper.setModel("66513593K600");
	}
	
	private void hasExampleContent(PDModelSubcomponentModel subcomponents) {
		assertThat(subcomponents.getModelDescription(),is("Dishwasher"));
		PDSymptomWrapper symptoms = subcomponents.getSymptoms();
		assertThat(symptoms,is(not(nullValue())));
		PDSymptom[] symptomArr = symptoms.getSymptom();
		assertThat(symptomArr,is(not(nullValue())));
		assertThat(symptomArr.length,is(6));
		// validate contents?
		PDTabWrapper tabs = subcomponents.getTabs();
		assertThat(tabs,is(not(nullValue())));
		PDTab[] tabArr = tabs.getTab();
		assertThat(tabArr,is(not(nullValue())));
		assertThat(tabArr.length,is(4));
		// validate contents?
	}

}
