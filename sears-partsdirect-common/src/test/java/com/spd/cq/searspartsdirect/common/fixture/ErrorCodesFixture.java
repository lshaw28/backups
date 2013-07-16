package com.spd.cq.searspartsdirect.common.fixture;

import org.apache.sling.api.resource.ValueMap;
import org.mockito.Mockito;

public class ErrorCodesFixture {
	
	public void setUpProperties(ValueMap properties) {
		Mockito.when(properties.get("errorCodeTable",new String[0])).thenReturn(getJSONData());
		Mockito.when(properties.get("codeType", String.class)).thenReturn(getCodeType());
	}
	
	public String[] getJSONData() {
		return new String[] {"{\"./errorCodeTable\":\"\",\"code\":\"code 101\",\"condition\":\"door is not working\",\"checkRepairLink\":\"/content/searspartsdirect/en/test\",\"shopParts\":\"/content/searspartsdirect/en/test\"}"};
	}
	
	public String getCodeType() {
		return "{\"codeType\":\"testtype\"}";
	}
}
