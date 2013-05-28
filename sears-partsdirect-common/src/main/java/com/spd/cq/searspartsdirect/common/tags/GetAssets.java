package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

/**
 * Custom tag to draw out a list of SPDAsset objects based on filters
 * Defaults to current page
 * @author Joseph
 *
 */
public class GetAssets extends CQBaseTag {

	protected static Logger log = LoggerFactory.getLogger(GetAssets.class);
	
	@Override
	public int doStartTag() throws JspException {
		
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
