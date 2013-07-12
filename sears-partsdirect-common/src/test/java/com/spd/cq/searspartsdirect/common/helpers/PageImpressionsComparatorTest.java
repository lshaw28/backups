package com.spd.cq.searspartsdirect.common.helpers;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.*; // We deliberately leave this out to force us to write the fixture correctly.
import static org.hamcrest.Matchers.not;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.fixture.PageImpressionsComparatorFixture;
import com.spd.cq.searspartsdirect.common.tags.MocksTag;

public class PageImpressionsComparatorTest extends MocksTag {

	private PageImpressionsComparatorFixture fixture;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new PageImpressionsComparatorFixture(resourceResolver);
	}

	@Test
	public void testEmptyList() {
		List<Page> pages = new ArrayList<Page>();
		Collections.sort(pages,new PageImpressionsComparator(resourceResolver));
		assertThat(pages,is(instanceOf(List.class)));
	}
	
	@Test
	public void testTheIncrediblyComplexMock() {
		try {
			Page newPage = fixture.createTestPage("/quux",69);
			assertThat(newPage,is(instanceOf(Page.class)));
			assertThat(newPage.getPath(),is("/quux"));
			assertThat(fixture.getTestPage("/quux"),is(newPage));
			assertThat(fixture.getTestPage("/quux").getPath(),is("/quux"));
			String statsPath = Constants.STATS_PAGE_PREFIX + newPage.getPath() + Constants.STATS_PAGE_SUFFIX;
			Resource statsResource = resourceResolver.getResource(statsPath);
			// We've shown that we can find our mock stats resource
			assertThat(statsResource,is(instanceOf(Resource.class)));
			Node statsNode = statsResource.adaptTo(Node.class);
			assertThat(statsNode,is(instanceOf(Node.class)));
			
			NodeIterator yearsIterator = statsNode.getNodes();
			assertThat(yearsIterator,is(instanceOf(NodeIterator.class)));
			// We've shown that we're able to get our mock iterator over stats years
			assertThat(yearsIterator.hasNext(),is(true));
			Node year1 = yearsIterator.nextNode();
			assertThat(year1,is(instanceOf(Node.class)));
			assertThat(yearsIterator.hasNext(),is(true));
			Node year2 = yearsIterator.nextNode();
			assertThat(year2,is(instanceOf(Node.class)));
			assertThat(yearsIterator.hasNext(),is(false));
			// We've shown that our mock iterator iterates correctly. Let's see if it resets
			yearsIterator = statsNode.getNodes();
			assertThat(yearsIterator,is(instanceOf(NodeIterator.class)));
			// We've shown that we're able to get our mock iterator over stats years, again
			assertThat(yearsIterator.hasNext(),is(true));
			year1 = yearsIterator.nextNode();
			assertThat(year1,is(instanceOf(Node.class)));
			assertThat(yearsIterator.hasNext(),is(true));
			year2 = yearsIterator.nextNode();
			assertThat(year2,is(instanceOf(Node.class)));
			assertThat(yearsIterator.hasNext(),is(false));
			// We've shown that a fresh mock iterator is returned each time getNodes is called on the stats node mock
			
			Property year1ViewsProp = year1.getProperty("views");
			assertThat(year1ViewsProp,is(instanceOf(Property.class)));
			Property year2ViewsProp = year2.getProperty("views");
			assertThat(year2ViewsProp,is(instanceOf(Property.class)));
			// We've shown that we can retrieve the mock property wrappers
			long year1Views = year1ViewsProp.getLong();
			long year2Views = year2ViewsProp.getLong();
			assertEquals(69,year1Views + year2Views);
			// We've shown that the sum of counts in mock years is equal to the expected amount.
			// BUT the test itself was still failing.
			// because I had mocked resolve() instead of getResource().
		} catch (RepositoryException re) {
			fail("Mock threw unexpected exception");
		}
	}
	
	@Test
	public void testSimpleComparison() {
		Page page1 = null;
		Page page2 = null;
		try {
			page1 = fixture.createTestPage("/up", 12);
			page2 = fixture.createTestPage("/dn", 21);
		} catch (RepositoryException re) {
			fail("Mock threw unexpected exception");
		}
		assertThat(page1,is(not(page2)));
		assertThat(fixture.getTestPage("/up"),is(page1));
		PageImpressionsComparator pic = new PageImpressionsComparator(resourceResolver);
		assertThat(pic.compare(page1, page2),is(not(0)));
		assertThat(pic.compare(page1, page2),is(-1));
		assertThat(pic.compare(page2, page1),is(1));
	}
	
	@Test
	public void testThreeShuffled() {
		List<Page> pages = new ArrayList<Page>();
		try {
			pages.add(fixture.createTestPage("/foo",20));
			pages.add(fixture.createTestPage("/bar",30));
			pages.add(fixture.createTestPage("/baz",50));
		} catch (RepositoryException re) {
			fail("Mock threw unexpected exception");
		}
		Collections.shuffle(pages);
		Collections.sort(pages,new PageImpressionsComparator(resourceResolver));
		assertThat(pages.get(0).getPath(),is("/foo"));
		assertThat(pages.get(1).getPath(),is("/bar"));
		assertThat(pages.get(2).getPath(),is("/baz"));
		assertThat(Collections.max(pages,new PageImpressionsComparator(resourceResolver)),is(fixture.getTestPage("/baz")));
	}
}
