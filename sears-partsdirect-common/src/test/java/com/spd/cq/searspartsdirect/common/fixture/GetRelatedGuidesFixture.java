package com.spd.cq.searspartsdirect.common.fixture;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.ValueMap;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.tags.GetRelatedGuidesTag;

import static org.mockito.Mockito.*;

public class GetRelatedGuidesFixture extends GetRelatedItemsFixture {

	private Page currentPage;
	private final List<String> directRelations;

	public GetRelatedGuidesFixture(PageManager pageManager, Page currentPage)
			throws RepositoryException {
		super(pageManager);
		this.currentPage = currentPage;
		directRelations = new ArrayList<String>();
	}

	public void setUpNoDirectRelations() {
		//Page workingPage = currentPage;
		//String[] relations = workingPage.getProperties().get("pages", empty);
		ValueMap pageProperties = mock(ValueMap.class);
		when(currentPage.getProperties()).thenReturn(pageProperties);
		directRelations.clear();
		final String[] empty = new String[0];
		when(pageProperties.get(GetRelatedGuidesTag.REL_GUIDES_ATTR,empty)).thenAnswer(new Answer<String[]>() {
			public String[] answer(InvocationOnMock invocation)
					throws Throwable {
				return directRelations.toArray(empty);
			}
		});
	}

	public void setUpDirectRelations(int howMany) {
		setUpNoDirectRelations();
		directRelations.add(Constants.GUIDES_ROOT + "/unresolvable"); // just for fun
		for (int i = 0; i < howMany; i++) {
			String pPath = Constants.GUIDES_ROOT + "/direct"+i;
			directRelations.add(pPath);
			// and also set up a dup
			directRelations.add(pPath);
			Page p = mock(Page.class);
			when(pageManager.getPage(pPath)).thenReturn(p);
			when(p.getPath()).thenReturn(pPath);
		}
	}
	
	public void andSetUpBadRelations(int howMany) {
		for (int i = 0; i < howMany; i++) {
			String pPath = "/direct"+i;
			directRelations.add(pPath);
			Page p = mock(Page.class);
			when(pageManager.getPage(pPath)).thenReturn(p);
			when(p.getPath()).thenReturn(pPath);
		}
	}

}
