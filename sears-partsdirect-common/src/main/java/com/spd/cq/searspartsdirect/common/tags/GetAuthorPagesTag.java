package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

public class GetAuthorPagesTag  extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetAuthorPagesTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Page> pages = new ArrayList<Page>();
		Value[] values = null;
		try {
			values = currentNode.getProperty("authors").getValues();
			// note for later: test values with a single value to see if it checks out
			for(Value path: values){
				Page p = pageManager.getPage(path.getString());
				pages.add(p);
			}
			
		} catch (ValueFormatException e) {
			log.error("", e);
		} catch (PathNotFoundException e) {
			log.error("", e);
		} catch (RepositoryException e) {
			log.error("", e);
		}
		pageContext.setAttribute("authorPages", pages);
		
		return SKIP_BODY;
	}

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
