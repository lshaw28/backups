package com.spd.cq.searspartsdirect.common.tags;


import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom tag to collect the list of all the Parts Required
 * @author dmartinez
 *
 */
public class PartsRequiredTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(TagsByPageTag.class);
	protected String url;
	
	@Override
	public int doStartTag() throws JspException {
		
		// *** THIS ARRAYLIST WILL BE REPLACED WITH THE ACTUAL LIST ***
		ArrayList<String> sampleList = new ArrayList<String>();
		sampleList.clear();
		sampleList.add("Hammer");
		sampleList.add("Nail");
		sampleList.add("Screwdriver");
		sampleList.add("Saw");

		try {
				pageContext.setAttribute("partsRequiredList", sampleList);
				
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
