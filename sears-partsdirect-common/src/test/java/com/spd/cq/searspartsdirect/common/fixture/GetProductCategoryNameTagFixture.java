package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import javax.servlet.jsp.PageContext;

import com.day.cq.wcm.api.Page;

public class GetProductCategoryNameTagFixture {
	
	private String title;
	private Page currentPage;
	private PageContext pageContext;

	public GetProductCategoryNameTagFixture(PageContext pageContext){
		this.pageContext = pageContext;
		title = "some title";
		currentPage = mock(Page.class);
		when((Page) pageContext.findAttribute("currentPage")).thenReturn(currentPage);
		when(currentPage.getTitle()).thenReturn(title);
	}
	
}
