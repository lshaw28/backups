package com.spd.cq.searspartsdirect.common.model;

public class PartCompositeKeyModel {
	
	private String partNumber;
	private String productGroupId;
	private String supplierId;
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
	@Override
	public String toString() {
		return "PartCompositeKeyModel [partNumber=" + partNumber
				+ ", productGroupId=" + productGroupId + ", supplierId="
				+ supplierId + "]";
	}
	

}
