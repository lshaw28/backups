package com.spd.cq.searspartsdirect.common.fixture;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.ValueMap;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

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
		when(pageProperties.get("pages",empty)).thenAnswer(new Answer<String[]>() {
			public String[] answer(InvocationOnMock invocation)
					throws Throwable {
				return directRelations.toArray(empty);
			}
		});
	}

}
