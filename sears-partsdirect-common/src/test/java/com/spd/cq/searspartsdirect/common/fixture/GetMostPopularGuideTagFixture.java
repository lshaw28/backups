package com.spd.cq.searspartsdirect.common.fixture;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.mockito.Mockito.*;

public class GetMostPopularGuideTagFixture {

	PageImpressionsComparatorFixture testPages;
	PageManager pageManager;
	ResourceResolver resourceResolver;
	
	public void setUpResults(ResourceResolver resourceResolver, PageManager pageManager) throws RepositoryException {
		this.pageManager = pageManager;
		this.resourceResolver = resourceResolver;
		
		//QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
		QueryBuilder qb = mock(QueryBuilder.class);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(qb);
		//List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();
		Query query = mock(Query.class);
		when(qb.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(query);
		SearchResult result = mock(SearchResult.class);
		when(query.getResult()).thenReturn(result);
		List<Hit> hits = new ArrayList<Hit>();
		when(result.getHits()).thenReturn(hits);
		
		testPages = new PageImpressionsComparatorFixture(resourceResolver);
		hits.add(createTestHitAndPage("/foo",9));
		hits.add(createTestHitAndPage("/bar",999));
		hits.add(createTestHitAndPage("/baz",99));
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
		Resource createdResource = mock(Resource.class);
		when(resourceResolver.getResource(path)).thenReturn(createdResource);
		Node resourceNode = mock(Node.class);
		// Node resourceNode = resource.adaptTo(Node.class);
		when(createdResource.adaptTo(Node.class)).thenReturn(resourceNode);
		//String difficultyLevel = resourceNode.getNode(Constants.GUIDES_REL_PATH).getProperty("difficultyLevel").getString();
		Node guidesNode = mock(Node.class);
		when(resourceNode.getNode(Constants.GUIDES_REL_PATH)).thenReturn(guidesNode);
		Property difficulty = mock(Property.class);
		when(guidesNode.getProperty("difficultyLevel")).thenReturn(difficulty);
		when(difficulty.getString()).thenReturn("Harrowing");
		//String timeRequired = resourceNode.getNode(Constants.GUIDES_REL_PATH).getProperty("timeRequired").getString();
		Property time = mock(Property.class);
		when(guidesNode.getProperty("timeRequired")).thenReturn(time);
		when(time.getString()).thenReturn("Infinite");
		return created;
	}
}
