package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.RelatedArticleModel;

/**
 * custom tag to return all the article Models
 * for a given product category, that have a Category 101 Tag
 * @author dmartinez
 *
 */
public class GetCategory101PagesTag extends CQBaseTag {
	
	protected String category;
	private String category101TagID = Constants.TAGS_FEATURES_PATH + "/category_101";
		
	@Override
	public int doStartTag() throws JspException {
		
		ArrayList<RelatedArticleModel> category101Models = new ArrayList<RelatedArticleModel>();
		Tag[] pageTags = null;
		TagManager tm = resourceResolver.adaptTo(TagManager.class);
		Tag cat101Tag = tm.resolve(category101TagID);

		// find all articles, filtered by category
		try {
			ArrayList<Page> result = new ArrayList<Page>();
			QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
			HashMap<String, String> props = new HashMap<String, String>();
	        props.put("type", "cq:Page");
	        props.put("path", "/content/searspartsdirect/en/articles");
	        props.put("property", "jcr:content/pages");
	        props.put("property.value", category);
	        
	        List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();

	        for (Hit hit: hits) {
	        	result.add(pageManager.getPage(hit.getPath()));
	        }
	        String description = "";
	        for(Page page: result){
	        	pageTags = page.getTags();
	        	List<Tag> pageTagsArray = new ArrayList<Tag>(Arrays.asList(pageTags));
	    		
	        	// filter those pages by cat101 tag
	        	if(pageTagsArray.contains(cat101Tag)){
	        		
					// We need to resolve the image path, and hand out a blank for image if the image does not exist
	        		String imagePath = page.getPath() + Constants.ASSETS_IMAGE_PATH;
	        		Resource imageResource = resourceResolver.getResource(imagePath);
	        		if (imageResource == null) {
	        			// If we cannot resolve to an image, we return a blank string
	        			imagePath = Constants.EMPTY;
	        		} else {
	        			Node imageNode = imageResource.adaptTo(Node.class);
	        			if (!(imageNode.hasProperty("fileReference") || imageNode.hasNode("file"))) {
	        				// If the image is not set up one way or another, we return a blank string
	        				imagePath = Constants.EMPTY;
	        			}
	        		}
	        		
		        	if(page.getProperties().containsKey("abstracttext")){
		        		description = page.getProperties().get("abstracttext").toString();
		        	} else {
		        		description = "";
		        	}
		    		// turn pages to models
		        	category101Models.add(new RelatedArticleModel(
		        			page.getPath(), 
		        			imagePath, 
		        			page.getTitle(),
		        			description));
	        	}
	        }	        	
		
		pageContext.setAttribute("category101Models", category101Models);
		
		}
		catch(Exception e){
			log.error("Error finding category 101 tagged pages", e);
		}
		 return SKIP_BODY;
	}
			
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
	}
			
	public void setCategory(String category) {
		this.category = category;
	}
}
