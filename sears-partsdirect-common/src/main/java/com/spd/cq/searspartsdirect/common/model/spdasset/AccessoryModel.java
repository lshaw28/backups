package com.spd.cq.searspartsdirect.common.model.spdasset;

public class AccessoryModel {
	private String path;
	private String title;
	private String description;
	private String imagePath;
	private String partNumber;
	private String linkUrl;
	
	public AccessoryModel(String path, String title, String description, String imagePath, String partNumber, String linkUrl) {
		this.path = path;
		this.title = title;
		this.description = description;
		this.partNumber = partNumber;
		this.imagePath = imagePath;
		this.linkUrl = linkUrl;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((linkUrl == null) ? 0 : linkUrl.hashCode());
		result = prime * result
				+ ((partNumber == null) ? 0 : partNumber.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (!(obj instanceof AccessoryModel)) {
			return false;
		}
		AccessoryModel other = (AccessoryModel) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (imagePath == null) {
			if (other.imagePath != null) {
				return false;
			}
		} else if (!imagePath.equals(other.imagePath)) {
			return false;
		}
		if (linkUrl == null) {
			if (other.linkUrl != null) {
				return false;
			}
		} else if (!linkUrl.equals(other.linkUrl)) {
			return false;
		}
		if (partNumber == null) {
			if (other.partNumber != null) {
				return false;
			}
		} else if (!partNumber.equals(other.partNumber)) {
			return false;
		}
		if (path == null) {
			if (other.path != null) {
				return false;
			}
		} else if (!path.equals(other.path)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}
}
