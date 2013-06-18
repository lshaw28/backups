package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

public class DisplayImageTagFixture {

	public DisplayImageTagFixture(ResourceResolver resourceResolver) {
		Resource imgResource = mock(Resource.class);
		String testPath = getTestPath();
		when(resourceResolver.resolve(testPath)).thenReturn(imgResource);
	}
	
	public String getTestPath() {
		return "/test_path";
	}
}
