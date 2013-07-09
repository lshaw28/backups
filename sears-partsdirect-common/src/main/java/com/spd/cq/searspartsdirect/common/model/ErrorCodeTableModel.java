package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

import com.spd.cq.searspartsdirect.common.model.spdasset.ErrorCodeModel;

public class ErrorCodeTableModel {
	
	private String errorCodeType;
	private List<ErrorCodeModel> errorCodes;
	
	
	public List<ErrorCodeModel> getErrorCodes() {
		return errorCodes;
	}
	public void setErrorCodes(List<ErrorCodeModel> errorCodes) {
		this.errorCodes = errorCodes;
	}
	public String getErrorCodeType() {
		return errorCodeType;
	}
	public void setErrorCodeType(String errorCodeType) {
		this.errorCodeType = errorCodeType;
	}

}
