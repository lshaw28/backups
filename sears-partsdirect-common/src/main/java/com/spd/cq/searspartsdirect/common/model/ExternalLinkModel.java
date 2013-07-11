package com.spd.cq.searspartsdirect.common.model;

public class ExternalLinkModel {
	private String href;
	private String linkText;
	
	public ExternalLinkModel(String href, String linkText) {
		super();
		this.href = href;
		this.linkText = linkText;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

}
