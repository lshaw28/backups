package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;

public class ModelSubcomponentAPIHelperFixture {

	private final SlingHttpServletRequest request;

	private final static String TEST_BRAND = "Kenmore";
	private final static String TEST_CATEGORY = "Dishwasher";
	private final static String TEST_MODEL = "66513593K600";

	public ModelSubcomponentAPIHelperFixture() throws Exception {
		new EnvironmentSettingsFixture().setUpRealDefaults(new EnvironmentSettings());
		final Map<String,Object> attributes = new HashMap<String,Object>();
		request = mock(SlingHttpServletRequest.class);
		doAnswer(new Answer<Void>() {
			//@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if (arguments != null && arguments.length == 2 && arguments[0] != null) {
					attributes.put((String)arguments[0], arguments[1]);
				}
				return null;
			}
		}).when(request).setAttribute(anyString(),anyObject());
		when(request.getAttribute(anyString())).thenAnswer(new Answer<Object>() {
			//@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if (arguments != null && arguments.length == 1 && arguments[0] != null) {
					String key = (String) arguments[0];
					if (attributes.containsKey(key)) {
						return attributes.get(key);
					}
				}
				return null;
			}
		});
	}

	public String getFiatJson() {
		return "{\"modelDescription\":\"Dishwasher\",\"symptoms\":{\"symptom\":[{\"description\":\"DRAINAGE: WATER DOES NOT DRAIN\",\"modelNumber\":null,\"successfulSolutions\":null,\"successfulFrequency\":49.528,\"recoveryCodesModel\":[]},{\"description\":\"STOPPAGE: WILL NOT START\",\"modelNumber\":null,\"successfulSolutions\":null,\"successfulFrequency\":34.373,\"recoveryCodesModel\":[]},{\"description\":\"CLEANING: DISHES NOT CLEAN\",\"modelNumber\":null,\"successfulSolutions\":null,\"successfulFrequency\":6.24,\"recoveryCodesModel\":[]},{\"description\":\"OTHER REPAIRS\",\"modelNumber\":null,\"successfulSolutions\":null,\"successfulFrequency\":3.985,\"recoveryCodesModel\":[]},{\"description\":\"LEAKS: DISHWASHER LEAKING\",\"modelNumber\":null,\"successfulSolutions\":null,\"successfulFrequency\":2.91,\"recoveryCodesModel\":[]},{\"description\":\"LEAKS: DISHWASHER LEAKINGPART@SIXCORNERS 2/10\",\"modelNumber\":null,\"successfulSolutions\":null,\"successfulFrequency\":1.626,\"recoveryCodesModel\":[]}]},\"manualsCount\":null,\"tabs\":{\"tab\":[{\"tabsLink\":\"http://www.searspartsdirect.com/partsdirect/part-model/Kenmore-Parts/Dishwasher-Parts/Model-66513593K600/0582/0130000\",\"tabsName\":\"Parts\"},{\"tabsLink\":\"http://www.searspartsdirect.com/partsdirect/part-model/Kenmore-Parts/Dishwasher-Parts/user-manuals/manual-downloads/Model-66513593K600/0582/0130000?modelNumber=66513593K600&diagramPageId=&documentId=\",\"tabsName\":\"Manuals (2)\"},{\"tabsLink\":\"http://www.searspartsdirect.com/partsdirect/part-model/Kenmore-Parts/Dishwasher-Parts/Repair-Maintenance/Questions-Answers/Model-66513593K600/0582/0130000?mmlLandingSearch=66513593K600&modelNumber=66513593K600&diagramPageId=&documentId=\",\"tabsName\":\"Questions & Answers\"},{\"tabsLink\":\"http://www.searspartsdirect.com/partsdirect/part-model/Kenmore-Parts/Dishwasher-Parts/Repair-Maintenance/Repair-Symptoms/Model-66513593K600/0582/0130000?modelNumber=66513593K600&diagramPageId=&documentId=\",\"tabsName\":\"Repair Symptoms & Solutions (6)\"}]}}";
	}

	public String getFiatUrl() {
		return "http://partsapivip.qa.ch3.s.com/pd-services/intra/v1/commonSymptoms/modelInfo?modelNumber=66513593K600&categoryName=Dishwasher&brandName=Kenmore";
	}

	public String getBrandName() {
		return TEST_BRAND;
	}

	public String getCategoryName() {
		return TEST_CATEGORY;
	}

	public String getModelNumber() {
		return TEST_MODEL;
	}

	public SlingHttpServletRequest getRequest() {
		return request;
	}

}
