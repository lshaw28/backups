package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;

public class TagsByPageTag extends CQBaseTag {

	protected static Logger log = LoggerFactory.getLogger(TagsByPageTag.class);
	protected String format;

	@Override
	public int doStartTag() throws JspException {
		ArrayList<String> tags = new ArrayList<String>();
		Tag[] pageTags = currentPage.getTags();
		for (Tag tag : pageTags) {
			if(format.equals("title")) {
				tags.add(tag.getTitle());
			}
			else if(format.equals("tagid")) {
				tags.add(tag.getTagID());
			}
			else {
				tags.add(tag.getPath());
			}
		}
		pageContext.setAttribute("tags",tags);
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setFormat(String format) {
		this.format = format;
	}
}
