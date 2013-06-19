package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.auth.Authenticator;
import org.apache.sling.api.auth.NoAuthenticationHandlerException;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.osgi.service.component.ComponentContext;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;

public class Four04TagFixture {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private SlingScriptHelper sling;
	private Authenticator authenticator;
	
	public Four04TagFixture(PageContext pageContext, SlingBindings bindings) {
		response = mock(HttpServletResponse.class);
		when(pageContext.getResponse()).thenReturn(response);
		request = mock(HttpServletRequest.class);
		when(pageContext.getRequest()).thenReturn(request);
		sling = mock(SlingScriptHelper.class);
		when(bindings.getSling()).thenReturn(sling);
		authenticator = mock(Authenticator.class);
		when(sling.getService(Authenticator.class)).thenReturn(authenticator);
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public Authenticator getAuthenticator() {
		return authenticator;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void configureTestEnvironment() throws Exception {
		ComponentContext osgiContext = mock(ComponentContext.class);
		Dictionary d = new Hashtable();
		d.put(EnvironmentSettings.HANDLE_404_URL, "/404.html");
		when(osgiContext.getProperties()).thenReturn(d);
		EnvironmentSettings env = new EnvironmentSettings();
		env.externalActivateForTesting(osgiContext);
	}
	
	public void setup404ErrorCode() {
		//Integer scObject = (Integer) jspRequest.getAttribute("javax.servlet.error.status_code");
		when(request.getAttribute("javax.servlet.error.status_code")).thenReturn(404);
	}
	
	public void setupToRequireAuthorization() {
		when(request.getRequestURI()).thenReturn("/system/console/bundles");
		when(request.getHeader("User-Agent")).thenReturn("MMMozillaaaa");
	}
	
	public void setupNotBrowser() {
		when(request.getHeader("User-Agent")).thenReturn("GGGodzillaaa");
	}
	
	public void setupToDoUsualRedirect() {
		when(request.getRequestURI()).thenReturn("/akjsdfsadfhsjdhfkjs");
	}

	public void removeAuthenticator() {
		when(sling.getService(Authenticator.class)).thenReturn(null);
	}

	public void irritateAuthenticator() {
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				throw new NoAuthenticationHandlerException();
			}
		}).when(authenticator).login(request, response);
	}
	
	public void setupAlreadyLoggedIn() {
		when(request.getAuthType()).thenReturn("Divine fiat");
		when(request.getRemoteUser()).thenReturn("ESR");
	}

	public void setupRequested404Page() {
		when(request.getRequestURI()).thenReturn(EnvironmentSettings.get404HandlerURL());
	}
}
