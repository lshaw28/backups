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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.model.JobCodesModel;
import com.spd.cq.searspartsdirect.common.model.RecoveryCodesModel;
import com.spd.cq.searspartsdirect.common.model.RelatedGuideModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.PartTypeModel;

public class GetJobCodesBySymptomTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	private Session session;
	private QueryBuilder builder;
	private Query query;
	
	List<JobCodeModel> models;
	JobCodesModel jobCodesModel;
	List<RecoveryCodesModel> updatedRecoveryCodesModels;
	List<RelatedGuideModel> guides;
	protected static Logger log = LoggerFactory.getLogger(GetJobCodesBySymptomTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		String symptomId = request.getParameter("symptomId");
		StringBuilder apiUrl = new StringBuilder(EnvironmentSettings.getPDJobCodeApiUrl());
		PartsDirectAPIHelper apiHelper = new PartsDirectAPIHelper();
		
		StringBuilder sb = new StringBuilder();
		sb.append(apiUrl.toString()).append("?symptomId=").append(symptomId);
		
		updatedRecoveryCodesModels = new ArrayList<RecoveryCodesModel>();
		models = new ArrayList<JobCodeModel>();
		jobCodesModel = new JobCodesModel();
		
		try {
			String jsonString = apiHelper.readJsonData("http://partsapivip.qa.ch3.s.com/pd-services/v1/commonSymptoms/jobcode/parts?symptomId=1");
			
			//uncomment following once functionality is hooked up
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
					
					//now get the part type info
					String[] partTypePages = (String[]) props.get("partType", String[].class);
					if (partTypePages != null && partTypePages.length == 1) {
							log.debug(partTypePages[0]);
							if (partTypePages[0].indexOf(Constants.ASSETS_PATH.concat("/partType")) > -1) {
								Page partTypePage = pageManager.getPage(partTypePages[0]);
								PartTypeModel partTypeModel = new PartTypeModel(partTypePage.getPath(), partTypePage.getTitle(), partTypePage.getDescription(), partTypePage.getPath() + Constants.ASSETS_IMAGE_PATH);
								model.setPartTypeModel(partTypeModel);
								log.debug("partTypeModel = "+ partTypeModel.toString());
						}
					}
					
					//now get the guide info
					guides = new ArrayList<RelatedGuideModel>();
					String[] guidePages = (String[]) props.get("guide", String[].class);
					log.debug("guidePages array"+ guidePages);
					if (guidePages != null) {
						for(int i=0; i< guidePages.length; i++) {
							log.debug("guide path "+ guidePages[i]);
							Page guidePage = pageManager.getPage(guidePages[i]);
							if (guidePage != null) {
								RelatedGuideModel guide = new RelatedGuideModel(guidePage.getPath(), null, guidePage.getTitle());
								log.debug("setting the guide"+ guide.toString());
								guides.add(guide);
							}
						}
						model.setGuides(guides);		
					}
					models.add(model);
				}
	        } catch (RepositoryException e) {
				log.error("RepositoryException while getting jobcodes ",e.fillInStackTrace());
			}
	    } 
	    
	    List<RecoveryCodesModel> recoveryCodesModels = jobCodesModel.getRecoveryCodesModel();
	   log.debug("recoveryCodesModels "+ recoveryCodesModels.toString());
	    
	    
	    for (RecoveryCodesModel recoveryCodesModel : recoveryCodesModels) {
	    	for(JobCodeModel jobCodeModel : models) {
	    		if (jobCodeModel.getTitle().equalsIgnoreCase(recoveryCodesModel.getCodeId())) {
	    			recoveryCodesModel.setPartTypeModel(jobCodeModel.getPartTypeModel());
	    			recoveryCodesModel.setGuides(jobCodeModel.getGuides());
	    			log.debug("setPartTypeModel "+jobCodeModel.getPartTypeModel());
	    			log.debug("setGuides "+jobCodeModel.getGuides());
	    			updatedRecoveryCodesModels.add(recoveryCodesModel);
	    		}
	    	}
		}
	    
	    JobCodesModel updatedJobCodesModel = new JobCodesModel();
	    updatedJobCodesModel.setDescription(jobCodesModel.getDescription());
	    updatedJobCodesModel.setModelNumber(jobCodesModel.getModelNumber());
	    updatedJobCodesModel.setRecoveryCodesModel(updatedRecoveryCodesModels);
	    
	    jobCodesModel.setRecoveryCodesModel(recoveryCodesModels);
	    log.error("updated jobcodeModel "+ jobCodesModel);
	    pageContext.setAttribute("symptomJobCodes", updatedJobCodesModel);
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
