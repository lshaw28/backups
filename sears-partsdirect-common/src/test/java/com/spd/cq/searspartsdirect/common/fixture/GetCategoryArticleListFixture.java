package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class GetCategoryArticleListFixture {
	
	private PageImpressionsComparatorFixture testPages;
	private PageManager pageManager;
	
	public GetCategoryArticleListFixture(PageContext pageContext, ResourceResolver resourceResolver, PageManager pageManager) throws RepositoryException {
		QueryBuilder ourFakeQueryBuilder = mock(QueryBuilder.class);
		Query ourFakeQuery = mock(Query.class);
		when(ourFakeQueryBuilder.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(ourFakeQuery);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(ourFakeQueryBuilder);
		SearchResult result = mock(SearchResult.class);
		when(ourFakeQuery.getResult()).thenReturn(result);
		this.pageManager = pageManager;
		testPages = new PageImpressionsComparatorFixture(resourceResolver);
		List<Hit> hits = new ArrayList<Hit>();
		hits.add(createTestHitAndPage("/foo",88));
		hits.add(createTestHitAndPage("/bar",818));
		hits.add(createTestHitAndPage("/baz",18081));
		when(result.getHits()).thenReturn(hits);
	}
	
	Hit createTestHitAndPage(String path, int viewCount) throws RepositoryException {
		Hit aHit = mock(Hit.class);
		when(aHit.getPath()).thenReturn(path);
		createTestPage(path,viewCount);
		return aHit;
	}
	
	Page createTestPage(String path, int viewCount) throws RepositoryException {
		Page created = testPages.createTestPage(path, viewCount);
		when(pageManager.getPage(path)).thenReturn(created);
		return created;
	}
	
	Page getTestPage(String path) {
		return testPages.getTestPage(path);
	}
}
