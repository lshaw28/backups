package com.spd.cq.searspartsdirect.common.fixture;

import org.apache.sling.api.resource.ValueMap;
import org.mockito.Mockito;

public class BrowseGlossaryTagFixture {
	
	public void setUpProperties(ValueMap properties) {
		Mockito.when(properties.get("Glossary",new String[0])).thenReturn(getJSONData());
	}
	
	public String[] getJSONData() {
		return new String[] {"{\"./Glossary\":\"\",\"glossaryLetter\":\"A-D\"}"};
	}
}
