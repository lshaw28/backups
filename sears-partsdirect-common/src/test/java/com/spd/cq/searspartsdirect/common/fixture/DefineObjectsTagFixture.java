package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.when;

import org.apache.sling.api.SlingHttpServletRequest;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.WCMMode;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class DefineObjectsTagFixture {

	private SlingHttpServletRequest slingRequest;
	
	public DefineObjectsTagFixture(SlingHttpServletRequest slingRequest) {
		this.slingRequest = slingRequest;
	}

	public void setUpDesignMode() {
		when(slingRequest.getAttribute(Constants.CQ_WCMMODE_REQ_ATTR)).thenReturn(WCMMode.DESIGN);
	}
	public void setUpEditMode() {
		when(slingRequest.getAttribute(Constants.CQ_WCMMODE_REQ_ATTR)).thenReturn(WCMMode.EDIT);
	}
	public void setUpPreviewMode() {
		when(slingRequest.getAttribute(Constants.CQ_WCMMODE_REQ_ATTR)).thenReturn(WCMMode.PREVIEW);
	}
	public void setUpReadOnlyMode() {
		when(slingRequest.getAttribute(Constants.CQ_WCMMODE_REQ_ATTR)).thenReturn(WCMMode.READ_ONLY);
	}

	public void breakTheWorld() {
		when(slingRequest.getAttribute(Constants.CQ_WCMMODE_REQ_ATTR)).thenAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) throws Throwable {
				throw new RuntimeException("world broken. try again?");
			}
		});
	}
}
