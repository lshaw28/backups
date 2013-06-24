package com.spd.cq.searspartsdirect.common.helpers;

import java.io.IOException;
import java.net.UnknownHostException;

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
		try {
			JSONObject json = helper.readJsonFromUrl(EnvironmentSettings.getPDUserDataApiUrl()+"&profileid=c4ccbcf4-3b71-4071-83dc-d88c75aded8c&cartid=8a6bc7483f5ba81b013f5dd11c360013");
			Assert.assertNotNull(json);
		}  catch (UnknownHostException uhe) {
			Assert.assertTrue("API is not reachable, this is not our problem.",true);
		} catch (IOException ioe) {
			String message = ioe.getMessage();
			if (message.contains("Server returned HTTP")) {
				Assert.assertTrue("API reached but complaining, this is not our problem",true);
			} else {
				throw ioe;
			}
		}
	}
}
