package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PageImpressionsComparator;
import com.spd.cq.searspartsdirect.common.model.GuideModel;

public class GetRelatedGuidesTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetRelatedGuidesTag.class);
	
	protected String categoryPath;
	protected int maxOutput = 4;
	
	public static final String REL_GUIDES_ATTR = Constants.ident("relatedGuides");
	
	@Override
	public int doStartTag() throws JspException {
		
		// We start with, any guides directly related to the page
		List<GuideModel> guides = findDirectlyRelatedGuides();
		
		// If we don't already have enough, we will do a search for guides for the passed category
		if (guides.size() < maxOutput) {
			try {
				List<Page> result = findMostPopularGuidePagesInCategory();
		        
		        for(Page page: result){
		        	
		        	GuideModel guideModel = makeModelFromPage(page);
		        	
		        	if (guides.size() < maxOutput) { // if we don't have enough
		        		if (!guides.contains(guideModel)) { // and it isn't a dup,
		        			guides.add(guideModel);
		        		} // else we continue, the next one might not be a dup
		        	} else {
		        		break; // we have enough, we're done
		        	}
		        }	        	
			}
			catch (Exception e) {
				log.error("Error finding related guides: " + e.toString());
			}
		}
		pageContext.setAttribute("guides", guides);
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
	}
	
	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}
	
	public void setMaxOutput(int maxOutput) {
		this.maxOutput  = maxOutput;
	}
	
	public List<GuideModel> findDirectlyRelatedGuides() {
		List<GuideModel> directlyRelated = new ArrayList<GuideModel>();
		String[] empty = new String[0];
		Page workingPage = currentPage;
		String[] relations = workingPage.getProperties().get(REL_GUIDES_ATTR, empty);
		for (int i = 0; i < relations.length && directlyRelated.size() < maxOutput; i++) {
			if (Pattern.matches(Constants.GUIDES_ROOT + "/[^/]+", relations[i])) {
				Page p = pageManager.getPage(relations[i]);
				if (p != null) {
					GuideModel model = makeModelFromPage(p);
					if (!directlyRelated.contains(model)) {
						directlyRelated.add(model);
					}
				} else {
					log.warn("Could not resolve path "+relations[i]+" to a page");
				}
			}
		}
		return directlyRelated;
	}
	
	public List<Page> findMostPopularGuidePagesInCategory() {
		List<Page> result = new ArrayList<Page>();
		
		QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
		HashMap<String, String> props = new HashMap<String, String>();
        props.put("type", Constants.CQ_PAGE);
        props.put("path", Constants.GUIDES_ROOT);
        props.put("property", Constants.ASSETS_PAGES_REL_PATH);
        props.put("property.value", categoryPath);
        
        List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();

        for (Hit hit: hits) {
        	// We collect all the results, because we do not know in advance where the most popular results will fall.
        	try {
        		Page page = pageManager.getPage(hit.getPath());
        		if (page != null && !page.equals(currentPage)) {
        			result.add(page);
        		}
			} catch (RepositoryException e) {
				log.error("Could not resolve a search hit: ", e);
			}
        }
        
        Collections.sort(result,Collections.reverseOrder(new PageImpressionsComparator(resourceResolver)));
        
        return result;
	}
	
	private GuideModel makeModelFromPage(Page page) {
		return new GuideModel(
    			page.getPath() + ".html", 
    			page.getPath() + Constants.ASSETS_IMAGE_PATH, 
    			page.getTitle());
	}
}
