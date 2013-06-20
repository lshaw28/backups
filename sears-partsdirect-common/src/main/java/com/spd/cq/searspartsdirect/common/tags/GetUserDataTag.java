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
import com.spd.cq.searspartsdirect.common.model.MyProfileModel;
import com.spd.cq.searspartsdirect.common.model.PDUserDataModel;
import com.spd.cq.searspartsdirect.common.model.Part;
import com.spd.cq.searspartsdirect.common.model.PartModel;

public class GetUserDataTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetUserDataTag.class);

	@Override
	public int doStartTag() throws JspException {
		Cookie[] cookies = request.getCookies();
		// hardcoded for now, need to be read from the properties file
		String apiUrl = "http://partsapivip.qa.ch3.s.com/pd-services/v1/userservice/retrive?username=";
		
		Cookie userNameCookie = null;
		Cookie myModelsCookie = null;
		Cookie shoppingCartCookie = null;
		PDUserDataModel pdUserDataModel = new PDUserDataModel();

		if (cookies != null) {
			userNameCookie = PartsDirectCookieHelper.getCookieInfo(cookies,
					Constants.USER_NAME_COOKIE);
			if (userNameCookie != null && userNameCookie.getValue() != null) {
				apiUrl = apiUrl + userNameCookie.getValue();
				pdUserDataModel.setLoggedIn(true);
			} else {
				myModelsCookie = PartsDirectCookieHelper.getCookieInfo(cookies,
						Constants.MY_MODEL_COOKIE);
				if (myModelsCookie != null && myModelsCookie.getValue() != null) {
					apiUrl = apiUrl + "&profileid="+myModelsCookie.getValue();
				}

				shoppingCartCookie = PartsDirectCookieHelper.getCookieInfo(
						cookies, Constants.SHOPPING_CART_COOKIE);
				if (shoppingCartCookie != null && shoppingCartCookie.getValue() != null) {
					apiUrl = apiUrl + "&cartid="+ shoppingCartCookie.getValue();
				}
			}
		}	
		
		List<PartModel> parts = new ArrayList<PartModel>();
		List<CartLineModel> lstCartLines = new ArrayList<CartLineModel>();
		JSONObject json;
		try {
			json = readJsonFromUrl(apiUrl);
			log.debug("json.toString() "+json.toString());
			if (json.has("cart")) {
				JSONObject cart = json.getJSONObject("cart");
				JSONArray cartLinesArray = cart.getJSONArray("cartLines");
				for (int i = 0; i < cartLinesArray.length(); i++) {
					JSONObject cartLineObj = cartLinesArray.getJSONObject(i);
					JSONObject partObj = cartLineObj.getJSONObject("part");
					int qty = cartLineObj.getInt("quantity");
					Part part = new Part(partObj.getString("partNumber"),
							partObj.getString("productGroupId"),
							partObj.getString("supplierId"));
					CartLineModel cartLines = new CartLineModel(part, qty);
					log.debug("cartLines2.toString() "+ cartLines.toString());
					lstCartLines.add(cartLines);
				}
				pdUserDataModel.setShoppingCart(lstCartLines);
			}

			//my profile models
			List<MyProfileModel> myProfileModels = new ArrayList<MyProfileModel>();
			JSONObject ownedModel = json.getJSONObject("ownedModels");
			log.debug("ownedModel ="+ownedModel.toString());
			
			if (ownedModel.has("profileModelsList")) {
				JSONArray modelArray = ownedModel.getJSONArray("profileModelsList");
				for (int i = 0; i < modelArray.length(); i++) {
					JSONObject modelObj = modelArray.getJSONObject(i);
					MyProfileModel myProfileModel = new MyProfileModel(
							modelObj.getString("brandName"),
							modelObj.getString("categoryName"),
							modelObj.getString("modelNumber"),
							modelObj.getString("itemURL"));
	
					myProfileModels.add(myProfileModel);
					log.debug("myProfileModels " + myProfileModels.toString());
				}
				pdUserDataModel.setMyProfileModels(myProfileModels);
			}
			
			
			if (json.has("firstName")) {
				pdUserDataModel.setFirstName("firstName");
			}
			
			if (json.has("lastName")) {
				pdUserDataModel.setLastName("lastName");
			}
			
			pageContext.setAttribute("userData", pdUserDataModel);

		} catch (IOException e) {
			log.error("IP Exception while getting data from PD API ", e);
		} catch (JSONException e) {
			log.error("JSON Exception while getting data from PD API ", e);
		}

		log.debug(parts.toString());
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

	private JSONObject readJsonFromUrl(String urlStr) throws IOException,
			JSONException {
		log.debug("PD API url String is="+urlStr);
		//urlStr="http://partsapivip.qa.ch3.s.com/pd-services/v1/userservice/retrive?username=&profileid=c4ccbcf4-3b71-4071-83dc-d88c75aded8c&cartid=8a6bc7483f5ba81b013f5dd11c360013"
		InputStream is = new URL(urlStr).openStream();
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
