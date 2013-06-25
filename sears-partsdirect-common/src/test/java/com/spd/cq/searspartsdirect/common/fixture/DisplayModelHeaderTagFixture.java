package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestPathInfo;

public class DisplayModelHeaderTagFixture {
	
	private String[] selectors;
	private JspWriter out;

	public DisplayModelHeaderTagFixture(SlingHttpServletRequest slingRequest, PageContext pageContext) {
		out = mock(JspWriter.class);
		when(pageContext.getOut()).thenReturn(out);
		RequestPathInfo rpi = mock(RequestPathInfo.class);
		when(slingRequest.getRequestPathInfo()).thenReturn(rpi);
		when(rpi.getSelectors()).thenReturn(selectors);
	}
	
	public void setUpWithSelectors() {
		
	}
	
	public void setUpWithoutSelectors() {
		
	}
	
	public void setUpExceptions() {
		//when(out.flush()).thenThrow(new IOException());
	}
}
