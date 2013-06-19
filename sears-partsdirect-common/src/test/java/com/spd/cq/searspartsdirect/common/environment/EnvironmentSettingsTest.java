package com.spd.cq.searspartsdirect.common.environment;


import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;
import org.osgi.service.component.ComponentContext;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class EnvironmentSettingsTest extends TestCase {

	ComponentContext componentContext;
	
	@SuppressWarnings("unchecked")
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		componentContext = mock(ComponentContext.class);
		@SuppressWarnings("rawtypes")
		Dictionary ourHash = new Hashtable();
		ourHash.put(EnvironmentSettings.HANDLE_404_URL, EnvironmentSettings.HANDLE_404_URL);
		ourHash.put(EnvironmentSettings.SITEMAP_START_PATHS, EnvironmentSettings.SITEMAP_START_PATHS);
		ourHash.put(EnvironmentSettings.SITEMAP_STOP_PATHS, EnvironmentSettings.SITEMAP_STOP_PATHS);
		ourHash.put(EnvironmentSettings.EXTERNAL_ADDED_PREFIX, EnvironmentSettings.EXTERNAL_ADDED_PREFIX);
		ourHash.put(EnvironmentSettings.EXTERNAL_ADDED_SUFFIX, EnvironmentSettings.EXTERNAL_ADDED_SUFFIX);
		when(componentContext.getProperties()).thenReturn(ourHash);
	}
	
	@Test
	public void testAllMethods() {
		EnvironmentSettings hook = new EnvironmentSettings();
		assertThat(EnvironmentSettings.get404HandlerURL(),is(Constants.EMPTY));
		assertThat(EnvironmentSettings.getSitemapStartPaths(),is(Constants.EMPTY));
		assertThat(EnvironmentSettings.getSitemapStopPaths(),is(Constants.EMPTY));
		assertThat(EnvironmentSettings.getExternalAddedPrefix(),is(Constants.EMPTY));
		assertThat(EnvironmentSettings.getExternalAddedSuffix(),is(Constants.EMPTY));
		try {
			hook.activate(componentContext);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		assertThat(EnvironmentSettings.get404HandlerURL(),is(EnvironmentSettings.HANDLE_404_URL));
		assertThat(EnvironmentSettings.getSitemapStartPaths(),is(EnvironmentSettings.SITEMAP_START_PATHS));
		assertThat(EnvironmentSettings.getSitemapStopPaths(),is(EnvironmentSettings.SITEMAP_STOP_PATHS));
		assertThat(EnvironmentSettings.getExternalAddedPrefix(),is(EnvironmentSettings.EXTERNAL_ADDED_PREFIX));
		assertThat(EnvironmentSettings.getExternalAddedSuffix(),is(EnvironmentSettings.EXTERNAL_ADDED_SUFFIX));
		try {
			hook.deactivate(componentContext);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
