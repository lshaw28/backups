package com.spd.cq.searspartsdirect.common.fixture;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class GetRelatedItemsFixture {
	
	protected PageManager pageManager;

	public GetRelatedItemsFixture(PageManager pageManager) throws RepositoryException {
		this.pageManager = pageManager;	
	}
	
	public void makeNHits(int hitTotal, ResourceResolver rr) throws RepositoryException{
		
		QueryBuilder qb = mock(QueryBuilder.class);
		when(rr.adaptTo(QueryBuilder.class)).thenReturn(qb);
		Session s = mock(Session.class);
		when(rr.adaptTo(Session.class)).thenReturn(s);
		Query q = mock(Query.class);
		when(qb.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(q);
		SearchResult sr = mock(SearchResult.class);
		when(q.getResult()).thenReturn(sr);
		List<Hit> hits = new ArrayList<Hit>();
		
		for (int i = 0; i < hitTotal; i++) {
			
			Hit hit = mock(Hit.class);
			Page page = mock(Page.class);
			String hitPath = "/hit"+i;
			when(hit.getPath()).thenReturn(hitPath);
			when(page.getPath()).thenReturn(hitPath);
			ValueMap properties = mock(ValueMap.class);
			if (i % 2 == 0) {
				when(properties.containsKey("abstracttext")).thenReturn(true);
				when(properties.get("abstracttext")).thenReturn("abstract "+i);
			}
			when(page.getProperties()).thenReturn(properties);
			when(pageManager.getPage(hitPath)).thenReturn(page);
			
			hits.add(hit);
		}
		when(sr.getHits()).thenReturn(hits);
	}
	
}
