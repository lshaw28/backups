package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;

public class AssetTypeFixture {

	private Page page;
	private ValueMap properties;
	private ResourceResolver rr;
	private Resource pageResource;
	
	public AssetTypeFixture() {
		page = mock(Page.class);
		when(page.getPath()).thenReturn(getPagePath());
		properties = mock(ValueMap.class);
		when(page.getProperties()).thenReturn(properties);
		rr = mock(ResourceResolver.class);
		pageResource = mock(Resource.class);
		when(pageResource.adaptTo(Page.class)).thenReturn(page);
		when(rr.getResource(getPagePath())).thenReturn(pageResource);
	}
	
	public Page getPage() {
		return page;
	}
	
	public ValueMap getProperties() {
		return properties;
	}
	
	public ResourceResolver getResourceResolver() {
		return rr;
	}
	
	public String getPagePath() {
		return "/test";
	}
}
