package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetJobCodesBySymptomTagFixture {
	
	private SlingHttpServletRequest slingRequest;
	private ResourceResolver resourceResolver;
	private PageManager pageManager;
	
	public GetJobCodesBySymptomTagFixture(SlingHttpServletRequest slingRequest,
			ResourceResolver resourceResolver, PageManager pageManager) {
		this.slingRequest = slingRequest;
		this.resourceResolver = resourceResolver;
		this.pageManager = pageManager;
	}
	
	public void setupFixture() throws RepositoryException{
		when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		Session session = mock(Session.class);
		when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
		QueryBuilder builder = mock(QueryBuilder.class);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(builder);
		Query query = mock(Query.class);
		when(builder.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(query);
		SearchResult result = mock(SearchResult.class);
		when(query.getResult()).thenReturn(result);
		List<Hit> hits = new ArrayList<Hit>();
		when(result.getHits()).thenReturn(hits);
		hits.add(createATestHit("/foo"));
		hits.add(createATestHit("/bar"));
		hits.add(createATestHit("/baz"));
		hits.add(createATestHit("/foo"));
		
	}
	
	private Hit createATestHit(String titleAndDesc) throws RepositoryException {
		Hit testHit = mock(Hit.class);
		when(testHit.getPath()).thenReturn(titleAndDesc);
		ValueMap props = mock(ValueMap.class);
		when(testHit.getProperties()).thenReturn(props);
		populateTestProps(props,titleAndDesc);
		Page testPage = mock(Page.class);
		when(pageManager.getPage(titleAndDesc)).thenReturn(testPage);
		when(testPage.getTitle()).thenReturn(titleAndDesc);
		when(testPage.getDescription()).thenReturn(titleAndDesc);
		Page partTypePage = mock(Page.class);
		when(pageManager.getPage(getPartTypePage(titleAndDesc))).thenReturn(partTypePage);
		when(partTypePage.getTitle()).thenReturn(titleAndDesc);
		when(partTypePage.getDescription()).thenReturn(titleAndDesc);
		when(partTypePage.getPath()).thenReturn(titleAndDesc);
		return testHit;
	}
	
	private void populateTestProps(ValueMap props, String titleAndDesc) {
		when(props.get("jcr:title", String.class)).thenReturn(titleAndDesc);
		when(props.get("partType", String[].class)).thenReturn(new String[]{getPartTypePage(titleAndDesc)});
		when(props.get("guide", String[].class)).thenReturn(new String[]{getGuidePage(titleAndDesc)});
	}

	private String getPartTypePage(String pathAndTitle) {
		return Constants.ASSETS_PATH+"/partType"+pathAndTitle;
	}
	
	private String getGuidePage(String pathAndTitle) {
		return Constants.GUIDES_ROOT+"/"+pathAndTitle;
	}
}
