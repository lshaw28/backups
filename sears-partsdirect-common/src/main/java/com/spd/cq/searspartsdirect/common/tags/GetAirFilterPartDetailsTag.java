package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.model.AirFilterPart;

public class GetAirFilterPartDetailsTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	public static final Logger log = LoggerFactory.getLogger(GetAirFilterPartDetailsTag.class);

	private String basePartNumber;
	private String divisionNumber;
	private String plsNumber;

	@Override
	public int doStartTag() throws JspException {

		List<AirFilterPart> airFilterParts = new ArrayList<AirFilterPart>();

		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();

		if (!StringUtils.isBlank(basePartNumber) && !StringUtils.isBlank(divisionNumber)
				&& !StringUtils.isBlank(plsNumber)) {
			try {
				String jsonResponse = apiHelper.readJsonData(buildApiUrl());
				log.debug("Air Filter Part Details API response: " + jsonResponse);
				airFilterParts = getAirFilterParts(jsonResponse);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}

		pageContext.setAttribute("airFilterParts", airFilterParts);

		return SKIP_BODY;
	}

	protected String buildApiUrl() {
		StringBuilder apiUrlStrBuilder = null;
		if (!StringUtils.isBlank(basePartNumber) && !StringUtils.isBlank(divisionNumber)
				&& !StringUtils.isBlank(plsNumber)) {

			apiUrlStrBuilder = new StringBuilder(EnvironmentSettings.getPDAirFilterPartDetailsApiUrl());
			apiUrlStrBuilder.append('?');
			try {
				apiUrlStrBuilder.append("number=");
				apiUrlStrBuilder.append(URLEncoder.encode(basePartNumber, Constants.ENCODING));
				apiUrlStrBuilder.append("&div=");
				apiUrlStrBuilder.append(URLEncoder.encode(divisionNumber, Constants.ENCODING));
				apiUrlStrBuilder.append("&pls=");
				apiUrlStrBuilder.append(URLEncoder.encode(plsNumber, Constants.ENCODING));
			} catch (UnsupportedEncodingException e) {}

			log.debug("API URL for fetching part details associated with AirFilter: " + apiUrlStrBuilder.toString());
		}
		return apiUrlStrBuilder != null ? apiUrlStrBuilder.toString() : null;
	}

	private List<AirFilterPart> getAirFilterParts(String jsonResponse) {

		List<AirFilterPart> airFilterParts = null;

		if (!StringUtils.isBlank(jsonResponse)) {

			try {
				Gson gson = new Gson();
				airFilterParts = gson.fromJson(jsonResponse, new TypeToken<List<AirFilterPart>>() {}.getType());
			} catch (JsonParseException jpe) {
				log.error("Error parsing: "+jpe.getMessage());
			} catch (Exception e) {
				log.error(e.getMessage());
			}

		}

		return airFilterParts;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getBasePartNumber() {
		return basePartNumber;
	}

	public void setBasePartNumber(String basePartNumber) {
		this.basePartNumber = basePartNumber;
	}

	public String getDivisionNumber() {
		return divisionNumber;
	}

	public void setDivisionNumber(String divisionNumber) {
		this.divisionNumber = divisionNumber;
	}

	public String getPlsNumber() {
		return plsNumber;
	}

	public void setPlsNumber(String plsNumber) {
		this.plsNumber = plsNumber;
	}
}
