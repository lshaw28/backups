package com.spd.cq.searspartsdirect.common.helpers;


import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PathStringUtilsTest extends TestCase {

	private PathStringUtils psu;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		psu = new PathStringUtils();
	}

	@Test
	public void testHasAnyParentIn() {
		Set<String> roots = new HashSet<String>();
		assertThat(psu.hasAnyParentIn(roots,"/foo/dog"),is(false));
		roots.add("/foo");
		roots.add("/bar/none");
		roots.add("/baz");
		assertThat(psu.hasAnyParentIn(roots, "baz"),is(false));
		assertThat(psu.hasAnyParentIn(roots,"/foo/dog"),is(true));
		assertThat(psu.hasAnyParentIn(roots,"/quux/foo/dog"),is(false));
		assertThat(psu.hasAnyParentIn(roots,"/bar/none/the/best/barbecuesauce/youevertasted.html"),is(true));
	}

	@Test
	public void testPathsSetFromCsv() {
		Set<String> paths = psu.pathsSetFromCsv("");
		assertThat(paths,is(notNullValue()));
		assertThat(paths,is(instanceOf(Set.class)));
		assertThat(paths,hasSize(0));
		paths = psu.pathsSetFromCsv("/foo, /bar; /baz ");
		assertThat(paths,is(notNullValue()));
		assertThat(paths,hasSize(3));
		assertThat(paths,hasItem("/foo"));
		assertThat(paths,not(hasItem("/bar;")));
		assertThat(paths,hasItem("/bar"));
		assertThat(paths,not(hasItem(" /baz ")));
		assertThat(paths,hasItem("/baz"));
	}
}
