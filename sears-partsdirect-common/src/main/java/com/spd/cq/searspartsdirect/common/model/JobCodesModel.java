package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

public class JobCodesModel {
	private String description;
	private String modelNumber;
	private List<RecoveryCodesModel> recoveryCodesModel;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public List<RecoveryCodesModel> getRecoveryCodesModel() {
		return recoveryCodesModel;
	}

	public void setRecoveryCodesModel(List<RecoveryCodesModel> recoveryCodesModel) {
		this.recoveryCodesModel = recoveryCodesModel;
	}

	@Override
	public String toString() {
		return "JobCodesModel [description=" + description + ", modelNumber="
				+ modelNumber + ", recoveryCodesModel=" + recoveryCodesModel
				+ "]";
	}

}
