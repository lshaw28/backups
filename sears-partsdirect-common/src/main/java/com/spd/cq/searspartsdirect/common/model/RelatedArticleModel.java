package com.spd.cq.searspartsdirect.common.model;

public class RelatedArticleModel {

	private String url;
	private String imagePath;
	private String title;
	private String description;

	public RelatedArticleModel(String url, String imagePath, String title, String description) {
		this.url = url;
		this.imagePath = imagePath;
		this.title = title;
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
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

	public String toString() {
		return "url:" + url + "||title:" + title + "||description:" + description + "||imagePath:" + imagePath;
	}
}
