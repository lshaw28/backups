package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class PageImpressionsComparatorFixture {

	private ResourceResolver resourceResolver;
	private Map<String,Page> testPages = new HashMap<String,Page>();
	private Random random = new Random();

	public PageImpressionsComparatorFixture(ResourceResolver resourceResolver) {
		random.setSeed(System.currentTimeMillis());
		this.resourceResolver = resourceResolver;
	}

	public Page createTestPage(String path, int allTimeCount) throws RepositoryException {
		return createTestPage(path, null, null, allTimeCount);
	}

	public Page createTestPage(String path, String title, Map<String, Object> properties, int allTimeCount) throws RepositoryException {
		/*
		 * We first create a Page mock, we will be returning this
		 */
		Page testPage = mock(Page.class);
		when(testPage.getPath()).thenReturn(path);
		when(testPage.getTitle()).thenReturn(title);
		when(testPage.getProperties()).thenReturn(new ValueMapDecorator(properties));

		int split = random.nextInt(allTimeCount);

		Node year1Node = mock(Node.class);
		Property year1Prop = mock(Property.class);
		when(year1Prop.getLong()).thenReturn(0L+split);
		when(year1Node.getProperty("views")).thenReturn(year1Prop);

		Node year2Node = mock(Node.class);
		Property year2Prop = mock(Property.class);
		when(year2Prop.getLong()).thenReturn(0L+(allTimeCount - split));
		when(year2Node.getProperty("views")).thenReturn(year2Prop);

		String statsPath = Constants.STATS_PAGE_PREFIX + testPage.getPath() + Constants.STATS_PAGE_SUFFIX;

		Node statsNode = mock(Node.class);
		Resource statsResource = mock(Resource.class);
		when(statsResource.adaptTo(Node.class)).thenReturn(statsNode);
		// Lost over an hour to having initially mocked resolve() rather than getResource().
		when(resourceResolver.getResource(statsPath)).thenReturn(statsResource);

		final List<Node> iterated = new ArrayList<Node>();
		iterated.add(year1Node);
		iterated.add(year2Node);
		when(statsNode.getNodes()).thenAnswer(new Answer<NodeIterator>() {
			public NodeIterator answer(InvocationOnMock invocation) throws Throwable {
				NodeIterator yearIterator = mock(NodeIterator.class);
				final Iterator<Node> insideIterator = iterated.iterator();
				when(yearIterator.hasNext()).thenAnswer(new Answer<Boolean>() {
				    //@Override
				    public Boolean answer(InvocationOnMock invocation) throws Throwable {
				    	return insideIterator.hasNext();
				    }
				});
				when(yearIterator.nextNode()).thenAnswer(new Answer<Node>() {
				    //@Override
				    public Node answer(InvocationOnMock invocation) throws Throwable {
				    	return insideIterator.next();
				    }
				});
				return yearIterator;
			}
		});

		testPages.put(path, testPage);
		return testPage;
	}

	public Page getTestPage(String path) {
		return testPages.get(path);
	}

	public Page createExplodingTestPage(String string, int i) throws RepositoryException {
		Page testPage = createTestPage(string,i);
		
		String statsPath = Constants.STATS_PAGE_PREFIX + testPage.getPath() + Constants.STATS_PAGE_SUFFIX;
		when(resourceResolver.getResource(statsPath)).thenThrow(new RuntimeException());
		//Resource allYearsResource = resourceResolver.getResource(statsPath);
		//when(allYearsResource.adaptTo(Node.class)).thenThrow(new RuntimeException());
		//when(allYearsNode.getNodes()).thenThrow(new RepositoryException());
		
		return testPage;
	}

}
