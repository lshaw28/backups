package com.spd.cq.searspartsdirect.common.model.spdasset;

public class ProductCategoryModel {
	
	private String title;
	private String singularTitle;
	private String description;
	private String imagePath;
	private String path;
	
	public ProductCategoryModel(String path, String title, String singularTitle, String description, String imagePath) {
		this.title = title;
		this.singularTitle = singularTitle;
		this.description = description;
		this.imagePath = imagePath;
		this.path = path;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSingularTitle() {
		return singularTitle;
	}
	public void setSingularTitle(String singularTitle) {
		this.singularTitle = singularTitle;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
