package com.spd.cq.searspartsdirect.common.part;

public class AvailablePacks {
	
	public int packs;
	
	public String price;

	public AvailablePacks(int packs, String price) {
		this.packs = packs;
		this.price = price;
	}

	public int getPacks() {
		return packs;
	}

	public void setPacks(int packs) {
		this.packs = packs;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AvailablePacks [packs=" + packs + ", price=" + price + "]";
	}
}
