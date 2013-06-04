package com.spd.cq.searspartsdirect.common.model.spdasset;

public class JobCodeModel {
	
	private String title;
	private String description;
	private String path;
	
	public JobCodeModel(String path, String title, String description) {
		this.title = title;
		this.description = description;
		this.path = path;
	}
	
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

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
