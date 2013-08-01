package com.spd.cq.searspartsdirect.common.tags;

import java.io.Writer;
import java.util.Iterator;

import javax.jcr.Node;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

/**
 * Tag to generate a list of categories for dropdown options
 * @author Joseph
 *
 */

@SuppressWarnings("serial")
public class GetCategoryJSONTag extends CQBaseTag {
	private static final Logger log = LoggerFactory.getLogger(GetCategoryJSONTag.class);
 
	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder();
		sb.append("[{\"value\":\"select\",\"text\":\"Select a Category\"},");
		
		try {
			Resource categoriesResource = resourceResolver.getResource(Constants.ASSETS_PATH + "/" + Constants.ASSETS_PRODUCT_CATEGORY_PATH);
			if (categoriesResource != null) {
				Node categoriesNode = categoriesResource.adaptTo(Node.class);
	    		@SuppressWarnings("unchecked")
	    		Iterator<Node> catIter = categoriesNode.getNodes();
	    		while (catIter.hasNext()) {
	    			Node child = catIter.next();
	    			Node content = child.getNode("jcr:content");
	    			sb.append("{\"value\":\"" + child.getName() + "\", \"text\":\"" + content.getProperty("jcr:title").getString() + "\"}");
	    			sb.append(",");
	    		}
	    	}
	    	sb.setCharAt(sb.length()-1, ']');
	    	Writer out = pageContext.getOut();
	    	out.append(sb.toString());
	    	out.flush();
    	} catch(Exception e) {
    		log.error(ExceptionUtils.getFullStackTrace(e));
    	}
		return SKIP_BODY;
    }
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
