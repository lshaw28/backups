package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetVideoPropertiesTag extends CQBaseTag{
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetVideoPropertiesTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		
		try {

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
