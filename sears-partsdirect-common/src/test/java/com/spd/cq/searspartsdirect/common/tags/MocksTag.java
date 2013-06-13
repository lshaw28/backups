package com.spd.cq.searspartsdirect.common.tags;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.scripting.SlingBindings;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import junit.framework.TestCase;

/**
 * Provides common objects needed when mocking for testing CQ custom tags
 * @author bzethmayr
 *
 */
public class MocksTag extends TestCase {
	protected HttpServletRequest request;
	protected PageContext pageContext;
	protected Node currentNode;
	protected ResourceResolver resourceResolver;
	protected Page currentPage;
	protected SlingHttpServletRequest slingRequest;
	protected PageManager pageManager;
	protected SlingBindings bindings;
	
	@Override
	protected void setUp() throws Exception {
		pageContext = mock(PageContext.class);
		final Map<String, Object> contextMap = new HashMap<String, Object>();
		// mock for method setAttribute
		doAnswer(new Answer<Void>() {
		    //@Override
		    public Void answer(InvocationOnMock invocation) throws Throwable {
		        Object[] arguments = invocation.getArguments();
		        if (arguments != null && arguments.length == 2 && arguments[0] != null) {
		            contextMap.put((String)arguments[0], arguments[1]);
		        }
		        return null;
		    }
		}).when(pageContext).setAttribute(anyString(),anyObject());

		// mock for method getAttribute
		when(pageContext.getAttribute(anyString())).thenAnswer(new Answer<Object>() {
		    //@Override
		    public Object answer(InvocationOnMock invocation) throws Throwable {
		        Object[] arguments = invocation.getArguments();
		        if (arguments != null && arguments.length == 1 && arguments[0] != null) {
		            String key = (String) arguments[0];
		            if (contextMap.containsKey(key)) {
		                return contextMap.get(key);
		            }
		        }
		        return null;
		    }
		});
		request = mock(HttpServletRequest.class);
		when(pageContext.getRequest()).thenReturn(request);
		currentNode = mock(Node.class);
		when(pageContext.findAttribute("currentNode")).thenReturn(currentNode);
		resourceResolver = mock(ResourceResolver.class);
		when(pageContext.findAttribute("resourceResolver")).thenReturn(resourceResolver);
		currentPage = mock(Page.class);
		when(pageContext.findAttribute("currentPage")).thenReturn(currentPage);
		slingRequest = mock(SlingHttpServletRequest.class);
		when(pageContext.findAttribute("slingRequest")).thenReturn(slingRequest);
		pageManager = mock(PageManager.class);
		when(pageContext.findAttribute("pageManager")).thenReturn(pageManager);
		bindings = mock(SlingBindings.class);
		when(pageContext.findAttribute("bindings")).thenReturn(bindings);
	}
}
