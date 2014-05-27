package com.spd.cq.searspartsdirect.common.model;

public class ModelPart {
	private Integer id;
	private PartCompositeKey partCompositeKey;
	private String description;
	private String productType;
	private PartImage partImage;
	private PriceAndAvailability priceAndAvailability;
	private Boolean forcedSubstitution;
	private String otcStockIndicator;
	private PartRestriction[] partRestrictions ;
	private Boolean featureCopyAvailable;
	private Boolean shippingGuarantee;
	private Integer quantityInCart;
	private String diagramReferenceId;
	private String keyId;
	private Integer packSize;


	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public PartCompositeKey getPartCompositeKey() {
	return partCompositeKey;
	}

	public void setPartCompositeKey(PartCompositeKey partCompositeKey) {
	this.partCompositeKey = partCompositeKey;
	}

	public String getDescription() {
	return description;
	}

	public void setDescription(String description) {
	this.description = description;
	}

	public String getProductType() {
	return productType;
	}

	public void setProductType(String productType) {
	this.productType = productType;
	}

	public PartImage getPartImage() {
	return partImage;
	}

	public void setPartImage(PartImage partImage) {
	this.partImage = partImage;
	}

	public PriceAndAvailability getPriceAndAvailability() {
	return priceAndAvailability;
	}

	public void setPriceAndAvailability(PriceAndAvailability priceAndAvailability) {
	this.priceAndAvailability = priceAndAvailability;
	}

	public Boolean getForcedSubstitution() {
	return forcedSubstitution;
	}

	public void setForcedSubstitution(Boolean forcedSubstitution) {
	this.forcedSubstitution = forcedSubstitution;
	}

	public String getOtcStockIndicator() {
	return otcStockIndicator;
	}

	public void setOtcStockIndicator(String otcStockIndicator) {
	this.otcStockIndicator = otcStockIndicator;
	}

	public PartRestriction[] getPartRestrictions() {
	return partRestrictions;
	}

	public void setPartRestrictions(PartRestriction[] partRestrictions) {
	this.partRestrictions = partRestrictions;
	}

	public Boolean getFeatureCopyAvailable() {
	return featureCopyAvailable;
	}

	public void setFeatureCopyAvailable(Boolean featureCopyAvailable) {
	this.featureCopyAvailable = featureCopyAvailable;
	}

	public Boolean getShippingGuarantee() {
	return shippingGuarantee;
	}

	public void setShippingGuarantee(Boolean shippingGuarantee) {
	this.shippingGuarantee = shippingGuarantee;
	}

	public Integer getQuantityInCart() {
	return quantityInCart;
	}

	public void setQuantityInCart(Integer quantityInCart) {
	this.quantityInCart = quantityInCart;
	}

	public String getDiagramReferenceId() {
	return diagramReferenceId;
	}

	public void setDiagramReferenceId(String diagramReferenceId) {
	this.diagramReferenceId = diagramReferenceId;
	}

	

	public String getKeyId() {
	return keyId;
	}

	public void setKeyId(String keyId) {
	this.keyId = keyId;
	}

	public Integer getPackSize() {
	return packSize;
	}

	public void setPackSize(Integer packSize) {
	this.packSize = packSize;
	}

	


}
