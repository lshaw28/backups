package com.spd.cq.searspartsdirect.common.model.spdasset;

public class ProductCategoryModel {
	
	private String trueName;
	private String title;
	private String pluralTitle;
	private String description;
	private String articleIndexCopy;
	private String category101;
	//private String imagePath;
	private String iconClass;
	private String path;
	
	public ProductCategoryModel(String trueName, String path, String title, String pluralTitle, String description, String articleIndexCopy, String category101, String iconClass) {
		this.trueName = trueName;
		this.title = title;
		this.pluralTitle = pluralTitle;
		this.description = description;
		this.articleIndexCopy = articleIndexCopy;
		this.category101 = category101;
		//this.imagePath = imagePath;
		this.iconClass = iconClass;
		this.path = path;
	}
	
	public String getTrueName() {
		return trueName;
	}
	
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPluralTitle() {
		return pluralTitle;
	}
	public void setPluralTitle(String pluralTitle) {
		this.pluralTitle = pluralTitle;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getArticleIndexCopy() {
		return articleIndexCopy;
	}

	public void setArticleIndexCopy(String articleIndexCopy) {
		this.articleIndexCopy = articleIndexCopy;
	}

	public String getCategory101() {
		return category101;
	}

	public void setCategory101(String category101) {
		this.category101 = category101;
	}

	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
