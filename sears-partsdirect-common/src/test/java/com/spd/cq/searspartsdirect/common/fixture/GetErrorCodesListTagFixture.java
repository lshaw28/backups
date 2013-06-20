package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class GetErrorCodesListTagFixture {

	private SlingHttpServletRequest slingRequest;
	private ResourceResolver resourceResolver;
	private PageManager pageManager;

	public GetErrorCodesListTagFixture(SlingHttpServletRequest slingRequest,
			ResourceResolver resourceResolver, PageManager pageManager) {
		this.slingRequest = slingRequest;
		this.resourceResolver = resourceResolver;
		this.pageManager = pageManager;
	}

	public void setUpWithCategory() throws RepositoryException {
		when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		Session session = mock(Session.class);
		when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
		QueryBuilder builder = mock(QueryBuilder.class);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(builder);
		Query query = mock(Query.class);
		when(builder.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(query);
		//SearchResult result = query.getResult();
		SearchResult result = mock(SearchResult.class);
		when(query.getResult()).thenReturn(result);
		//log.debug("total results found "+ result.getQueryStatement().toString());
		List<Hit> hits = new ArrayList<Hit>();
		when(result.getHits()).thenReturn(hits);
	    //for (Hit hit : result.getHits()) {
		hits.add(createATestHit("/foo"));
		hits.add(createATestHit("/bar"));
		hits.add(createATestHit("/baz"));
		hits.add(createATestHit("/foo"));
		hits.add(createAPropertylessTestHit("/quux"));
		hits.add(createExplodingTestHit("/quux"));
	}
	
	private Hit createATestHit(String pathAndTitle) throws RepositoryException {
		Hit testHit = mock(Hit.class);
		when(testHit.getPath()).thenReturn(pathAndTitle);
		ValueMap props = mock(ValueMap.class);
		when(testHit.getProperties()).thenReturn(props);
		populateTestProps(props,pathAndTitle);
		Page testPage = mock(Page.class);
		when(pageManager.getPage(pathAndTitle)).thenReturn(testPage);
		//ErrorCodeListModel model = new ErrorCodeListModel(props.get("jcr:title", String.class), p.getPath());
		when(testPage.getPath()).thenReturn(pathAndTitle);
		//String[] pages = (String[]) props.get("pages", String[].class);
		Page brandPage = mock(Page.class);
		when(pageManager.getPage(getBrandPagePath(pathAndTitle))).thenReturn(brandPage);
		when(brandPage.getTitle()).thenReturn(pathAndTitle);
		return testHit;
	}
	
	private Hit createAPropertylessTestHit(String pathAndTitle) throws RepositoryException {
		Hit testHit = createATestHit(pathAndTitle);
		when(testHit.getProperties()).thenReturn(null);
		return testHit;
	}
	
	private Hit createExplodingTestHit(String pathAndTitle) throws RepositoryException {
		Hit testHit = createATestHit(pathAndTitle);
		when(testHit.getProperties()).thenThrow(new RepositoryException());
		return testHit;
	}

	private void populateTestProps(ValueMap props, String pathAndTitle) {
		when(props.get("jcr:title", String.class)).thenReturn(pathAndTitle);
		when(props.get("pages", String[].class)).thenReturn(new String[]{getBrandPagePath(pathAndTitle)});
	}

	private String getBrandPagePath(String pathAndTitle) {
		return Constants.ASSETS_PATH+"/brand"+pathAndTitle;
	}
	
	public String getCategoryPath() {
		return "/dummyCategory";
	}
}
