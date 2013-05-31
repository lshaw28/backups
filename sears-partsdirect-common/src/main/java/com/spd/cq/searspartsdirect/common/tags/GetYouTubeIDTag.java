package com.spd.cq.searspartsdirect.common.tags;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetYouTubeIDTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory
			.getLogger(GetYouTubeIDTag.class);

	@Override
	public int doStartTag() throws JspException {
		String youTubeID = "";

		try {
			String link = properties.get("youTubeLink", "");
			Pattern p = Pattern.compile("(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*");
			Matcher m = p.matcher(link);
			if (m.find()) {
				youTubeID = m.group();
			}
		}

		catch (Exception e) {
			log.error(e.toString());
		}
		pageContext.setAttribute("youTubeID", youTubeID);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}