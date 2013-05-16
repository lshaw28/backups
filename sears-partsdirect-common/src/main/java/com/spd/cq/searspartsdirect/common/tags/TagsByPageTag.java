package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;

/**
 * Custom tag to draw out a list of Tag objects when given a Page path
 * Defaults to current page
 * @author Joseph
 *
 */
public class TagsByPageTag extends CQBaseTag {

	protected static Logger log = LoggerFactory.getLogger(TagsByPageTag.class);
	protected String pagepath;
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Tag> tags = new ArrayList<Tag>();
		
		Tag[] pageTags = null;
		if (pagepath != null) {
			pageTags = pageManager.getPage(pagepath).getTags();
		}
		else {
			pageTags = currentPage.getTags();
		}
		for (Tag tag : pageTags) {
			tags.add(tag);
		}
		pageContext.setAttribute("tags",tags);
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
}
