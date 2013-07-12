package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

public class CQBaseTagFixture {

	
	
	private HttpServletRequest request;

	public CQBaseTagFixture(HttpServletRequest request) {
		this.request = request;
	}

	public void setUpContextPath() {
		when(request.getContextPath()).thenReturn("");
	}

}
