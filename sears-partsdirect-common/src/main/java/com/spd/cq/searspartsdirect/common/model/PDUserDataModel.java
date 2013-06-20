package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

public class PDUserDataModel {
	private String firstName;
	private String lastName;
	private List<MyProfileModel> myProfileModels;
	private List<CartLineModel> shoppingCart;
	private boolean loggedIn;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	@Override
	public String toString() {
		return "PDUserDataModel [firstName=" + firstName + ", lastName="
				+ lastName + ", myProfileModels=" + myProfileModels
				+ ", shoppingCart=" + shoppingCart + ", loggedIn=" + loggedIn
				+ "]";
	}
}
