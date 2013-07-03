package com.spd.cq.searspartsdirect.common.model.spdasset;

import java.util.List;

import com.spd.cq.searspartsdirect.common.model.Part;
import com.spd.cq.searspartsdirect.common.model.RelatedGuideModel;

public class JobCodeModel {
	
	private String title;
	private String description;
	private String path;
	private PartTypeModel partTypeModel;
	private List<RelatedGuideModel> guides;
	private List<Part> parts;
	
	public JobCodeModel(String path, String title, String description) {
		this.title = title;
		this.description = description;
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

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public PartTypeModel getPartTypeModel() {
		return partTypeModel;
	}

	public void setPartTypeModel(PartTypeModel partTypeModel) {
		this.partTypeModel = partTypeModel;
	}

	public List<RelatedGuideModel> getGuides() {
		return guides;
	}

	public void setGuides(List<RelatedGuideModel> guides) {
		this.guides = guides;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	@Override
	public String toString() {
		return "JobCodeModel [title=" + title + ", description=" + description
				+ ", path=" + path + ", partTypeModel=" + partTypeModel
				+ ", guides=" + guides + ", parts=" + parts + "]";
	}

}
