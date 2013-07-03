package com.spd.cq.searspartsdirect.common.model;

/*
 * Should anything need to deserialize these from some JSON
 * source other than the Top Parts API, it will be time to 
 * break this out into a TopPartsModel and give it an adaptTo
 * to PartModel. 
 */
import com.google.gson.annotations.SerializedName;

public class PartModel {
	
	@SerializedName("partDesc")
	private String text;
	
	private String id;
	
	@SerializedName("partDetailsPageURL")
	private String url;
	
	@SerializedName("imageURL")
	private String imageUrl;
	
	public PartModel(String text, String id, String url) {
		this.text = text;
		this.id = id;
		this.url = url;
	}
	
	/**
	 * Constructs an instance with all fields null.
	 */
	public PartModel() {
		
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PartModel)) {
			return false;
		}
		PartModel other = (PartModel) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (imageUrl == null) {
			if (other.imageUrl != null) {
				return false;
			}
		} else if (!imageUrl.equals(other.imageUrl)) {
			return false;
		}
		if (text == null) {
			if (other.text != null) {
				return false;
			}
		} else if (!text.equals(other.text)) {
			return false;
		}
		if (url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!url.equals(other.url)) {
			return false;
		}
		return true;
	}
}
