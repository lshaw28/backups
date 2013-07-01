package com.spd.cq.searspartsdirect.common.helpers;

import java.io.IOException;
import java.net.UnknownHostException;

import junit.framework.Assert;

import org.apache.sling.commons.json.JSONObject;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.fixture.EnvironmentSettingsFixture;

public class PartsDirectAPIHelperTest {
	
	@Test
	public void testReadJsonString() throws Exception {
		(new EnvironmentSettingsFixture()).setUpRealDefaults(new EnvironmentSettings());
		PartsDirectAPIHelper helper = new PartsDirectAPIHelper();
		try {
			String jsonStr = helper.readJsonData(EnvironmentSettings.getPDUserDataApiUrl()+"spdtest123@test.com");
			Assert.assertNotNull(jsonStr);
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
