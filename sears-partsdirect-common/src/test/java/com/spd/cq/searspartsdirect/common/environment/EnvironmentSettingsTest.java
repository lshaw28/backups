package com.spd.cq.searspartsdirect.common.environment;


import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;
import org.osgi.service.component.ComponentContext;

import com.spd.cq.searspartsdirect.common.fixture.EnvironmentSettingsFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class EnvironmentSettingsTest extends TestCase {

	private EnvironmentSettingsFixture fixture;

	@SuppressWarnings("unchecked")
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new EnvironmentSettingsFixture();
	}
	
	@Test
	public void testAllMethods() throws Exception {
		EnvironmentSettings hook = new EnvironmentSettings();
		assertThat(EnvironmentSettings.get404HandlerURL(),is(instanceOf(String.class)));
		assertThat(EnvironmentSettings.getSitemapStartPaths(),is(instanceOf(String.class)));
		assertThat(EnvironmentSettings.getSitemapStopPaths(),is(instanceOf(String.class)));
		assertThat(EnvironmentSettings.getExternalAddedPrefix(),is(instanceOf(String.class)));
		assertThat(EnvironmentSettings.getExternalAddedSuffix(),is(instanceOf(String.class)));
		assertThat(EnvironmentSettings.getPDUserDataApiUrl(), is(instanceOf(String.class)));
		assertThat(EnvironmentSettings.getPDJobCodeApiUrl(), is(instanceOf(String.class)));
		fixture.setUpStubSettings(hook);
		assertThat(EnvironmentSettings.get404HandlerURL(),is(EnvironmentSettings.HANDLE_404_URL));
		assertThat(EnvironmentSettings.getSitemapStartPaths(),is(EnvironmentSettings.SITEMAP_START_PATHS));
		assertThat(EnvironmentSettings.getSitemapStopPaths(),is(EnvironmentSettings.SITEMAP_STOP_PATHS));
		assertThat(EnvironmentSettings.getExternalAddedPrefix(),is(EnvironmentSettings.EXTERNAL_ADDED_PREFIX));
		assertThat(EnvironmentSettings.getExternalAddedSuffix(),is(EnvironmentSettings.EXTERNAL_ADDED_SUFFIX));
		assertThat(EnvironmentSettings.getPDUserDataApiUrl(), is (EnvironmentSettings.PD_USERDATA_API));
		assertThat(EnvironmentSettings.getPDUrl(), is (EnvironmentSettings.PD_URL));
		assertThat(EnvironmentSettings.getPDJobCodeApiUrl(), is (EnvironmentSettings.PD_JOBCODE_API));
		try {
			hook.deactivate(fixture.getComponentContext());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
