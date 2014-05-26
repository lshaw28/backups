package com.spd.cq.searspartsdirect.common.model;

public class PartRestriction {
	private String restrictionId;

	private String restrictionType;

	private String partRestrictionText;

	private Boolean excessiveRestrictionFlag;

	public String getRestrictionId() {
	return restrictionId;
	}

	public void setRestrictionId(String restrictionId) {
	this.restrictionId = restrictionId;
	}

	public String getRestrictionType() {
	return restrictionType;
	}

	public void setRestrictionType(String restrictionType) {
	this.restrictionType = restrictionType;
	}

	public String getPartRestrictionText() {
	return partRestrictionText;
	}

	public void setPartRestrictionText(String partRestrictionText) {
	this.partRestrictionText = partRestrictionText;
	}

	public Boolean getExcessiveRestrictionFlag() {
	return excessiveRestrictionFlag;
	}

	public void setExcessiveRestrictionFlag(Boolean excessiveRestrictionFlag) {
	this.excessiveRestrictionFlag = excessiveRestrictionFlag;
	}


}
