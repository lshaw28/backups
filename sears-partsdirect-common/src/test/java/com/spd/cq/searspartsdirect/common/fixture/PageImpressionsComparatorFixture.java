package com.spd.cq.searspartsdirect.common.fixture;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.mockito.Mockito.*;

public class PageImpressionsComparatorFixture {

	private ResourceResolver resourceResolver;
	private Map<String,Page> testPages = new HashMap<String,Page>();
	private Random random = new Random();
	
	public PageImpressionsComparatorFixture(ResourceResolver resourceResolver) {
		random.setSeed(System.currentTimeMillis());
		this.resourceResolver = resourceResolver;
	}

	public Page createTestPage(String path, int allTimeCount) {
		// TODO Auto-generated method stub
		Page testPage = mock(Page.class);
		when(testPage.getPath()).thenReturn(path);
		String statsPath = Constants.STATS_PAGE_PREFIX + testPage.getPath() + Constants.STATS_PAGE_SUFFIX;
		Resource statsResource = mock(Resource.class);
		when(resourceResolver.resolve(statsPath)).thenReturn(statsResource);
		Node statsNode = mock(Node.class);
		when(statsResource.adaptTo(Node.class)).thenReturn(statsNode);
		
		int split = random.nextInt(allTimeCount);
		try {
			Node year1Node = mock(Node.class);
			Property year1Prop = mock(Property.class);
			when(year1Prop.getLong()).thenReturn(0L+split);
			when(year1Node.getProperty("views")).thenReturn(year1Prop);
			Node year2Node = mock(Node.class);
			Property year2Prop = mock(Property.class);
			when(year2Prop.getLong()).thenReturn(0L+(allTimeCount - split));
			when(year2Node.getProperty("views")).thenReturn(year2Prop);
		} catch (RepositoryException re) {
			
		}
		
		testPages.put(path, testPage);
		return testPage;
	}
	
	public Page getTestPage(String path) {
		return testPages.get(path);
	}
	
}
