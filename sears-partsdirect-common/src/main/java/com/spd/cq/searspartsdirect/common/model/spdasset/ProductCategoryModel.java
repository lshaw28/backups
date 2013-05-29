package com.spd.cq.searspartsdirect.common.model.spdasset;

public class ProductCategoryModel {
	
	private String title;
	private String description;
	private String imagePath;
	
	public ProductCategoryModel(String title, String description, String imagePath) {
		this.title = title;
		this.description = description;
		this.imagePath = imagePath;
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

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
