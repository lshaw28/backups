package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, List<ErrorCodeModel>> errorCodeTableData = new HashMap<String, List<ErrorCodeModel>>();
		try {
			String[]  multiJsons = properties.get("multi",new String[0]);
			for (String json : multiJsons) {
				JSONObject jsob = new JSONObject(json);
				//log.debug(json.toString());
				ErrorCodeModel model = new ErrorCodeModel("", jsob.getString("code"),jsob.getString("condition"), jsob.getString("checkRepairLink"));
				String errorCodeType = jsob.getString("codeType");
				if (!errorCodeTableData.containsKey(errorCodeType)) {
					List<ErrorCodeModel> newModels = new ArrayList<ErrorCodeModel>();
					newModels.add(model);
					errorCodeTableData.put(errorCodeType, newModels);
				} else {
					List<ErrorCodeModel> newModels = errorCodeTableData.get(errorCodeType);
					newModels.add(model);
					errorCodeTableData.remove(errorCodeType);
					errorCodeTableData.put(errorCodeType, newModels);
				}
			}
			pageContext.setAttribute("errorCodeTableData", errorCodeTableData);
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
