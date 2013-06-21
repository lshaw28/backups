package com.spd.cq.searspartsdirect.common.helpers;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.fixture.EnvironmentSettingsFixture;

public class PartsDirectAPIHelperTest {
	
	@Test
	public void testReadJsonFromUrl() throws Exception {
		(new EnvironmentSettingsFixture()).setUpRealDefaults(new EnvironmentSettings());
		PartsDirectAPIHelper helper = new PartsDirectAPIHelper();
		//Mockito.when(helper.readJsonFromUrl(Mockito.any(String.class))).thenReturn(Mockito.any(JSONObject.class));
		JSONObject json = helper.readJsonFromUrl(EnvironmentSettings.getPDUserDataApiUrl()+"&profileid=c4ccbcf4-3b71-4071-83dc-d88c75aded8c&cartid=8a6bc7483f5ba81b013f5dd11c360013");
		Assert.assertNotNull(json);
	}
}
