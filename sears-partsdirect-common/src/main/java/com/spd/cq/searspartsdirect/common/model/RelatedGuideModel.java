package com.spd.cq.searspartsdirect.common.model;

public class RelatedGuideModel {

	private String url;
	private String imagePath;
	private String title;

	// for Related Articles
	// private String Description;

	public RelatedGuideModel(String url, String imagePath, String title) {
		this.url = url;
		this.imagePath = imagePath;
		this.title = title;
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

}
