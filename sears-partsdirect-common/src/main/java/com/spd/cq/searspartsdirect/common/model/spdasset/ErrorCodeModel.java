package com.spd.cq.searspartsdirect.common.model.spdasset;

public class ErrorCodeModel {
	
	private String code;
	private String condition;
	private String repairPath;
	private String repairPathText;
	private String path;
	private String shopPartsLink;
	
	public ErrorCodeModel(String path, String code, String condition, String repairPath, String shopPartsLink, String repairPathText) {
		this.code = code;
		this.condition = condition;
		this.repairPath = repairPath;
		this.path = path;
		this.shopPartsLink = shopPartsLink;
		this.repairPathText = repairPathText;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getRepairPath() {
		return repairPath;
	}
	public void setRepairPath(String repairPath) {
		this.repairPath = repairPath;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getShopPartsLink() {
		return shopPartsLink;
	}

	public void setShopPartsLink(String shopPartsLink) {
		this.shopPartsLink = shopPartsLink;
	}

	public String getRepairPathText() {
		return repairPathText;
	}

	public void setRepairPathText(String repairPathText) {
		this.repairPathText = repairPathText;
	}
}
