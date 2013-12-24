package com.spd.cq.searspartsdirect.common.part;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AirFilterPartDetails {

	@SerializedName("partDivId")
	private String divId;

	@SerializedName("partPls")
	private String pls;

	@SerializedName("partNumber")
	private String number;

	@SerializedName("shippingLength")
	private String length;

	@SerializedName("shippingWidth")
	private String width;

	@SerializedName("shippingHeight")
	private String height;

	@SerializedName("shipingWeight")
	private String weight;
	
	@SerializedName("thickness")
	private String thickness;
	
	@SerializedName("mervRating")
	private int mervRating;
	
	@SerializedName("quality")
	private String quality;
	
	@SerializedName("availablePacks")
	private int availablePacks;
	
	@SerializedName("manufacturer")
	private String manufacturer;
	
	@SerializedName("subscribable")
	private boolean subscribable;
	
	@SerializedName("imageUrl")
	private String imageUrl;
	
	@SerializedName("priceForParts")
	private String priceForParts;
	
	@SerializedName("partDesc")
	private String partDesc;
	
	@SerializedName("basePartNumber")
	private String basePartNumber;
	
	private List<AvailablePacks> availablePacksList;
	
	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getPls() {
		return pls;
	}

	public void setPls(String pls) {
		this.pls = pls;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getThickness() {
		return thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public boolean isSubscribable() {
		return subscribable;
	}

	public void setSubscribable(boolean subscribable) {
		this.subscribable = subscribable;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getPriceForParts() {
		return priceForParts;
	}

	public void setPriceForParts(String priceForParts) {
		this.priceForParts = priceForParts;
	}

	public int getMervRating() {
		return mervRating;
	}

	public void setMervRating(int mervRating) {
		this.mervRating = mervRating;
	}

	public int getAvailablePacks() {
		return availablePacks;
	}

	public void setAvailablePacks(int availablePacks) {
		this.availablePacks = availablePacks;
	}

	public String getPartDesc() {
		return partDesc;
	}

	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}

	public String getBasePartNumber() {
		return basePartNumber;
	}

	public void setBasePartNumber(String basePartNumber) {
		this.basePartNumber = basePartNumber;
	}

	public List<AvailablePacks> getAvailablePacksList() {
		return availablePacksList;
	}

	public void setAvailablePacksList(List<AvailablePacks> availablePacksList) {
		this.availablePacksList = availablePacksList;
	}

	@Override
	public String toString() {
		return "AirFilterPartDetails [divId=" + divId + ", pls=" + pls
				+ ", number=" + number + ", length=" + length + ", width="
				+ width + ", height=" + height + ", weight=" + weight
				+ ", thickness=" + thickness + ", mervRating=" + mervRating
				+ ", quality=" + quality + ", availablePacks=" + availablePacks
				+ ", manufacturer=" + manufacturer + ", subscribable="
				+ subscribable + ", imageUrl=" + imageUrl + ", priceForParts="
				+ priceForParts + ", partDesc=" + partDesc
				+ ", basePartNumber=" + basePartNumber
				+ ", availablePacksList=" + availablePacksList + "]";
	}

}
