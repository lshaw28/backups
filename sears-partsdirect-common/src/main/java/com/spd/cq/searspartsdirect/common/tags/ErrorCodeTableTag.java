package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.ErrorCodeTableModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ErrorCodeModel;

public class ErrorCodeTableTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(ErrorCodeTableTag.class);

	@Override
	public int doStartTag() throws JspException {
		ErrorCodeTableModel errorCodeTableModel = new ErrorCodeTableModel();
		
		try {
			String codeType = properties.get("codeType",String.class);
			errorCodeTableModel.setErrorCodeType(codeType);
			
			String[]  multiJsons = properties.get("errorCodeTable",new String[0]);
			List<ErrorCodeModel> errorCodes = new ArrayList<ErrorCodeModel>();
			
			for (String json : multiJsons) {
				JSONObject jsob = new JSONObject(json);
				ErrorCodeModel model = new ErrorCodeModel("", jsob.getString("code"), jsob.getString("condition"), 
										jsob.getString("checkRepairLink"), jsob.getString("checkRepairText"), 
										jsob.getString("shopPartsLink"), jsob.getString("shopPartsText"));
				errorCodes.add(model);
			}
			errorCodeTableModel.setErrorCodes(errorCodes);
			pageContext.setAttribute("errorCodeTableData", errorCodeTableModel);
		}
		catch (Exception e) {
			log.error("Error while retrieving error code table, ",e.fillInStackTrace());
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
