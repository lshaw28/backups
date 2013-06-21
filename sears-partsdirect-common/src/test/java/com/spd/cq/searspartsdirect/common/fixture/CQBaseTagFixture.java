package com.spd.cq.searspartsdirect.common.fixture;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;

public class CQBaseTagFixture {

	
	
	private HttpServletRequest request;

	public CQBaseTagFixture(HttpServletRequest request) {
		this.request = request;
	}

	public void setUpContextPath() {
		when(request.getContextPath()).thenReturn("");
	}

}
