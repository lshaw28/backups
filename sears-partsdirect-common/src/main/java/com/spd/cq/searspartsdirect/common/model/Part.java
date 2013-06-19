package com.spd.cq.searspartsdirect.common.model;

public class Part {
 private String partNumber;
 private String productGroupId;
 private String supplierId;

public String getPartNumber() {
	return partNumber;
}

public void setPartNumber(String partNumber) {
	this.partNumber = partNumber;
}


public Part(String partNumber, String productGroupId, String supplierId) {
	super();
	this.partNumber = partNumber;
	this.productGroupId = productGroupId;
	this.supplierId = supplierId;
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
}
