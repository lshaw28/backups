package com.spd.cq.searspartsdirect.common.model;

public class ToolModel {
	
	private String text;
	private String id;
	private String url;
	
	public ToolModel(String text, String id, String url) {
		this.text = text;
		this.id = id;
		this.url = url;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}