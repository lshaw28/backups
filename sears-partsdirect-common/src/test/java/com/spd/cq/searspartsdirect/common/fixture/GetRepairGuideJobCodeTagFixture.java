package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;

public class GetRepairGuideJobCodeTagFixture {

	private PageContext pageContext;
	private ResourceResolver resourceResolver;
	private Page currentPage;

	public GetRepairGuideJobCodeTagFixture(PageContext pageContext, ResourceResolver resourceResolver, Page currentPage) {
		this.pageContext = pageContext;
		this.resourceResolver = resourceResolver;
		this.currentPage = currentPage;
	}

	public void setup() {
		when(currentPage.getPath()).thenReturn("somePath");
		ValueMap props = mockRepairGuidePropertiesValueMap();
		when(currentPage.getProperties()).thenReturn(props);
		Resource mockResource = mock(Resource.class);
		when(resourceResolver.getResource("abc")).thenReturn(mockResource);
		when(resourceResolver.getResource("def")).thenReturn(mockResource);
		when(mockResource.adaptTo(Page.class)).thenReturn(currentPage);
	}

	public void setupNullCurrentPagePath() {
		when(currentPage.getPath()).thenReturn(null);
	}
	
	public void setupNullCurrentPage() {
		when(pageContext.findAttribute("currentPage")).thenReturn(null);
	}

	public void setupCurrentPageWithNullProperties() {
		when(currentPage.getPath()).thenReturn("somePath");
		when(currentPage.getProperties()).thenReturn(null);
	}

	private ValueMap mockRepairGuidePropertiesValueMap() {
		ValueMap valueMap = mock(ValueMap.class);
		when(valueMap.containsKey("pages")).thenReturn(true);
		when(valueMap.get("pages", String[].class)).thenReturn(new String[] {"abc", "def"});
		when(valueMap.get("id", String.class)).thenReturn("someId");
		return valueMap;
	}

	public void setupCurrentPageWithoutPagesKey() {
		ValueMap props = mock(ValueMap.class);
		when(currentPage.getProperties()).thenReturn(props);
		when(currentPage.getProperties().containsKey("pages")).thenReturn(false);
	}

	public void setupNullResource() {
		when(resourceResolver.getResource("abc")).thenReturn(null);
	}

	public void setupResource() {
		when(currentPage.getPath()).thenReturn("somePath");
		ValueMap props = mockRepairGuidePropertiesValueMap();
		when(currentPage.getProperties()).thenReturn(props);
		Resource resource = mock(Resource.class);
		when(resourceResolver.getResource("abc")).thenReturn(resource);
	}

	public void setupNullPage() {
		Resource resource = mock(Resource.class);
		when(resource.adaptTo(Page.class)).thenReturn(null);
	}

	public void setupException() {
		when(currentPage.getProperties()).thenThrow(new RuntimeException());
	}

}
