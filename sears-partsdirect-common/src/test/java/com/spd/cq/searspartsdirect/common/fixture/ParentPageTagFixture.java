package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class ParentPageTagFixture {

	private Page currentPage;
	private Page parentPage;
	
	public ParentPageTagFixture(Page currentPage) {
		this.currentPage = currentPage;
		parentPage = mock(Page.class);
	}

	public String getParent() {
		return "/parent";
	}

	public void setUpParent() {
		attachParent();
		when(parentPage.getPath()).thenReturn(getParent());
	}

	public void setUpNoParent() {
		when(currentPage.getParent()).thenReturn((Page)null);
	}

	public void setUpNullPathParent() {
		attachParent();
		when(parentPage.getPath()).thenReturn((String)null);
	}
	
	public void setUpEmptyPathParent() {
		attachParent();
		when(parentPage.getPath()).thenReturn(Constants.EMPTY);
	}
	
	private void attachParent() {
		when(currentPage.getParent()).thenReturn(parentPage);
	}
}
