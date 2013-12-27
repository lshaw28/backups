package com.spd.cq.searspartsdirect.common.model;

import java.math.BigDecimal;
import java.util.List;

public class AirFilterPart {

	private String availabilityStatus;
	private boolean availableInStore;
	private int availablePacks;
	private boolean backOrdered;
	private String basePartNumber;
	private String partNumber;
	private String supplierId;
	private boolean inStock;
	private String manufacturer;
	private int mervRating;
	private String partDivId;
	private String partPls;
	private BigDecimal priceForParts;
	private String productGroupDescription;
	private String shippingWeight;
	private String shippingLength;
	private String shippingWidth;
	private String shippingHeight;
	private String thickness;
	private List<Subscription> subscriptions;
	private boolean subscribable;

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public boolean isAvailableInStore() {
		return availableInStore;
	}

	public void setAvailableInStore(boolean availableInStore) {
		this.availableInStore = availableInStore;
	}

	public int getAvailablePacks() {
		return availablePacks;
	}

	public void setAvailablePacks(int availablePacks) {
		this.availablePacks = availablePacks;
	}

	public boolean isBackOrdered() {
		return backOrdered;
	}

	public void setBackOrdered(boolean backOrdered) {
		this.backOrdered = backOrdered;
	}

	public String getBasePartNumber() {
		return basePartNumber;
	}

	public void setBasePartNumber(String basePartNumber) {
		this.basePartNumber = basePartNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getMervRating() {
		return mervRating;
	}

	public void setMervRating(int mervRating) {
		this.mervRating = mervRating;
	}

	public String getPartDivId() {
		return partDivId;
	}

	public void setPartDivId(String partDivId) {
		this.partDivId = partDivId;
	}

	public String getPartPls() {
		return partPls;
	}

	public void setPartPls(String partPls) {
		this.partPls = partPls;
	}

	public BigDecimal getPriceForParts() {
		return priceForParts;
	}

	public void setPriceForParts(BigDecimal priceForParts) {
		this.priceForParts = priceForParts;
	}

	public String getProductGroupDescription() {
		return productGroupDescription;
	}

	public void setProductGroupDescription(String productGroupDescription) {
		this.productGroupDescription = productGroupDescription;
	}

	public String getShippingWeight() {
		return shippingWeight;
	}

	public void setShippingWeight(String shippingWeight) {
		this.shippingWeight = shippingWeight;
	}

	public String getShippingLength() {
		return shippingLength;
	}

	public void setShippingLength(String shippingLength) {
		this.shippingLength = shippingLength;
	}

	public String getShippingWidth() {
		return shippingWidth;
	}

	public void setShippingWidth(String shippingWidth) {
		this.shippingWidth = shippingWidth;
	}

	public String getShippingHeight() {
		return shippingHeight;
	}

	public void setShippingHeight(String shippingHeight) {
		this.shippingHeight = shippingHeight;
	}

	public String getThickness() {
		return thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public boolean isSubscribable() {
		return subscribable;
	}

	public void setSubscribable(boolean subscribable) {
		this.subscribable = subscribable;
	}

}
