package com.spd.cq.searspartsdirect.common.model;

public class PriceAndAvailabilityModel {
	
	private String availabilityStatus;
	private String sellingPrice;
	
	public String getAvailabilityStatus() {
		if (this.availabilityStatus.equals("INST")) {
			return "In Stock";
		}
		return availabilityStatus;
	}
	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	public String getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	@Override
	public String toString() {
		return "PriceAndAvailabilitymodel [availabilityStatus="
				+ availabilityStatus + ", sellingPrice=" + sellingPrice + "]";
	}

}
