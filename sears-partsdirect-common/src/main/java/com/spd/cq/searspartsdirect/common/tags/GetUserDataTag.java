package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONException;
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

		if (cookies != null) {
			userNameCookie = PartsDirectCookieHelper.getCookieInfo(cookies,
					Constants.USER_NAME_COOKIE);
			if (userNameCookie != null && userNameCookie.getValue() != null) {
				apiUrl.append(userNameCookie.getValue());
			} else {
				myModelsCookie = PartsDirectCookieHelper.getCookieInfo(cookies,
						Constants.MY_MODEL_COOKIE);
				if (myModelsCookie != null && myModelsCookie.getValue() != null) {
					apiUrl.append("&profileid="+myModelsCookie.getValue());
				}

				shoppingCartCookie = PartsDirectCookieHelper.getCookieInfo(
						cookies, Constants.SHOPPING_CART_COOKIE);
				if (shoppingCartCookie != null && shoppingCartCookie.getValue() != null) {
					apiUrl.append("&cartid="+ shoppingCartCookie.getValue());
				}
			}
		}
		
		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
		try {
			String jsonString = apiHelper.readJsonString(apiUrl.toString());
			Gson gson = new Gson();
	        PDUserDataModel model = gson.fromJson(jsonString, PDUserDataModel.class);
	        log.debug("*********JSON Parsing"+ model.toString());
			log.debug("json.toString() "+jsonString);
			if (model.getUserName() != null) {
				model.setLoggedIn(true);
			}
			pageContext.setAttribute("userData", model);

		} catch (IOException e) {
			log.error("I/O Exception while getting data from PD API ", e);
		} catch (JSONException e) {
			log.error("JSON Exception while getting data from PD API ", e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
