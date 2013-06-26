package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.AnchorLinkModel;

public class GetMaintenanceTopicJumpTag  extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetMaintenanceTopicJumpTag.class);

	@Override
	public int doStartTag() throws JspException {
		List<AnchorLinkModel> jumpTopics = new ArrayList<AnchorLinkModel>();
		jumpTopics.add(new AnchorLinkModel("foo","Foo"));
		jumpTopics.add(new AnchorLinkModel("bar","Bar"));
		pageContext.setAttribute("jumpTopics",jumpTopics);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
