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
	
	private Page currentPage;

	public GetRelatedItemsFixture(PageManager pageManager) throws RepositoryException {
		this.pageManager = pageManager;	
	}
	
	public abstract static class HitGetPathBehavior {
		public abstract void addPathBehavior(Hit hit, String path) throws RepositoryException;
	}
	
	public static class ReturnsPath extends HitGetPathBehavior {
		public void addPathBehavior(Hit hit, String path) throws RepositoryException {
			when(hit.getPath()).thenReturn(path);
		}
	}
	
	public static class Explodes extends HitGetPathBehavior {
		public void addPathBehavior(Hit hit, String path) throws RepositoryException {
			when(hit.getPath()).thenThrow(new RepositoryException());
		}
	}
	
	public void setCurrentPage(Page currentPage) {
		this.currentPage = currentPage;
		//when(currentPage.equals(currentPage)).thenReturn(true); // Mockito disallows this.
	}
	
	public void makeNHits(int hitTotal, ResourceResolver rr) throws RepositoryException {
		makeNHits(hitTotal, rr, new ReturnsPath(), 0);
	}
	
	public void makeNHits(int hitTotal, ResourceResolver rr, int nDuplicates) throws RepositoryException {
		makeNHits(hitTotal, rr, new ReturnsPath(), nDuplicates);
	}
	
	public void makeNHits(int hitTotal, ResourceResolver rr, HitGetPathBehavior hitGetPathBehavior) throws RepositoryException {
		makeNHits(hitTotal, rr, hitGetPathBehavior, 0);
	}
	
	public void makeNHits(int hitTotal, ResourceResolver rr, HitGetPathBehavior hitGetPathBehavior, int nDuplicates) throws RepositoryException{
		
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
			
			hitGetPathBehavior.addPathBehavior(hit,hitPath);
			
			when(page.getPath()).thenReturn(hitPath);
			ValueMap properties = mock(ValueMap.class);
			if (i % 2 == 0) {
				when(properties.containsKey("abstracttext")).thenReturn(true);
				when(properties.get("abstracttext")).thenReturn("abstract "+i);
			}
			when(page.getProperties()).thenReturn(properties);
			when(pageManager.getPage(hitPath)).thenReturn(page);
			
			hits.add(hit);
			for (int j = 0; j < nDuplicates; j++) {
				hits.add(hit);
			}
		}
		if (currentPage != null) {
			Hit current = mock(Hit.class);
			when(current.getPath()).thenReturn("__currentPage");
			when(pageManager.getPage("__currentPage")).thenReturn(currentPage);
			hits.add(current);
			Hit currant = mock(Hit.class);
			when(currant.getPath()).thenReturn("__currantPage");
			hits.add(currant);
		}
		when(sr.getHits()).thenReturn(hits);
	}
	
}
