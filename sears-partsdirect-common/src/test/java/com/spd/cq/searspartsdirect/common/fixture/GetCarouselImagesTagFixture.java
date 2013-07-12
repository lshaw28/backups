package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.when;

import javax.servlet.jsp.PageContext;

public class GetCarouselImagesTagFixture {

	PageContext pageContext;
	
	public GetCarouselImagesTagFixture(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public void removeCurrentNode() {
		when(pageContext.findAttribute("currentNode")).thenReturn(null);
	}

}
