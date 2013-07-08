package com.spd.cq.searspartsdirect.common.fixture;

import org.apache.sling.api.resource.ValueMap;
import static org.mockito.Mockito.*;

public class GetYouTubeIDTagFixture {

	private ValueMap properties;

	final String validID = "https://www.youtube.com/watch?v=Kw1sS_gK3Rs";
	final String invalidID = "aclkmfeiwtgbnksadfqwiovlzv";

	public GetYouTubeIDTagFixture(ValueMap properties) {
		// String link = properties.get("youTubeLink", "");
		this.properties = properties;
	}

	public String getValidID() {
		return validID;
	}

	public String getInvalidID() {
		return invalidID;
	}

	public void setupValidID() {
		when(properties.get("youTubeLink", "")).thenReturn(getValidID());
	}

	public void setupInvalidID() {
		when(properties.get("youTubeLink", "")).thenReturn(getInvalidID());
	}

	public void setupExplodingID() {
		when(properties.get("youTubeLunk", "")).thenReturn(null);
	}

}
