package com.spd.cq.searspartsdirect.common.fixture;

import java.util.ArrayList;
import java.util.List;

import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;

public class GetSymptomFrequencyFixture {
	
	public List<SymptomModel> getSymptoms() {
		List<SymptomModel> models = new ArrayList<SymptomModel>();
		SymptomModel model = new SymptomModel(null, "some title", "description", "201");
		models.add(model);
		return models;
	}

}
