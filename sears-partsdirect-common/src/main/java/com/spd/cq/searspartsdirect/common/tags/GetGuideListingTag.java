package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PageImpressionsComparator;
import com.spd.cq.searspartsdirect.common.model.RelatedArticleModel;

import org.apache.commons.lang.StringUtils;

public class GetGuideListingTag extends CQBaseTag{
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetGuideListingTag.class);
	protected String categoryPath;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			HashMap<String, List<RelatedArticleModel>> guides = new HashMap<String, List<RelatedArticleModel>>();
			ArrayList<Page> result = new ArrayList<Page>();
			//Get the guides based on the category
			QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
			HashMap<String, String> props = new HashMap<String, String>();
	        props.put("type", "cq:Page");
	        props.put("path", "/content/searspartsdirect/en/guides");
	        props.put("property", "jcr:content/pages");
	        props.put("property.value", categoryPath);
	        
	        List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();
	        for (Hit hit: hits) {
	        	result.add(pageManager.getPage(hit.getPath()));
	        }
	        
	        Collections.sort(result, Collections.reverseOrder(new PageImpressionsComparator(resourceResolver)));
	        for(Page page: result){
	        	String subcategoryName = null;
	        	if (!page.equals(currentPage)) { 
	        		Tag[] tagsArr = page.getTags();
	        		if (tagsArr != null){
		        		for(int i=0; i<=tagsArr.length-1 ; i++){
		        			if (StringUtils.contains(tagsArr[i].getTagID(), Constants.SUBCATEGORY_TAG)){
		        				subcategoryName = tagsArr[i].getName();
		        				break;
		        			}
		        		}
	        		}
	        	}
	        	
	        	if (!(guides.isEmpty()) && guides.containsKey(subcategoryName)){
	        		List<RelatedArticleModel> tmp = guides.get(subcategoryName);
	        		tmp.add( new RelatedArticleModel(
	        				page.getPath() + ".html", 
	        				page.getPath() + Constants.ASSETS_IMAGE_PATH, 
	        				page.getTitle(), 
	        				page.getDescription()));
	        	}else{
	        		List<RelatedArticleModel> tmpGuides = new ArrayList<RelatedArticleModel>();
		        	tmpGuides.add( new RelatedArticleModel(
	        				page.getPath() + ".html", 
	        				page.getPath() + Constants.ASSETS_IMAGE_PATH, 
	        				page.getTitle(), 
	        				page.getDescription()));
		        		//Add the guides to the list 
		        	guides.put(subcategoryName, tmpGuides);
	        	}
        	}
	   
		pageContext.setAttribute("guides", guides);
	        
		}
		catch (Exception e) {
			
		}
		return SKIP_BODY;
	}
 
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}
}
