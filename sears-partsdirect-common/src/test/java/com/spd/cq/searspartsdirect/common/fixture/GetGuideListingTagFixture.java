package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetGuideListingTagFixture {
	private ResourceResolver resourceResolver;
	private PageImpressionsComparatorFixture testPages;
	private PageManager pageManager;
	private Page currentPage;
	
	
	public GetGuideListingTagFixture(PageContext pageContext, ResourceResolver resourceResolver, PageManager pageManager, Page currentPage) throws RepositoryException {
		this.resourceResolver = resourceResolver;
		this.pageManager = pageManager;
		this.currentPage = currentPage;
	}
	
	public void setUpComplete() throws RepositoryException {
		QueryBuilder ourFakeQueryBuilder = mock(QueryBuilder.class);
		Query ourFakeQuery = mock(Query.class);
		when(ourFakeQueryBuilder.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(ourFakeQuery);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(ourFakeQueryBuilder);
		SearchResult result = mock(SearchResult.class);
		when(ourFakeQuery.getResult()).thenReturn(result);
		
		testPages = new PageImpressionsComparatorFixture(resourceResolver);
		List<Hit> hits = new ArrayList<Hit>();
		hits.add(createTestHitAndPage("/foo",88));
		hits.add(createTestHitAndPage("/bar",818));
		hits.add(createTestHitAndPage("/baz",18081));
		hits.add(createTestHitAndPage("/quux",9999999));
		when(result.getHits()).thenReturn(hits);

		Page testPage = mock(Page.class);
		when(pageManager.getPage("/foo")).thenReturn(testPage);
		Tag testTagInType = mock(Tag.class);
		when(testTagInType.getTagID()).thenReturn("searspartsdirect:subcategories");
		
		when(pageManager.getPage("/quux")).thenReturn(currentPage);
		
		Tag[] tags = {testTagInType};
		when(testPage.getTags()).thenReturn(tags);
		ValueMap properties = mock(ValueMap.class);
		when(testPage.getProperties()).thenReturn(properties);
		when(properties.get("abstracttext",Constants.EMPTY)).thenReturn(Constants.EMPTY);
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
		ValueMap properties = created.getProperties();
		when(properties.get("abstracttext", Constants.EMPTY)).thenReturn(path+" description");
		return created;
	}
	
	public String getTestPagePath() {
		return "/page/path";
	}
	
	Page getTestPage(String path) {
		return testPages.getTestPage(path);
	}


}
