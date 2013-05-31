package com.spd.cq.searspartsdirect.common.fixture;

import java.util.LinkedList;

import javax.servlet.http.Cookie;

import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.ModelCookieModel;
import com.spd.cq.searspartsdirect.common.model.PartCookieModel;

public class RecentlyViewedFixture {
	
	public Cookie[] getCookie() {
		Cookie cookie = new Cookie( "testCookie", "1234" );
        cookie.setMaxAge(12000);
        cookie.setPath("/partsdirect/");
        cookie.setDomain("localhost");
        return new Cookie[] {cookie};
	}
	
	public Cookie[] getPartAndModelCookie() {
		Cookie modelCookie = new Cookie(Constants.RECENTLY_VIEWED_MODEL_COOKIE, "");
		modelCookie.setValue("3333|Some description|http://www.SPD.com/ModelUrl");
        modelCookie.setMaxAge(12000);
        modelCookie.setPath("/partsdirect/");
        modelCookie.setDomain("localhost");
        
        Cookie partCookie = new Cookie(Constants.RECENTLY_VIEWED_PART_COOKIE, "");
        partCookie.setValue("9010P|water filter|partUrl|itemImageURL");
        partCookie.setMaxAge(12000);
        partCookie.setPath("/partsdirect/");
        partCookie.setDomain("localhost");
        return new Cookie[] {modelCookie, partCookie};
	}
	
	public LinkedList<ModelCookieModel> getModelList() {
		LinkedList<ModelCookieModel> modelList = new LinkedList<ModelCookieModel>();
		ModelCookieModel modelCookieModel = new ModelCookieModel();
		modelCookieModel.setItemName("3333");
		modelCookieModel.setItemDescription("Some description");
		modelCookieModel.setItemURL("http://www.SPD.com/ModelUrl");
		modelList.add(modelCookieModel);
		return modelList;
	}
	
	public LinkedList<PartCookieModel> getPartList() {
		LinkedList<PartCookieModel> partModel = new LinkedList<PartCookieModel>();
		PartCookieModel partCookieModel = new PartCookieModel();
		partCookieModel.setItemName("9010P");
		partCookieModel.setItemDescription("water filter");
		partCookieModel.setItemImageURL("itemImageURL");
		partCookieModel.setItemURL("partUrl");
		partModel.add(partCookieModel);
		return partModel;
	}

}
