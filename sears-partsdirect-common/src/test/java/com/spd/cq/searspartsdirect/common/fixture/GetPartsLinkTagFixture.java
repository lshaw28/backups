package com.spd.cq.searspartsdirect.common.fixture;

import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.ModelSubcomponentAPIHelper;
import com.spd.cq.searspartsdirect.common.model.PDModelSubcomponentModel;
import com.spd.cq.searspartsdirect.common.model.PDTab;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPartsLinkTagFixture {
	
	SlingHttpServletRequest slingRequest;
	PDModelSubcomponentModel fakeResult;
	
	public GetPartsLinkTagFixture(SlingHttpServletRequest slingRequest, PageContext pageContext) throws Exception {
		new EnvironmentSettingsFixture().setUpRealDefaults(new EnvironmentSettings());
		this.slingRequest = slingRequest;
	}

	public String getModelNumber() {
		return "66513593K600";
	}
	
	public void setUpFakeCompleteApiResult() {
		setUpFakeApiResult();
		final PDTab[] tabs = new PDTab[2];
		when(fakeResult.getTabsArr()).thenReturn(tabs);
		PDTab parts = mock(PDTab.class);
		when(parts.getTabsName()).thenReturn("Parts");
		when(parts.getTabsLink()).thenReturn("parts:link");
		tabs[1] = parts;
		PDTab notParts = mock(PDTab.class);
		when(notParts.getTabsName()).thenReturn("Not Parts");
		tabs[0] = notParts;
	}
	
	public void setUpFakeEmptyApiResult() {
		setUpFakeApiResult();
		final PDTab[] tabs = new PDTab[0];
		when(fakeResult.getTabsArr()).thenReturn(tabs);
	}
	
	private void setUpFakeApiResult() {
		fakeResult = mock(PDModelSubcomponentModel.class);
		when(slingRequest.getAttribute(ModelSubcomponentAPIHelper.MODELSUB_REQATTR)).thenReturn(fakeResult);
	}

	

}
