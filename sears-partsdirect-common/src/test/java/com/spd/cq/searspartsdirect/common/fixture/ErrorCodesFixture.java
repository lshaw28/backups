package com.spd.cq.searspartsdirect.common.fixture;

public class ErrorCodesFixture {
	
	public String[] getJSONData() {
		return new String[] {"{\"./errorCodeTable\":\"\",\"codeType\":\"Type1\",\"code\":\"code 101\",\"condition\":\"door is not working\",\"checkRepairLink\":\"/content/searspartsdirect/en/test\"}",
			   "{\"./errorCodeTable\":\"\",\"codeType\":\"Type2\",\"code\":\"code 102\",\"condition\":\"Latch switch broken\",\"checkRepairLink\":\"buy a new part\"}",
			   "{\"./errorCodeTable\":\"\",\"codeType\":\"Type1\",\"code\":\"code 101\",\"condition\":\"door is not working\",\"checkRepairLink\":\"/content/searspartsdirect/en/test\"}",
			   "{\"./errorCodeTable\":\"\",\"codeType\":\"\",\"code\":\"code 101\",\"condition\":\"door is not working\",\"checkRepairLink\":\"/content/searspartsdirect/en/test\"}"};
		
	}

}
