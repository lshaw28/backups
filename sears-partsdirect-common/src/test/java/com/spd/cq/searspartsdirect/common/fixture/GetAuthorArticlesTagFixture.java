package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class GetAuthorArticlesTagFixture {

	private PageImpressionsComparatorFixture testPages;
	private PageManager pageManager;
	private ResourceResolver resourceResolver;
	private Page currentPage;

	public GetAuthorArticlesTagFixture(Page currentPage, ResourceResolver resourceResolver,
			PageManager pageManager) {
		this.pageManager = pageManager;
		this.resourceResolver = resourceResolver;
		this.currentPage = currentPage;
		
		testPages = new PageImpressionsComparatorFixture(resourceResolver);

	}
	
	public void setUpComplete() throws RepositoryException {
		// TODO Auto-generated method stub
		List<Hit> hits = setUpEmptyHits();
		
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("abstracttext", "Lorem Ipsum");
		hits.add(createTestHitAndPage("/foo", "Article 1", props, 88));
		hits.add(createTestHitAndPage("/bar", "Article 2", props, 818));
		hits.add(createTestHitAndPage("/baz", "Article 3", props, 18081));
	}
	
	public void setUpExplodes() throws RepositoryException {
		// TODO Auto-generated method stub
		List<Hit> hits = setUpEmptyHits();
		
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("abstracttext", "Lorem Ipsum");
		hits.add(createExplodingHit("/foo", "Article 1", props, 88));
		hits.add(createExplodingHit("/bar", "Article 2", props, 818));
		hits.add(createExplodingHit("/baz", "Article 3", props, 18081));
	}
	
	List<Hit> setUpEmptyHits() {
		QueryBuilder ourFakeQueryBuilder = mock(QueryBuilder.class);
		Query ourFakeQuery = mock(Query.class);
		when(ourFakeQueryBuilder.createQuery(any(PredicateGroup.class), any(Session.class))).thenReturn(ourFakeQuery);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(ourFakeQueryBuilder);
		SearchResult result = mock(SearchResult.class);
		when(ourFakeQuery.getResult()).thenReturn(result);
		
//		currentPage = mock(Page.class);
//		when((Page) pageContext.findAttribute("currentPage")).thenReturn(currentPage);
		
		List<Hit> hits = new ArrayList<Hit>();
		when(result.getHits()).thenReturn(hits);
		return hits;
	}

	Hit createTestHitAndPage(String path, String title, Map<String, Object> properties, int viewCount)
			throws RepositoryException {

		Hit aHit = mock(Hit.class);
		when(aHit.getPath()).thenReturn(path);
		when(aHit.getTitle()).thenReturn(title);
		createTestPage(path, title, properties, viewCount);
		return aHit;
	}
	
	Hit createExplodingHit(String path, String title, Map<String, Object> properties, int viewCount)
			throws RepositoryException {

		Hit aHit = mock(Hit.class);
		when(aHit.getPath()).thenThrow(new RepositoryException());
		when(aHit.getTitle()).thenReturn(title);
		createTestPage(path, title, properties, viewCount);
		return aHit;
	}

	Page createTestPage(String path, String title, Map<String, Object> properties, int viewCount)
			throws RepositoryException {

		Page created = testPages.createTestPage(path, title, properties, viewCount);
		when(pageManager.getPage(path)).thenReturn(created);
		return created;
	}

	public void setCurrentPagePath() {
		when(currentPage.getPath()).thenReturn("/mypath");
	}

	public String getTestPagePath() {
		return "/page/path";
	}

	Page getTestPage(String path) {
		return testPages.getTestPage(path);
	}

}
