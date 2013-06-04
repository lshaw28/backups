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

/**
 * Custom tag to draw out a list of associated Pages from a given Related Asset/Page, filterable by Template and RootPath
 * Defaults to current page
 * @author Joseph
 *
 */
public class GetRelatedPagesTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetRelatedPagesTag.class);
	protected String rootPath;
	protected String assetPath;
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Page> result = new ArrayList<Page>();
		
		QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
		HashMap<String, String> props = new HashMap<String, String>();
        props.put("type", "cq:Page");
        if (rootPath != null) {
        	props.put("path", rootPath);
        }
        props.put("property", "jcr:content/pages");
        props.put("property.value", assetPath);
        List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();
		try {
	        for (Hit hit: hits) {
	        	result.add(pageManager.getPage(hit.getPath()));
	        }
		}
		catch (Exception e) {
			log.error("Error querying pages by Asset: " + e.toString());
		}
		pageContext.setAttribute("relatedPages", result);
		
		return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setAssetPath(String assetPath) {
		this.assetPath = assetPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
}
