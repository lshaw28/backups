package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import com.day.cq.wcm.api.Page;

public class ParentPageTagFixture {

	private Page parentPage;
	
	public ParentPageTagFixture(Page currentPage) {
		parentPage = mock(Page.class);
		when(currentPage.getParent()).thenReturn(parentPage);
	}

	public String getParent() {
		return "/parent";
	}

	public void setUpParent() {
		when(parentPage.getPath()).thenReturn(getParent());
	}

	public void setUpNoParent() {
		// We do not set up a path for the parent page here.
	}
	
}
