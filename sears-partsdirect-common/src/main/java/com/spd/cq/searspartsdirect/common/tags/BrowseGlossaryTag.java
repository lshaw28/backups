package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowseGlossaryTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(BrowseGlossaryTag.class);

	@Override
	public int doStartTag() throws JspException {
		try {
			String[]  multiJsons = properties.get("Glossary",new String[0]);
			List<String> glossary = new ArrayList<String>();
			
			for (String json : multiJsons) {
				JSONObject jsob = new JSONObject(json);
				glossary.add(jsob.getString("glossaryLetter"));
				log.debug("***"+ jsob.getString("glossaryLetter"));
			}
			pageContext.setAttribute("glossary", glossary);
		}
		catch (Exception e) {
			log.error("Error while retrieving browse glossary data, ",e.fillInStackTrace());
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
