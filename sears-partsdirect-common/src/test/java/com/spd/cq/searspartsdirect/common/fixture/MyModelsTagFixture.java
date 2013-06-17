package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class MyModelsTagFixture {

	private HttpServletRequest request;
	private Cookie ourCookie;

	private static final String cookieName = "myProfileModels";
	private static final String cookieValue = "REPLACE WITH SOMETHING MEANINGFUL TO API";
	
	public MyModelsTagFixture(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getValidCookieName() {
		return cookieName;
	}
	
	private String getValidCookieValue() {
		return cookieValue;
	}
	
	public void setUpNoCookies() {
		// This method is empty, since to have no cookies, we do not set up any cookies.
	}

	public void setUpWithoutOurCookie() {
		//Cookie[] cookies = request.getCookies();
		Cookie[] cookies = {};
		when(request.getCookies()).thenReturn(cookies);
	}

	public void setUpWithOurCookieInvalid() {
		//Cookie myModelsCookie= PartsDirectCookieHelper.getCookieInfo(cookies, "myProfileModels");
		ourCookie = mock(Cookie.class);
		//if (cookies[i].getName().equals(cookieName)) {
		when(ourCookie.getName()).thenReturn(getValidCookieName());
		Cookie[] cookies = {ourCookie};
		when(request.getCookies()).thenReturn(cookies);
	}

	public void setUpWithOurCookieValid() {
		setUpWithOurCookieInvalid();
		when(ourCookie.getValue()).thenReturn(getValidCookieValue());
	}
	
}
