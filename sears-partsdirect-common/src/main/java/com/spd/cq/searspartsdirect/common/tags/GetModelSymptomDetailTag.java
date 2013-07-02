package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.ValueMap;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.ModelSymptomModel;
import com.spd.cq.searspartsdirect.common.model.RelatedGuideModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.PartTypeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;

public class GetModelSymptomDetailTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	private Session session;
	private QueryBuilder builder;
	private Query query;
	ModelSymptomModel modelSymptomModel;
	
	List<JobCodeModel> jobCodeModels;

	@Override
	public int doStartTag() throws JspException {
		//read the symptom seo text from url and get the symptom first
		
		String symptomId = request.getParameter("id");
		
		session = slingRequest.getResourceResolver().adaptTo(Session.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("path", Constants.ASSETS_PATH + "/symptom");
		map.put("type", Constants.CQ_PAGE);
		map.put("property", "jcr:content/id");
		map.put("property.value", "203");
		
		builder = resourceResolver.adaptTo(QueryBuilder.class);
		query = builder.createQuery(PredicateGroup.create(map), session);
		SearchResult result = query.getResult();
		
		modelSymptomModel = new ModelSymptomModel();
		
		for (Hit hit : result.getHits()) {
	       try {
				ValueMap props = hit.getProperties();
				Page p = pageManager.getPage(hit.getPath());
				if (props != null) {
					SymptomModel symptomModel  = new SymptomModel(p.getPath(), props.get("jcr:title", String.class), props.get("jcr:description", String.class), props.get("id", String.class));
					
					// now get the jobcode and gudes info
					String[] pages = (String[]) props.get("pages", String[].class);
					if (pages != null) {
						jobCodeModels = new ArrayList<JobCodeModel>();
						for(int i = 0; i< pages.length; i++) {
							log.error("**********"+ pages[i]);
							if (pages[i].indexOf(Constants.ASSETS_PATH.concat("/jobCode")) > -1) {
								Page jobCodePage = pageManager.getPage(pages[i]);
								if (jobCodePage != null) {
									JobCodeModel jobCodeModel = new JobCodeModel(jobCodePage.getPath(), jobCodePage.getTitle(), jobCodePage.getDescription());
									//getting jobcode
									ValueMap jobcodeProps = jobCodePage.getProperties();
									if (jobcodeProps != null) {
										String partType = (String) jobcodeProps.get("partType");
										Page partTypePage = pageManager.getPage(partType);
										
										PartTypeModel partTypeModel = new PartTypeModel(partTypePage.getPath(), 
																	partTypePage.getTitle(), partTypePage.getDescription(), 
																	partTypePage.getPath() + Constants.ASSETS_IMAGE_PATH);
										jobCodeModel.setPartTypeModel(partTypeModel);
										jobCodeModels.add(jobCodeModel);
										
										//getting guides
										String[] guides = (String[]) jobcodeProps.get("guide", String[].class);
										if (guides != null) {
											List<RelatedGuideModel> guideList = new ArrayList<RelatedGuideModel>();
											for (int j = 0; j<guides.length; j++) {
												Page guidePage = pageManager.getPage(guides[j]);
												RelatedGuideModel guide = new RelatedGuideModel(guidePage.getPath(), null, guidePage.getTitle());
												guideList.add(guide);
											}
											jobCodeModel.setGuides(guideList);
										}
									}
								}
							}
						}
					}
					modelSymptomModel.setJobCodeModels(jobCodeModels);
					modelSymptomModel.setSymptomModel(symptomModel);
				}
			} catch (RepositoryException e) {
				log.error("Failure building results, ",e);
			}
	    }
		
		pageContext.setAttribute("modelSymptom", modelSymptomModel);
		return SKIP_BODY;	
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
