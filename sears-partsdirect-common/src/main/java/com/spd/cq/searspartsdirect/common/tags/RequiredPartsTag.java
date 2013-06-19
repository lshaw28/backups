package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.PartModel;

public class RequiredPartsTag  extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(RequiredPartsTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<PartModel> parts = new ArrayList<PartModel>();
		try {
			String[]  multiJsons = properties.get("multipart",new String[0]);
			for (String json : multiJsons) {
				JSONObject jsob = new JSONObject(json);
				PartModel part = new PartModel(jsob.getString("text"),jsob.getString("id"));
				parts.add(part);
			}
			pageContext.setAttribute("parts", parts);
		}
		catch (Exception e) {
			
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
