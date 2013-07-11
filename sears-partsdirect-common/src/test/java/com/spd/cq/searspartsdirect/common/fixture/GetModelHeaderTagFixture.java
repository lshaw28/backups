package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestPathInfo;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;

public class GetModelHeaderTagFixture {

	public GetModelHeaderTagFixture(SlingHttpServletRequest slingRequest, PageContext pageContext) throws Exception {
		new EnvironmentSettingsFixture().setUpRealDefaults(new EnvironmentSettings());
	}
}
