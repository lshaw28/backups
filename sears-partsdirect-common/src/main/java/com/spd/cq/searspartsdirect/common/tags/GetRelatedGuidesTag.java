package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Session;
import javax.servlet.jsp.JspException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.RelatedGuideModel;

public class GetRelatedGuidesTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetRelatedGuidesTag.class);
	protected String categoryPath;
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<RelatedGuideModel> guides = new ArrayList<RelatedGuideModel>();
		
		try {

			ArrayList<Page> result = new ArrayList<Page>();

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
	        
	        // populate only up to four elements of "guides" return ArrayList, as per specifications
	        // May need to update this code in order to accommodate Guide selection preference
	        if (result.size() <= 4){
		        for(Page page: result){
		        	guides.add(new RelatedGuideModel(
		        			page.getPath() + ".html", 
		        			page.getPath() + Constants.ASSETS_IMAGE_PATH, 
		        			page.getTitle()));
		        }	        	
	        }
	        else {
	        	for (int i=0; i < 4; i++){
	        		guides.add(new RelatedGuideModel(
	        				result.get(i).getPath() + ".html", 
	        				result.get(i).getPath() + Constants.ASSETS_IMAGE_PATH, 
	        				result.get(i).getTitle()));
	        	}
	        }
			
			pageContext.setAttribute("guides", guides);
		}
		catch (Exception e) {
			log.error("Error finding related guides: " + e.toString());
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
