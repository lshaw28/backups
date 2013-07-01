package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RecoveryPartsModel {
	
	@SerializedName("part")
	private JobCodePartModel jobCodePart;
	
	@SerializedName("partDescription")
	private String partDescription;
	
	@SerializedName("partNumber")
	private String partNumber;
	
	@SerializedName("recoveryFrequency")
	private double recoveryFrequency;


	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public JobCodePartModel getJobCodePart() {
		return jobCodePart;
	}

	public void setJobCodePart(JobCodePartModel jobCodePart) {
		this.jobCodePart = jobCodePart;
	}

	public double getRecoveryFrequency() {
		return recoveryFrequency;
	}

	public void setRecoveryFrequency(double recoveryFrequency) {
		this.recoveryFrequency = recoveryFrequency;
	}

	@Override
	public String toString() {
		return "RecoveryPartsModel [jobCodePart=" + jobCodePart
				+ ", partDescription=" + partDescription + ", partNumber="
				+ partNumber + ", recoveryFrequency=" + recoveryFrequency + "]";
	}
}