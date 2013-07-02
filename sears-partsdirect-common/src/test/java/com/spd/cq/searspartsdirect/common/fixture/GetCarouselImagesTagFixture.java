package com.spd.cq.searspartsdirect.common.fixture;

import javax.servlet.jsp.PageContext;

import static org.mockito.Mockito.*;

public class GetCarouselImagesTagFixture {

	PageContext pageContext;
	
	public GetCarouselImagesTagFixture(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public void removeCurrentNode() {
		when(pageContext.findAttribute("currentNode")).thenReturn(null);
	}

}
