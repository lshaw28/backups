package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParentPageTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(TagsByPageTag.class);
	protected String url;
	
	@Override
	public int doStartTag() throws JspException {
		
		try {
			
			url = currentPage.getParent().getPath();
			if (url != null && !url.isEmpty()){
				pageContext.setAttribute("parentPage", url);
			}
			else {
				url ="";
			}
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
