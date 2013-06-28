package com.spd.cq.searspartsdirect.common.model;

import com.google.gson.annotations.SerializedName;

public class PartImageModel {
	
	@SerializedName("imageURL")
	private String imageURL;

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public String toString() {
		return "PartImage [imageURL=" + imageURL + "]";
	}

}
