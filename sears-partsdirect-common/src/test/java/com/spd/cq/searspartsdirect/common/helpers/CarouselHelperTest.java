package com.spd.cq.searspartsdirect.common.helpers;


import java.util.List;

import javax.jcr.RepositoryException;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.CarouselHelperFixture;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CarouselHelperTest extends TestCase {

	CarouselHelperFixture fixture;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new CarouselHelperFixture();
	}

	@Test
	public void testHasUselessNullaryConstructor() {
		CarouselHelper helper = new CarouselHelper();
		assertThat(helper,isA(CarouselHelper.class));
	}
	
	@Test
	public void testGetImageListDegenNode() {
		fixture.setUpEmptyNode();
		List<String> empty = CarouselHelper.getImageList(fixture.getNode());
		assertThat(empty,is(not(nullValue())));
	}
	
	@Test
	public void testGetImageListEmptyProp() throws RepositoryException {
		fixture.setUpPropIsEmpty();
		List<String> empty = CarouselHelper.getImageList(fixture.getNode());
		assertThat(empty,isA(List.class));
		assertThat(empty,hasSize(0));
	}
	
	@Test
	public void testGetImageListExplodingProp() throws RepositoryException {
		fixture.setUpPropExplodes();
		List<String> empty = CarouselHelper.getImageList(fixture.getNode());
		assertThat(empty,isA(List.class));
		assertThat(empty,hasSize(0));
	}
	
	@Test
	public void testGetImageListSingleProp() throws RepositoryException {
		fixture.setUpValidSingleProp("image");
		List<String> single = CarouselHelper.getImageList(fixture.getNode());
		assertThat(single,isA(List.class));
		assertThat(single,hasSize(1));
		assertThat(single.get(0),is("image"));
	}
	
	@Test
	public void testGetImageListMultiProp() throws RepositoryException {
		fixture.setUpValidMultiProp("one","two","three");
		List<String> single = CarouselHelper.getImageList(fixture.getNode());
		assertThat(single,isA(List.class));
		assertThat(single,hasSize(3));
		assertThat(single.get(0),is("one"));
		assertThat(single.get(1),is("two"));
		assertThat(single.get(2),is("three"));
	}
	
	@Test
	public void testGetImageListMultiPropWithExtraGarbage() throws RepositoryException {
		fixture.setUpValidMultiProp("one","two","three",""," ");
		List<String> single = CarouselHelper.getImageList(fixture.getNode());
		assertThat(single,isA(List.class));
		assertThat(single,hasSize(3));
		assertThat(single.get(0),is("one"));
		assertThat(single.get(1),is("two"));
		assertThat(single.get(2),is("three"));
	}

}
