package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.commons.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectCookieHelper;
import com.spd.cq.searspartsdirect.common.model.PDUserDataModel;
import com.spd.cq.searspartsdirect.common.model.PartModel;

@SuppressWarnings("serial")
public class GetTopPartsTag extends CQBaseTag {

	private static final String MODEL_NAME = "modelNumber";
	private static final String BRAND_NAME = "modelBrandId";
	private static final String CATEGORY_NAME = "modelCategoryId";
	
	protected static Logger log = LoggerFactory.getLogger(GetTopPartsTag.class);
	
	private String modelName;
	private String brandName;
	private String categoryName;

	@Override
	public int doStartTag() throws JspException {
		List<PartModel> topParts = new ArrayList<PartModel>();
		
		String url = buildUrl();
		if (url != null) {
			topParts.addAll(getPartsFromJson(getJsonFromApi(url)));
		}
		
		pageContext.setAttribute("topParts", topParts);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	String buildUrl() {
		// http://partsapivip.qa.ch3.s.com/pd-services/v1/modelTop/topParts
		// ?modelNumber=123-848F401
		// &modelBrandId=0736
		// &modelCategoryId=1500600
		if (StringUtils.isNotBlank(modelName) 
					&& StringUtils.isNotBlank(brandName) 
					&& StringUtils.isNotBlank(categoryName)
				) {
			StringBuilder url = new StringBuilder(EnvironmentSettings.getPDTopPartsApiUrl());
			url.append("?"+MODEL_NAME+"="+modelName);
			url.append("&"+BRAND_NAME+"="+brandName);
			url.append("&"+CATEGORY_NAME+"="+categoryName);
			return url.toString();
		} else {
			return null;
		}
	}
	
	String getJsonFromApi(String apiUrl) {
		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
		String jsonString = null;
		
		try {
			
			jsonString = apiHelper.readJsonData(apiUrl.toString());
		} catch (IOException e) {
			log.error("I/O Exception while getting data from PD API ", e);
		} catch (JSONException e) {
			log.error("JSON Exception while getting data from PD API ", e);
		}
		
		jsonString = "[{\"partName\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0071_247_736-0242\",\"partDesc\":\"Bell Washer\",\"partDetailsPageURL\":\"http://www.searspartsdirect.com/partsdirect/part-number/736-0242/0071/247\"},{\"partName\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0071_247_736-0242\",\"partDesc\":\"Bell Washer\",\"partDetailsPageURL\":\"http://www.searspartsdirect.com/partsdirect/part-number/736-0242/0071/247\"},{\"partName\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0071_247_736-0242\",\"partDesc\":\"Bell Washer\",\"partDetailsPageURL\":\"http://www.searspartsdirect.com/partsdirect/part-number/736-0242/0071/247\"},{\"partName\":null,\"imageURL\":\"\",\"partDesc\":\"Screw\",\"partDetailsPageURL\":\"http://www.searspartsdirect.com/partsdirect/part-number/710-0599/0071/247\"},{\"partName\":null,\"imageURL\":\"\",\"partDesc\":\"Screw\",\"partDetailsPageURL\":\"http://www.searspartsdirect.com/partsdirect/part-number/710-0599/0071/247\"}]";
		
		log.debug("jsonString "+jsonString);
		return jsonString;
	}
	
	Collection<PartModel> getPartsFromJson(String jsonString) {
		// We had duplicates in the initial test data from the API.
		LinkedHashSet<PartModel> noDups = new LinkedHashSet<PartModel>();
		
		try {
			Gson gson = new Gson();
			PartModel[] parts = gson.fromJson(jsonString, PartModel[].class);
		    log.debug("JSON Parsing"+ parts.toString());
				
			noDups.addAll(Arrays.asList(parts));
		} catch (Exception e) {
			log.error("Parsing "+jsonString+", ",e);
		}
		return noDups;
	}
	
}
