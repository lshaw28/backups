package com.spd.cq.searspartsdirect.common.model;

import java.math.BigDecimal;

public class PDSymptom {
	String description;
	String modelNumber;
	String id;
	String successfulSolutions;
	BigDecimal successfulFrequency;
	String[] recoveryCodesModel;
	
	public PDSymptom() {
		// nullary constructor for Gson
	}

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

	public String getSuccessfulSolutions() {
		return successfulSolutions;
	}

	public void setSuccessfulSolutions(String successfulSolutions) {
		this.successfulSolutions = successfulSolutions;
	}

	public BigDecimal getSuccessfulFrequency() {
		return successfulFrequency;
	}

	public void setSuccessfulFrequency(BigDecimal successfulFrequency) {
		this.successfulFrequency = successfulFrequency;
	}

	public String[] getRecoveryCodesModel() {
		return recoveryCodesModel;
	}

	public void setRecoveryCodesModel(String[] recoveryCodesModel) {
		this.recoveryCodesModel = recoveryCodesModel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
