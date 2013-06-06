package com.spd.cq.searspartsdirect.common.tags;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import junit.framework.TestCase;



public class MocksTag extends TestCase {
	protected HttpServletRequest request;
	protected PageContext pageContext;
	protected Node currentNode;
	
	@Override
	protected void setUp() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		pageContext = Mockito.mock(PageContext.class);
		currentNode = Mockito.mock(Node.class);
		when(pageContext.getRequest()).thenReturn(request);
		when(pageContext.findAttribute("currentNode")).thenReturn(currentNode);
		final Map<String, Object> contextMap = new HashMap<String, Object>();

		// mock for method persist
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

		// mock for method findByName
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
	}
}
