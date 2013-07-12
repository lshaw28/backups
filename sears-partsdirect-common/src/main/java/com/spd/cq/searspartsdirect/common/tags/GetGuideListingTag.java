package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
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
import com.spd.cq.searspartsdirect.common.helpers.PDUtils;
import com.spd.cq.searspartsdirect.common.helpers.PageImpressionsComparator;
import com.spd.cq.searspartsdirect.common.model.ArticleModel;

public class GetGuideListingTag extends CQBaseTag{

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetGuideListingTag.class);
	protected String categoryPath;

	@Override
	public int doStartTag() throws JspException {

		
		try {
			HashMap<String, List<ArticleModel>> guides = new HashMap<String, List<ArticleModel>>();
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
			for(Page page: result){

				if (!page.equals(currentPage)) {

					String subcategoryName = PDUtils.getSubcategoryFromPage(page);

					if (!(guides.isEmpty()) && guides.containsKey(subcategoryName)){
						List<ArticleModel> tmp = guides.get(subcategoryName);
						tmp.add( new ArticleModel(
								page.getPath() + ".html",
								page.getPath() + Constants.ASSETS_IMAGE_PATH,
								page.getTitle(),
								page.getDescription()));
					}else{
						List<ArticleModel> tmpGuides = new ArrayList<ArticleModel>();
						tmpGuides.add( new ArticleModel(
								page.getPath() + ".html",
								page.getPath() + Constants.ASSETS_IMAGE_PATH,
								page.getTitle(),
								page.getDescription()));
							//Add the guides to the list
						guides.put(subcategoryName, tmpGuides);
					}
				}
			}

		pageContext.setAttribute("guides", guides);

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
