package com.spd.cq.searspartsdirect.common.tags;


import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.RequiredToolsTagFixture;

public class RequiredToolsTagTest extends MocksTag {

	private RequiredToolsTagFixture fixture;
	private RequiredToolsTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new RequiredToolsTagFixture();
		tag = new RequiredToolsTag();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRequiredTools() {
		try {
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
