package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
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
			String nodeDetails = "select * from nt:base where jcr:path like '/content/searspartsdirect/en/repair-guide/%' and pages  like '%"+categoryPath+"%'";
			Session session = resourceResolver.adaptTo(Session.class);
			Query nodeDetailsQuery = session.getWorkspace().getQueryManager()
					.createQuery(nodeDetails, Query.SQL);
			QueryResult results = nodeDetailsQuery.execute();
			if (results.getNodes().hasNext()) {
				NodeIterator it = results.getNodes();
				while (it.hasNext()) {
					Node node = it.nextNode();
					result.add(pageManager.getPage(node.getParent().getPath()));
				}
			}
			Collections.sort(result, Collections.reverseOrder(new PageImpressionsComparator(resourceResolver)));
			for(Page page: result){

				if (!page.equals(currentPage)) {

					String subcategoryName = PDUtils.getSubcategoryFromPage(page);

					List<ArticleModel> tmpGuides;
					if (!(guides.isEmpty()) && guides.containsKey(subcategoryName)){
						tmpGuides = guides.get(subcategoryName);
					}else{
						tmpGuides = new ArrayList<ArticleModel>();
						guides.put(subcategoryName, tmpGuides);
					}
					tmpGuides.add( new ArticleModel(page));
				}
			}

		pageContext.setAttribute("guides", guides);

		}
		catch (Exception e) {
			log.error("Error finding guides: " + e.toString());
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
