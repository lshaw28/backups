package com.spd.cq.searspartsdirect.common.tags;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetNameByNodePathTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetYouTubeIDTag.class);
	protected String nodePath;

	@Override
	public int doStartTag() throws JspException {
		
		// return the substring starting from the last occurence of "/"
		pageContext.setAttribute("nodeName", nodePath.substring(nodePath.lastIndexOf("/") + 1));
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}
}
