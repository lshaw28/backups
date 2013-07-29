package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetHeaderHelperTagFixture {
	
	private HttpServletRequest request;
	
	private List<Cookie> cookies;
	private final static Cookie[] emptyCookies = new Cookie[]{};
	
	public GetHeaderHelperTagFixture(HttpServletRequest request) throws Exception {
		this.request = request;
	}

	public void setUpNoCookies() {
		// noop - to have no cookies, we refrain from setting any up.
	}
	
	public void setUpEmptyCookies() {
		setUpRequestCookies();
		cookies.clear();
	}
	
	private void setUpRequestCookies() {
		if (cookies == null) {
			cookies = new ArrayList<Cookie>();
			when(request.getCookies()).thenAnswer(new Answer<Cookie[]>() {
				public Cookie[] answer(InvocationOnMock invocation)
						throws Throwable {
					return cookies.toArray(emptyCookies);
				}
			});
		}
	}
	
	public void setUpPopulatedModelCookie() {
		setUpRequestCookies();
		Cookie modelCookie = makeEmptyModelCookie();
		when(modelCookie.getValue()).thenReturn(getModelCookieValue());
		cookies.add(modelCookie);
	}
	
	public void setUpPopulatedShoppingCartCookie() {
		setUpRequestCookies();
		Cookie cartCookie = makeEmptyShoppingCartCookie();
		when(cartCookie.getValue()).thenReturn(getShoppingCartCookieValue());
		cookies.add(cartCookie);
	}
	
	public String getModelCookieValue() {
		return "e83db321-034a-41c6-9885-15dc9d655e9b";
	}
	
	public String getShoppingCartCookieValue() {
		return "e83db321-034a-41c6-9885-15dc9d655e9b";
	}
	
	private Cookie makeEmptyModelCookie() {
		Cookie modelCookie = mock(Cookie.class);
		when(modelCookie.getName()).thenReturn(Constants.MY_MODEL_COOKIE);
		return modelCookie;
	}
	
	private Cookie makeEmptyShoppingCartCookie() {
		Cookie cartCookie = mock(Cookie.class);
		when(cartCookie.getName()).thenReturn(Constants.SHOPPING_CART_COOKIE);
		return cartCookie;
	}


}

