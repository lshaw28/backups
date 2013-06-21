package com.spd.cq.searspartsdirect.common.foundation;


import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ModelSubPageFilterFixture;

import junit.framework.TestCase;

import static org.mockito.Mockito.verify;

public class ModelSubPageFilterTest extends TestCase {

	private ModelSubPageFilterFixture fixture;
	private ModelSubPageFilter filter;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ModelSubPageFilterFixture();
		filter = new ModelSubPageFilter();
	}

	@Test
	public void testFilterWrongPath() throws ServletException, IOException {
		fixture.setUpPathAndExt("/res/path", ".ext");
		filter.init(fixture.getFilterConfig());
		filter.doFilter(fixture.getRequest(), fixture.getResponse(), fixture.getFilterChain());
		filter.destroy();
		shouldDoFilter(); 
	}
	
	@Test
	public void testFilterQuoteRightUnquotePath() throws ServletException, IOException {
		fixture.setUpPathAndExt("/fake/url/fake/page", ".ext");
		fixture.setUpDispatcher("/fake/page");
		filter.init(fixture.getFilterConfig());
		filter.doFilter(fixture.getRequest(), fixture.getResponse(), fixture.getFilterChain());
		filter.destroy();
		shouldForward(); 
	}
	
	private void shouldForward() throws ServletException, IOException {
		verify(fixture.getDispatcher()).forward(fixture.getRequest(),fixture.getResponse());
	}

	private void shouldDoFilter() throws IOException, ServletException {
		verify(fixture.getFilterChain()).doFilter(fixture.getRequest(), fixture.getResponse());
	}
}
