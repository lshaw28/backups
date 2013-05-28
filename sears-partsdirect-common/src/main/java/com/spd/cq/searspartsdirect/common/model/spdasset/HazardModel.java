package com.spd.cq.searspartsdirect.common.model.spdasset;

public class HazardModel {
	
	private String title;
	private String imagePath;
	
	public HazardModel(String title, String imagePath) {
		this.title = title;
		this.imagePath = imagePath;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
