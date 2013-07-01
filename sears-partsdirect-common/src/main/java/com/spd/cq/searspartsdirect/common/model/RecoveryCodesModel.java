package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.spd.cq.searspartsdirect.common.model.spdasset.PartTypeModel;


public class RecoveryCodesModel {
	
	private String codeId;
	private String description;
	
	@SerializedName("recoveryPartsModel")
	private List<RecoveryPartsModel> recoveryPartsModel;
	
	private PartTypeModel partTypeModel;
	
	private List<RelatedGuideModel> guides;
	
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<RecoveryPartsModel> getRecoveryPartsModel() {
		return recoveryPartsModel;
	}
	public void setRecoveryPartsModel(List<RecoveryPartsModel> recoveryPartsModel) {
		this.recoveryPartsModel = recoveryPartsModel;
	}
	public PartTypeModel getPartTypeModel() {
		return partTypeModel;
	}
	public void setPartTypeModel(PartTypeModel partTypeModel) {
		this.partTypeModel = partTypeModel;
	}
	public List<RelatedGuideModel> getGuides() {
		return guides;
	}
	public void setGuides(List<RelatedGuideModel> guides) {
		this.guides = guides;
	}
	@Override
	public String toString() {
		return "RecoveryCodesModel [codeId=" + codeId + ", description="
				+ description + ", recoveryPartsModel=" + recoveryPartsModel
				+ ", partTypeModel=" + partTypeModel + ", guides=" + guides
				+ "]";
	}
}