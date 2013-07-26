package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PDUtils;
import com.spd.cq.searspartsdirect.common.helpers.PageImpressionsComparator;
import com.spd.cq.searspartsdirect.common.model.ArticleModel;

public class GetCategoryArticleListTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetCategoryArticleListTag.class);
	protected String categoryPath;
	protected String categoryName;
	
	@Override
	public int doStartTag() throws JspException {
		
		Map<String,List<ArticleModel>> articles = new LinkedHashMap<String,List<ArticleModel>>();
		try {

			List<Page> result = new ArrayList<Page>();

			QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
			Map<String, String> props = new HashMap<String, String>();
	        props.put("type", Constants.CQ_PAGE);
	        props.put("path", Constants.ARTICLES_ROOT);
	        props.put("property", Constants.ASSETS_PAGES_REL_PATH);
	        props.put("property.value", categoryPath);
	        
	        List<Hit> hits = qb.createQuery(
	        			PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)
	        		).getResult().getHits();

	        for (Hit hit: hits) {
	        	result.add(pageManager.getPage(hit.getPath()));
	        }
	        
	        String[] relatedPageUrls = new String[] { Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" + categoryName + Constants.COMMON_PARTS_PATH_SUFFIX,
	        		Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" +categoryName + Constants.COMMON_QUESTIONS_PATH_SUFFIX,
	        		Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" + categoryName + Constants.MAINTENANCE_TIPS_PATH_SUFFIX};
	        
	        for (int i=0; i<relatedPageUrls.length; i++) {
	        	if (getPageByPath(relatedPageUrls[i])) {
	        		log.debug("this page should be added " + relatedPageUrls[i]);
		        	result.add(pageManager.getPage(relatedPageUrls[i]));
		        }
	        }
	        
	        Collections.sort(result, Collections.reverseOrder(new PageImpressionsComparator(resourceResolver)));
	          
	        for(Page page: result){
	        	if (!page.equals(currentPage)) { // we exclude ourself from results
	        		String subcategory = PDUtils.getSubcategoryFromPage(page);
	        		List<ArticleModel> articleList = articles.get(subcategory);
	        		if (articleList == null) {
	        			articleList = new ArrayList<ArticleModel>();
	        			articles.put(subcategory, articleList);
	        		}
	        		articleList.add(getArticleModelFromPage(page));
	        	}
	        }	        	
		}
		catch (Exception e) {
			log.error("Failure building article list, ",e);
		}
		pageContext.setAttribute("articles", articles);
        return SKIP_BODY;
	}
	
	public ArticleModel getArticleModelFromPage(Page page) throws RepositoryException {
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
		return new ArticleModel(
				page.getPath() + ".html", 
				imagePath, 
				page.getTitle(), 
				page.getProperties().get("abstracttext",Constants.EMPTY).toString());
	}
	
	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	private boolean getPageByPath(String pagePath) {
		log.debug("page path is "+pagePath);
        Page commonPartsPage = pageManager.getPage(pagePath);
        if (commonPartsPage != null) {
        	log.debug("***Page is not null" + pagePath);
        	ValueMap commonPartsPageProp = commonPartsPage.getProperties();
        	String[] pages = commonPartsPageProp.get("pages", String[].class);
        	if (pages != null) {
	        	for (int i =0; i< pages.length; i++) {
	        		if (pages[i].contains("/productCategory")) {
	        			return true;
	        		}
	        	}
        	}
        }
        return false;
	}
}
