package com.spd.cq.searspartsdirect.common.model;

import java.util.Arrays;


public class CartModel {
	
	private CartLineModel[] cartLine;

	public CartLineModel[] getCartLine() {
		return cartLine;
	}

	public void setCartLine(CartLineModel[] cartLine) {
		this.cartLine = cartLine;
	}

	@Override
	public String toString() {
		return "CartModel [cartLine=" + Arrays.toString(cartLine) + "]";
	}
}
