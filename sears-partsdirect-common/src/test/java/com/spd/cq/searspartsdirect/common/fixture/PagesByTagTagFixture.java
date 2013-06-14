package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;

public class PagesByTagTagFixture {

	public PagesByTagTagFixture(ResourceResolver resourceResolver) {
		TagManager tagManager = mock(TagManager.class);
		when(resourceResolver.adaptTo(TagManager.class)).thenReturn(tagManager);
		//Tag finderTag = tm.resolve(tag);
		String testTag = getTestTag();
		Tag finderTag = mock(Tag.class);
		when(tagManager.resolve(testTag)).thenReturn(finderTag);
		
		final List<Resource> tagged = new ArrayList<Resource>();
		Page page1 = mock(Page.class);
		tagged.add(makeContentResourceFor(page1));
		tagged.add(makeContentResourceFor(null));
		Page page2 = mock(Page.class);
		tagged.add(makeContentResourceFor(page2));
		when(finderTag.find()).thenAnswer(new Answer<Iterator<Resource>>() {

			public Iterator<Resource> answer(InvocationOnMock invocation)
					throws Throwable {
				return tagged.iterator();
			}
			
		});
	}
	
	Resource makeContentResourceFor(Page pageOrNot) {
		Resource jcrContent = mock(Resource.class);
		Resource parent = mock(Resource.class);
		when (jcrContent.getParent()).thenReturn(parent);
		if (pageOrNot != null) {
			when(parent.adaptTo(Page.class)).thenReturn(pageOrNot);
		}
		return jcrContent;
	}
	
	public String getTestTag() {
		return "test_tag";
	}

}
