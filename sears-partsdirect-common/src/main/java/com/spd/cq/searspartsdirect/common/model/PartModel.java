package com.spd.cq.searspartsdirect.common.model;

public class PartModel {
	
	private String text;
	private String id;
	
	public PartModel(String text, String id) {
		this.text = text;
		this.id = id;
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
}
