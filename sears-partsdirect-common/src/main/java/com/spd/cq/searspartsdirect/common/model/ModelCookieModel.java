package com.spd.cq.searspartsdirect.common.model;

public class ModelCookieModel {

	public final static String recentlyViewedModelCookieName = "recentlyViewedModels";
	protected String itemName;
	protected String itemDescription;
	protected String itemURL;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemURL() {
		return itemURL;
	}

	public void setItemURL(String itemURL) {
		this.itemURL = itemURL;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemDescription == null) ? 0 : itemDescription.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof ModelCookieModel)) {
			return false;
		}
		ModelCookieModel other = (ModelCookieModel) obj;
		if (itemDescription == null) {
			if (other.itemDescription != null) {
				return false;
			}
		} else if (!itemDescription.equals(other.itemDescription)) {
			return false;
		}
		if (itemName == null) {
			if (other.itemName != null) {
				return false;
			}
		} else if (!itemName.equals(other.itemName)) {
			return false;
		}
		return true;
	}

}
