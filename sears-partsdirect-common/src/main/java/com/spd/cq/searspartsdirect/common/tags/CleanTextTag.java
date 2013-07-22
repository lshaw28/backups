package com.spd.cq.searspartsdirect.common.tags;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

/**
 * Custom Tag to clean out inline styles randomly added by TinyMCE
 * @author Ben
 *
 */
public class CleanTextTag extends CQBaseTag {
	protected final static Logger log = LoggerFactory.getLogger(CleanTextTag.class);
	
	private final static Pattern inlineStyle = Pattern.compile("(<[^>]*)style *= *\"[^\">]*\"");
	
	protected String text;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		// default placeholder uses no inline styles
		// need to strip style attrs from tags
		StringBuffer cleanedText = new StringBuffer(text.length());
		
		Matcher styleFinder = inlineStyle.matcher(text);
		while (styleFinder.find()) {
			styleFinder.appendReplacement(cleanedText, Matcher.quoteReplacement(styleFinder.group(1)));
		}
		styleFinder.appendTail(cleanedText);
		
		try {
			out.write(cleanedText.toString());
		} catch (Exception e) {
			log.error("Can't write cleaned text. ",e);
		}
		
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setText(String text) {
		this.text = text;
	}
}
