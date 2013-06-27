package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MyProfileModel {
	
	@SerializedName("profileModelsList")
	private List<ProfileModelsListModel> profileModelsList;
	
	public MyProfileModel() {
	}
	
	public List<ProfileModelsListModel> getProfileModelsList() {
		return profileModelsList;
	}

	public void setProfileModelsList(List<ProfileModelsListModel> profileModelsList) {
		this.profileModelsList = profileModelsList;
	}

	@Override
	public String toString() {
		return "MyProfileModel [profileModelsList=" + profileModelsList + "]";
	}

}
