package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

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
import com.spd.cq.searspartsdirect.common.model.ErrorCodeListModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ErrorCodeModel;

public class GetErrorCodesDataTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	private String categoryPath;
	private String subCategoryPath;
	private String brandPath;
	
	Session session;
	QueryBuilder builder;
	Query query;
	
	protected static Logger log = LoggerFactory.getLogger(GetErrorCodesDataTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		if (categoryPath != null && brandPath != null) {
			log.debug("category and brand names:-"+categoryPath +"   "+brandPath + "subcat="+subCategoryPath);
			List<ErrorCodeModel> errorCodeModelList = new ArrayList<ErrorCodeModel>();
			session = slingRequest.getResourceResolver().adaptTo(Session.class);
			Map<String, String> map = new HashMap<String, String>();
			map.put("path", "/etc/spdAssets/scaffolding/errorCode/");
			map.put("type", Constants.CQ_PAGE);
			map.put("2_property", "jcr:content/pages");
			map.put("2_property.value", categoryPath);
			map.put("3_property", "jcr:content/pages");
			map.put("3_property.value", brandPath);
			
			//need to check the following
			/*if (subCategoryPath != null) {
				map.put("4_property", "jcr:content/cq:tags");
				map.put("4_property.value", subCategoryPath);
			}*/
			
			builder = resourceResolver.adaptTo(QueryBuilder.class);
			query = builder.createQuery(PredicateGroup.create(map), session);
			SearchResult result = query.getResult();
			log.debug("hits"+result.getHits());
			 for (Hit hit : result.getHits()) {
		        try {
					ValueMap props = hit.getProperties();
					if (props != null) {
						ErrorCodeModel model = new ErrorCodeModel("",props.get("jcr:title", String.class), props.get("jcr:description", String.class), props.get("repairLink", String.class));
						errorCodeModelList.add(model);
					}
				} catch (RepositoryException e) {
					e.printStackTrace();
				}
			 }
			pageContext.setAttribute("errorCodeTable", errorCodeModelList);
		} else if (categoryPath != null) {
			
			HashMap<BrandModel, List<ErrorCodeListModel>> errorCodeList = new HashMap<BrandModel, List<ErrorCodeListModel>>();
			Map<BrandModel, List<ErrorCodeListModel>> finalErrorCodeList = new LinkedHashMap<BrandModel, List<ErrorCodeListModel>>();
			
			session = slingRequest.getResourceResolver().adaptTo(Session.class);
			Map<String, String> map = new HashMap<String, String>();
			map.put("path", "/content/searspartsdirect/en/error-tables");
			map.put("type", Constants.CQ_PAGE);
			map.put("1_property", "jcr:content/pages");
			map.put("1_property.value", categoryPath);
			 
			builder = resourceResolver.adaptTo(QueryBuilder.class);
			query = builder.createQuery(PredicateGroup.create(map), session);
			
			SearchResult result = query.getResult();
			log.debug("total results found "+ result.getQueryStatement().toString());
			
		    for (Hit hit : result.getHits()) {
		        try {
					ValueMap props = hit.getProperties();
					Page p = pageManager.getPage(hit.getPath());
					if (props != null) {
						ErrorCodeListModel model = new ErrorCodeListModel(props.get("jcr:title", String.class), p.getPath());
						String[] pages = (String[]) props.get("pages", String[].class);
						
						if (pages != null) {
							log.debug("pages.length "+pages.length);
							for(int i = 0; i< pages.length; i++) {
								log.debug(pages[i]);
								if (pages[i].indexOf(Constants.ASSETS_PATH.concat("/brand")) > -1) {
									List<ErrorCodeListModel> errorCodeModels = new ArrayList<ErrorCodeListModel>();
									Page brandPage = pageManager.getPage(pages[i]);
									//log.debug("brandPage.getTitle()"+brandPage.getTitle() +"description "+ brandPage.getDescription());
									BrandModel brandModel = new BrandModel("",brandPage.getTitle(), brandPage.getDescription(), brandPage.getPath() + Constants.ASSETS_LOGO_PATH);
									
									//TODO - need to clean up the following messy code to have a better code option
									if (!errorCodeList.containsKey(brandModel)) {
										errorCodeModels.add(model);
										errorCodeList.put(brandModel, errorCodeModels);
									} else {
										List<ErrorCodeListModel> newModel = errorCodeList.get(brandModel);
										newModel.add(model);
										errorCodeList.remove(brandModel);
										errorCodeList.put(brandModel, newModel);
									}
								}
							}
						}
					}
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		    //need to sort the collection by brand name
		    List<BrandModel> brandKeys = new ArrayList<BrandModel>();
            brandKeys.addAll(errorCodeList.keySet());
            Collections.sort(brandKeys, new Comparator<BrandModel>() {
                public int compare(BrandModel o1, BrandModel o2) {
                    return o1.getTitle().compareToIgnoreCase(o2.getTitle());
                }
            });
            
           for (BrandModel brandModel : brandKeys) {
				finalErrorCodeList.put(brandModel, errorCodeList.get(brandModel));
			}
           
			pageContext.setAttribute("finalErrorCodeList", finalErrorCodeList);
		} 
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	public String getSubCategoryPath() {
		return subCategoryPath;
	}

	public void setSubCategoryPath(String subCategoryPath) {
		this.subCategoryPath = subCategoryPath;
	}

	public String getBrandPath() {
		return brandPath;
	}

	public void setBrandPath(String brandPath) {
		this.brandPath = brandPath;
	}

	
}