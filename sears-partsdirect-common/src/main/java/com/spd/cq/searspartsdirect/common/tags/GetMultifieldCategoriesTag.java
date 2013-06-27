package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.CategoryModel;

public class GetMultifieldCategoriesTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetMultifieldCategoriesTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<CategoryModel> categories = new ArrayList<CategoryModel>();
		List<Page> pages = new ArrayList<Page>();

		try {
			String[]  multiJsons = properties.get("multipaths",new String[0]);
			for (String json : multiJsons) {
				JSONObject jsob = new JSONObject(json);
				pages.add(pageManager.getPage(jsob.getString("url")));
			}
			String description = "";
			for (Page page: pages){
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
        		
				CategoryModel category = new CategoryModel(
						page.getPath(),
						imagePath,
						page.getTitle(),
						description);
				categories.add(category);
			}
			pageContext.setAttribute("categories", categories);
		}
		catch (Exception e) {
			log.error("Error creating multifield categories", e);
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
	}
}
