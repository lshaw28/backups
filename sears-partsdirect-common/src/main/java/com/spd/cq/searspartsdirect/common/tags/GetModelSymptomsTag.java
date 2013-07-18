package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.ModelSubcomponentAPIHelper;
import com.spd.cq.searspartsdirect.common.model.PDModelSubcomponentModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;

public class GetModelSymptomsTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	public static final Logger log = LoggerFactory.getLogger(GetModelSymptomsTag.class);

	Session session;
	QueryBuilder builder;
	Query query;
	List<SymptomModel> symptomModels;
	private String brandName;
	private String categoryName;
	private String modelNumber;
	

	@Override
	public int doStartTag() throws JspException {
		List<SymptomModel> symptoms = new ArrayList<SymptomModel>();
		if (!StringUtils.isEmpty(brandName) && !StringUtils.isEmpty(categoryName) && !StringUtils.isEmpty(modelNumber)) {
			ModelSubcomponentAPIHelper apiHelper = new ModelSubcomponentAPIHelper(brandName, categoryName, modelNumber);
			PDModelSubcomponentModel subcomponents = apiHelper.getModelSubcomponents(slingRequest);
			log.debug("brandName "+brandName+ " categoryName "+categoryName+" model is "+modelNumber);
			
			if (subcomponents != null) {
				 log.debug("PD symptom length "+ subcomponents.getSymptomsArr().length);
				 
				 for(int i=0; i<subcomponents.getSymptomsArr().length; i++) {
					log.debug("ids are " + subcomponents.getSymptomsArr()[i].getId());
				 	session = slingRequest.getResourceResolver().adaptTo(Session.class);
					Map<String, String> map = new HashMap<String, String>();
					map.put("path", Constants.ASSETS_PATH + "/symptom");
					map.put("type", Constants.CQ_PAGE);
					map.put("property", "jcr:content/id");
					map.put("property.value", subcomponents.getSymptomsArr()[i].getId());
					
					builder = resourceResolver.adaptTo(QueryBuilder.class);
					query = builder.createQuery(PredicateGroup.create(map), session);
					SearchResult result = query.getResult();
					
					for (Hit hit : result.getHits()) {
					       try {
								ValueMap props = hit.getProperties();
								Page p = pageManager.getPage(hit.getPath());
								if (props != null) {
									SymptomModel symptomModel  = new SymptomModel(p.getPath(), props.get("jcr:title", String.class), props.get("jcr:description", String.class), props.get("id", String.class));
									if (subcomponents.getSymptomsArr()[i].getSuccessfulFrequency() != null) {
										symptomModel.setFrequency(Math.round(subcomponents.getSymptomsArr()[i].getSuccessfulFrequency().doubleValue()));
									}
									symptoms.add(symptomModel);
								}
					       } catch (RepositoryException e) {
								log.error("error while getting model symptoms, ",e.fillInStackTrace());
							}
					}  
				 }
			 }
			pageContext.setAttribute("modelSymptoms", symptoms);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
}
