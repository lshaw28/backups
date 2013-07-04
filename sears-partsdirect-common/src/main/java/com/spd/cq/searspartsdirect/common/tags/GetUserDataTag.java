package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;
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
		boolean cookieFound = false;

		if (cookies != null) {
			userNameCookie = PartsDirectCookieHelper.getCookieInfo(cookies,
					Constants.USER_NAME_COOKIE);
			if (userNameCookie != null && userNameCookie.getValue() != null) {
				apiUrl.append(userNameCookie.getValue());
				cookieFound = true;
			} else {
				myModelsCookie = PartsDirectCookieHelper.getCookieInfo(cookies,
						Constants.MY_MODEL_COOKIE);
				if (myModelsCookie != null && myModelsCookie.getValue() != null) {
					apiUrl.append("&profileid="+myModelsCookie.getValue());
					cookieFound = true;
				}

				shoppingCartCookie = PartsDirectCookieHelper.getCookieInfo(
						cookies, Constants.SHOPPING_CART_COOKIE);
				if (shoppingCartCookie != null && shoppingCartCookie.getValue() != null) {
					apiUrl.append("&cartid="+ shoppingCartCookie.getValue());
					cookieFound = true;
				}
			}
		}
		
		if (cookieFound) {
			PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
			try {
				String jsonString = apiHelper.readJsonData(apiUrl.toString());
				Gson gson = new Gson();
		        PDUserDataModel userData = gson.fromJson(jsonString, PDUserDataModel.class);
		        log.debug("JSON Parsing"+ userData.toString());
				log.debug("json.toString() "+jsonString);
				if (userData.getUserName() != null) {
					userData.setLoggedIn(true);
				}
				pageContext.setAttribute("userData", userData);
	
			} catch (IOException e) {
				log.error("I/O Exception while getting data from PD API ", e);
			} 
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
