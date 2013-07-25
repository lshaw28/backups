package com.spd.cq.searspartsdirect.common.fixture;

import org.apache.sling.api.SlingHttpServletRequest;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.ModelSubcomponentAPIHelper;
import com.spd.cq.searspartsdirect.common.model.PDModelSubcomponentModel;
import com.spd.cq.searspartsdirect.common.model.PDTab;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetModelHeaderTagFixture {

	private SlingHttpServletRequest slingRequest;

	public GetModelHeaderTagFixture(SlingHttpServletRequest slingRequest) throws Exception {
		this.slingRequest = slingRequest;
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

	public void setUpFakeCompleteResult() {
		PDModelSubcomponentModel fakeResult = setUpFakeResult();
		PDTab[] tabsArr = new PDTab[2];
		when(fakeResult.getTabsArr()).thenReturn(tabsArr);
		PDTab first = mock(PDTab.class);
		when(first.getTabsName()).thenReturn("First");
		when(first.getTabsLink()).thenReturn("tab:first");
		tabsArr[0] = first;
		PDTab last = mock(PDTab.class);
		when(last.getTabsName()).thenReturn("Last");
		when(last.getTabsLink()).thenReturn("tab:last");
		tabsArr[1] = last;
	}
	
	public void setUpFakeEmptyResult() {
		PDModelSubcomponentModel fakeResult = setUpFakeResult();
		PDTab[] tabsArr = new PDTab[0];
		when(fakeResult.getTabsArr()).thenReturn(tabsArr);
	}
	
	private PDModelSubcomponentModel setUpFakeResult() {
		PDModelSubcomponentModel fakeResult = mock(PDModelSubcomponentModel.class);
		when(slingRequest.getAttribute(ModelSubcomponentAPIHelper.MODELSUB_REQATTR)).thenReturn(fakeResult);
		return fakeResult;
	}
}
