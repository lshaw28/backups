package com.spd.cq.searspartsdirect.common.model;

import com.google.gson.annotations.SerializedName;

public class PartImageModel {

	@SerializedName("imageURL")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PartImage [imageURL=" + url + "]";
	}

}
