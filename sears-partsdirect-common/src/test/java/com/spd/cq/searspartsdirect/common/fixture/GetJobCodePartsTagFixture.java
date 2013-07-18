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
}
