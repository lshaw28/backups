package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

public class ModelSearchServletFixture {

	private SlingHttpServletRequest request;
	private SlingHttpServletResponse response;
	private ResourceResolver resourceResolver;
	private Session session;
	
	private QueryBuilder bothBuilder;
	private Query categoryQuery;
	private List<Hit> categoryHits;
	private Query brandQuery;
	private List<Hit> brandHits;
	private Query queryToReturn;
	
	public ModelSearchServletFixture() {
		request = mock(SlingHttpServletRequest.class);
		resourceResolver = mock(ResourceResolver.class);
		when(request.getResourceResolver()).thenReturn(resourceResolver);
		
		session = mock(Session.class);
		when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
		
		when(request.getParameter("category")).thenReturn(getCategoryTitle());
		when(request.getParameter("brand")).thenReturn(getBrandTitle());
		when(request.getParameter("model")).thenReturn(getModel());
		when(request.getParameter("link")).thenReturn(getLink());
		response = mock(SlingHttpServletResponse.class);
		
		bothBuilder = mock(QueryBuilder.class);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(bothBuilder);
		
		categoryQuery = mock(Query.class);
		SearchResult categoryResult = mock(SearchResult.class);
		when(categoryQuery.getResult()).thenReturn(categoryResult);
		categoryHits = new ArrayList<Hit>();
		when(categoryResult.getHits()).thenReturn(categoryHits);
		
		brandQuery = mock(Query.class);
		SearchResult brandResult = mock(SearchResult.class);
		when(brandQuery.getResult()).thenReturn(brandResult);
		brandHits = new ArrayList<Hit>();
		when(brandResult.getHits()).thenReturn(brandHits);
		
		queryToReturn = categoryQuery;
		when(bothBuilder.createQuery(any(PredicateGroup.class),any(Session.class))).thenAnswer(new Answer<Query>() {
			// Mockito doesn't recognize these PredicateGroups as matching, even when created with same args. So, instead just alternating mock queries returned.
			public Query answer(InvocationOnMock invocation) throws Throwable {
				if (queryToReturn == categoryQuery) {
					queryToReturn = brandQuery;
					return categoryQuery;
				} else {
					queryToReturn = categoryQuery;
					return brandQuery;
				}
			}
		});
	}
	
	public SlingHttpServletRequest getRequest() {
		return request;
	}

	public SlingHttpServletResponse getResponse() {
		return response;
	}
	
	public String getCategoryTitle() {
		return "Category";
	}
	
	public String getCategory() {
		return "category";
	}
	
	public String getBrandTitle() {
		return "Brand";
	}
	
	public String getBrand() {
		return "brand";
	}
	
	public String getModel() {
		return "M0D3L";
	}
	
	public String getLink() {
		return "http://link";
	}
	
	public String getExpectedRedirect() {
		return "/" + getBrand() + "/" + getCategory() + "/model-" + getModel() + "-repair.html";
	}

	public void setUpCategoryFound() throws RepositoryException {
		Hit catHit = mock(Hit.class);
		categoryHits.add(catHit);
		Node catNode = mock(Node.class);
		when(catHit.getNode()).thenReturn(catNode);
		when(catNode.getName()).thenReturn(getCategory());
	}
	
	public void setUpCategoryFoundButThrows() throws RepositoryException {
		Hit catHit = mock(Hit.class);
		categoryHits.add(catHit);
		when(catHit.getNode()).thenThrow(new RepositoryException());
	}

	public void setUpBrandFound() throws RepositoryException {
		Hit brandHit = mock(Hit.class);
		brandHits.add(brandHit);
		Node brandNode = mock(Node.class);
		when(brandHit.getNode()).thenReturn(brandNode);
		when(brandNode.getName()).thenReturn(getBrand());
	}

	public void setUpCategoryNotFound() {
		// noop - query setup creates empty hits
	}

	public void setUpBrandNotFound() {
		// noop - query setup creates empty hits
	}

}
