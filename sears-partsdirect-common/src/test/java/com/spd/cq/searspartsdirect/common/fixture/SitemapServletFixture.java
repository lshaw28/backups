package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.osgi.service.component.ComponentContext;

import com.day.cq.replication.ReplicationStatus;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.NavigablePageFilter;

public class SitemapServletFixture {
	private SlingHttpServletRequest slingRequest;
	private SlingHttpServletResponse slingResponse;
	private StringWriter snoop;
	
	public SitemapServletFixture() throws Exception {
		slingRequest = mock(SlingHttpServletRequest.class);
		slingResponse = mock(SlingHttpServletResponse.class);
		
		snoop = new StringWriter();
		PrintWriter snoopedOut = new PrintWriter(snoop);
		when(slingResponse.getWriter()).thenReturn(snoopedOut);
		
		ResourceResolver resourceResolver = mock(ResourceResolver.class);
		when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		
		configureTestEnvironment();
		
		ReplicationStatus replicationStatus = mock(ReplicationStatus.class);
		when(replicationStatus.isActivated()).thenReturn(true);
		
		Resource foo = mock(Resource.class);
		when(resourceResolver.getResource("/foo")).thenReturn(foo);
		when(resourceResolver.map(slingRequest,"/foo")).thenReturn("/foo");
		Page fooPage = mock(Page.class);
		when(foo.adaptTo(Page.class)).thenReturn(fooPage);
		when(fooPage.getPath()).thenReturn("/foo");
		when(fooPage.getLastModified()).thenReturn(Calendar.getInstance());
		when(fooPage.adaptTo(ReplicationStatus.class)).thenReturn(replicationStatus);
		
		final List<Page> fooChildren = new ArrayList<Page>();
		Page barPage = mock(Page.class);
		when(barPage.getPath()).thenReturn("/foo/bar");
		when(resourceResolver.map(slingRequest,"/foo/bar")).thenReturn("/foo/bar");
		// we could reduce the above and the similar to above above above to an Answer - but why..
		when(barPage.getLastModified()).thenReturn(Calendar.getInstance());
		when(barPage.isValid()).thenReturn(true);
		when(barPage.adaptTo(ReplicationStatus.class)).thenReturn(replicationStatus);
		
		fooChildren.add(barPage);
		when(fooPage.listChildren(any(NavigablePageFilter.class))).thenAnswer(new Answer<Iterator<Page>>() {
			public Iterator<Page> answer(InvocationOnMock invocation) {
				return fooChildren.iterator();
			}
		});
		
		final List<Page> barChildren = new ArrayList<Page>();
		Page bazPage = mock(Page.class);
		when(bazPage.getPath()).thenReturn("/foo/bar/baz");
		when(resourceResolver.map(slingRequest,"/foo/bar/baz")).thenReturn("/foo/bar/baz");
		
		barChildren.add(bazPage);
		when(barPage.listChildren(any(NavigablePageFilter.class))).thenAnswer(new Answer<Iterator<Page>>() {
			public Iterator<Page> answer(InvocationOnMock invocation) {
				return barChildren.iterator();
			}
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void configureTestEnvironment() throws Exception {
		ComponentContext osgiContext = mock(ComponentContext.class);
		Dictionary d = new Hashtable();
		d.put(EnvironmentSettings.SITEMAP_START_PATHS, "/foo /dog");
		d.put(EnvironmentSettings.SITEMAP_STOP_PATHS, "/foo/bar/baz");
		d.put(EnvironmentSettings.EXTERNAL_ADDED_PREFIX, "http://www.searspartsdirect.com");
		d.put(EnvironmentSettings.EXTERNAL_ADDED_SUFFIX, ".html");
		when(osgiContext.getProperties()).thenReturn(d);
		EnvironmentSettings env = new EnvironmentSettings();
		env.externalActivateForTesting(osgiContext);
	}
	
	public void breakTheWriter() throws IOException {
		when(slingResponse.getWriter()).thenThrow(new IOException("Cannot commit after response has been forwarded"));
	}
	
	public SlingHttpServletRequest getRequest() {
		return slingRequest;
	}
	
	public SlingHttpServletResponse getResponse() {
		return slingResponse;
	}
	
	public StringWriter getSnoop() {
		return snoop;
	}
}
