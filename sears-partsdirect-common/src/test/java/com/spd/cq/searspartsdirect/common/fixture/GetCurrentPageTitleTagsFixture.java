package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import javax.servlet.jsp.PageContext;

import com.day.cq.wcm.api.Page;

public class GetCurrentPageTitleTagsFixture {
	
	private Page currentPage;
	private PageContext pageContext;

	public GetCurrentPageTitleTagsFixture(PageContext pageContext){
		this.pageContext = pageContext;
		currentPage = mock(Page.class);
		when((Page) pageContext.findAttribute("currentPage")).thenReturn(currentPage);
	}
	
	public void setProductTitle(){
		when(currentPage.getTitle()).thenReturn("productTitle");
	}
	
	public void setBrandTitle(){
		when(currentPage.getTitle()).thenReturn("brandTitle");
	}
	
}
