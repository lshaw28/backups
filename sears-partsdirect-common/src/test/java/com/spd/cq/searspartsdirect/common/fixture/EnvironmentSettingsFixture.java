package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.service.component.ComponentContext;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class EnvironmentSettingsFixture {

	private ComponentContext componentContext;

	public EnvironmentSettingsFixture() {
		componentContext = mock(ComponentContext.class);
	}

	@SuppressWarnings("unchecked")
	public void setUpStubSettings(EnvironmentSettings env) throws Exception {
		@SuppressWarnings("rawtypes")
		Dictionary ourHash = new Hashtable();
		ourHash.put(EnvironmentSettings.HANDLE_404_URL, EnvironmentSettings.HANDLE_404_URL);
		ourHash.put(EnvironmentSettings.SITEMAP_START_PATHS, EnvironmentSettings.SITEMAP_START_PATHS);
		ourHash.put(EnvironmentSettings.SITEMAP_STOP_PATHS, EnvironmentSettings.SITEMAP_STOP_PATHS);
		ourHash.put(EnvironmentSettings.EXTERNAL_ADDED_PREFIX, EnvironmentSettings.EXTERNAL_ADDED_PREFIX);
		ourHash.put(EnvironmentSettings.EXTERNAL_ADDED_SUFFIX, EnvironmentSettings.EXTERNAL_ADDED_SUFFIX);
		ourHash.put(EnvironmentSettings.PD_USERDATA_API, EnvironmentSettings.PD_USERDATA_API);
		ourHash.put(EnvironmentSettings.PD_URL, EnvironmentSettings.PD_URL);
		ourHash.put(EnvironmentSettings.PD_TOPPARTS_API, EnvironmentSettings.PD_TOPPARTS_API);
		ourHash.put(EnvironmentSettings.PD_MODELSUB_API, EnvironmentSettings.PD_MODELSUB_API);
		ourHash.put(EnvironmentSettings.PD_JOBCODE_PARTS_API, EnvironmentSettings.PD_JOBCODE_PARTS_API);
		ourHash.put(EnvironmentSettings.PD_API_ROOT, EnvironmentSettings.PD_API_ROOT);
		ourHash.put(EnvironmentSettings.LOCAL_HTTP_N_PORT, EnvironmentSettings.LOCAL_HTTP_N_PORT);
		ourHash.put(EnvironmentSettings.LOCAL_HTTPS_N_PORT, EnvironmentSettings.LOCAL_HTTPS_N_PORT);
		ourHash.put(EnvironmentSettings.PD_COMMERCIAL_URL, EnvironmentSettings.PD_COMMERCIAL_URL);
		when(componentContext.getProperties()).thenReturn(ourHash);
		env.externalActivateForTesting(componentContext);
	}

	@SuppressWarnings("unchecked")
	public void setUpRealDefaults(EnvironmentSettings env) throws Exception {
		@SuppressWarnings("rawtypes")
		Dictionary ourHash = new Hashtable();
		ourHash.put(EnvironmentSettings.HANDLE_404_URL, "/content/searspartsdirect/en/404.html");
		ourHash.put(EnvironmentSettings.SITEMAP_START_PATHS, "/content/searspartsdirect/en");
		ourHash.put(EnvironmentSettings.SITEMAP_STOP_PATHS, Constants.EMPTY);
		ourHash.put(EnvironmentSettings.EXTERNAL_ADDED_PREFIX, "http://www.searspartsdirect.com");
		ourHash.put(EnvironmentSettings.EXTERNAL_ADDED_SUFFIX, ".html");
		ourHash.put(EnvironmentSettings.PD_USERDATA_API, "https://partsapivip.qa.ch3.s.com/pd-services/v1/userservice/retrive?username=");
		ourHash.put(EnvironmentSettings.PD_URL, "http://www.searspartsdirect.com");
		ourHash.put(EnvironmentSettings.PD_TOPPARTS_API, "http://partsapivip.qa.ch3.s.com/pd-services/intra/v1/modelTop/topParts");
		ourHash.put(EnvironmentSettings.PD_MODELSUB_API, "http://partsapivip.qa.ch3.s.com/pd-services/intra/v1/commonSymptoms/modelInfo");
		ourHash.put(EnvironmentSettings.PD_JOBCODE_PARTS_API, "http://partsapivip.qa.ch3.s.com/pd-services/intra/v1/commonSymptoms/jobcodelist/partlist");
		ourHash.put(EnvironmentSettings.PD_API_ROOT, "http://partsapivip.qa.ch3.s.com/pd-services/v1/");
		ourHash.put(EnvironmentSettings.LOCAL_HTTP_N_PORT, "localhost:4502");
		ourHash.put(EnvironmentSettings.LOCAL_HTTPS_N_PORT, "localhost:5433");
		ourHash.put(EnvironmentSettings.PD_COMMERCIAL_URL, "searspartsdirect.commercial.url");
		when(componentContext.getProperties()).thenReturn(ourHash);
		env.externalActivateForTesting(componentContext);
	}

	public ComponentContext getComponentContext() {
		return componentContext;
	}
}
