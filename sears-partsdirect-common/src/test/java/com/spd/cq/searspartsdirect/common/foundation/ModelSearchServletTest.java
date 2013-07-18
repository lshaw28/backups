package com.spd.cq.searspartsdirect.common.foundation;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ModelSearchServletFixture;

public class ModelSearchServletTest extends TestCase {

	private ModelSearchServletFixture fixture;
	private ModelSearchServlet servlet;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		servlet = new ModelSearchServlet();
		fixture = new ModelSearchServletFixture();
	}

	@Test
	public void testFound() {
		fixture.setUpFound();
		try {
			//servlet.doGet(fixture.getRequest(), fixture.getResponse());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testNotFound() {
		fixture.setUpNotFound();
		try {
			//servlet.doGet(fixture.getRequest(), fixture.getResponse());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
