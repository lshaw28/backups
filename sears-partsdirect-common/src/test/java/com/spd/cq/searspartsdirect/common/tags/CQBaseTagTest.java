package com.spd.cq.searspartsdirect.common.tags;


import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.CQBaseTagFixture;

public class CQBaseTagTest extends MocksTag {

	private CQBaseTag tag;
	private CQBaseTagFixture fixture;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new CQBaseTagFixture(request);
		tag = new CQBaseTag();
	}

	@Test
	public void testSetPageContextNoContextRoot() {
		tag.setPageContext(pageContext);
	}
	
	@Test
	public void testSetPageContextWithContextRoot() {
		fixture.setUpContextPath();
		tag.setPageContext(pageContext);
	}

}
