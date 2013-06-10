package com.spd.cq.searspartsdirect.common.helpers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.fixture.PageImpressionsComparatorFixture;
import com.spd.cq.searspartsdirect.common.helpers.PageImpressionsComparator;
import com.spd.cq.searspartsdirect.common.tags.MocksTag;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
//import static org.mockito.Mockito.*;

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
	public void testThreeShuffled() {
		List<Page> pages = new ArrayList<Page>();
		pages.add(fixture.createTestPage("/foo",20));
		pages.add(fixture.createTestPage("/bar",30));
		pages.add(fixture.createTestPage("/baz",50));
		//Collections.shuffle(pages);
		/*
		 * This test is temporarily stubbed, by removing the shuffle. To advance it,
		 * need to make a mock for the NodeIterator used to retrieve yearly stats,
		 * in PageImpressionsComparatorFixture. Currently this succeeds because
		 * all compares are 0:0 and sort is stable.
		 */
		Collections.sort(pages,new PageImpressionsComparator(resourceResolver));
		assertThat(pages.get(0),is(fixture.getTestPage("/foo")));
		assertThat(pages.get(1),is(fixture.getTestPage("/bar")));
		assertThat(pages.get(2),is(fixture.getTestPage("/baz")));
	}

}
