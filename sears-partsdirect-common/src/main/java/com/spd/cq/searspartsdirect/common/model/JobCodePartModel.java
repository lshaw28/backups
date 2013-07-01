package com.spd.cq.searspartsdirect.common.model;

import com.google.gson.annotations.SerializedName;

public class JobCodePartModel {
	
	@SerializedName("partCompositeKey")
	private PartCompositeKeyModel partCompositeKey;
	
	@SerializedName("priceAndAvailability")
	private PriceAndAvailabilityModel priceAndAvailability;
	
	@SerializedName("partImage")
	private PartImageModel partImage;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PartCompositeKeyModel getPartCompositeKey() {
		return partCompositeKey;
	}

	public void setPartCompositeKey(PartCompositeKeyModel partCompositeKey) {
		this.partCompositeKey = partCompositeKey;
	}

	public PriceAndAvailabilityModel getPriceAndAvailability() {
		return priceAndAvailability;
	}

	public void setPriceAndAvailability(
			PriceAndAvailabilityModel priceAndAvailability) {
		this.priceAndAvailability = priceAndAvailability;
	}

	@Override
	public String toString() {
		return "JobCodePart [partCompositeKey=" + partCompositeKey
				+ ", priceAndAvailability=" + priceAndAvailability
				+ ", description=" + description + "]";
	}

	public PartImageModel getPartImage() {
		return partImage;
	}

	public void setPartImage(PartImageModel partImage) {
		this.partImage = partImage;
	}
}
