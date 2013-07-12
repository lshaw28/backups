package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.DefineObjectsTagFixture;

public class DefineObjectsTagTest extends MocksTag {

	private DefineObjectsTagFixture fixture;
	private DefineObjectsTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new DefineObjectsTagFixture(slingRequest);
		tag = new DefineObjectsTag();
	}

	@Test
	public void testDoStartTag() {
		try {
			tag.setPageContext(pageContext);
			tag.doStartTag();
			tag.doEndTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testInDesignMode() {
		fixture.setUpDesignMode();
		testDoStartTag();
		assertThat((Boolean)pageContext.getAttribute("isDesignMode"),is(true));
	}
	
	@Test
	public void testInEditMode() {
		fixture.setUpEditMode();
		testDoStartTag();
		assertThat((Boolean)pageContext.getAttribute("isEditMode"),is(true));
	}
	
	@Test
	public void testInPreviewMode() {
		fixture.setUpPreviewMode();
		testDoStartTag();
		assertThat((Boolean)pageContext.getAttribute("isPreviewMode"),is(true));
	}
	
	@Test
	public void testInReadOnlyMode() {
		fixture.setUpReadOnlyMode();
		testDoStartTag();
		assertThat((Boolean)pageContext.getAttribute("isReadOnlyMode"),is(true));
	}
	
	@Test
	public void testDoingItTwice() {
		testDoStartTag();
		testDoStartTag();
	}
	
	@Test
	public void testDoingItWrong() {
		fixture.breakTheWorld();
		testDoStartTag();
	}
}
