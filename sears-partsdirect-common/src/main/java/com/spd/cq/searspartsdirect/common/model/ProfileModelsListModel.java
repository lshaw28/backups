package com.spd.cq.searspartsdirect.common.model;

public class ProfileModelsListModel {
	
	private String brandName;
	private String categoryName;
	private String modelNumber;
	private String itemURL;
	private String modelDescription;
	private long categoryId;
	private long brandId;
	
	public ProfileModelsListModel() {
	}

	public ProfileModelsListModel(String brandName, String categoryName,
			String modelNumber, String itemURL, String modelDescription, long categoryId, long brandId) {
		super();
		this.brandName = brandName;
		this.categoryName = categoryName;
		this.modelNumber = modelNumber;
		this.itemURL = itemURL;
		this.modelDescription = modelDescription;
		this.categoryId = categoryId;
		this.brandId = brandId;
	}

	public String getBrand() {
		return brandName;
	}
	public void setBrand(String brandName) {
		this.brandName = brandName;
	}
	
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getItemURL() {
		return itemURL;
	}

	public void setItemURL(String itemURL) {
		this.itemURL = itemURL;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	@Override
	public String toString() {
		return "ProfileModelsListModel [brandName=" + brandName
				+ ", categoryName=" + categoryName + ", modelNumber="
				+ modelNumber + ", itemURL=" + itemURL + ", modelDescription="
				+ modelDescription + ", categoryId=" + categoryId
				+ ", brandId=" + brandId + "]";
	}

}
