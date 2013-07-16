package com.spd.cq.searspartsdirect.common.model.spdasset;

public class SymptomModel {
	
	private String title;
	private String description;
	private String id;
	private String path;
	private long frequency;
	
	public SymptomModel(String path, String title, String description, String id) {
		this.title = title;
		this.description = description;
		this.id = id;
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public long getFrequency() {
		return frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}
}
