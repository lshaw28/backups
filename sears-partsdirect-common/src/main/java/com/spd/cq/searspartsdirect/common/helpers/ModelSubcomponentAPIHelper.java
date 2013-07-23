package com.spd.cq.searspartsdirect.common.helpers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.model.PDModelSubcomponentModel;

public class ModelSubcomponentAPIHelper {

	protected static Logger log = LoggerFactory.getLogger(ModelSubcomponentAPIHelper.class);

	final static String MODELSUB_REQATTR = Constants.ident("spd.model.subcomponents");

	private final static String BRAND_PARAM = "brandName";
	private final static String CATEGORY_PARAM = "categoryName";
	private final static String MODEL_PARAM = "modelNumber";

	private String brand;
	private String category;
	private String model;

	public ModelSubcomponentAPIHelper(String brand, String category, String model) {
		super();
		this.brand = brand;
		this.category = category;
		this.model = model;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public PDModelSubcomponentModel getModelSubcomponents(SlingHttpServletRequest slingRequest) {
		PDModelSubcomponentModel modelSub = (PDModelSubcomponentModel) slingRequest.getAttribute(MODELSUB_REQATTR);
		if (modelSub == null) {
			modelSub = getModelSubcomponentsFromApi();
			slingRequest.setAttribute(MODELSUB_REQATTR, modelSub);
		}
		return modelSub;
	}

	public PDModelSubcomponentModel getModelSubcomponentsFromApi() {
		String url = buildUrl();
		if (url != null) {
			return getModelSubcomponentsFromJson(getJsonFromApi(url));
		} else {
			return null;
		}
	}

	String buildUrl() {
		// http://partsapivip.qa.ch3.s.com/pd-services/v1/commonSymptoms/modelInfo
		// ?modelNumber=66513593K600
		// &categoryName=Dishwasher
		// &brandName=Kenmore
		if (StringUtils.isNotBlank(model) && StringUtils.isNotBlank(brand) && StringUtils.isNotBlank(category)) {
			StringBuilder url = new StringBuilder(EnvironmentSettings.getPDModelSubApiUrl());
			try {
				url.append("?" + MODEL_PARAM + "=" + URLEncoder.encode(model, Constants.ENCODING));
				url.append("&" + CATEGORY_PARAM + "=" + URLEncoder.encode(category, Constants.ENCODING));
				url.append("&" + BRAND_PARAM + "=" + URLEncoder.encode(brand, Constants.ENCODING));
			} catch (UnsupportedEncodingException e) {} // CANTHAPPEN - we are using a guaranteed encoding.
			return url.toString();
		} else {
			return null;
		}
	}

	String getJsonFromApi(String apiUrl) {
		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
		String jsonString = null;

		try {
			jsonString = apiHelper.readJsonData(apiUrl);
		} catch (IOException e) {
			log.error("reading from " + apiUrl + ", ", e);
		}

		log.debug("jsonString " + jsonString);
		return jsonString;
	}

	PDModelSubcomponentModel getModelSubcomponentsFromJson(String jsonFromApi) {
		PDModelSubcomponentModel subcomponents = null;
		try {
			Gson gson = new Gson();
			subcomponents = gson.fromJson(jsonFromApi, PDModelSubcomponentModel.class);
			log.debug("JSON parsed to " + subcomponents);
		} catch (Exception e) {
			log.error("Parsing " + jsonFromApi + ", ", e);
		}
		return subcomponents;
	}
}
