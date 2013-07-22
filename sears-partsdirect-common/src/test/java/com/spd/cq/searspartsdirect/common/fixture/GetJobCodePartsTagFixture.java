package com.spd.cq.searspartsdirect.common.fixture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.model.JobCodePartModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;

public class GetJobCodePartsTagFixture {

	public GetJobCodePartsTagFixture() throws Exception {
		(new EnvironmentSettingsFixture()).setUpRealDefaults(new EnvironmentSettings());
	}

	public List<JobCodeModel> mockValidJobCodes() {
		List<JobCodeModel> jobCodesMock = mockEmptyJobCodes();
		JobCodeModel jobCodeModel1 = new JobCodeModel("34761", null, null, null);
		JobCodeModel jobCodeModel2 = new JobCodeModel("678", null, null, null);
		jobCodesMock.add(jobCodeModel1);
		jobCodesMock.add(jobCodeModel2);
		return jobCodesMock;
	}

	public List<JobCodeModel> mockEmptyJobCodes() {
		List<JobCodeModel> jobCodesMock = new ArrayList<JobCodeModel>();
		return jobCodesMock;
	}

	public Map<String, List<JobCodePartModel>> setupValidJobCodeParts() {
		Map<String, List<JobCodePartModel>> jobCodeParts = new HashMap<String, List<JobCodePartModel>>();
		List<JobCodePartModel> parts = new ArrayList<JobCodePartModel>();
		parts.add(new JobCodePartModel());
		parts.add(new JobCodePartModel());
		jobCodeParts.put("one", parts);
		jobCodeParts.put("two", parts);
		return jobCodeParts;
	}

	public String getSuccessfulJsonResponse() {
		return "[{\"part\":[{\"forcedSubstitution\":\"N\",\"partName\":null,\"recoveryFrequency\":16.429,\"partNumber\":\"499486S\",\"productGroupId\":\"071\",\"supplierId\":\"500\",\"partImage\":{\"caption\":null,\"title\":null,\"width\":0,\"height\":0,\"resolution\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0071_500_499486S\",\"imageType\":null},\"sellingPrice\":\"13.99\",\"availabilityStatus\":\"INST\",\"forcedSubstitutionReason\":null,\"returnable\":true,\"suggestedQty\":null,\"qtyIncluded\":null,\"accessoryIndicator\":\"N\",\"backOrderedDate\":null,\"fPDAdditionalComments\":null,\"hasRestriction\":false,\"recoveryPartRestriction\":null,\"subbedFlag\":false,\"partDetailUrl\":\"/partsdirect/part-number/499486S/071/500\",\"available\":true,\"description\":\"FILTER-A/C C\"},{\"forcedSubstitution\":\"N\",\"partName\":null,\"recoveryFrequency\":15.714,\"partNumber\":\"RC12YC\",\"productGroupId\":\"098\",\"supplierId\":\"661\",\"partImage\":{\"caption\":null,\"title\":null,\"width\":0,\"height\":0,\"resolution\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0022_353_RC12YC\",\"imageType\":null},\"sellingPrice\":\"3.99\",\"availabilityStatus\":\"INST\",\"forcedSubstitutionReason\":null,\"returnable\":false,\"suggestedQty\":null,\"qtyIncluded\":null,\"accessoryIndicator\":\"Y\",\"backOrderedDate\":null,\"fPDAdditionalComments\":null,\"hasRestriction\":false,\"recoveryPartRestriction\":null,\"subbedFlag\":false,\"partDetailUrl\":\"/partsdirect/part-number/RC12YC/098/661\",\"available\":true,\"description\":\"PLUG RC12YC\"},{\"forcedSubstitution\":\"N\",\"partName\":null,\"recoveryFrequency\":12.857,\"partNumber\":\"273638S\",\"productGroupId\":\"071\",\"supplierId\":\"500\",\"partImage\":{\"caption\":null,\"title\":null,\"width\":0,\"height\":0,\"resolution\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0071_500_273638S\",\"imageType\":null},\"sellingPrice\":\"6.68\",\"availabilityStatus\":\"INST\",\"forcedSubstitutionReason\":null,\"returnable\":false,\"suggestedQty\":null,\"qtyIncluded\":\"1\",\"accessoryIndicator\":\"N\",\"backOrderedDate\":null,\"fPDAdditionalComments\":null,\"hasRestriction\":false,\"recoveryPartRestriction\":null,\"subbedFlag\":false,\"partDetailUrl\":\"/partsdirect/part-number/273638S/071/500\",\"available\":true,\"description\":\"FILTER-PRECL\"},{\"forcedSubstitution\":\"N\",\"partName\":null,\"recoveryFrequency\":10.714,\"partNumber\":\"696854\",\"productGroupId\":\"071\",\"supplierId\":\"500\",\"partImage\":{\"caption\":null,\"title\":null,\"width\":0,\"height\":0,\"resolution\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0071_500_696854\",\"imageType\":null},\"sellingPrice\":\"15.49\",\"availabilityStatus\":\"INST\",\"forcedSubstitutionReason\":null,\"returnable\":true,\"suggestedQty\":null,\"qtyIncluded\":\"1\",\"accessoryIndicator\":\"N\",\"backOrderedDate\":null,\"fPDAdditionalComments\":null,\"hasRestriction\":false,\"recoveryPartRestriction\":null,\"subbedFlag\":false,\"partDetailUrl\":\"/partsdirect/part-number/696854/071/500\",\"available\":true,\"description\":\"OIL FILTER\"},{\"forcedSubstitution\":\"N\",\"partName\":null,\"recoveryFrequency\":10.0,\"partNumber\":\"33448\",\"productGroupId\":\"071\",\"supplierId\":\"071\",\"partImage\":{\"caption\":null,\"title\":null,\"width\":0,\"height\":0,\"resolution\":null,\"imageURL\":null,\"imageType\":null},\"sellingPrice\":\"7.99\",\"availabilityStatus\":\"INST\",\"forcedSubstitutionReason\":null,\"returnable\":true,\"suggestedQty\":null,\"qtyIncluded\":null,\"accessoryIndicator\":\"Y\",\"backOrderedDate\":null,\"fPDAdditionalComments\":null,\"hasRestriction\":false,\"recoveryPartRestriction\":null,\"subbedFlag\":false,\"partDetailUrl\":\"/partsdirect/part-number/33448/071/071\",\"available\":true,\"description\":\"48 oz.30W\"}],\"recoveryId\":\"34761\"},{\"part\":[{\"forcedSubstitution\":\"N\",\"partName\":null,\"recoveryFrequency\":92.857,\"partNumber\":\"74003122\",\"productGroupId\":\"022\",\"supplierId\":\"464\",\"partImage\":{\"caption\":null,\"title\":null,\"width\":0,\"height\":0,\"resolution\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0022_335_74003122\",\"imageType\":null},\"sellingPrice\":\"44.8\",\"availabilityStatus\":\"INST\",\"forcedSubstitutionReason\":null,\"returnable\":true,\"suggestedQty\":null,\"qtyIncluded\":null,\"accessoryIndicator\":\"N\",\"backOrderedDate\":null,\"fPDAdditionalComments\":null,\"hasRestriction\":false,\"recoveryPartRestriction\":null,\"subbedFlag\":false,\"partDetailUrl\":\"/partsdirect/part-number/74003122/022/464\",\"available\":true,\"description\":\"SWITCH, ELEM\"}],\"recoveryId\":\"678\"}]";
	}
}
