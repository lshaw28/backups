package com.spd.cq.searspartsdirect.common.model;

public class PDSymptomWrapper {
	private PDSymptom[] symptom;
	
	public PDSymptomWrapper() {
		// nullary constructor for Gson
	}

	public PDSymptom[] getSymptom() {
		return symptom;
	}

	public void setSymptom(PDSymptom[] symptom) {
		this.symptom = symptom;
	}
	
}
