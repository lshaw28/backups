package com.spd.cq.searspartsdirect.common.tags;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import javax.jcr.Node;
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
import com.spd.cq.searspartsdirect.common.model.RelatedArticleModel;

public class GetCategoryArticleListTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetCategoryArticleListTag.class);
	protected String categoryPath;
	
	@Override
	public int doStartTag() throws JspException {
		
		List<RelatedArticleModel> articles = new ArrayList<RelatedArticleModel>();
		try {

			List<Page> result = new ArrayList<Page>();

			QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
			Map<String, String> props = new HashMap<String, String>();
	        props.put("type", "cq:Page");
	        props.put("path", "/content/searspartsdirect/en/articles");
	        props.put("property", "jcr:content/pages");
	        props.put("property.value", categoryPath); //?!?! suspect...
	        
	        List<Hit> hits = qb.createQuery(
	        			PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)
	        		).getResult().getHits();

	        for (Hit hit: hits) {
	        	result.add(pageManager.getPage(hit.getPath()));
	        }
	        
	        Collections.sort(result, Collections.reverseOrder(new PageImpressionsComparator(resourceResolver)));
	          
	        for(Page page: result){
	        	if (!page.equals(currentPage)) { // we exclude ourself from results
	        		articles.add(new RelatedArticleModel(
		        				page.getPath() + ".html", 
		        				page.getPath() + Constants.ASSETS_IMAGE_PATH, 
		        				page.getTitle(), 
		        				page.getDescription())
	        				);
	        	}
	        }	        	

			pageContext.setAttribute("articles", articles);
		}
		catch (Exception e) {
			log.error("Failed to build article list, ",e);
		}
        return SKIP_BODY;
	}
	
	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}
}
