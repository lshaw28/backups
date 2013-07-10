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

public class GetSymptomDetailTagFixture {
	
	private SlingHttpServletRequest slingRequest;
	private ResourceResolver resourceResolver;
	private PageManager pageManager;
	
	private List<Hit> hits;
	
	public GetSymptomDetailTagFixture(SlingHttpServletRequest slingRequest,
			ResourceResolver resourceResolver, PageManager pageManager) {
		this.slingRequest = slingRequest;
		this.resourceResolver = resourceResolver;
		this.pageManager = pageManager;
	}
	
	public void setupFixtureComplete() throws RepositoryException {
		createQueryNoHitsYet();
		hits.add(popJobCodePage(populateTestHitPagesExceptJobCode(popProps(createTestHitProps(createATestHit("/foo")))),true));
		hits.add(popJobCodePage(populateTestHitPagesExceptJobCode(popProps(createTestHitProps(createATestHit("/bar")))),true));
		hits.add(popJobCodePage(populateTestHitPagesExceptJobCode(popProps(createTestHitProps(createATestHit("/baz")))),true));
		hits.add(popJobCodePage(populateTestHitPagesExceptJobCode(popProps(createTestHitProps(createATestHit("/foo")))),true));
	}
	
	public void setUpNoJobCodePageProps() throws RepositoryException {
		createQueryNoHitsYet();
		hits.add(popJobCodePage(popProps(createTestHitProps(createATestHit("/foo"))),false));
		hits.add(popJobCodePage(popProps(createTestHitProps(createATestHit("/bar"))),false));
		hits.add(popJobCodePage(popProps(createTestHitProps(createATestHit("/baz"))),false));
		hits.add(popJobCodePage(popProps(createTestHitProps(createATestHit("/foo"))),false));
	}
	
	public void setUpNoHitProps() throws RepositoryException {
		createQueryNoHitsYet();
		hits.add(createATestHit("/foo"));
		hits.add(createATestHit("/bar"));
		hits.add(createATestHit("/baz"));
		hits.add(createATestHit("/foo"));
	}
	
	public void setUpEmptyHitProps() throws RepositoryException {
		createQueryNoHitsYet();
		hits.add(createTestHitProps(createATestHit("/foo")));
		hits.add(createTestHitProps(createATestHit("/bar")));
		hits.add(createTestHitProps(createATestHit("/baz")));
		hits.add(createTestHitProps(createATestHit("/foo")));
	}
	
	public void setUpNoJobCodeProps() throws RepositoryException {
		createQueryNoHitsYet();
		hits.add(populateTestHitPropsNoJobCode(createTestHitProps(createATestHit("/foo"))));
		hits.add(populateTestHitPropsNoJobCode(createTestHitProps(createATestHit("/bar"))));
		hits.add(populateTestHitPropsNoJobCode(createTestHitProps(createATestHit("/baz"))));
		hits.add(populateTestHitPropsNoJobCode(createTestHitProps(createATestHit("/foo"))));
	}
	
	public void setUpNoJobCodePages() throws RepositoryException {
		createQueryNoHitsYet();
		hits.add(popProps(createTestHitProps(createATestHit("/foo"))));
		hits.add(popProps(createTestHitProps(createATestHit("/bar"))));
		hits.add(popProps(createTestHitProps(createATestHit("/baz"))));
		hits.add(popProps(createTestHitProps(createATestHit("/foo"))));
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
		when(result.getHits()).thenAnswer(new Answer<List<Hit>>() {
			public List<Hit> answer(InvocationOnMock invocation) {
				return hits;
			}
		});
	}
	
	private Hit createATestHit(String titleAndDesc) throws RepositoryException {
		Hit testHit = mock(Hit.class);
		when(testHit.getPath()).thenReturn(titleAndDesc);
		return testHit;
	}
	
	private Hit createTestHitProps(Hit testHit) throws RepositoryException {
		String titleAndDesc = testHit.getPath();
		ValueMap props = mock(ValueMap.class);
		when(testHit.getProperties()).thenReturn(props);
		Page testPage = mock(Page.class);
		when(pageManager.getPage(titleAndDesc)).thenReturn(testPage);
		when(testPage.getTitle()).thenReturn(titleAndDesc);
		when(testPage.getDescription()).thenReturn(titleAndDesc);
		return testHit;
	}
	
	private Hit popProps(Hit testHit) throws RepositoryException {
		String titleAndDesc = testHit.getPath();
		ValueMap props = testHit.getProperties();
		populateTestProps(props,titleAndDesc);
		return testHit;
	}
	
	private Hit populateTestHitPropsNoJobCode(Hit testHit) throws RepositoryException {
		String titleAndDesc = testHit.getPath();
		ValueMap props = testHit.getProperties();
		populateTestPropsNoJobCode(props,titleAndDesc);
		return testHit;
	}
	
	private Hit populateTestHitPagesExceptJobCode(Hit testHit) throws RepositoryException {
		String titleAndDesc = testHit.getPath();
		Page partTypePage = mock(Page.class);
		when(pageManager.getPage(getPartTypePage(titleAndDesc))).thenReturn(partTypePage);
		when(partTypePage.getTitle()).thenReturn(titleAndDesc);
		when(partTypePage.getDescription()).thenReturn(titleAndDesc);
		when(partTypePage.getPath()).thenReturn(titleAndDesc);
		
		Page guide = mock(Page.class);
		when(pageManager.getPage(getGuidePage(titleAndDesc))).thenReturn(guide);
		when(guide.getTitle()).thenReturn(titleAndDesc);
		when(guide.getDescription()).thenReturn(titleAndDesc);
		when(guide.getPath()).thenReturn(titleAndDesc);
		return testHit;
	}
	
	private Hit popJobCodePage(Hit testHit, boolean hasProps) throws RepositoryException {
		String titleAndDesc = testHit.getPath();
		ValueMap props = testHit.getProperties();
		Page jobCodePage = mock(Page.class);
		when(pageManager.getPage(getJobCodePage(titleAndDesc))).thenReturn(jobCodePage);
		when(jobCodePage.getTitle()).thenReturn(titleAndDesc);
		when(jobCodePage.getDescription()).thenReturn(titleAndDesc);
		when(jobCodePage.getPath()).thenReturn(titleAndDesc);
		if (hasProps) {
			when(jobCodePage.getProperties()).thenReturn(props);
		}
		when(props.get("partType")).thenReturn(getPartTypePage(titleAndDesc));
		when(props.get("guides")).thenReturn(getGuidePage(titleAndDesc));
		return testHit;
	}
	
	private void populateTestProps(ValueMap props, String titleAndDesc) {
		when(props.get("pages", String[].class)).thenReturn(new String[]{getJobCodePage(titleAndDesc)});
		when(props.get("jcr:title", String.class)).thenReturn(titleAndDesc);
		when(props.get("partType", String.class)).thenReturn(new String(getPartTypePage(titleAndDesc)));
		when(props.get("guides", String[].class)).thenReturn(new String[]{getGuidePage(titleAndDesc)});
	}
	
	private void populateTestPropsNoJobCode(ValueMap props, String titleAndDesc) {
		when(props.get("pages", String[].class)).thenReturn(new String[]{"beeeezer"});
		when(props.get("jcr:title", String.class)).thenReturn(titleAndDesc);
		when(props.get("partType", String.class)).thenReturn(new String(getPartTypePage(titleAndDesc)));
		when(props.get("guides", String[].class)).thenReturn(new String[]{getGuidePage(titleAndDesc)});
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
