package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import java.lang.reflect.Type;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.part.AirFilterDimensions;
import com.spd.cq.searspartsdirect.common.part.AirFilterPart;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import javax.servlet.jsp.JspException;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;


public class GetAirFilterDimensionsTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;

	protected static Logger log = LoggerFactory.getLogger(GetAirFilterDimensionsTag.class);

	@Override
	public int doStartTag() throws JspException {
		AirFilterDimensions airFilterDimensionList = null;
		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
			try {
				String jsonResponse = apiHelper.readJsonData(buildApiUrl(), 15000);
				log.debug("Air Filter-Dimension API response: " + jsonResponse);
				airFilterDimensionList = getAirFilterParts(jsonResponse);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		pageContext.setAttribute("airFilterDimensionList", airFilterDimensionList);
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	protected String buildApiUrl() {
		StringBuilder apiUrlStrBuilder = null;
			apiUrlStrBuilder = new StringBuilder(
					EnvironmentSettings.getPDAirFilterDimensionApiUrl());
			System.out.println("URL for this :"+apiUrlStrBuilder);
			log.debug("API for fetching parts associated with AirFilter: "
					+ apiUrlStrBuilder.toString());
		return apiUrlStrBuilder != null ? apiUrlStrBuilder.toString() : null;
	}

	protected AirFilterDimensions getAirFilterParts(String jsonResponse) {
		AirFilterDimensions airFilterDimension = null;
		if (StringUtils.isNotBlank(jsonResponse)) {
			try {
				Gson gson = new Gson();
				airFilterDimension = gson.fromJson(jsonResponse, AirFilterDimensions.class);
			} catch (JsonParseException jpe) {
				log.error("Error parsing: " +jpe.getMessage());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return airFilterDimension;
	}

}