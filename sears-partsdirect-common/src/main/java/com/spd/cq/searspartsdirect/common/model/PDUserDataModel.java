package com.spd.cq.searspartsdirect.common.model;

import com.google.gson.annotations.SerializedName;

public class PDUserDataModel {
	public PDUserDataModel() {
	}

	private String firstName;
	private String lastName;
	
	@SerializedName("username")
	private String userName;
	
	@SerializedName("ownedModels")
	private MyProfileModel myProfileModels;
	
	@SerializedName("cart")
	private CartModel cart;
	
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
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public MyProfileModel getMyProfileModels() {
		return myProfileModels;
	}
	public void setMyProfileModels(MyProfileModel myProfileModels) {
		this.myProfileModels = myProfileModels;
	}

	public CartModel getCart() {
		return cart;
	}
	public void setCart(CartModel cart) {
		this.cart = cart;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "PDUserDataModel [firstName=" + firstName + ", lastName="
				+ lastName + ", userName=" + userName + ", myProfileModels="
				+ myProfileModels + ", cart=" + cart + ", loggedIn=" + loggedIn
				+ "]";
	}
}
