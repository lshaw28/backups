package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.sun.mail.mbox.NewlineOutputStream;

public class BackButtonTag extends CQBaseTag {
	protected static Logger log = LoggerFactory.getLogger(TagsByPageTag.class);
	protected StringBuffer url = new StringBuffer();
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			
			// append .html?
			
			// also, perhaps try with ^/[^\.]+$ or ^/[^\\.]+$
			
			if (url.equals("^/(.*)") == true && url.equals("^//(.*)") == false && url.equals("(.*)/$") == false) {
				url.append(".html");
			}
			
			
			// write out
			out.print(url);
			
			
			//String to Page Path
			//String to Page Title
			//<a href="${pathResult}">${titleResult}</a>
			//pageContext.setAttribute(pathResult //Append .html if neccessary)
			// www.google.com
			// /content/searspartsdirect
			// /content/searspartsdirect.html
			// www.google.com/foobar
			// www.google.com/foobar.html			
			
			
			//Get the current page
			//Look to the parent
			//Output reference to parent page
			//<a href="www.sears.com/foobar.html">Text</a>
			
		}
		catch (Exception e) {
			log.error(e.toString());
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
