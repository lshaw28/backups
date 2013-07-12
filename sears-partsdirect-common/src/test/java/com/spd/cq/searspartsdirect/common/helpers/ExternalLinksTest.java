package com.spd.cq.searspartsdirect.common.helpers;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import junit.framework.TestCase;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ExternalLinksFixture;

public class ExternalLinksTest extends TestCase {
	
	private ExternalLinksFixture fixture;
	private ExternalLinks generator;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ExternalLinksFixture();
		generator = new ExternalLinks(fixture.getSlingRequest());
	}

	@Test
	public void testExternalLinks() {
		assertThat(generator,is(instanceOf(ExternalLinks.class)));
	}

	@Test
	public void testGetExternalUrlForPage() {
		fixture.setUpIdentityResolver();
		String external = generator.getExternalUrlForPage(fixture.getTestPath());
		assertThat(external,is(instanceOf(String.class)));
		assertThat(external,is(not(nullValue())));
	}
	
	@Test
	public void testGetDecoratedExternalUrlForPage() {
		fixture.setUpIdentityResolver();
		setGeneratorDecoration();
		String external = generator.getExternalUrlForPage(fixture.getTestPath());
		checkResult(external);
	}
	
	@Test
	public void testGetExternalUrlWhenResolverIsHelping() {
		fixture.setUpHelpfulResolver();
		setGeneratorDecoration();
		String external = generator.getExternalUrlForPage(fixture.getTestPath());
		checkResult(external);
	}
	
	private void setGeneratorDecoration() {
		generator.setAddedPrefix(fixture.getTestPrefix());
		generator.setAddedSuffix(fixture.getTestSuffix());
	}
	
	private void checkResult(String external) {
		assertThat(external,Matchers.startsWith(generator.getAddedPrefix()));
		assertThat(external,Matchers.startsWith(fixture.getTestPrefix()));
		assertThat(external,Matchers.endsWith(generator.getAddedSuffix()));
		assertThat(external,Matchers.endsWith(fixture.getTestSuffix()));
		assertThat(external,is(fixture.getFinalUrl()));
	}
	
}
