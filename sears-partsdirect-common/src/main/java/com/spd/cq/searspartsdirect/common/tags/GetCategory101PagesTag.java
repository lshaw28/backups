package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PDUtils;
import com.spd.cq.searspartsdirect.common.model.ArticleModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

/**
 * custom tag to return all the article Models
 * for a given product category, that have a Category 101 Tag
 * @author dmartinez
 *
 */
public class GetCategory101PagesTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;

	protected final static Logger log = LoggerFactory.getLogger(GetCategory101PagesTag.class);

	protected ProductCategoryModel category;
	private String category101TagID = Constants.TAGS_FEATURES_PATH + "/category_101";

	@Override
	public int doStartTag() throws JspException {

		// find all articles, filtered by category
		try {
			ArrayList<Page> result = new ArrayList<Page>();
			QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
			HashMap<String, String> props = new HashMap<String, String>();
			props.put("type", "cq:Page");
			props.put("path", Constants.ARTICLES_ROOT);
			props.put("property", Constants.ASSETS_PAGES_REL_PATH);
			props.put("property.value", category.getPath());
            ArrayList<ArticleModel> category101Models = new ArrayList<ArticleModel>();

			List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();

			for (Hit hit: hits) {
				result.add(pageManager.getPage(hit.getPath()));
			}
            for(Page page: result){
                // turn pages to models
                ArticleModel articleModel = getArticleModel(page);
                if(articleModel != null) {
                    category101Models.add(articleModel);
                }
            }

            if(StringUtils.isNotEmpty(category.getPath())) { // check that categoryPath is not empty b/c page blows up otherwise
                String categoryName = category.getTrueName();
                String pagePathPrefix = Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" + categoryName;

                if (PDUtils.doesPageContainCategoryAsset(pageManager, pagePathPrefix + Constants.COMMON_PARTS_PATH_SUFFIX)) {
                    Page page = pageManager.getPage(pagePathPrefix + Constants.COMMON_PARTS_PATH_SUFFIX);
                    ArticleModel articleModel = getArticleModel(page);
                    if(articleModel != null) {
                        category101Models.add(articleModel);
                    }
                }

                if (PDUtils.doesPageContainCategoryAsset(pageManager, pagePathPrefix + Constants.COMMON_QUESTIONS_PATH_SUFFIX)) {
                    Page page = pageManager.getPage(pagePathPrefix + Constants.COMMON_QUESTIONS_PATH_SUFFIX);
                    ArticleModel articleModel = getArticleModel(page);
                    if(articleModel != null) {
                        category101Models.add(articleModel);
                    }
                }

                if (PDUtils.doesPageContainCategoryAsset(pageManager, pagePathPrefix + Constants.MAINTENANCE_TIPS_PATH_SUFFIX)) {
                    Page page = pageManager.getPage(pagePathPrefix + Constants.MAINTENANCE_TIPS_PATH_SUFFIX);
                    ArticleModel articleModel = getArticleModel(page);
                    if(articleModel != null) {
                        category101Models.add(articleModel);
                    }
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

	public void setCategory(ProductCategoryModel category) {
		this.category = category;
	}

    // turn pages to models
    private ArticleModel getArticleModel(Page page) throws Exception{
        TagManager tm = resourceResolver.adaptTo(TagManager.class);
        Tag cat101Tag = tm.resolve(category101TagID);

        Tag[] pageTags = page.getTags();
        List<Tag> pageTagsArray = new ArrayList<Tag>(Arrays.asList(pageTags));

        ArticleModel articleModel = null;
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

            // turn pages to models
            articleModel = new ArticleModel(page, imagePath);
        }

        return articleModel;
    }
}
