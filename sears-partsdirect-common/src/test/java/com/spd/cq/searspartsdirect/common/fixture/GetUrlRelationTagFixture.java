package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetUrlRelationTagFixture {

	private final List<String> selectors;
	
	private final String BRAND = "brand";
	private final String CATEGORY = "category";
	private final String MODEL = "model";
	private final String SYMPTOM = "symptom";

	private PageManager pageManager;
	private SlingHttpServletRequest slingRequest;
	private ResourceResolver resourceResolver;
	
	public GetUrlRelationTagFixture(SlingHttpServletRequest slingRequest, PageManager pageManager, ResourceResolver resourceResolver) {
		this.pageManager = pageManager;
		this.slingRequest = slingRequest;
		this.resourceResolver = resourceResolver;
		selectors = new ArrayList<String>();
		RequestPathInfo rpi = mock(RequestPathInfo.class);
		when(slingRequest.getRequestPathInfo()).thenReturn(rpi);
		when(rpi.getSelectors()).thenAnswer(new Answer<String[]>() {
			public String[] answer(InvocationOnMock invocation)
					throws Throwable {
				return selectors.toArray(new String[0]);
			}
		});
	}

	public void setUpBCMSelectors() {
		selectors.clear();
		selectors.add(BRAND);
		selectors.add(CATEGORY);
		selectors.add(MODEL);
	}
	
	public void setUpLongBCMSelectors() {
		selectors.clear();
		selectors.add(BRAND+BRAND+BRAND+BRAND+BRAND);
		selectors.add(CATEGORY+CATEGORY+CATEGORY);
		selectors.add(MODEL+MODEL+MODEL+MODEL+MODEL);
	}
	
	public void setUpSSelector() {
		selectors.clear();
		selectors.add(SYMPTOM);
	}
	
	public void setUpBCMSSelectors() {
		selectors.clear();
		selectors.add(BRAND);
		selectors.add(CATEGORY);
		selectors.add(MODEL);
		selectors.add(SYMPTOM);
	}
	
	public void setUpBCSelectors() {
		selectors.clear();
		selectors.add(BRAND);
		selectors.add(CATEGORY);
	}
	
	public void setUpProductCategory() {
		String relatedAssetPath = Constants.ASSETS_PATH + "/productCategory/" + CATEGORY;
		Page p = mock(Page.class);
		when(pageManager.getPage(relatedAssetPath)).thenReturn(p);
		ValueMap properties = mock(ValueMap.class);
		when(p.getProperties()).thenReturn(properties);
	}
	
	public void setUpProductCategoryUri() {
		when(slingRequest.getRequestURI()).thenReturn(CATEGORY+"-repair/");
	}
	
	public void setUpJRandomUri() {
		when(slingRequest.getRequestURI()).thenReturn(CATEGORY+"-aupair/");
	}

	public void setUpBrand() {
		String relatedAssetPath = Constants.ASSETS_PATH + "/brand/" + BRAND;
		Page p = mock(Page.class);
		when(pageManager.getPage(relatedAssetPath)).thenReturn(p);
		ValueMap properties = mock(ValueMap.class);
		when(p.getProperties()).thenReturn(properties);
	}
	
	public void setUpLongBrand() {
		String relatedAssetPath = Constants.ASSETS_PATH + "/brand/" + (BRAND+BRAND+BRAND+BRAND+BRAND);
		Page p = mock(Page.class);
		when(pageManager.getPage(relatedAssetPath)).thenReturn(p);
		ValueMap properties = mock(ValueMap.class);
		when(p.getProperties()).thenReturn(properties);
	}
	
	public void setUpSymptom() throws RepositoryException {
		String relatedAssetPath = Constants.ASSETS_PATH + "/symptom/" + SYMPTOM;
		Hit symptomFound = createSymptomHit(relatedAssetPath);
		when(symptomFound.getPath()).thenReturn(relatedAssetPath);
	}
	
	public void setUpExplodingSymptom() throws RepositoryException {
		String relatedAssetPath = Constants.ASSETS_PATH + "/symptom/" + SYMPTOM;
		Hit symptomFound = createSymptomHit(relatedAssetPath);
		when(symptomFound.getPath()).thenThrow(new RepositoryException());
	}

	public Object getModel() {
		return MODEL;
	}

	private Hit createSymptomHit(String relatedAssetPath) throws RepositoryException {
        QueryBuilder qb = mock(QueryBuilder.class);
        when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(qb);
        Query query = mock(Query.class);
        when(qb.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(query);
        SearchResult result = mock(SearchResult.class);
        when(query.getResult()).thenReturn(result);
        List<Hit> hits = new ArrayList<Hit>();
        when(result.getHits()).thenReturn(hits);
        Hit symptomFound = mock(Hit.class);
        hits.add(symptomFound);
		
		Page p = mock(Page.class);
		when(pageManager.getPage(relatedAssetPath)).thenReturn(p);
		ValueMap properties = mock(ValueMap.class);
		when(p.getProperties()).thenReturn(properties);
		
		return symptomFound;
	}

}
