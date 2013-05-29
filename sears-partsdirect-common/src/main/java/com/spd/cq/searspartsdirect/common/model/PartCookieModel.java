package com.spd.cq.searspartsdirect.common.model;

public class PartCookieModel {
	
	public final static String recentlyViewedPartCookieName = "recentlyViewedParts";	
	private String itemImageURL;
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
	public String getItemImageURL() {
		return itemImageURL;
	}
	
	public void setItemImageURL(String itemImageURL) {
		this.itemImageURL = itemImageURL;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemDescription == null) ? 0 : itemDescription.hashCode());
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemURL == null) ? 0 : itemURL.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartCookieModel other = (PartCookieModel) obj;
		if (itemDescription == null) {
			if (other.itemDescription != null)
				return false;
		} else if (!itemDescription.equals(other.itemDescription))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemURL == null) {
			if (other.itemURL != null)
				return false;
		} else if (!itemURL.equals(other.itemURL))
			return false;
		return true;
	}		

}
