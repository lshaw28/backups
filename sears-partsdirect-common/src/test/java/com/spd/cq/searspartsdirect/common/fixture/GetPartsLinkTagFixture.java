package com.spd.cq.searspartsdirect.common.fixture;

public class GetPartsLinkTagFixture {

	ModelSubcomponentAPIHelperFixture modelSubFixture;
	
	public GetPartsLinkTagFixture() throws Exception {
		modelSubFixture = new ModelSubcomponentAPIHelperFixture();
	}
	
	public String getBrandName() {
		return modelSubFixture.getBrandName();
	}

	public String getCategoryName() {
		return modelSubFixture.getCategoryName();
	}

	public String getModelNumber() {
		return modelSubFixture.getModelNumber();
	}

}
