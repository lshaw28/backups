package com.spd.cq.searspartsdirect.common.foundation;

import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.ServletException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ModelSearchServletFixture;
import com.spd.cq.searspartsdirect.common.fixture.ModelSubPageFilterFixture;

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
			servlet.doPost(fixture.getRequest(), fixture.getResponse());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testNotFound() {
		fixture.setUpNotFound();
		try {
			servlet.doPost(fixture.getRequest(), fixture.getResponse());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
