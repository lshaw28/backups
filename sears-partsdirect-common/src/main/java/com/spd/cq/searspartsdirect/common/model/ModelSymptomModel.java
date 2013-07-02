package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;

public class ModelSymptomModel {
	
	private SymptomModel symptomModel;
	
	private List<JobCodeModel> jobCodeModels;

	public SymptomModel getSymptomModel() {
		return symptomModel;
	}

	public void setSymptomModel(SymptomModel symptomModel) {
		this.symptomModel = symptomModel;
	}

	public List<JobCodeModel> getJobCodeModels() {
		return jobCodeModels;
	}

	public void setJobCodeModels(List<JobCodeModel> jobCodeModels) {
		this.jobCodeModels = jobCodeModels;
	}

}
