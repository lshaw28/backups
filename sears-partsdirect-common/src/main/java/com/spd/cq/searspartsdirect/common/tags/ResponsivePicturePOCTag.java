package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponsivePicturePOCTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected final static Logger log = LoggerFactory.getLogger(ResponsivePicturePOCTag.class);
	
	final String imageUrlPrefix = "http://s.sears.com/is/image/Sears";
	String imageUrl = "";
	
	@Override
	public int doStartTag() throws JspException {
	    String fileReference = properties.get("fileReference",String.class);
	    log.debug("$$$$fileReference  "+fileReference);
	    
	    if (fileReference != null && fileReference.endsWith(".jpg")) {
	    	log.debug("NOT A SCENE7 IMAGE");
	    	String respImageName = fileReference.substring(fileReference.lastIndexOf("/"), fileReference.lastIndexOf("."));
			log.debug("%%IMAGE%% "+respImageName);
			imageUrl = imageUrlPrefix + respImageName;
			 pageContext.setAttribute("respImage", imageUrl);
	    } else {
	    	log.debug("COULD BE A SCENE7 IMAGE");
	    	 pageContext.setAttribute("respImage", fileReference);
	    }
		return SKIP_BODY;	
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
