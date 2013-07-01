package com.spd.cq.searspartsdirect.common.fixture;

import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

import static org.mockito.Mockito.*;

public class DisplayTagTitleTagFixture {

	private TagManager tm;
	
	public DisplayTagTitleTagFixture(ResourceResolver resourceResolver) {
		tm = mock(TagManager.class);
		when(resourceResolver.adaptTo(TagManager.class)).thenReturn(tm);
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
