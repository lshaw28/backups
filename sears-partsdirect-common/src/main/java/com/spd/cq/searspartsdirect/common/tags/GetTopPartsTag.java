package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.model.PartModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

@SuppressWarnings("serial")
public class GetTopPartsTag extends CQBaseTag {

	private static final String MODEL_NAME = "modelNumber";
	private static final String BRAND_NAME = "modelBrandName";
	private static final String CATEGORY_NAME = "modelCategoryName";
	
	public static final String NO_IMAGE_URL = Constants.ident("http://c.searspartsdirect.com/pd-web-consumer-3.112.20130618-05/assets/img/images/no_part.gif");
	
	protected static final Logger log = LoggerFactory.getLogger(GetTopPartsTag.class);
	
	private String model;
	private BrandModel brand;
	private ProductCategoryModel category;

	@Override
	public int doStartTag() throws JspException {
		List<PartModel> topParts = new ArrayList<PartModel>();
		
		String url = buildUrl();
		if (url != null) {
			topParts.addAll(getPartsFromJson(getJsonFromApi(url)));
		}
		addNoImageUrl(topParts);
		
		pageContext.setAttribute("topParts", topParts);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setBrand(BrandModel brand) {
		this.brand = brand;
	}

	public void setCategory(ProductCategoryModel category) {
		this.category = category;
	}
	
	String buildUrl() {
		// http://partsapivip.qa.ch3.s.com/pd-services/v1/modelTop/topParts
		// ?modelNumber=123-848F401
		// &modelBrandId=0736
		// &modelCategoryId=1500600
		String brandName = null;
		if (brand != null) {
			brandName = brand.getTitle();
		}
		String categoryName = null;
		if (category != null) {
			categoryName = category.getTitle();
		}
		if (StringUtils.isNotBlank(model) 
					&& StringUtils.isNotBlank(brandName) 
					&& StringUtils.isNotBlank(categoryName)
				) {
			StringBuilder url = new StringBuilder(EnvironmentSettings.getPDTopPartsApiUrl());
			try {
				url.append("?"+MODEL_NAME+"="+URLEncoder.encode(model,Constants.ENCODING));
				url.append("&"+BRAND_NAME+"="+URLEncoder.encode(brandName,Constants.ENCODING));
				url.append("&"+CATEGORY_NAME+"="+URLEncoder.encode(categoryName,Constants.ENCODING));
			} catch (UnsupportedEncodingException e) {
				log.error("Java is Broken!!! UTF-8 no longer supported!!!");
				return null;
			}
			log.debug("Top Parts API call URL is "+url);
			return url.toString();
		} else {
			log.debug("Did not construct a URL from "+brandName+","+categoryName+","+model);
			return null;
		}
	}
	
	String getJsonFromApi(String apiUrl) {
		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
		String jsonString = null;
		
		try {
			jsonString = apiHelper.readJsonData(apiUrl);
		} catch (IOException e) {
			log.error("reading from "+apiUrl+", ", e);
		}
		
		log.debug("jsonString "+jsonString);
		return jsonString;
	}
	
	Collection<PartModel> getPartsFromJson(String jsonString) {
		// We had duplicates in the initial test data from the API.
		LinkedHashSet<PartModel> noDups = new LinkedHashSet<PartModel>();
		
		try {
			Gson gson = new Gson();
			PartModel[] parts = gson.fromJson(jsonString, PartModel[].class);
		    log.debug("JSON parsed to "+ Arrays.toString(parts));
				
			noDups.addAll(Arrays.asList(parts));
		} catch (Exception e) {
			log.error("Parsing "+jsonString+", ",e);
		}
		return noDups;
	}
	
	void addNoImageUrl(List<PartModel> topParts) {
		for (PartModel part : topParts) {
			if (StringUtils.isBlank(part.getImageUrl())) {
				part.setImageUrl(NO_IMAGE_URL);
			}
		}
	}
}
