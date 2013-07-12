package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ExternalLinksFixture {

	private SlingHttpServletRequest slingRequest;
	private ResourceResolver resourceResolver;

	public ExternalLinksFixture() {
		slingRequest = mock(SlingHttpServletRequest.class);
		resourceResolver = mock(ResourceResolver.class);
		when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
	}
	
	public void setUpIdentityResolver() {
		when(resourceResolver.map(Mockito.any(SlingHttpServletRequest.class),anyString())).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				return (String)arguments[1];
			}
		});
	}
	
	public void setUpHelpfulResolver() {
		when(resourceResolver.map(Mockito.any(SlingHttpServletRequest.class),anyString())).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				return getTestPrefix()+(String)arguments[1]+getTestSuffix();
			}
		});
	}
	
	public SlingHttpServletRequest getSlingRequest() {
		return slingRequest;
	}

	public String getTestPrefix() {
		return "http://www.searspartsdirect.com";
	}

	public String getTestSuffix() {
		return ".html";
	}

	public String getTestPath() {
		return "/content/searspartsdirect/en";
	}

	public String getFinalUrl() {
		return "http://www.searspartsdirect.com/content/searspartsdirect/en.html";
	}
}
