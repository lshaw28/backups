package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;
import com.spd.cq.searspartsdirect.common.model.CartLineModel;
import com.spd.cq.searspartsdirect.common.model.MyProfileModel;
import com.spd.cq.searspartsdirect.common.model.PDUserDataModel;
import com.spd.cq.searspartsdirect.common.model.Part;

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
		PDUserDataModel pdUserDataModel = new PDUserDataModel();

		if (cookies != null) {
			userNameCookie = PartsDirectCookieHelper.getCookieInfo(cookies,
					Constants.USER_NAME_COOKIE);
			if (userNameCookie != null && userNameCookie.getValue() != null) {
				apiUrl.append(userNameCookie.getValue());
				pdUserDataModel.setLoggedIn(true);
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
		
		List<CartLineModel> lstCartLines = new ArrayList<CartLineModel>();
		JSONObject json;
		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
		try {
			json = apiHelper.readJsonFromUrl(apiUrl.toString());
			log.debug("json.toString() "+json.toString());
			if (json.has("cart")) {
				Object cartObj = json.get("cart");
					if (cartObj != null && cartObj instanceof JSONObject) {
					JSONObject cart = json.getJSONObject("cart");
					if (cart != null) {
						Object cartLinesObj = cart.get("cartLines");
						if (cartLinesObj instanceof JSONArray) {
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
						} else if (cartLinesObj instanceof JSONObject) {
							JSONObject cartLines = cart.getJSONObject("cartLines");
							JSONObject partObject = cartLines.getJSONObject("part");
							int qty = cartLines.getInt("quantity");
							Part part = new Part(partObject.getString("partNumber"),
									partObject.getString("productGroupId"),
									partObject.getString("supplierId"));
							CartLineModel model = new CartLineModel(part, qty);
							lstCartLines.add(model);
							log.debug("lstCartLines.toString() "+ lstCartLines.toString());
						}
					}	
					pdUserDataModel.setShoppingCart(lstCartLines);
				}
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
				pdUserDataModel.setFirstName(json.getString("firstName"));
			}
			
			if (json.has("lastName")) {
				pdUserDataModel.setLastName(json.getString("lastName"));
			}
			
			pageContext.setAttribute("userData", pdUserDataModel);

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
