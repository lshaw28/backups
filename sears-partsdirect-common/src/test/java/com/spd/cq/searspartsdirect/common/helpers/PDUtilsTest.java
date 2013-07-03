package com.spd.cq.searspartsdirect.common.helpers;


import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.PDUtilsFixture;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PDUtilsTest extends TestCase {

	private PDUtilsFixture fixture;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new PDUtilsFixture();
	}

	@Test
	public void testGetSubcategoryFromPageWithoutTags() {
		String subcategory = PDUtils.getSubcategoryFromPage(fixture.getPageWithoutTags());
		assertThat(subcategory,is(nullValue()));
	}
	
	@Test
	public void testGetSubcategoryFromPageWithEmptyTags() {
		String subcategory = PDUtils.getSubcategoryFromPage(fixture.getPageWithEmptyTags());
		assertThat(subcategory,is(nullValue()));
	}
	
	@Test
	public void testGetSubcategoryFromPageWithOtherTag() {
		String subcategory = PDUtils.getSubcategoryFromPage(fixture.getPageWithOtherTag());
		assertThat(subcategory,is(nullValue()));
	}
	
	@Test
	public void testGetSubcategoryFromPageWithSubcategory() {
		String subcategory = PDUtils.getSubcategoryFromPage(fixture.getPageWithSubcategory());
		assertThat(subcategory,is(not(nullValue())));
	}
	
	@Test
	public void testUselessNullaryConstructor() {
		PDUtils whyWouldAnyoneDoThis = new PDUtils();
		assertThat(whyWouldAnyoneDoThis,isA(PDUtils.class));
	}

}
