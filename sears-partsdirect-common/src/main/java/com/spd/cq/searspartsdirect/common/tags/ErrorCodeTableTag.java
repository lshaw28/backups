package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.spdasset.ErrorCodeModel;

public class ErrorCodeTableTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(ErrorCodeTableTag.class);

	@Override
	public int doStartTag() throws JspException {
		try {
			String[]  multiJsons = properties.get("errorCodeTable",new String[0]);
			for (String json : multiJsons) {
				JSONObject jsob = new JSONObject(json);
				String errorCodeType = jsob.getString("codeType");
				//log.debug(json.toString());
				ErrorCodeModel model = new ErrorCodeModel("", jsob.getString("code"),jsob.getString("condition"), jsob.getString("checkRepairLink"), jsob.getString("shopParts"));
				log.debug("Error code model type="+errorCodeType +"and "+model);
			}
			//pageContext.setAttribute("errorCodeTableData", errorCodeTableData);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
