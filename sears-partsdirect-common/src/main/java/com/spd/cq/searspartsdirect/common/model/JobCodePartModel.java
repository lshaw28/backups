package com.spd.cq.searspartsdirect.common.model;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

public class JobCodePartModel {

	@SerializedName("description")
	private String name;

	@SerializedName("partNumber")
	private String number;

	@SerializedName("recoveryFrequency")
	private float frequency;

	@SerializedName("sellingPrice")
	private BigDecimal price;

	private boolean available;

	private String availabilityStatus;

	@SerializedName("hasRestriction")
	private boolean restriction;

	@SerializedName("partDetailUrl")
	private String url;

	@SerializedName("partImage")
	private PartImageModel image;
	
	private boolean returnable;
	
	private String productGroupId;
	
	private String supplierId;
	
	private boolean availableInStore;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public float getFrequency() {
		return frequency;
	}

	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getAvailabilityStatus() {

		if ("INST".equalsIgnoreCase(availabilityStatus)) {
			return "In stock";
		} else if ("BORD".equalsIgnoreCase(availabilityStatus)) {
			return "Back ordered";
		} else if ("NLA".equalsIgnoreCase(availabilityStatus)) {
			return "No longer available";
		} else if ("PNF".equalsIgnoreCase(availabilityStatus)) {
			return "Part not found";
		} else {
			return "";
		}
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public boolean isRestriction() {
		return restriction;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setRestriction(boolean restriction) {
		this.restriction = restriction;
	}

	public PartImageModel getImage() {
		return image;
	}

	public void setImage(PartImageModel image) {
		this.image = image;
	}
	
	public boolean isReturnable() {
		return returnable;
	}

	public void setReturnable(boolean returnable) {
		this.returnable = returnable;
	}

	@Override
	public String toString() {
		return "[name: " + name
				+ " || number: " + number
				+ " || frequency: " + frequency + " || price: " + price
				+ " || availabilityStatus: " + availabilityStatus
				+ " || imageUrl: " + image.getUrl()
				+ " || restriction: " + restriction	+ "]";
	}

	public String getProductGroupId() {
		return productGroupId;
	}

	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public boolean isAvailableInStore() {
		return availableInStore;
	}

	public void setAvailableInStore(boolean availableInStore) {
		this.availableInStore = availableInStore;
	}
}
