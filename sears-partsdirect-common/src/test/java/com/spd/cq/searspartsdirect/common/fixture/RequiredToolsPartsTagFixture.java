package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.ValueMap;


public class RequiredToolsPartsTagFixture {
	
	private ValueMap properties;
	
	public RequiredToolsPartsTagFixture(ValueMap properties) {
		this.properties = properties;
	}
	
	public void setUpSomeTools() {
		//String[]  multiJsons = properties.get("multi",new String[0]);
		when(properties.get("multiTools",new String[0])).thenReturn(someToolsOrParts());
	}
	
	public void setUpSomeParts() {
		//String[]  multiJsons = properties.get("multi",new String[0]);
		when(properties.get("multiParts",new String[0])).thenReturn(someToolsOrParts());
	}
	
	private String[] someToolsOrParts() {
		return new String[]{
				"{\"text\":\"text1\",\"id\":\"id1\",\"url\":\"url1\"}",
				"{\"text\":\"text2\",\"id\":\"id2\",\"url\":\"url2\"}",
		};
	}
}
