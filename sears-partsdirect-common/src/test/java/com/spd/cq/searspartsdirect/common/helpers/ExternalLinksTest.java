package com.spd.cq.searspartsdirect.common.helpers;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class ExternalLinksTest extends TestCase {
	
	private SlingHttpServletRequest slingRequest;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		slingRequest = mock(SlingHttpServletRequest.class);
		ResourceResolver resourceResolver = mock(ResourceResolver.class);
		when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		when(resourceResolver.map(Mockito.any(SlingHttpServletRequest.class),anyString())).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				return (String)arguments[1];
			}
		});
	}

	@Test
	public void testExternalLinks() {
		ExternalLinks generator = new ExternalLinks(slingRequest);
		assertThat(generator,is(instanceOf(ExternalLinks.class)));
	}

	@Test
	public void testGetExternalUrlForPage() {
		ExternalLinks generator = new ExternalLinks(slingRequest);
		String external = generator.getExternalUrlForPage("/content/searspartsdirect/en");
		assertThat(external,is(instanceOf(String.class)));
		assertThat(external,Matchers.startsWith(generator.getAddedPrefix()));
		assertThat(external,Matchers.startsWith(generator.getAddedSuffix()));
	}
	
	@Test
	public void testGetDecoratedExternalUrlForPage() {
		ExternalLinks generator = new ExternalLinks(slingRequest);
		String ourPrefix = "http://www.searspartsdirect.com";
		String ourSuffix = ".html";
		generator.setAddedPrefix(ourPrefix);
		generator.setAddedSuffix(ourSuffix);
		String external = generator.getExternalUrlForPage("/content/searspartsdirect/en");
		assertThat(external,is(instanceOf(String.class)));
		assertThat(external,Matchers.startsWith(generator.getAddedPrefix()));
		assertThat(external,Matchers.startsWith(ourPrefix));
		assertThat(external,Matchers.endsWith(generator.getAddedSuffix()));
		assertThat(external,Matchers.endsWith(ourSuffix));
		assertThat(external,is("http://www.searspartsdirect.com/content/searspartsdirect/en.html"));
	}
}
