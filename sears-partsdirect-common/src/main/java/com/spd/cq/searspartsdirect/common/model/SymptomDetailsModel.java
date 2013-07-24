package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;

public class SymptomDetailsModel {
	private String title;
	private String description;
	private List<JobCodeModel> jobCodeModels;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<JobCodeModel> getJobCodeModels() {
		return jobCodeModels;
	}

	public void setJobCodeModels(List<JobCodeModel> jobCodeModels) {
		this.jobCodeModels = jobCodeModels;
	}

}
