package com.spd.cq.searspartsdirect.common.model.spdasset;

import java.util.List;

import com.spd.cq.searspartsdirect.common.model.GuideModel;

public class PartTypeModel {
	
	private String title;
	private String description;
	private String pluralTitle;
	private String imagePath;
	private String path;
	private List<GuideModel> guides;
	
	public PartTypeModel(String path, String title, String description, String imagePath, String pluralTitle) {
		this.title = title;
		this.description = description;
		this.imagePath = imagePath;
		this.path = path;
		this.pluralTitle = pluralTitle;
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

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "PartTypeModel [title=" + title + ", description=" + description
				+ ", imagePath=" + imagePath + ", path=" + path + "]";
	}

	public String getPluralTitle() {
		return pluralTitle;
	}

	public void setPluralTitle(String pluralTitle) {
		this.pluralTitle = pluralTitle;
	}

	public List<GuideModel> getGuides() {
		return guides;
	}

	public void setGuides(List<GuideModel> guides) {
		this.guides = guides;
	}
}
