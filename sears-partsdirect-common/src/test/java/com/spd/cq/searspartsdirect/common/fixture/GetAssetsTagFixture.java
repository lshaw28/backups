package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

public class GetAssetsTagFixture {
	/*{	// snippets from GetAssetsTag, so that I can mock them more conveniently..
		Map props = new HashMap();
		ResourceResolver resourceResolver = mock(ResourceResolver.class);
		QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
		List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();
	}*/
	public GetAssetsTagFixture(ResourceResolver rr) {
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
			hits.add(mth(i));
		}
		when(sr.getHits()).thenReturn(hits);
	}
	
	Hit mth(int i) {
		Hit hit = mock(Hit.class);
		return hit;
	}
}
