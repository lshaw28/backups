package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.sling.api.resource.Resource;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;

public class UniqueIDTagFixture {

	private Resource resource;
	
	public UniqueIDTagFixture(Page currentPage, Resource resource) {
		Resource contentResource = mock(Resource.class);
		when(currentPage.getContentResource()).thenReturn(contentResource);
		when(contentResource.getPath()).thenReturn(getPagePath()+"/"+JcrConstants.JCR_CONTENT);
		when(currentPage.getPath()).thenReturn(getPagePath());
		this.resource = resource;
	}

	public void setUpPathIntoContent() {
		when(resource.getPath()).thenReturn(getTestPathIntoContent());
	}
	
	public void setUpPathAboveContent() {
		when(resource.getPath()).thenReturn(getTestPathAboveContent());
	}
	
	public String getPagePath() {
		return "/content/searspartsdirect/en";
	}
	
	public String getTestPathIntoContent() {
		return "/content/searspartsdirect/en/jcr:content/image";
	}
	
	public String getTestIdentifierInContent() {
		return "image"; 
	}
	
	public String getTestPathAboveContent() {
		return "/content/searspartsdirect/en.html";
	}
	
	public String getTestIdentifierAboveContent() {
		return "html";
	}

}
