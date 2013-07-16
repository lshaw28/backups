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
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetAssetsTagFixture {
	
	private PageManager pageManager;
	private ResourceResolver rr;
	
	public GetAssetsTagFixture(ResourceResolver rr, PageManager pageManager) {
		this.pageManager = pageManager;
		this.rr = rr;
	}
	
	public void setUpTestHits() throws RepositoryException {
		List<Hit> hits = makeEmptyHits();
		for (int i = 0; i < 3; i++) {
			hits.add(makeTestHitPage(i));
		}
	}
	
	public void setUpExplodingHits() throws RepositoryException {
		List<Hit> hits = makeEmptyHits();
		for (int i = 0; i < 3; i++) {
			hits.add(makeExplodingHitPage(i));
		}
	}
	
	List<Hit> makeEmptyHits() {
		QueryBuilder qb = mock(QueryBuilder.class);
		when(rr.adaptTo(QueryBuilder.class)).thenReturn(qb);
		Session s = mock(Session.class);
		when(rr.adaptTo(Session.class)).thenReturn(s);
		Query q = mock(Query.class);
		when(qb.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(q);
		SearchResult sr = mock(SearchResult.class);
		when(q.getResult()).thenReturn(sr);
		List<Hit> hits = new ArrayList<Hit>();
		when(sr.getHits()).thenReturn(hits);
		return hits;
	}
	
	Hit makeTestHitPage(int i) throws RepositoryException {
		String hitPath = "/hit"+i;
		Hit hit = makeProtoHit(hitPath);
		when(hit.getPath()).thenReturn(hitPath);
		return hit;
	}
	
	Hit makeExplodingHitPage(int i) throws RepositoryException {
		String hitPath = "/hit"+i;
		Hit hit = makeProtoHit(hitPath);
		when(hit.getPath()).thenThrow(new RepositoryException());
		return hit;
	}
	
	Hit makeProtoHit(String hitPath) {
		Hit hit = mock(Hit.class);
		Page page = mock(Page.class);
		when(page.getPath()).thenReturn(hitPath);
		ValueMap properties = mock(ValueMap.class);
		when(page.getProperties()).thenReturn(properties);
		when(properties.get(Constants.ASSETS_TITLE_PATH,"")).thenReturn(hitPath+" title");
		when(pageManager.getPage(hitPath)).thenReturn(page);
		return hit;
	}


}
