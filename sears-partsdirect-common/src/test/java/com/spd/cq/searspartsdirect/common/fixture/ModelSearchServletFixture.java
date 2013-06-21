package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;

public class ModelSearchServletFixture {

	private SlingHttpServletRequest request;
	private SlingHttpServletResponse response;
	
	public ModelSearchServletFixture() {
		request = mock(SlingHttpServletRequest.class);
		response = mock(SlingHttpServletResponse.class);
		ResourceResolver resourceResolver = mock(ResourceResolver.class);
		when(request.getResourceResolver()).thenReturn(resourceResolver);
		String query = "query";
		when(request.getParameter("query")).thenReturn(query);
		PrintWriter writer = mock(PrintWriter.class);
		try {
			when(response.getWriter()).thenReturn(writer);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setUpFound() {
		when(request.getParameter("found")).thenReturn("true");
	}
	
	public void setUpNotFound() {
		when(request.getParameter("found")).thenReturn("false");
	}
	
	public SlingHttpServletRequest getRequest() {
		return request;
	}

	public SlingHttpServletResponse getResponse() {
		return response;
	}
}
