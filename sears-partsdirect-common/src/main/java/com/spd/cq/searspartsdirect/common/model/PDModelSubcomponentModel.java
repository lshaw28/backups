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
	
	// sugar to elide wrappers
	public PDSymptom[] getSymptomsArr() {
		if (symptoms != null) {
			PDSymptom[] symptomArr = symptoms.getSymptom();
			if (symptomArr != null) {
				return symptomArr;
			}
		} 
		return new PDSymptom[0];
	}
	
	public PDTab[] getTabsArr() {
		if (tabs != null) {
			PDTab[] tabArr = tabs.getTab();
			if (tabArr != null) {
				return tabArr;
			}
		} 
		return new PDTab[0];
	}

}
