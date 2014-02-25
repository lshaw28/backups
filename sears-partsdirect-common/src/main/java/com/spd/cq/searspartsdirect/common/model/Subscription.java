package com.spd.cq.searspartsdirect.common.model;

public class Subscription {

	private String description;
	private long id;
	private String promotionCode;
	private int renewalPeriod;
	private String renewalPeriodType;

	public Subscription(String description, long id, String promotionCode, int renewalPeriod, String renewalPeriodType) {
		this.description = description;
		this.id = id;
		this.promotionCode = promotionCode;
		this.renewalPeriod = renewalPeriod;
		this.renewalPeriodType = renewalPeriodType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public int getRenewalPeriod() {
		return renewalPeriod;
	}

	public void setRenewalPeriod(int renewalPeriod) {
		this.renewalPeriod = renewalPeriod;
	}

	public String getRenewalPeriodType() {
		return renewalPeriodType;
	}

	public void setRenewalPeriodType(String renewalPeriodType) {
		this.renewalPeriodType = renewalPeriodType;
	}
}
