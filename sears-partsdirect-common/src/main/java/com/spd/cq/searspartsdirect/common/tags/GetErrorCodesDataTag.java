package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.model.spdasset.ErrorCodeModel;

public class GetErrorCodesDataTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	private String categoryName;
	private String subCategoryName;
	private String brandName;
	
	@Override
	public int doStartTag() throws JspException {
		if (categoryName != null && subCategoryName != null && brandName != null) {
			//get the pages from scaffolding page by passing category, subcategory and brand and loop thru them to extract and store in
			// errorCodeTable with error-code-type as a key and code, desc, condition, repair in the form of an object
			Map<String, ArrayList<ErrorCodeModel>> errorCodeTable = new LinkedHashMap<String, ArrayList<ErrorCodeModel>>();
			ArrayList<ErrorCodeModel> errorCodeModels = new ArrayList<ErrorCodeModel>();
			ErrorCodeModel errorCodeModel1 = new ErrorCodeModel("101", "recalled by the manufacturer", null);
			ErrorCodeModel errorCodeModel2 = new ErrorCodeModel("102","major issue with the engine", "call kenmore at 1-800-KENMORE");

			errorCodeModels.add(errorCodeModel1);
			errorCodeModels.add(errorCodeModel2);
			
			errorCodeTable.put("Error Code Type 1", errorCodeModels);
			pageContext.setAttribute("errorCodeTable", errorCodeTable);
		} else if (categoryName != null) {
			//using QueryBuilder
			Map<String, ArrayList<ErrorCodeModel>> errorCodeList = new LinkedHashMap<String, ArrayList<ErrorCodeModel>>();
			ArrayList<ErrorCodeModel> errorCodeModels = new ArrayList<ErrorCodeModel>();
			
			Session session = slingRequest.getResourceResolver().adaptTo(Session.class);
			Map<String, String> map = new HashMap<String, String>();
			map.put("path", "/etc/spdAssets/scaffolding/errorCode/");
			map.put("type", "cq:Page");
			// map.put("property", "jcr:title");
			//map.put("property.1_value", "Error Code");
			//map.put("fulltext", categoryName) //need to figure out how to pass the input param 
			 
			QueryBuilder builder = resourceResolver.adaptTo(QueryBuilder.class);
			/*log.error("build is "+ builder);
			log.error("session is "+ session);*/
			Query query = builder.createQuery(PredicateGroup.create(map), session);
			
			SearchResult result = query.getResult();
			log.error("total results found "+ result.getQueryStatement().toString());
			
			// iterating over the results
		    for (Hit hit : result.getHits()) {
		        try {
					/*String path = hit.getPath();
					  log.error(path);*/
					ValueMap props = hit.getProperties();
					if (props != null) {
						ErrorCodeModel model = new ErrorCodeModel(props.get("jcr:title", String.class), props.get("jcr:description", String.class), null);
						errorCodeModels.add(model);
					}
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    errorCodeList.put("HardCoded Type", errorCodeModels);
			pageContext.setAttribute("errorCodeList", errorCodeList);
		} else {
			//using the resource resolver
			ArrayList<ErrorCodeModel> errorCodes = new ArrayList<ErrorCodeModel>();
			Resource r = resourceResolver.getResource("/etc/spdAssets/scaffolding/errorCode");
			if (r != null) {
				Iterator<Resource> iter = r.listChildren();
				while (iter.hasNext()) {
					Resource child = iter.next();
					Page p = child.adaptTo(Page.class);
					ValueMap properties = p.getProperties();
					errorCodes.add(new ErrorCodeModel(properties.get("jcr:title",""), properties.get("jcr:description",""), null));
				}
			}
			pageContext.setAttribute("errorCodeList", errorCodes);
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}