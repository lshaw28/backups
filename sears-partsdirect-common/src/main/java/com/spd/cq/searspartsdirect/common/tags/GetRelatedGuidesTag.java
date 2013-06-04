package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.spd.cq.searspartsdirect.common.model.RelatedGuideModel;

public class GetRelatedGuidesTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetRelatedGuidesTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		
		ArrayList<RelatedGuideModel> guides = new ArrayList<RelatedGuideModel>();
		try {
			// redo the population of guides
			
				RelatedGuideModel guide1 = new RelatedGuideModel("url1", "imagePath1", "title1");
				RelatedGuideModel guide2 = new RelatedGuideModel("url2", "imagePath2", "title2");
				RelatedGuideModel guide3 = new RelatedGuideModel("url3", "imagePath3", "title3");
				RelatedGuideModel guide4 = new RelatedGuideModel("url4", "imagePath4", "title4");

				guides.add(guide1);
				guides.add(guide2);				
				guides.add(guide3);			
				guides.add(guide4);
				
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
}
