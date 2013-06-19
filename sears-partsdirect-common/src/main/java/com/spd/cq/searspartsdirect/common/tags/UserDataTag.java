package com.spd.cq.searspartsdirect.common.tags;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;
import com.spd.cq.searspartsdirect.common.model.CartLineModel;
import com.spd.cq.searspartsdirect.common.model.Part;
import com.spd.cq.searspartsdirect.common.model.PartModel;

public class UserDataTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(UserDataTag.class);

	@Override
	public int doStartTag() throws JspException {
		Cookie[] cookies = request.getCookies();
		//hardcoded for now, need to be read from the properties file
		StringBuilder apiUrl = new StringBuilder("http://partsapivip.qa.ch3.s.com/pd-services/v1/userservice/retrive");
		Cookie userNameCookie = null;
		
		if (cookies != null) {
			log.error("cookies are found");
			userNameCookie = PartsDirectCookieHelper.getCookieInfo(cookies, Constants.USER_NAME_COOKIE);
			
			if (userNameCookie != null && userNameCookie.getValue() != null) {
				log.debug("username cookie is not null "+ userNameCookie.getValue());
				apiUrl = apiUrl.append("?username=").append(userNameCookie.getValue());
				log.debug("api url is "+ apiUrl.toString());
				}
			} else {
				log.error("username cookie not found");
			}
		
		List<PartModel> parts = new ArrayList<PartModel>();
		List<CartLineModel> lstCartLines = new ArrayList<CartLineModel>(); 
		JSONObject json;
		try {
			json = readJsonFromUrl(apiUrl.toString());
			//log.debug(json.toString());
			// System.out.println(json.get(""));
			JSONObject cart = json.getJSONObject("cart");
			//log.debug(cart.toString());
			JSONArray cartLinesArray = cart.getJSONArray("cartLines");
			for (int i = 0; i < cartLinesArray.length(); i++) {
				JSONObject cartLineObj = cartLinesArray.getJSONObject(i);
				//log.debug(cartLineObj.toString());
				JSONObject partObj = cartLineObj.getJSONObject("part");
				int qty = cartLineObj.getInt("quantity");
				Part part = new Part(partObj.getString("partNumber"),
						partObj.getString("productGroupId"),
						partObj.getString("supplierId"));
				CartLineModel cartLines = new CartLineModel(part, qty);
				//log.error("cartLines2.toString() "+ cartLines.toString());
				//parts.add(part);
				lstCartLines.add(cartLines);
			}
			pageContext.setAttribute("shoppingCart",lstCartLines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.error(parts.toString());
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	private JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

}
