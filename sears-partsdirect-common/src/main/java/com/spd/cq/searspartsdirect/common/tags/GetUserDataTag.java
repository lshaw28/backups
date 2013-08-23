package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;
import com.spd.cq.searspartsdirect.common.model.CartLinesModel;
import com.spd.cq.searspartsdirect.common.model.CartModel;
import com.spd.cq.searspartsdirect.common.model.PDUserDataModel;

public class GetUserDataTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetUserDataTag.class);

	@Override
	public int doStartTag() throws JspException {
		Cookie[] cookies = request.getCookies();
		StringBuilder apiUrl = new StringBuilder(EnvironmentSettings.getPDUserDataApiUrl());

		Cookie userNameCookie = null;
		Cookie myModelsCookie = null;
		Cookie shoppingCartCookie = null;
		Cookie shoppingCartSizeCookie = null;
		boolean cookieFound = false;

		if (cookies != null) {
			userNameCookie = PartsDirectCookieHelper.getCookieInfo(cookies, Constants.USER_NAME_COOKIE);
			try {
			if (userNameCookie != null && userNameCookie.getValue() != null) {
				apiUrl.append(URLEncoder.encode(userNameCookie.getValue(), Constants.ENCODING));
				cookieFound = true;
			} else {
				myModelsCookie = PartsDirectCookieHelper.getCookieInfo(cookies, Constants.MY_MODEL_COOKIE);
				if (myModelsCookie != null && myModelsCookie.getValue() != null) {
					apiUrl.append("&profileid=" + URLEncoder.encode(myModelsCookie.getValue(), Constants.ENCODING));
					cookieFound = true;
				}
				shoppingCartCookie = PartsDirectCookieHelper.getCookieInfo(cookies, Constants.SHOPPING_CART_COOKIE);
				if (shoppingCartCookie != null && shoppingCartCookie.getValue() != null) {
					apiUrl.append("&cartid=" + URLEncoder.encode(shoppingCartCookie.getValue(), Constants.ENCODING));
					cookieFound = true;
				}
			}
			} catch(UnsupportedEncodingException e) {}// CANTHAPPEN - we are using a guaranteed encoding.
		}

		if (cookieFound) {
			PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
			try {
				String jsonString = apiHelper.readJsonData(apiUrl.toString());
				Gson gson = new Gson();
				PDUserDataModel userData = gson.fromJson(jsonString, PDUserDataModel.class);
				//log.debug("JSON Parsing" + userData.toString());
				//log.debug("json.toString() " + jsonString);
				if (userData.getUserName() != null) {
					userData.setLoggedIn(true);
				}
				
				/*int cartCount = 0;
				CartModel cartModel = userData.getCart();
				if (cartModel != null && cartModel.getCartLines() != null) {
					for (CartLinesModel cartLine : cartModel.getCartLines()) {
						cartCount = cartCount +  cartLine.getQuantity();
					}
				}*/
				//pageContext.setAttribute("cartCount", cartCount);	
				shoppingCartSizeCookie = PartsDirectCookieHelper.getCookieInfo(cookies, Constants.SHOPPING_CART_SIZE_COOKIE);
				if (shoppingCartSizeCookie != null && shoppingCartSizeCookie.getValue() != null) {
					pageContext.setAttribute("cartCount", shoppingCartSizeCookie.getValue());
				} else {
					pageContext.setAttribute("cartCount", 0);
				}
				pageContext.setAttribute("userData", userData);
				

			} catch (IOException e) {
				log.error("I/O Exception while getting data from PD API ", e.fillInStackTrace());
			}
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
