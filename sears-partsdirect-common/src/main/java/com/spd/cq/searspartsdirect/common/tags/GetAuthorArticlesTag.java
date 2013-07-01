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
import com.spd.cq.searspartsdirect.common.model.RelatedArticleModel;

public class GetAuthorArticlesTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetAuthorArticlesTag.class);

	@Override
	public int doStartTag() throws JspException {

		ArrayList<RelatedArticleModel> articles = new ArrayList<RelatedArticleModel>();
		try {
			log.debug("Author Article");
			ArrayList<Page> result = new ArrayList<Page>();
			// Get the list of all the pages/articles authored by the given
			// author
			QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
			HashMap<String, String> props = new HashMap<String, String>();
			props.put("type", "cq:Page");
			props.put("path", "/content/searspartsdirect/en/articles");
			props.put("property", "jcr:content/authors");
			props.put("property.value", currentPage.getPath());
			log.debug("currentPage.getPath(): " + currentPage.getPath());

			List<Hit> hits = qb.createQuery(PredicateGroup.create(props),
												resourceResolver.adaptTo(Session.class))
												.getResult().getHits();
			for (Hit hit : hits) {
				result.add(pageManager.getPage(hit.getPath()));
			}

			// Add the pages to the arraylist
			// reusing RelatedArticleModel
			for (Page page : result) {

				articles.add(new RelatedArticleModel(page.getPath() + ".html",
												page.getPath() + Constants.ASSETS_IMAGE_PATH,
												page.getTitle(),
												page.getProperties().get("abstracttext", Constants.EMPTY).toString()));
			}

			pageContext.setAttribute("articles", articles);
		} catch (Exception e) {
			log.error("Error finding author articles: " + e.toString());
		}

		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
