package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.RelatedArticleModel;

public class GetRelatedArticlesTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetRelatedArticlesTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		
		ArrayList<RelatedArticleModel> articles = new ArrayList<RelatedArticleModel>();
		try {
			// redo the population of guides
			
				RelatedArticleModel article = new RelatedArticleModel("url", "imagePath", "title", "description");
				RelatedArticleModel article2 = new RelatedArticleModel("url2", "imagePath2", "title2", "description2");

				articles.add(article);
				articles.add(article2);

				pageContext.setAttribute("articles", articles);
		}
		catch (Exception e) {
			
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
	}
}
