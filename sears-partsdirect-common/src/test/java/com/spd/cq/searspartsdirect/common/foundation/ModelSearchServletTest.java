package com.spd.cq.searspartsdirect.common.foundation;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ModelSearchServletFixture;

import static org.mockito.Mockito.verify;

public class ModelSearchServletTest extends TestCase {

	private ModelSearchServletFixture fixture;
	private ModelSearchServlet servlet;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		servlet = new ModelSearchServlet();
		fixture = new ModelSearchServletFixture();
	}

//	response.sendRedirect("/" + brandTrueName + "/" + categoryTrueName + "/model-" + model + "-repair.html");
//	 84	 	
//	                         }
//	 85	 1	
//	                         catch (Exception e) {
//	 86	 1	
//	                                 log.error(ExceptionUtils.getFullStackTrace(e));
//	 87	 3	
//	                         }
//	 88	 	
//	                 }
//	 89	 	
//	             else {
//	 90	 2	
//	                     response.sendRedirect(link);
	
	@Test
	public void testBothFound() throws Exception {
		fixture.setUpCategoryFound();
		fixture.setUpBrandFound();
		servlet.doGet(fixture.getRequest(), fixture.getResponse());
		verify(fixture.getResponse()).sendRedirect(fixture.getExpectedRedirect());
	}

	@Test
	public void testNeitherFound() throws Exception {
		fixture.setUpCategoryNotFound();
		fixture.setUpBrandNotFound();
		servlet.doGet(fixture.getRequest(), fixture.getResponse());
		verify(fixture.getResponse()).sendRedirect(fixture.getLink());
	}
	
	@Test
	public void testOnlyCategoryFound() throws Exception {
		fixture.setUpCategoryFound();
		fixture.setUpBrandNotFound();
		servlet.doGet(fixture.getRequest(), fixture.getResponse());
		verify(fixture.getResponse()).sendRedirect(fixture.getExpectedRedirect());
	}
	
	@Test
	public void testOnlyBrandFound() throws Exception {
		fixture.setUpCategoryNotFound();
		fixture.setUpBrandFound();
		servlet.doGet(fixture.getRequest(), fixture.getResponse());
		verify(fixture.getResponse()).sendRedirect(fixture.getLink());
	}
	
	@Test
	public void testBothFoundButCategoryNodeThrows() throws Exception {
		fixture.setUpCategoryFoundButThrows();
		fixture.setUpBrandFound();
		servlet.doGet(fixture.getRequest(), fixture.getResponse());
	}
}
