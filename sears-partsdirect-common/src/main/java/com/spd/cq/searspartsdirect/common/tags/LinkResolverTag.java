package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkResolverTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(LinkResolverTag.class);
	protected String url = "";

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			// append .html if necessary
			if (url.matches("^/(.*)") == true && url.matches("^//(.*)") == false
					&& url.matches("(.*)/$") == false) {
				url = url + ".html";
			}
			// write out
			out.print(url);
		} catch (IOException e) {
			log.error("cannot resolve link: ", e);
		}
		pageContext.setAttribute("url", url);
		return SKIP_BODY;
	}
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
	/**
	 * @param url
	 */
	public void setValue(String url) {
		this.url = url;
	}
}
