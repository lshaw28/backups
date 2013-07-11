package com.spd.cq.searspartsdirect.common.model;

public class PDModelSubcomponentModel {

	private String modelDescription;
	private PDSymptomWrapper symptoms;
	private String manualsCount;
	private PDTabWrapper tabs;
	
	public PDModelSubcomponentModel() {
		// nullary constructor for deserialization
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	public PDSymptomWrapper getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(PDSymptomWrapper symptoms) {
		this.symptoms = symptoms;
	}

	public String getManualsCount() {
		return manualsCount;
	}

	public void setManualsCount(String manualsCount) {
		this.manualsCount = manualsCount;
	}

	public PDTabWrapper getTabs() {
		return tabs;
	}

	public void setTabs(PDTabWrapper tabs) {
		this.tabs = tabs;
	}

}
