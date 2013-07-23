package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

/**
 * Custom Tag to display the title for a named tag
 * @author Ben
 *
 */
@SuppressWarnings("serial")
public class DisplayTagTitleTag extends CQBaseTag {
	protected final static Logger log = LoggerFactory.getLogger(DisplayTagTitleTag.class);
	
	protected String tagId;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		TagManager tm = resourceResolver.adaptTo(TagManager.class);
		
		Tag named = tm.resolve(tagId);
		
		try {
			out.write(named.getTitle());
		} catch (Exception e) {
			log.error("Can't write tag title. ",e);
		}
		
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
}
