package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PageImpressionsComparator;
import com.spd.cq.searspartsdirect.common.model.PageStatistics;
import com.spd.cq.searspartsdirect.common.model.RelatedArticleModel;

public class GetGuideListingTag extends CQBaseTag{
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetGuideListingTag.class);
	protected String categoryPath;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			List<RelatedArticleModel> guides = new ArrayList<RelatedArticleModel>();
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
	        	if (!page.equals(currentPage)) { // we exclude ourself from results
	        		//page.getProperties().containsKey(
	        		Tag[] tagsArr = page.getTags();
	        		log.debug("Tags" + page.getTags().toString());
	        		/*List <Tag>  tags = Arrays.asList(tagsArr);
	        		if (tags.contains("searspartsdirect:subcategories")) {
	        			
	        		}*/
	        		

	        		guides.add(new RelatedArticleModel(
		        				page.getPath() + ".html", 
		        				page.getPath() + Constants.ASSETS_IMAGE_PATH, 
		        				page.getTitle(), 
		        				page.getDescription())
	        				);
	        	}
	        }	     
	        // dummy data
		/*	PageStatistics guide1 = new PageStatistics("Title1","url1.html", "imagePath1", 4L);
			PageStatistics guide2 = new PageStatistics("title2","url2.html", "imagePath2", 5L);
			PageStatistics guide3 = new PageStatistics("title3","url3.html", "imagePath3", 3L);
			PageStatistics guide4 = new PageStatistics("title4","url4.html", "imagePath4", 2L);

			pageList.add(guide1);
			pageList.add(guide2);				
			pageList.add(guide3);			
			pageList.add(guide4); */
	        
	   
		pageContext.setAttribute("guides", guides.get(0));
		pageContext.setAttribute("viewAllGuides", guides);
	        
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
