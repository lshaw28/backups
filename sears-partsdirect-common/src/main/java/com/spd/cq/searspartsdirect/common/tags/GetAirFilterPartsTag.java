package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import javax.servlet.jsp.JspException;
import org.slf4j.LoggerFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import org.slf4j.Logger;

import sun.security.x509.AVA;

import com.google.gson.JsonParseException;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.part.AirFilterPart;
import com.spd.cq.searspartsdirect.common.part.AirFilterList;
import com.spd.cq.searspartsdirect.common.part.AirFilterPartDetails;
import com.spd.cq.searspartsdirect.common.part.AvailablePacks;


public class GetAirFilterPartsTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;

	protected static Logger log = LoggerFactory.getLogger(GetAirFilterPartsTag.class);

	private String width;
	private String height;
	private String length;

	@Override
	public int doStartTag() throws JspException {
		AirFilterList airFilterPartList = null;
		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
		if (StringUtils.isNotEmpty(width) && StringUtils.isNotEmpty(height) && StringUtils.isNotEmpty(length)) {
			try {
				String jsonResponse = apiHelper.readJsonData(buildApiUrl(), 15000);
				log.debug("Air Filter-Parts API response: " + jsonResponse);
				airFilterPartList = getAirFilterParts(jsonResponse);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		pageContext.setAttribute("airFilterPartList", airFilterPartList);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	protected String buildApiUrl() {
		StringBuilder apiUrlStrBuilder = null;
		if (StringUtils.isNotEmpty(width) && StringUtils.isNotEmpty(height)
				&& StringUtils.isNotEmpty(length)) {
			apiUrlStrBuilder = new StringBuilder(
					EnvironmentSettings.getPDAirFilterPartsApiUrl());
			apiUrlStrBuilder.append('?');
			try {
				apiUrlStrBuilder.append("w=");
				apiUrlStrBuilder.append(URLEncoder.encode(width,
						Constants.ENCODING));
				apiUrlStrBuilder.append("&h=");
				apiUrlStrBuilder.append(URLEncoder.encode(height,
						Constants.ENCODING));
				apiUrlStrBuilder.append("&d=");
				apiUrlStrBuilder.append(URLEncoder.encode(length,
						Constants.ENCODING));
			} catch (UnsupportedEncodingException e) {
			} // CANTHAPPEN - we are using a guaranteed encoding.
			log.debug("API for fetching parts associated with AirFilter: "
					+ apiUrlStrBuilder.toString());
		}
		return apiUrlStrBuilder != null ? apiUrlStrBuilder.toString() : null;
	}

	protected AirFilterList getAirFilterParts(String jsonResponse) {
		AirFilterList airFilterPartObj = null;
		if (StringUtils.isNotBlank(jsonResponse)) {
			try {
				Gson gson = new Gson();
				airFilterPartObj = gson.fromJson(jsonResponse, AirFilterList.class);
				List<AirFilterPartDetails> parts = airFilterPartObj.getBestAirFilters().getPart();
				airFilterPartObj.getBestAirFilters().setQuality("Best");
				airFilterPartObj.getBestAirFilters().setPart(groupAvailablePacksIn(airFilterPartObj.getBestAirFilters().getPart()));
				airFilterPartObj.getBetterAirFilters().setQuality("Better");
				airFilterPartObj.getBetterAirFilters().setPart(groupAvailablePacksIn(airFilterPartObj.getBetterAirFilters().getPart()));
				airFilterPartObj.getGoodAirFilters().setQuality("Good");
				airFilterPartObj.getGoodAirFilters().setPart(groupAvailablePacksIn(airFilterPartObj.getGoodAirFilters().getPart()));
			} catch (JsonParseException jpe) {
				log.error("Error parsing: "+jpe.getMessage());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return airFilterPartObj;
	}
	
	private List<AirFilterPartDetails> groupAvailablePacksIn(List<AirFilterPartDetails> parts){
		List<AirFilterPartDetails> airFilterPartList = new ArrayList<AirFilterPartDetails>();
		int k = 1;
		for(AirFilterPartDetails part : parts){
			List<AvailablePacks> availPacks = new ArrayList<AvailablePacks>();
			int j=k;
			for(int i=j;i<parts.size();i++){
				AirFilterPartDetails part1 = parts.get(i);
				if(part.getBasePartNumber().equals(part1.getBasePartNumber())){
					AvailablePacks pack = new AvailablePacks(part1.getAvailablePacks(),part1.getPriceForParts());
					availPacks.add(pack);
					parts.remove(part1);
				}
				j = j+1;
			}
			part.setAvailablePacksList(availPacks);
			airFilterPartList.add(part);
			k = k+1;
		}
		return airFilterPartList;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
}