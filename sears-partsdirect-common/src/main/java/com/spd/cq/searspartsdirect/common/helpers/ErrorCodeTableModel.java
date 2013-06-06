package com.spd.cq.searspartsdirect.common.helpers;

import java.util.List;

import com.spd.cq.searspartsdirect.common.model.spdasset.ErrorCodeModel;

public class ErrorCodeTableModel {
	
	private String errorCodeType;
	private List<ErrorCodeModel> errorCodeModel;
	
	public List<ErrorCodeModel> getErrorCodeModel() {
		return errorCodeModel;
	}
	public void setErrorCodeModel(List<ErrorCodeModel> errorCodeModel) {
		this.errorCodeModel = errorCodeModel;
	}
	public String getErrorCodeType() {
		return errorCodeType;
	}
	public void setErrorCodeType(String errorCodeType) {
		this.errorCodeType = errorCodeType;
	}

}
