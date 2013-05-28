package com.spd.cq.searspartsdirect.common.model.spdasset;

public class ProductCategoryModel {
	
	private String title;
	private String description;
	
	public ProductCategoryModel(String title, String description) {
		this.title = title;
		this.description = description;
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
}
