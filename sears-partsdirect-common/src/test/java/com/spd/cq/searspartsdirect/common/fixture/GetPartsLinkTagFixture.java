package com.spd.cq.searspartsdirect.common.fixture;

import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;

public class GetPartsLinkTagFixture {
	
	public GetPartsLinkTagFixture(SlingHttpServletRequest slingRequest, PageContext pageContext) throws Exception {
		new EnvironmentSettingsFixture().setUpRealDefaults(new EnvironmentSettings());
	}

	public String getBrandName() {
		return "Kenmore";
	}

	public String getCategoryName() {
		return "Dishwasher";
	}

	public String getModelNumber() {
		return "66513593K600";
	}

}
