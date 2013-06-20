package com.spd.cq.searspartsdirect.common.model;

public class MyProfileModel {
	
	private String brand;
	private String category;
	private String modelNumber;
	private String url;
	
	public MyProfileModel(String brand, String category, String modelNumber,
			String url) {
		super();
		this.brand = brand;
		this.category = category;
		this.modelNumber = modelNumber;
		this.url = url;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "MyProfileModel [brand=" + brand + ", category=" + category
				+ ", modelNumber=" + modelNumber + ", url=" + url + "]";
	}

}
