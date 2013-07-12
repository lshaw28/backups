package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Node;
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

public class GetMostPopularGuideTag extends CQBaseTag{
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetMostPopularGuideTag.class);
	protected String categoryPath;

	
	@Override
	public int doStartTag() throws JspException {

		try {
			List<ArticleModel> guides = new ArrayList<ArticleModel>();
			ArrayList<Page> result = new ArrayList<Page>();
			//Get the guides based on the category
			QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
			HashMap<String, String> props = new HashMap<String, String>();
			props.put("type", "cq:Page");
			props.put("path", Constants.GUIDES_ROOT);
			props.put("property", Constants.ASSETS_PAGES_REL_PATH);
			props.put("property.value", categoryPath);

			List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();
			for (Hit hit: hits) {
				result.add(pageManager.getPage(hit.getPath()));
			}

			Collections.sort(result, Collections.reverseOrder(new PageImpressionsComparator(resourceResolver)));

			guides.add( new ArticleModel(
					result.get(0).getPath() + ".html",
					result.get(0).getPath() + Constants.ASSETS_IMAGE_PATH,
					result.get(0).getTitle(),
					result.get(0).getDescription()));

			pageContext.setAttribute("guides", guides);
			
			//Get the difficulty level from the repair guide page
			resource = resourceResolver.getResource(result.get(0).getPath());
			Node resourceNode = resource.adaptTo(Node.class);
			String difficultyLevel = resourceNode.getNode(Constants.GUIDES_REL_PATH).getProperty("difficultyLevel").getString();
			pageContext.setAttribute("difficultyLevel", difficultyLevel);
			//Get time required from the repair guide page
			String timeRequired = resourceNode.getNode(Constants.GUIDES_REL_PATH).getProperty("timeRequired").getString();
			pageContext.setAttribute("timeRequired", timeRequired);

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
