package com.spd.cq.searspartsdirect.common.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class CartModel {
	
	@SerializedName("cartLines")
	private List<CartLinesModel> cartLines;

	public CartModel() {
	}

	@Override
	public String toString() {
		return "Cart [cartLine=" + cartLines + "]";
	}

	public List<CartLinesModel> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<CartLinesModel> cartLines) {
		this.cartLines = cartLines;
	}

	
}
