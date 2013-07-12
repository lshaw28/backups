package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetCategoryArticleListFixture {
	
	private PageImpressionsComparatorFixture testPages;
	private PageManager pageManager;
	private ResourceResolver resourceResolver;
	
	public GetCategoryArticleListFixture(PageContext pageContext, ResourceResolver resourceResolver, PageManager pageManager) throws RepositoryException {
		this.pageManager = pageManager;
		this.resourceResolver = resourceResolver;
		
		QueryBuilder ourFakeQueryBuilder = mock(QueryBuilder.class);
		Query ourFakeQuery = mock(Query.class);
		when(ourFakeQueryBuilder.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(ourFakeQuery);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(ourFakeQueryBuilder);
		SearchResult result = mock(SearchResult.class);
		when(ourFakeQuery.getResult()).thenReturn(result);
		
		testPages = new PageImpressionsComparatorFixture(resourceResolver);
		List<Hit> hits = new ArrayList<Hit>();
		hits.add(createTestHitAndPage("/foo",88));
		hits.add(createTestHitAndPage("/bar",818));
		hits.add(createTestHitAndPage("/quux",181));
		hits.add(createTestHitAndPage("/baz",18081));
		Hit hitCurrent = mock(Hit.class);
		when(hitCurrent.getPath()).thenReturn("/");
		Page currentPage = (Page)pageContext.findAttribute("currentPage");
		when(pageManager.getPage("/")).thenReturn(currentPage);
		hits.add(hitCurrent);
		when(result.getHits()).thenReturn(hits);
	}
	
	Hit createTestHitAndPage(String path, int viewCount) throws RepositoryException {
		Hit aHit = mock(Hit.class);
		when(aHit.getPath()).thenReturn(path);
		createTestPage(path,viewCount);
		return aHit;
	}
	
	Page createTestPage(String path, int viewCount) throws RepositoryException {
		Page created = testPages.createTestPage(path, viewCount);
		when(pageManager.getPage(path)).thenReturn(created);
		//page.getProperties().get("abstracttext").toString()
		ValueMap pageProperties = mock(ValueMap.class);
		when(created.getProperties()).thenReturn(pageProperties);
		when(pageProperties.get("abstracttext",Constants.EMPTY)).thenReturn("description");
		if (viewCount < 10000) {
			String imagePath = path + Constants.ASSETS_IMAGE_PATH;
			//Resource imageResource = resourceResolver.getResource(imagePath);
			Resource imageResource = mock(Resource.class);
			when(resourceResolver.getResource(imagePath)).thenReturn(imageResource);
			Node imageNode = mock(Node.class);
			when(imageResource.adaptTo(Node.class)).thenReturn(imageNode);
			if (viewCount < 200) {
				when(imageNode.hasProperty("fileReference")).thenReturn(true);
			} else if (viewCount > 1000) {
				when(imageNode.hasNode("file")).thenReturn(true);
			}
		}
		Tag hasFour = mock(Tag.class);
		when(hasFour.getTagID()).thenReturn(Constants.SUBCATEGORY_TAG+"/hasFour");
		Tag[] tagArr = new Tag[]{hasFour};
		when(created.getTags()).thenReturn(tagArr);
		return created;
	}
	
	Page getTestPage(String path) {
		return testPages.getTestPage(path);
	}

	public void setUpToThrow() {
		when(pageManager.getPage(anyString())).thenThrow(new RuntimeException("Computer Over."));
	}
}
