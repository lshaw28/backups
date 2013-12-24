package com.spd.cq.searspartsdirect.common.part;

import java.util.List;

public class AirFilterPart {

	private String quality;
	List<AirFilterPartDetails> part;

	public List<AirFilterPartDetails> getPart() {
		return part;
	}

	public void setPart(List<AirFilterPartDetails> part) {
		this.part = part;
	}

	@Override
	public String toString() {
		return "AirFilterPart [part=" + part + "]";
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	
}
