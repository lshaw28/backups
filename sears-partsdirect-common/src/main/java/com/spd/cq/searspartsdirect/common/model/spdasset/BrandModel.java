package com.spd.cq.searspartsdirect.common.model.spdasset;

public class BrandModel {
	
	private String title;
	private String description;
	private String logoPath;
	private String path;
	
	public BrandModel(String path, String title, String description, String logoPath) {
		this.title = title;
		this.description = description;
		this.logoPath = logoPath;
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
	
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
