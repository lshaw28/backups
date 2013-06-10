package com.spd.cq.searspartsdirect.common.model;

public class ErrorCodeListModel {
	
	private String title;
	private String path;
	
	public ErrorCodeListModel(String title, String path) {
		super();
		this.title = title;
		this.path = path;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
