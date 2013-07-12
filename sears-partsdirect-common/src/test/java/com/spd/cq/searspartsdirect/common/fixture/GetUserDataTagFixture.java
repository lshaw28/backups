package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetUserDataTagFixture {
	
	private EnvironmentSettingsFixture envFixture;
	private EnvironmentSettings env;
	
	private HttpServletRequest request;
	private List<Cookie> cookies;
	private final static Cookie[] emptyCookies = new Cookie[]{};

	public GetUserDataTagFixture(HttpServletRequest request) throws Exception {
		envFixture = new EnvironmentSettingsFixture();
		env = new EnvironmentSettings();
		envFixture.setUpRealDefaults(env);
		this.request = request;
	}
	
	public void setUpEmptyCookies() {
		setUpRequestCookies();
		cookies.clear();
	}
	
	public void setUpEmptyUserCookie() {
		setUpRequestCookies();
		cookies.add(makeEmptyUserCookie());
	}
	
	public void setUpPopulatedUserCookie() {
		setUpRequestCookies();
		Cookie userCookie = makeEmptyUserCookie();
		when(userCookie.getValue()).thenReturn(getUserCookieValue());
		cookies.add(userCookie);
	}
	
	public void setUpEmptyModelCookie() {
		setUpRequestCookies();
		cookies.add(makeEmptyModelCookie());
	}
	
	public void setUpPopulatedModelCookie() {
		setUpRequestCookies();
		Cookie modelCookie = makeEmptyModelCookie();
		when(modelCookie.getValue()).thenReturn(getModelCookieValue());
		cookies.add(modelCookie);
	}
	
	public void setUpEmptyCartCookie() {
		setUpRequestCookies();
		cookies.add(makeEmptyCartCookie());
	}
	
	public void setUpPopulatedCartCookie() {
		setUpRequestCookies();
		Cookie cartCookie = makeEmptyCartCookie();
		when(cartCookie.getValue()).thenReturn(getCartCookieValue());
		cookies.add(cartCookie);
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
	
	private Cookie makeEmptyUserCookie() {
		Cookie userCookie = mock(Cookie.class);
		when(userCookie.getName()).thenReturn(Constants.USER_NAME_COOKIE);
		return userCookie;
	}
	
	private Cookie makeEmptyModelCookie() {
		Cookie modelCookie = mock(Cookie.class);
		when(modelCookie.getName()).thenReturn(Constants.MY_MODEL_COOKIE);
		return modelCookie;
	}
	
	private Cookie makeEmptyCartCookie() {
		Cookie cartCookie = mock(Cookie.class);
		when(cartCookie.getName()).thenReturn(Constants.SHOPPING_CART_COOKIE);
		return cartCookie;
	}
	
	public String getUserCookieValue() {
		return "bzethmayr@siteworx.com";
	}
	
	public String getModelCookieValue() {
		return "c4ccbcf4-3b71-4071-83dc-d88c75aded8c";
	}
	
	public String getCartCookieValue() {
		return "8a6bc7483f5ba81b013f5dd11c360013";
	}
}
