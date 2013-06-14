package com.spd.cq.searspartsdirect.common.fixture;

import javax.servlet.jsp.PageContext;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import static org.mockito.Mockito.*;

public class TagsByPageTagFixture {
	
	public TagsByPageTagFixture(PageContext pageContext, PageManager pageManager, Page currentPage) {
		Page testPage = mock(Page.class);
		when(pageManager.getPage(getTestPagePath())).thenReturn(testPage);
		Tag testTagInType = mock(Tag.class);
		when(testTagInType.getTagID()).thenReturn("searspartsdirect:"+getTestTagType()+"/a");
		Tag testTagNotInType = mock(Tag.class);
		when(testTagNotInType.getTagID()).thenReturn("searspartsdirect:THESEARENOTHEDROIDSYOUARELOOKINGFOR/a");
		Tag[] tags = {testTagInType,testTagNotInType};
		when(testPage.getTags()).thenReturn(tags);
		when(currentPage.getTags()).thenReturn(tags);
	}
	
	public String getTestPagePath() {
		return "/page/path";
	}

	public String getTestTagType() {
		return "tag_type";
	}
}
