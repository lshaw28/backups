package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
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
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

public class GetCommonPartsTagFixture {
	
	private SlingHttpServletRequest slingRequest;
	private ResourceResolver resourceResolver;
	private PageManager pageManager;
	
	private List<Hit> hits;

	public GetCommonPartsTagFixture(SlingHttpServletRequest slingRequest,
			ResourceResolver resourceResolver, PageManager pageManager) {
		this.slingRequest = slingRequest;
		this.resourceResolver = resourceResolver;
		this.pageManager = pageManager;
	}
	
	public void setUpComplete() throws RepositoryException {
		createQueryNoHitsYet();

		addTestHit("/foo");
		addTestHit("/bar");
	}
	
	private void addTestHit(String pathTitleDesc) throws RepositoryException {
		Hit testHit = mock(Hit.class);
		when(testHit.getPath()).thenReturn(pathTitleDesc);
		hits.add(testHit);
		
		ValueMap hitProps = mock(ValueMap.class);
		when(testHit.getProperties()).thenReturn(hitProps);
		Page testPage = mock(Page.class);
		when(pageManager.getPage(pathTitleDesc)).thenReturn(testPage);
		when(testPage.getTitle()).thenReturn(pathTitleDesc);
		when(testPage.getDescription()).thenReturn(pathTitleDesc);
		when(testPage.getProperties()).thenReturn(hitProps);
		when(hitProps.get(Constants.ASSETS_GUIDES, String[].class)).thenReturn(getGuides(pathTitleDesc));
		
		when(hitProps.get("jcr:title", String.class)).thenReturn(pathTitleDesc);
		when(hitProps.get("jcr:description", String.class)).thenReturn(pathTitleDesc);
		
		Page guide = mock(Page.class);
		when(pageManager.getPage(getGuidePage(pathTitleDesc))).thenReturn(guide);
		when(guide.getTitle()).thenReturn(pathTitleDesc);
		when(guide.getDescription()).thenReturn(pathTitleDesc);
		when(guide.getPath()).thenReturn(pathTitleDesc);
	}
	
	public void removeHitProperties() throws RepositoryException {
		for (Hit hit : hits) {
			when(hit.getProperties()).thenReturn(null);
		}
	}
	
	public void breakHitProperties() throws RepositoryException {
		for (Hit hit : hits) {
			when(hit.getProperties()).thenThrow(new RepositoryException());
		}
	}
	
	public void removeHitPageGuidesProperties() throws RepositoryException {
		for (Hit hit : hits) {
			Page hitPage = pageManager.getPage(hit.getPath());
			ValueMap pageProps = hitPage.getProperties();
			when(pageProps.get(Constants.ASSETS_GUIDES, String[].class)).thenReturn(null);
		}
	}
	
	public void removeHitGuidesGuidePages() throws RepositoryException {
		for (Hit hit : hits) {
			when(pageManager.getPage(getGuidePage(hit.getPath()))).thenReturn(null);
		}
	}
	
	private void createQueryNoHitsYet() {
		when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		Session session = mock(Session.class);
		when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
		QueryBuilder builder = mock(QueryBuilder.class);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(builder);
		Query query = mock(Query.class);
		when(builder.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(query);
		SearchResult result = mock(SearchResult.class);
		when(query.getResult()).thenReturn(result);
		hits = new ArrayList<Hit>();
		when(result.getHits()).thenReturn(hits);
	}
	
	private String getGuidePage(String pathAndTitle) {
		return Constants.GUIDES_ROOT+ pathAndTitle;
	}
	
	private String[] getGuides(String pathTitleDesc) {
		return new String[] {getGuidePage(pathTitleDesc)};
	}
	
	public ProductCategoryModel getProductCategoryModel() {
		ProductCategoryModel productCategoryModel = new ProductCategoryModel("someTrueName","somePath",null,null,null,null,null, null);
		return productCategoryModel;
	}

}
