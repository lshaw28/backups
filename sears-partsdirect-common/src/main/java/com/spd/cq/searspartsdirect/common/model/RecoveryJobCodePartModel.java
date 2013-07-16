package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RecoveryJobCodePartModel {

	@SerializedName("part")
	private List<JobCodePartModel> jobCodePartModels;

	@SerializedName("recoveryId")
	private String jobCode;

	public List<JobCodePartModel> getJobCodePartModels() {
		return jobCodePartModels;
	}
	public void setJobCodePartModels(List<JobCodePartModel> jobCodePartModels) {
		this.jobCodePartModels = jobCodePartModels;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
}
