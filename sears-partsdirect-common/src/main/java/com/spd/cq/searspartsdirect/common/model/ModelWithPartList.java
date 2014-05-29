package com.spd.cq.searspartsdirect.common.model;

import com.google.gson.annotations.SerializedName;

public class ModelWithPartList {

	private Integer id;
	private String modelNumber;
	private String formattedModelNumber;
	private String modelDescription;
	private String brandId;
	private String brandName;
	private String seoFormattedBrandName;
	private String categoryId;
	private String categoryName;
	private String seoFormattedProductTypeName;
	@SerializedName("parts")
	private ModelPart[] modelPart;
	private Boolean hasManuals;
	private Component[] components;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getFormattedModelNumber() {
		return formattedModelNumber;
	}

	public void setFormattedModelNumber(String formattedModelNumber) {
		this.formattedModelNumber = formattedModelNumber;
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSeoFormattedBrandName() {
		return seoFormattedBrandName;
	}

	public void setSeoFormattedBrandName(String seoFormattedBrandName) {
		this.seoFormattedBrandName = seoFormattedBrandName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSeoFormattedProductTypeName() {
		return seoFormattedProductTypeName;
	}

	public void setSeoFormattedProductTypeName(
			String seoFormattedProductTypeName) {
		this.seoFormattedProductTypeName = seoFormattedProductTypeName;
	}

	public ModelPart[] getModelPart() {
		return modelPart;
	}

	public void setModelPart(ModelPart[] modelPart) {
		this.modelPart = modelPart;
	}

	public Boolean getHasManuals() {
		return hasManuals;
	}

	public void setHasManuals(Boolean hasManuals) {
		this.hasManuals = hasManuals;
	}

	public Component[] getComponents() {
		return components;
	}

	public void setComponents(Component[] components) {
		this.components = components;
	}

}
