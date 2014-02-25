package com.spd.cq.searspartsdirect.common.part;

public class AirFilterList {
	
	private AirFilterPart bestAirFilters;
	private AirFilterPart betterAirFilters;
	private AirFilterPart goodAirFilters;

	public AirFilterPart getBestAirFilters() {
		return bestAirFilters;
	}

	public void setBestAirFilters(AirFilterPart bestAirFilters) {
		this.bestAirFilters = bestAirFilters;
	}

	public AirFilterPart getBetterAirFilters() {
		return betterAirFilters;
	}

	public void setBetterAirFilters(AirFilterPart betterAirFilters) {
		this.betterAirFilters = betterAirFilters;
	}

	public AirFilterPart getGoodAirFilters() {
		return goodAirFilters;
	}

	public void setGoodAirFilters(AirFilterPart goodAirFilters) {
		this.goodAirFilters = goodAirFilters;
	}

	@Override
	public String toString() {
		return "AirFilterList [bestAirFilters=" + bestAirFilters
				+ ", betterAirFilters=" + betterAirFilters
				+ ", goodAirFilters=" + goodAirFilters + "]";
	}

	
}
