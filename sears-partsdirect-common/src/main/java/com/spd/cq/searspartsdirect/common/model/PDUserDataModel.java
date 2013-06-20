package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

public class PDUserDataModel {
	private String firstName;
	private String lastname;
	private List<MyProfileModel> myProfileModels;
	private List<CartLineModel> shoppingCart;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public List<MyProfileModel> getMyProfileModels() {
		return myProfileModels;
	}
	public void setMyProfileModels(List<MyProfileModel> myProfileModels) {
		this.myProfileModels = myProfileModels;
	}
	public List<CartLineModel> getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(List<CartLineModel> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
}
