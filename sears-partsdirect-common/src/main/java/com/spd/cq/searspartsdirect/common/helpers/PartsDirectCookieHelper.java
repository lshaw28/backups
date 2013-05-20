package com.spd.cq.searspartsdirect.common.helpers;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartsDirectCookieHelper {

	protected static Logger log = LoggerFactory.getLogger(PartsDirectCookieHelper.class);

	public Cookie getCookieInfo(Cookie[] cookies, String cookieName) {
		Cookie cookie = null;

		for (int i = 0; i < cookies.length; i++) {
			log.error(cookies[i].getName());
			if (cookies[i].getName().equals(cookieName)) {
				cookie = cookies[i];
				break;
			}
		}
		return cookie;
	}
}
