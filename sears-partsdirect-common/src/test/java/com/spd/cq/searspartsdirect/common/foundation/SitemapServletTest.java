package com.spd.cq.searspartsdirect.common.foundation;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

import java.io.StringReader;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.apache.xerces.parsers.XMLDocumentParser;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.SitemapServletFixture;

public class SitemapServletTest extends TestCase {

	private SitemapServletFixture fixture;
	private SitemapServlet servlet;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new SitemapServletFixture();
		servlet = new SitemapServlet();
	}

	@Test
	public void testDoGet() {
		assertThat(servlet,instanceOf(SitemapServlet.class));
		servlet.doGet(fixture.getRequest(), fixture.getResponse());
		StringWriter snoop = fixture.getSnoop();
		snoop.flush();
		String snoopedOut = snoop.toString();
		try {
			snoop.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		XMLDocumentParser parser = new XMLDocumentParser();
		XMLInputSource source = new XMLInputSource(null, null, null, new StringReader(snoopedOut), "UTF-8");
		try {
			parser.parse(source); // We attempt to parse the sitemap created, any problem will cause a test failure.
		} catch (Exception e) {
			throw new RuntimeException(snoopedOut,e);
		}
		assertThat(snoopedOut,containsString("/foo.html"));
		assertThat(snoopedOut,containsString("/foo/bar.html"));
		assertThat(snoopedOut,not(containsString("baz")));
	}
	
	@Test
	public void testBrokenWriter() {
		try {
			fixture.breakTheWriter();
			servlet.doGet(fixture.getRequest(), fixture.getResponse());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
