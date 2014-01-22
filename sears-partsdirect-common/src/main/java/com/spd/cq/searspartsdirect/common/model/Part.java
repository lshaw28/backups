package com.spd.cq.searspartsdirect.common.model;

public class Part {
	
	private String partNumber;
	private String productGroupId;
	private String supplierId;
	private String description;
	
	public Part() {
	}

	public Part(String partNumber, String productGroupId, String supplierId,
			String description) {
		this.partNumber = partNumber;
		this.productGroupId = productGroupId;
		this.supplierId = supplierId;
		this.description = description;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Part [partNumber=" + partNumber + ", productGroupId="
				+ productGroupId + ", supplierId=" + supplierId
				+ ", description=" + description + "]";
	}
}
