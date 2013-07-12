package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetCategory101PagesTagFixture {

	private Tag category101Tag;
	private QueryBuilder qb;
	private List<Hit> hits;
	private PageManager pageManager;
	private ResourceResolver resourceResolver;
	
	public GetCategory101PagesTagFixture(ResourceResolver resourceResolver, PageManager pageManager) {
		this.pageManager = pageManager;
		this.resourceResolver = resourceResolver;
		TagManager tm = mock(TagManager.class);
		when(resourceResolver.adaptTo(TagManager.class)).thenReturn(tm);
		category101Tag = mock(Tag.class);
		when(tm.resolve(getCategory101TagId())).thenReturn(category101Tag);
		qb = mock(QueryBuilder.class);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(qb);
	}

	public String getCategory() {
		return "Category";
	}

	public void setUpEmptyResults() {
		hits = new ArrayList<Hit>();
		Query query = mock(Query.class);
		when(qb.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(query);
		SearchResult result = mock(SearchResult.class);
		when(query.getResult()).thenReturn(result);
		when(result.getHits()).thenReturn(hits);
	}

	public Page setUpOneResult(String path) throws RepositoryException {
		setUpEmptyResults();
		Page page = makeOneResult(path);
		Tag[] pageTags = new Tag[]{category101Tag};
		when(page.getTags()).thenReturn(pageTags);
		return page;
	}
	
	public void setUpOneResultNoTags(String path) throws RepositoryException {
		setUpEmptyResults();
		Page page = makeOneResult(path);
		Tag[] pageTags = new Tag[]{};
		when(page.getTags()).thenReturn(pageTags);
	}
	
	public Node setUpOneResultWithAbstractAndEmptyImage(String path) throws RepositoryException {
		Page page = setUpOneResult(path);
		ValueMap props = page.getProperties();
		//if(page.getProperties().containsKey("abstracttext")){
    	//description = page.getProperties().get("abstracttext").toString();
		when(props.containsKey("abstracttext")).thenReturn(true);
		when(props.get("abstracttext")).thenReturn(path+path+path);
		String imagePath = page.getPath() + Constants.ASSETS_IMAGE_PATH;
		//Resource imageResource = resourceResolver.getResource(imagePath);
		Resource imageResource = mock(Resource.class);
		when(resourceResolver.getResource(imagePath)).thenReturn(imageResource);
		Node imageNode = mock(Node.class);
		when(imageResource.adaptTo(Node.class)).thenReturn(imageNode);
		return imageNode;
	}
	
	public void setUpOneResultWithFileImage(String path) throws RepositoryException {
		Node imageNode = setUpOneResultWithAbstractAndEmptyImage(path);
		when(imageNode.hasNode("file")).thenReturn(true);
	}
	
	public void setUpOneResultWithFileRefImage(String path) throws RepositoryException {
		Node imageNode = setUpOneResultWithAbstractAndEmptyImage(path);
		when(imageNode.hasProperty("fileReference")).thenReturn(true);
	}
	
	private Page makeOneResult(String path) throws RepositoryException {
		Hit hit = mock(Hit.class);
		hits.add(hit);
		when(hit.getPath()).thenReturn(path);
		Page page = mock(Page.class);
		when(pageManager.getPage(path)).thenReturn(page);
		ValueMap props = mock(ValueMap.class);
		when(page.getProperties()).thenReturn(props);
		return page;
	}
	
	public String getCategory101TagId() {
		return Constants.TAGS_FEATURES_PATH + "/category_101";
	}

}
