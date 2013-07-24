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

public class GetSymptomDetailTagFixture {

	private SlingHttpServletRequest slingRequest;
	private ResourceResolver resourceResolver;
	private PageManager pageManager;

	private List<Hit> hits;

	public GetSymptomDetailTagFixture(SlingHttpServletRequest slingRequest, ResourceResolver resourceResolver, PageManager pageManager) {
		this.slingRequest = slingRequest;
		this.resourceResolver = resourceResolver;
		this.pageManager = pageManager;
	}
	
	public void setUpMinimus() {
		createQueryNoHitsYet();
	}
	
	public void setUpComplete() throws RepositoryException {
		createQueryNoHitsYet();

		addTestHit("/foo");
		addTestHit("/bar");
		addTestHit("/baz");
		addTestHit("/foo");
	}
	
	public void removeHitProps() throws RepositoryException {
		for (Hit hit : hits) {
			when(hit.getProperties()).thenReturn(null);
		}
	}
	
	public void makeHitPropsExplode() throws RepositoryException {
		for (Hit hit : hits) {
			when(hit.getProperties()).thenThrow(new RepositoryException());
		}
	}
	
	public void removeHitPagesProp() throws RepositoryException {
		for (Hit hit : hits) {
			ValueMap hitProps = hit.getProperties();
			when(hitProps.get("pages", String[].class)).thenReturn((String[])null);
		}
	}
	
	public void makeJobCodePagesInvalid() throws RepositoryException {
		for (Hit hit : hits) {
			ValueMap hitProps = hit.getProperties();
			when(hitProps.get("pages", String[].class)).thenReturn(new String[]{getGuidePage("")});
		}
	}
	
	public void makeJobCodePagesMissing() throws RepositoryException {
		for (Hit hit : hits) {
			when(pageManager.getPage(getJobCodePage(hit.getPath()))).thenReturn(null);
		}
	}
	
	public void makeJobCodePagesMissingProps() throws RepositoryException {
		for (Hit hit : hits) {
			Page jobCodePage = pageManager.getPage(getJobCodePage(hit.getPath()));
			when(jobCodePage.getProperties()).thenReturn(null);
		}
	}
	
	public void makeJobCodePagesPropsEmpty() throws RepositoryException {
		for (Hit hit : hits) {
			Page jobCodePage = pageManager.getPage(getJobCodePage(hit.getPath()));
			ValueMap emptyProps = mock(ValueMap.class);
			when(jobCodePage.getProperties()).thenReturn(emptyProps);
		}
	}
	
	public String getSymptomId() {
		return "testId";
	}
	
	public String getJobCodeId() {
		return "testId";
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
		
		when(hitProps.get("id", String.class)).thenReturn(getSymptomId());
		when(hitProps.get("pages", String[].class)).thenReturn(new String[]{getJobCodePage(pathTitleDesc)});
		when(hitProps.get("jcr:title", String.class)).thenReturn(pathTitleDesc);
		when(hitProps.get("jcr:description", String.class)).thenReturn(pathTitleDesc);
		
		Page partTypePage = mock(Page.class);
		when(pageManager.getPage(getPartTypePage(pathTitleDesc))).thenReturn(partTypePage);
		when(partTypePage.getTitle()).thenReturn(pathTitleDesc);
		when(partTypePage.getDescription()).thenReturn(pathTitleDesc);
		when(partTypePage.getPath()).thenReturn(pathTitleDesc);

		Page guide = mock(Page.class);
		when(pageManager.getPage(getGuidePage(pathTitleDesc))).thenReturn(guide);
		when(guide.getTitle()).thenReturn(pathTitleDesc);
		when(guide.getDescription()).thenReturn(pathTitleDesc);
		when(guide.getPath()).thenReturn(pathTitleDesc);
		
		Page jobCodePage = mock(Page.class);
		when(pageManager.getPage(getJobCodePage(pathTitleDesc))).thenReturn(jobCodePage);
		when(jobCodePage.getTitle()).thenReturn(pathTitleDesc);
		when(jobCodePage.getDescription()).thenReturn(pathTitleDesc);
		when(jobCodePage.getPath()).thenReturn(pathTitleDesc);
		
		ValueMap jobCodeProps = mock(ValueMap.class);
		when(jobCodePage.getProperties()).thenReturn(jobCodeProps);
		when(jobCodeProps.get("id", String.class)).thenReturn(getJobCodeId());
		when(jobCodeProps.get("partType")).thenReturn(getPartTypePage(pathTitleDesc));
		when(jobCodeProps.get("guides", String[].class)).thenReturn(new String[]{getGuidePage(pathTitleDesc)});
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

	private String getPartTypePage(String pathAndTitle) {
		return Constants.ASSETS_PATH+"/partType"+pathAndTitle;
	}

	private String getGuidePage(String pathAndTitle) {
		return Constants.GUIDES_ROOT+ pathAndTitle;
	}

	private String getJobCodePage(String pathAndTitle) {
		return Constants.ASSETS_PATH+"/jobCode"+pathAndTitle;
	}

}
