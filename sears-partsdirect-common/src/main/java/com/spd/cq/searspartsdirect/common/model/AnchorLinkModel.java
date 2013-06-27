package com.spd.cq.searspartsdirect.common.model;

public class AnchorLinkModel {
	private String anchorName;
	private String linkText;
	
	public AnchorLinkModel(String anchorName, String linkText) {
		super();
		this.anchorName = anchorName;
		this.linkText = linkText;
	}

	public String getAnchorName() {
		return anchorName;
	}

	public void setAnchorName(String anchorName) {
		this.anchorName = anchorName;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

}
