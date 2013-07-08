package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestPathInfo;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class DisplayModelHeaderTagFixture {
	
	private String[] selectors;
	private JspWriter out;

	public DisplayModelHeaderTagFixture(SlingHttpServletRequest slingRequest, PageContext pageContext) {
		out = mock(JspWriter.class);
		when(pageContext.getOut()).thenReturn(out);
		RequestPathInfo rpi = mock(RequestPathInfo.class);
		when(slingRequest.getRequestPathInfo()).thenReturn(rpi);
		when(rpi.getSelectors()).thenAnswer(new Answer<String[]>() {
			public String[] answer(InvocationOnMock invocation)
					throws Throwable {
				return selectors;
			}
		});
	}
	
	public void setUpWithSelectors() {
		selectors = new String[]{"brand","category","model"};
	}
	
	public void setUpWithoutSelectors() {
		selectors = new String[0];
	}
	
	public void setUpExceptions() throws IOException {
		doThrow(new IOException()).when(out).flush();
	}
}
