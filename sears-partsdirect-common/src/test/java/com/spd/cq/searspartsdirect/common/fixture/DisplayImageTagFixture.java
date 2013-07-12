package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.wcm.api.designer.Design;

public class DisplayImageTagFixture {

	private ResourceResolver resourceResolver;
	private Resource imgResource;
	private PageContext pageContext;

	public DisplayImageTagFixture(ResourceResolver resourceResolver, PageContext pageContext) {
		imgResource = mock(Resource.class);
		this.resourceResolver = resourceResolver;
		this.pageContext = pageContext;
	}
	
	public void setUpForAbsolutePath() {
		when(resourceResolver.resolve(getAbsoluteTestPath())).thenReturn(imgResource);
	}
	
	public String getAbsoluteTestPath() {
		return "/test_path";
	}

	public void setUpForRelativePath() {
		
	}

	public String getRelativeTestPath() {
		return "test_path";
	}

	public void setUpSameDesign() {
		Design sameDesign = mock(Design.class);
		when(pageContext.findAttribute("currentDesign")).thenReturn(sameDesign);
		when(pageContext.findAttribute("resourceDesign")).thenReturn(sameDesign);
	}
}
