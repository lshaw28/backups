package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.spd.cq.searspartsdirect.common.model.ArticleModel;

public class GetRelatedArticlesTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetRelatedArticlesTag.class);
	protected String categoryPath;
	
	@Override
	public int doStartTag() throws JspException {
		
		ArrayList<ArticleModel> articles = new ArrayList<ArticleModel>();
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
	        
	        Collections.sort(result, Collections.reverseOrder(new PageImpressionsComparator(resourceResolver)));
	          
	        String description = "";
			if (result.size() <= 4){
				for(Page page: result){
					if(page.getProperties().containsKey("abstracttext")){
						description = page.getProperties().get("abstracttext").toString();
					} else {
						description = "";
					}
					articles.add(new ArticleModel(
							page.getPath() + ".html",
							page.getPath() + Constants.ASSETS_IMAGE_PATH,
							page.getTitle(),
							description));
				}
			}
			else {
				for (int i=0; i < 4; i++){
					if(result.get(i).getProperties().containsKey("abstracttext")){
						description = result.get(i).getProperties().get("abstracttext").toString();
					} else {
						description = "";
					}
					articles.add(new ArticleModel(
							result.get(i).getPath() + ".html",
							result.get(i).getPath() + Constants.ASSETS_IMAGE_PATH,
							result.get(i).getTitle(),
							description));
				}
			}

		}
		
		catch (Exception e) {
			log.error("Failure building article list, ",e);
		}
		pageContext.setAttribute("articles", articles);
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
