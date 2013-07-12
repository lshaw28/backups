package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.AnchorLinkModel;

public class GetMaintenanceTopicJumpTag  extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetMaintenanceTopicJumpTag.class);
	
	public final static String PARSYS_TYPE = Constants.ident("foundation/components/parsys");
	public final static String TOPIC_TYPE = Constants.ident("searspartsdirect/components/content/maintenanceTipTopic");
	public final static String TITLE_PROP = Constants.ident("topicTitle");
	
	@Override
	public int doStartTag() throws JspException {
		List<AnchorLinkModel> jumpTopics = new ArrayList<AnchorLinkModel>();
		// we find the parsyses attached to the current page
		for (Resource pageChild : currentPage.getContentResource().getChildren()) {
			if (isParsys(pageChild)) {
				for (Resource parChild : pageChild.getChildren()) {
					if (isTopic(parChild)) {
						jumpTopics.add(new AnchorLinkModel(getAName(pageChild,parChild),getTopicTitle(parChild)));
					}
				}
			}
		}
		
		// we output an empty list if there are not at least two items
		if (jumpTopics.size() < 2) {
			jumpTopics = new ArrayList<AnchorLinkModel>();
		}
		pageContext.setAttribute("jumpTopics",jumpTopics);
		return SKIP_BODY;
	}

	boolean isParsys(Resource maybeParsys) {
		// extend to set check if more than a couple more needed
		return maybeParsys.getResourceType().equals(PARSYS_TYPE);
	}
	
	boolean isTopic(Resource maybeTopic) {
		return maybeTopic.getResourceType().equals(TOPIC_TYPE);
	}
	
	String getAName(Resource parsys, Resource topic) {
		return parsys.getName()+"_"+topic.getName();
	}
	
	String getTopicTitle(Resource topicResource) {
		String title = "";
		try {
			title = topicResource.adaptTo(Node.class).getProperty(TITLE_PROP).getString();
		} catch (Exception e) {
			log.error("Retrieving title from topic "+topicResource+", ",e);
		}
		return title;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
