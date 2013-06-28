package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class RecoveryCodesModel {
	
	private String codeId;
	private String description;
	
	@SerializedName("recoveryPartsModel")
	private List<RecoveryPartsModel> recoveryPartsModel;
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<RecoveryPartsModel> getRecoveryPartsModel() {
		return recoveryPartsModel;
	}
	public void setRecoveryPartsModel(List<RecoveryPartsModel> recoveryPartsModel) {
		this.recoveryPartsModel = recoveryPartsModel;
	}
	@Override
	public String toString() {
		return "RecoveryCodesModel [codeId=" + codeId + ", description="
				+ description + ", recoveryPartsModel=" + recoveryPartsModel
				+ "]";
	}

	
	

}
