package com.spd.cq.searspartsdirect.common.fixture;

import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;

public class GetModelHeaderTagFixture {

	public GetModelHeaderTagFixture(SlingHttpServletRequest slingRequest, PageContext pageContext) throws Exception {
		new EnvironmentSettingsFixture().setUpRealDefaults(new EnvironmentSettings());
	}
}
