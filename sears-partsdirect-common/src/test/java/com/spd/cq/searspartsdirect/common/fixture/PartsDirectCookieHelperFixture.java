package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.Cookie;

public class PartsDirectCookieHelperFixture {

	private final static String badCookie = "badcookie";
	
	public Cookie[] getTestCookies() {
		Cookie cookie1 = mock(Cookie.class);
		when(cookie1.getName()).thenReturn(badCookie);
		Cookie cookie2 = mock(Cookie.class);
		when(cookie2.getName()).thenReturn(badCookie);
		Cookie theRealCookie = mock(Cookie.class);
		when(theRealCookie.getName()).thenReturn(getTestCookieName());
		return new Cookie[]{cookie1,cookie2,theRealCookie};
	}

	public String getTestCookieName() {
		return "goodcookie";
	}

	public String getModelItemsString() {
		return "n1|d1|u1|n2|d2|u2|n3|d3|u3|n4|d4|u4";
	}

	public String getPartItemsString() {
		return "n1|d1|u1|iu1|n2|d2|u2|iu2|n3|d3|u3|iu3|n4|d4|u4|iu4";
	}
	
}
