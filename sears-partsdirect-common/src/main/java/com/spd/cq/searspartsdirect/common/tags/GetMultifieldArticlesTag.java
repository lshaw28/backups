package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PDUtils;
import com.spd.cq.searspartsdirect.common.model.CategoryModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

public class GetMultifieldArticlesTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetMultifieldArticlesTag.class);
	protected ProductCategoryModel category;
	
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<CategoryModel> articles = new ArrayList<CategoryModel>();
		List<Page> pages = new ArrayList<Page>();
		ValueMap assetProperties = null;
		if(category != null && StringUtils.isNotEmpty(category.getPath())){ // check that categoryPath is not empty b/c page blows up otherwise
			try {
			assetProperties = pageManager.getPage(category.getPath()).getProperties();
			String[]  multiJsons = assetProperties.get("multipaths",new String[0]);
			for (String json : multiJsons) {
				JSONObject jsob = new JSONObject(json);
				pages.add(pageManager.getPage(jsob.getString("url")));
			}
			String description = "";
			for (Page page: pages) {
					if (page != null) {
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
					articles.add(category);
				}
			}
			
			String categoryName = category.getTrueName();
			String includeCommonParts = assetProperties.get("includeCommonParts", "");
			String includeMaintenanceTips = assetProperties.get("includeMaintenanceTips", "");
			String includeCommonQuestions = assetProperties.get("includeCommonQuestions", "");
			
			if ("true".equals(includeCommonParts) 
					&& PDUtils.doesPageContainCategoryAsset(pageManager, Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" +  categoryName + Constants.COMMON_PARTS_PATH_SUFFIX)) {
				Page page = pageManager.getPage(Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" + categoryName + Constants.COMMON_PARTS_PATH_SUFFIX);
				if (currentPage.getPath().compareTo(page.getPath()) != 0) {
					CategoryModel category = new CategoryModel(page.getPath(), null, page.getTitle(), null);
					articles.add(category);
				}
			}
			
			if ("true".equals(includeCommonQuestions) 
					&& PDUtils.doesPageContainCategoryAsset(pageManager, Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" + categoryName + Constants.COMMON_QUESTIONS_PATH_SUFFIX)) {
				Page page = pageManager.getPage(Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" + categoryName + Constants.COMMON_QUESTIONS_PATH_SUFFIX);
				if (currentPage.getPath().compareTo(page.getPath()) != 0) {
					CategoryModel category = new CategoryModel(page.getPath(), null, page.getTitle(), null);
					articles.add(category);
				}
			}
			if ("true".equals(includeMaintenanceTips) 
						&& PDUtils.doesPageContainCategoryAsset(pageManager, Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" + categoryName + Constants.MAINTENANCE_TIPS_PATH_SUFFIX)) {
				Page page = pageManager.getPage(Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" + categoryName + Constants.MAINTENANCE_TIPS_PATH_SUFFIX);
				if (currentPage.getPath().compareTo(page.getPath()) != 0) {
					CategoryModel category = new CategoryModel(page.getPath(), null, page.getTitle(), null);
					articles.add(category);
				}
			}
			
			String header = assetProperties.get("header", "");
			String link = assetProperties.get("viewAllLinkText", "");

			pageContext.setAttribute("articles", articles);
			pageContext.setAttribute("category101header", header);
			pageContext.setAttribute("category101linkText", link);
		}
		catch (Exception e) {
			log.error("Error creating multifield categories", e);
			e.printStackTrace();
		}
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
	}
	
	public void setCategory(ProductCategoryModel category) {
		this.category = category;
	}
	

	
}
