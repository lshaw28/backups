package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONException;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.model.JobCodesModel;
import com.spd.cq.searspartsdirect.common.model.RecoveryCodesModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;

public class GetJobCodesBySymptomTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	private Session session;
	private QueryBuilder builder;
	private Query query;
	
	List<JobCodeModel> models = new ArrayList<JobCodeModel>();
	JobCodesModel jobCodesModel = new JobCodesModel();
	
	@Override
	public int doStartTag() throws JspException {
		String symptomId = request.getParameter("symptomId");
		StringBuilder apiUrl = new StringBuilder(EnvironmentSettings.getPDJobCodeApiUrl());
		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
		
		StringBuilder sb = new StringBuilder();
		sb.append(apiUrl.toString()).append("?symptomId=").append(symptomId);
		
		try {
			String jsonString = apiHelper.readJsonData("http://partsapivip.qa.ch3.s.com/pd-services/v1/commonSymptoms/jobcode/parts?symptomId=1");
			//String jsonString = apiHelper.readJsonString(sb.toString());
			Gson gson = new Gson();
	        jobCodesModel = gson.fromJson(jsonString, JobCodesModel.class);
	      } catch (IOException e) {
			log.error("IOException while getting jobcodes by symptom", e.fillInStackTrace());
			e.printStackTrace();
		} catch (JSONException e) {
			log.error("JSONException while getting jobcodes by symptom", e.fillInStackTrace());
			e.printStackTrace();
		} 
			
		session = slingRequest.getResourceResolver().adaptTo(Session.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("path", Constants.ASSETS_PATH+"/jobCode");
		map.put("type", Constants.CQ_PAGE);
		 
		builder = resourceResolver.adaptTo(QueryBuilder.class);
		query = builder.createQuery(PredicateGroup.create(map), session);
		SearchResult result = query.getResult();

	    for (Hit hit : result.getHits()) {
	        try {
				ValueMap props = hit.getProperties();
				if (props != null) {
					JobCodeModel model = new JobCodeModel("", props.get("jcr:title", String.class), props.get("jcr:description", String.class));
					models.add(model);
				}
	        } catch (RepositoryException e) {
				log.error("RepositoryException while getting jobcodes ",e.fillInStackTrace());
			}
	    } 
	    
	    List<RecoveryCodesModel> recoveryCodesModels = jobCodesModel.getRecoveryCodesModel();
	    log.error("recoveryCodesModels "+ recoveryCodesModels.toString());
	    
	    List<RecoveryCodesModel> updatedRecoveryCodesModels =  new ArrayList<RecoveryCodesModel>();
	    for (RecoveryCodesModel recoveryCodesModel : recoveryCodesModels) {
	    	for(JobCodeModel jobCodeModel : models){
	    		if (jobCodeModel.getTitle().equalsIgnoreCase(recoveryCodesModel.getCodeId())) {
	    			updatedRecoveryCodesModels.add(recoveryCodesModel);
	    		}
	    	}
		}
	    
	    JobCodesModel updatedJobCodesModel = new JobCodesModel();
	    updatedJobCodesModel.setDescription(jobCodesModel.getDescription());
	    updatedJobCodesModel.setModelNumber(jobCodesModel.getModelNumber());
	    updatedJobCodesModel.setRecoveryCodesModel(updatedRecoveryCodesModels);
	    
	    jobCodesModel.setRecoveryCodesModel(recoveryCodesModels);
	    log.debug("jobCodesModel "+ jobCodesModel.toString());
	    log.debug("updatedJobCodesModel "+ updatedJobCodesModel.toString());
	    pageContext.setAttribute("symptomJobCodes", updatedJobCodesModel);
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
