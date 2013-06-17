package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
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
		Map<String, List<ErrorCodeModel>> tempErrorCodeTableData = new HashMap<String, List<ErrorCodeModel>>();
		Map<String, List<ErrorCodeModel>> errorCodeTableData = new HashMap<String, List<ErrorCodeModel>>();
		try {
			String[]  multiJsons = properties.get("errorCodeTable",new String[0]);
			for (String json : multiJsons) {
				JSONObject jsob = new JSONObject(json);
				//log.debug(json.toString());
				ErrorCodeModel model = new ErrorCodeModel("", jsob.getString("code"),jsob.getString("condition"), jsob.getString("checkRepairLink"));
				String errorCodeType = jsob.getString("codeType");
				log.debug("Error code type="+errorCodeType);
				if (tempErrorCodeTableData.containsKey(errorCodeType)) {
					List<ErrorCodeModel> newModels = tempErrorCodeTableData.get(errorCodeType);
					newModels.add(model);
					tempErrorCodeTableData.remove(errorCodeType);
					tempErrorCodeTableData.put(errorCodeType, newModels);
				} else {
					List<ErrorCodeModel> newModels = new ArrayList<ErrorCodeModel>();
					newModels.add(model);
					if ("".equals(errorCodeType)) {
						tempErrorCodeTableData.put("Other Error Code Type", newModels);
					} else {
						tempErrorCodeTableData.put(errorCodeType, newModels);
					}
				}
			}
			
			List<String> keys = new ArrayList<String>();
            keys.addAll(tempErrorCodeTableData.keySet());
            Collections.sort(keys);
            for (String key : keys) {
            	errorCodeTableData.put(key, tempErrorCodeTableData.get(key));
			}
            
			pageContext.setAttribute("errorCodeTableData", errorCodeTableData);
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
