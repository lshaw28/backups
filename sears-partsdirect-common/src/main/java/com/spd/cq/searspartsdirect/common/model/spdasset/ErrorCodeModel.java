package com.spd.cq.searspartsdirect.common.model.spdasset;

public class ErrorCodeModel {
	
	private String code;
	private String condition;
	private String repairPath;
	
	public ErrorCodeModel(String code, String condition, String repairPath) {
		this.code = code;
		this.condition = condition;
		this.repairPath = repairPath;
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
}
