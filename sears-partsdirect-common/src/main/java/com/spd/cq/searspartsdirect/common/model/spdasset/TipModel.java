package com.spd.cq.searspartsdirect.common.model.spdasset;

public class TipModel {
	
	private String title;
	private String imagePath;
	private String path;
	
	public TipModel(String path, String title, String imagePath) {
		this.title = title;
		this.imagePath = imagePath;
		this.path = path;
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

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
