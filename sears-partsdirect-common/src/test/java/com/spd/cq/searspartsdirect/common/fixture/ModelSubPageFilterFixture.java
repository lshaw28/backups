package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

public class ModelSubPageFilterFixture {

	private FilterConfig config;
	private SlingHttpServletRequest request;
	private SlingHttpServletResponse response;
	private FilterChain filterChain;
	private RequestDispatcher dispatcher;
	
	private String resPath;
	private String ext;
	
	public ModelSubPageFilterFixture() {
		config = mock(FilterConfig.class);
		request = mock(SlingHttpServletRequest.class);
		response = mock(SlingHttpServletResponse.class);
		filterChain = mock(FilterChain.class);
	}
	
	public void setUpPath(String resPath) {
		this.resPath = resPath;
		ResourceResolver resourceResolver = mock(ResourceResolver.class);
		when(request.getResourceResolver()).thenReturn(resourceResolver);
		//RequestPathInfo rpi = request.getRequestPathInfo();
		RequestPathInfo rpi = mock(RequestPathInfo.class);
		when(request.getRequestPathInfo()).thenReturn(rpi);
        //String ext = rpi.getExtension(), resPath = rpi.getResourcePath();
		when(rpi.getResourcePath()).thenReturn(getResPath());
        //Resource res = resourceResolver.getResource(resPath);
		Resource res = mock(Resource.class);
		when(resourceResolver.getResource(getResPath())).thenReturn(res);
	}
	
	public void setUpDispatcher(String pathUnderRoot) {
//		 Pattern p = Pattern.compile("(/fake/url)(.*)");
//	        Matcher m = p.matcher(resPath);
//	        if (m.find() && m.groupCount() >= 2) {
//	        	//response.sendRedirect(m.group(2));
//	        	request.getRequestDispatcher(m.group(2)).forward(request, response);
		dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(pathUnderRoot)).thenReturn(dispatcher);
	}

	public String getResPath() {
		return resPath;
	}

	public String getExt() {
		return ext;
	}

	public FilterConfig getFilterConfig() {
		return config;
	}

	public ServletRequest getRequest() {
		return request;
	}

	public ServletResponse getResponse() {
		return response;
	}

	public FilterChain getFilterChain() {
		return filterChain;
	}
	
	public RequestDispatcher getDispatcher() {
		return dispatcher;
	}
}
