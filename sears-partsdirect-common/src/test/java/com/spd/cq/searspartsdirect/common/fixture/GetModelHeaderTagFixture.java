package com.spd.cq.searspartsdirect.common.fixture;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetModelHeaderTagFixture {

	public GetModelHeaderTagFixture() throws Exception {
		new EnvironmentSettingsFixture().setUpRealDefaults(new EnvironmentSettings());
	}
	
	public String getModel() {
		return "66513593K600";
	}

	public ProductCategoryModel getProductCategory() {
		ProductCategoryModel testCategory = mock(ProductCategoryModel.class);
		when(testCategory.getTitle()).thenReturn("Dishwasher");
		return testCategory;
	}

	public BrandModel getBrand() {
		BrandModel testBrand = mock(BrandModel.class);
		when(testBrand.getTitle()).thenReturn("Kenmore");
		return testBrand;
	}
}
