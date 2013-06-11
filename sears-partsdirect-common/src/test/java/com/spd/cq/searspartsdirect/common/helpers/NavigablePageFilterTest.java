package com.spd.cq.searspartsdirect.common.helpers;


import org.junit.Before;
import org.junit.Test;

import com.day.cq.wcm.api.Page;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*; // mocking needed here is minimal, so we are forgoing the fixture

public class NavigablePageFilterTest extends TestCase {

	private NavigablePageFilter underTest;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		underTest = new NavigablePageFilter();
	}

	@Test
	public void testIncludesNavigablePage() {
		Page navigable = mock(Page.class);
		/*
		 * Added isValid, isValid iss looked at by PageFilter
		 */
		when(navigable.isValid()).thenReturn(true);
		when(navigable.isHideInNav()).thenReturn(false);
		assertThat(underTest.includes(navigable),is(true));
	}
	
	@Test
	public void testDoesNotIncludeNonNavigablePage() {
		Page nonNavigable = mock(Page.class);
		when(nonNavigable.isValid()).thenReturn(true);
		when(nonNavigable.isHideInNav()).thenReturn(true);
		assertThat(underTest.includes(nonNavigable),is(false));
	}

}
