package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.model.JobCodePartModel;
import com.spd.cq.searspartsdirect.common.model.RecoveryJobCodePartModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;

public class GetJobCodePartsTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;

	protected static Logger log = LoggerFactory.getLogger(GetJobCodePartsTag.class);

	private List<JobCodeModel> jobCodes;
	private String modelNumber;

	@Override
	public int doStartTag() throws JspException {

		Map<String, List<JobCodePartModel>> jobCodeParts = new HashMap<String, List<JobCodePartModel>>();

		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();

		if (jobCodes != null && jobCodes.size() > 0) {
			try {
				String jsonResponse = apiHelper.readJsonData(buildApiUrl());
				log.debug("JobCode-Parts API response: " + jsonResponse);
				jobCodeParts = getJobCodeParts(jsonResponse);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		pageContext.setAttribute("jobCodeParts", jobCodeParts);

		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	protected String buildApiUrl() {

		StringBuilder apiUrlStrBuilder = null;

		if (CollectionUtils.isNotEmpty(jobCodes)) {
			apiUrlStrBuilder = new StringBuilder(EnvironmentSettings.getPDJobCodePartsApiUrl());
			apiUrlStrBuilder.append('?');
			for (int i = 0; i < jobCodes.size(); i++) {
				if (i > 0) {
					apiUrlStrBuilder.append('&');
				}
				try {
					apiUrlStrBuilder.append("jobCodeList=" + URLEncoder.encode(((JobCodeModel)jobCodes.get(i)).getId(), Constants.ENCODING));
				} catch (UnsupportedEncodingException e) {} // CANTHAPPEN - we are using a guaranteed encoding.
			}
			if (StringUtils.isNotBlank(modelNumber)) {
				try {
					apiUrlStrBuilder.append("&modelNumber=");
					apiUrlStrBuilder.append(URLEncoder.encode(modelNumber, Constants.ENCODING));
				} catch (UnsupportedEncodingException e) {} // CANTHAPPEN - we are using a guaranteed encoding.
			}

			log.debug("API for fetching parts associated with jobCodes: " + apiUrlStrBuilder.toString());
		}


		return apiUrlStrBuilder != null ? apiUrlStrBuilder.toString() : null;
	}

	protected Map<String, List<JobCodePartModel>> getJobCodeParts(String jsonResponse) {

		Map<String, List<JobCodePartModel>> jobCodeParts = new HashMap<String, List<JobCodePartModel>>();

		if (StringUtils.isNotBlank(jsonResponse)) {
			try {
				Gson gson = new Gson();
				List<RecoveryJobCodePartModel> recoveryJobCodePartModels = gson.fromJson(jsonResponse, new TypeToken<List<RecoveryJobCodePartModel>>(){}.getType());

				for (RecoveryJobCodePartModel recoveryJobCodePartModel : recoveryJobCodePartModels) {
					jobCodeParts.put(recoveryJobCodePartModel.getJobCode(), recoveryJobCodePartModel.getJobCodePartModels());
				}

			} catch (JsonParseException jpe) {
				log.error("Error parsing: " + jsonResponse + ", ", jpe.getMessage());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		return jobCodeParts;
	}

	public List<JobCodeModel> getJobCodes() {
		return jobCodes;
	}

	public void setJobCodes(List<JobCodeModel> jobCodes) {
		this.jobCodes = jobCodes;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
}