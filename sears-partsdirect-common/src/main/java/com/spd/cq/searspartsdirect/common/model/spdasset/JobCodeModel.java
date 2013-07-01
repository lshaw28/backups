package com.spd.cq.searspartsdirect.common.model.spdasset;

import com.spd.cq.searspartsdirect.common.model.RelatedGuideModel;

public class JobCodeModel {
	
	private String title;
	private String description;
	private String path;
	private PartTypeModel partTypeModel;
	private RelatedGuideModel guide;
	
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

	public RelatedGuideModel getGuide() {
		return guide;
	}

	public void setGuide(RelatedGuideModel guide) {
		this.guide = guide;
	}
}
