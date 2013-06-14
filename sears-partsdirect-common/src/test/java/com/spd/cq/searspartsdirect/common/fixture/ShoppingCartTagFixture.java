package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ShoppingCartTagFixture {
	public ShoppingCartTagFixture(HttpServletRequest request) {
		Cookie[] testCookies = getTestCookies();
		when(request.getCookies()).thenReturn(testCookies);
	}
	
	public Cookie[] getTestCookies() {
		Cookie[] cookies;
		Cookie cartCountCookie = mock(Cookie.class);
		when(cartCountCookie.getName()).thenReturn("cartSize");
		when(cartCountCookie.getValue()).thenReturn("23");
		Cookie cidCookie = mock(Cookie.class);
		when(cidCookie.getName()).thenReturn("cid");
		cookies = new Cookie[]{cartCountCookie,cidCookie};
		return cookies;
	}
}
