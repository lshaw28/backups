package com.spd.cq.searspartsdirect.common.model;

public class CartLinesModel {
	
	public CartLinesModel() {
	}

	private Part part;
	
	private int quantity;

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public CartLinesModel(Part part, int quantity) {
		super();
		this.part = part;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartLineModel [part=" + part + ", quantity=" + quantity + "]";
	}
}
