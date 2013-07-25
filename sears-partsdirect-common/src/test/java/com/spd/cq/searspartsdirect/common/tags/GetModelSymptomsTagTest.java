package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.api.resource.ValueMap;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;

public class GetModelSymptomsTagTest extends MocksTag {
	
	private GetModelSymptomsTag tag;
	private List<Hit> hits;

	@Test
	public void testWithArguments() throws Exception {
		tag = new GetModelSymptomsTag();
		//fixture = new GetModelSymptomsTagFixture(slingRequest, pageContext);
		
		createQueryWithNoHitsYet();
		
		hits.add(popProps(createTestHitProps(createATestHit("/foo"),true)));
		
		tag.setBrandName("Kenmore");
		tag.setCategoryName("Dishwasher");
		tag.setModelNumber("66513593K600");
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithArgumentsButHitUnresolvable() throws Exception {
		tag = new GetModelSymptomsTag();
		//fixture = new GetModelSymptomsTagFixture(slingRequest, pageContext);
		
		createQueryWithNoHitsYet();
		
		hits.add(popProps(createTestHitProps(createATestHit("/foo"),false)));
		
		tag.setBrandName("Kenmore");
		tag.setCategoryName("Dishwasher");
		tag.setModelNumber("66513593K600");
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithWrongArguments() throws Exception {
		tag = new GetModelSymptomsTag();
		//fixture = new GetModelSymptomsTagFixture(slingRequest, pageContext);
		
		createQueryWithNoHitsYet();
		
		hits.add(popProps(createTestHitProps(createATestHit("/foo"),true)));
		
		tag.setBrandName("Kenmoar");
		tag.setCategoryName("Dishcleaner");
		tag.setModelNumber("665135931024600");
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithArgumentsButNoProperties() throws Exception {
		tag = new GetModelSymptomsTag();
		//fixture = new GetModelSymptomsTagFixture(slingRequest, pageContext);
		
		createQueryWithNoHitsYet();
		
		hits.add(createATestHit("/foo"));
		
		tag.setBrandName("Kenmore");
		tag.setCategoryName("Dishwasher");
		tag.setModelNumber("66513593K600");
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithArgumentsButExplodes() throws Exception {
		tag = new GetModelSymptomsTag();
		//fixture = new GetModelSymptomsTagFixture(slingRequest, pageContext);
		
		createQueryWithNoHitsYet();
		
		Hit explodingHit = createATestHit("/foo");
		when(explodingHit.getPath()).thenThrow(new RepositoryException());
		hits.add(explodingHit);
		
		tag.setBrandName("Kenmore");
		tag.setCategoryName("Dishwasher");
		tag.setModelNumber("66513593K600");
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithMissingModel() throws Exception {
		tag = new GetModelSymptomsTag();
		//fixture = new GetModelSymptomsTagFixture(slingRequest, pageContext);
		
		createQueryWithNoHitsYet();
		
		hits.add(popProps(createTestHitProps(createATestHit("/foo"),true)));
		
		tag.setBrandName("Kenmore");
		tag.setCategoryName("Dishwasher");
		assertThat(tag.getModelNumber(),nullValue());
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithOnlyBrand() throws Exception {
		tag = new GetModelSymptomsTag();
		//fixture = new GetModelSymptomsTagFixture(slingRequest, pageContext);
		
		createQueryWithNoHitsYet();
		
		hits.add(popProps(createTestHitProps(createATestHit("/foo"),true)));
		
		tag.setBrandName("Kenmore");
		assertThat(tag.getCategoryName(),nullValue());
		assertThat(tag.getModelNumber(),nullValue());
		runTagShouldSkipBodyEvalPage();
	}
	
	@Test
	public void testWithNoArguments() throws Exception {
		tag = new GetModelSymptomsTag();
		//fixture = new GetModelSymptomsTagFixture(slingRequest, pageContext);
		
		createQueryWithNoHitsYet();
		
		hits.add(popProps(createTestHitProps(createATestHit("/foo"),true)));
		
		assertThat(tag.getBrandName(),nullValue());
		assertThat(tag.getCategoryName(),nullValue());
		assertThat(tag.getModelNumber(),nullValue());
		runTagShouldSkipBodyEvalPage();
	}
	
	private void createQueryWithNoHitsYet() {
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
	
	private Hit createTestHitProps(Hit testHit, boolean resolvable) throws RepositoryException {
		String titleAndDesc = testHit.getPath();
		ValueMap props = mock(ValueMap.class);
		when(testHit.getProperties()).thenReturn(props);
		Page testPage = mock(Page.class);
		if (resolvable) {
			when(pageManager.getPage(titleAndDesc)).thenReturn(testPage);
		}
		when(testPage.getTitle()).thenReturn(titleAndDesc);
		when(testPage.getDescription()).thenReturn(titleAndDesc);
		return testHit;
	}
	
	private void runTagShouldSkipBodyEvalPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
	
	private Hit popProps(Hit testHit) throws RepositoryException {
		String titleAndDesc = testHit.getPath();
		ValueMap props = testHit.getProperties();
		populateTestProps(props,titleAndDesc);
		return testHit;
	}
	
	private void populateTestProps(ValueMap props, String titleAndDesc) {
		when(props.get("jcr:title", String.class)).thenReturn(titleAndDesc);
		when(props.get("jcr:description", String.class)).thenReturn(titleAndDesc);
		when(props.get("id", String.class)).thenReturn(titleAndDesc);
	}
}
