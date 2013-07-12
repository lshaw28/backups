package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

public class DisplayTagTitleTagFixture {

	private TagManager tm;
	private JspWriter bitbucket;
	
	public DisplayTagTitleTagFixture(ResourceResolver resourceResolver, PageContext pageContext) {
		tm = mock(TagManager.class);
		when(resourceResolver.adaptTo(TagManager.class)).thenReturn(tm);
		bitbucket = mock(JspWriter.class);
		when(pageContext.getOut()).thenReturn(bitbucket);
	}

	public String getTagId() {
		return "FOOOOOOO";
	}

	public void setUpExplodingTag() {
		when(tm.resolve(getTagId())).thenReturn(null);
	}
	
	public void setUpResolvingTag() {
		Tag tag = mock(Tag.class);
		when(tag.getTitle()).thenReturn("BARRRR");
		when(tm.resolve(getTagId())).thenReturn(tag);
	}

}
