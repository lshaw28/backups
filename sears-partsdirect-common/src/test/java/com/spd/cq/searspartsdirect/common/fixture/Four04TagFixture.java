package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.auth.Authenticator;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;

public class Four04TagFixture {
	
	private HttpServletRequest request;
	private SlingBindings bindings;
	
	public Four04TagFixture(PageContext pageContext, SlingBindings bindings) {
		this.bindings = bindings;
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(pageContext.getResponse()).thenReturn(response);
		request = mock(HttpServletRequest.class);
		when(pageContext.getRequest()).thenReturn(request);
	}
	
	public void setupToRequireAuthorization() {
		when(request.getRequestURI()).thenReturn("/system/console/bundles");
		when(request.getHeader("User-Agent")).thenReturn("MMMozillaaaa");
		SlingScriptHelper sling = mock(SlingScriptHelper.class);
		when(bindings.getSling()).thenReturn(sling);
		Authenticator authenticator = mock(Authenticator.class);
		when(sling.getService(Authenticator.class)).thenReturn(authenticator);
	}
	
	public void setupToDoUsualRedirect() {
		when(request.getRequestURI()).thenReturn("/akjsdfsadfhsjdhfkjs");
	}
}
