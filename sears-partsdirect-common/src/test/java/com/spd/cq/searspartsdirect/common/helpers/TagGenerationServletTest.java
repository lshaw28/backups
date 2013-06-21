package com.spd.cq.searspartsdirect.common.helpers;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.TagGenerationServletFixture;

public class TagGenerationServletTest extends TestCase {
	
	public TagGenerationServletFixture fixture;
	public TagGenerationServlet servlet;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture  = new TagGenerationServletFixture();
		servlet = new TagGenerationServlet();
	}

	@Test
	public void testWithValues() {
		try {
			fixture.setUpNamespace();
			servlet.doPost(fixture.getRequest(), fixture.getResponse());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testWithNulls() {
		try {
			fixture.setUpNullNamespace();
			servlet.doPost(fixture.getRequest(), fixture.getResponse());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testExceptions() {
		try {
			fixture.setUpExceptionOne();
			servlet.doPost(fixture.getRequest(), fixture.getResponse());
			fixture.setUpExceptionTwo();
			servlet.doPost(fixture.getRequest(), fixture.getResponse());
			fixture.setUpExceptionThree();
			servlet.doPost(fixture.getRequest(), fixture.getResponse());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
