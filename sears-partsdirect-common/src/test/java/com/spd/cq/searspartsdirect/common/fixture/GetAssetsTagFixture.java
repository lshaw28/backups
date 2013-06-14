package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public GetAssetsTagFixture(ResourceResolver rr, PageManager pageManager) throws RepositoryException {
		this.pageManager = pageManager;
		QueryBuilder qb = mock(QueryBuilder.class);
		when(rr.adaptTo(QueryBuilder.class)).thenReturn(qb);
		Session s = mock(Session.class);
		when(rr.adaptTo(Session.class)).thenReturn(s);
		Query q = mock(Query.class);
		when(qb.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(q);
		SearchResult sr = mock(SearchResult.class);
		when(q.getResult()).thenReturn(sr);
		List<Hit> hits = new ArrayList<Hit>();
		for (int i = 0; i < 3; i++) {
			hits.add(makeTestHitPage(i));
		}
		when(sr.getHits()).thenReturn(hits);
	}
	
	Hit makeTestHitPage(int i) throws RepositoryException {
		Hit hit = mock(Hit.class);
		Page page = mock(Page.class);
		String hitPath = "/hit"+i;
		when(hit.getPath()).thenReturn(hitPath);
		when(page.getPath()).thenReturn(hitPath);
		ValueMap properties = mock(ValueMap.class);
		when(page.getProperties()).thenReturn(properties);
		when(properties.get(Constants.ASSETS_TITLE_PATH,"")).thenReturn(hitPath+" title");
		when(pageManager.getPage(hitPath)).thenReturn(page);
		return hit;
	}
}
