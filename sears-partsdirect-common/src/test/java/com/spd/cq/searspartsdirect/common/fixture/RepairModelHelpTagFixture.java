package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

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
