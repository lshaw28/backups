package com.spd.cq.searspartsdirect.common.fixture;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import static org.mockito.Mockito.*;

public class RepairModelHelpTagFixture {

	private PageManager pageManager;
	private Page errorCodePage;
	
	public RepairModelHelpTagFixture(PageManager pageManager) {
		this.pageManager = pageManager;
	}
	
	public void setUpErrorCodesExist() {
		errorCodePage = mock(Page.class);
		when(errorCodePage.isValid()).thenReturn(true);
		when(pageManager.getPage(anyString())).thenAnswer(new Answer<Page>() {
			public Page answer(InvocationOnMock invocation) throws Throwable {
				when(errorCodePage.getPath()).thenReturn((String)invocation.getArguments()[0]);
				return errorCodePage;
			}
		});
	}
	
	public void setUpErrorCodesInvalid() {
		setUpErrorCodesExist();
		when(errorCodePage.isValid()).thenReturn(false);
	}

}
