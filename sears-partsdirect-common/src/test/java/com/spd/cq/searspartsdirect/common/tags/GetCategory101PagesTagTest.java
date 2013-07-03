package com.spd.cq.searspartsdirect.common.tags;


import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetCategory101PagesTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetCategory101PagesTagTest extends MocksTag {

	private GetCategory101PagesTagFixture fixture;
	private GetCategory101PagesTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetCategory101PagesTagFixture(resourceResolver,pageManager);
		tag = new GetCategory101PagesTag();
	}

	@Test
	public void testMinimusDoStartTag() throws JspException {
		runsTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testWithNoResults() throws JspException {
		fixture.setUpEmptyResults();
		tag.setCategory(fixture.getCategory());
		runsTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testWithAResult() throws JspException, RepositoryException {
		fixture.setUpOneResult("foo");
		tag.setCategory(fixture.getCategory());
		runsTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testWithAnUntaggedResult() throws JspException, RepositoryException {
		fixture.setUpOneResultNoTags("bar");
		tag.setCategory(fixture.getCategory());
		runsTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testWithAResultWithAbstractAndEmptyImage() throws JspException, RepositoryException {
		fixture.setUpOneResultWithAbstractAndEmptyImage("baz");
		tag.setCategory(fixture.getCategory());
		runsTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testWithAResultWithFileImage() throws JspException, RepositoryException {
		fixture.setUpOneResultWithFileImage("quux");
		tag.setCategory(fixture.getCategory());
		runsTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testWithAResultWithFileRefImage() throws JspException, RepositoryException {
		fixture.setUpOneResultWithFileRefImage("quuxare");
		tag.setCategory(fixture.getCategory());
		runsTagSkipsBodyEvalsPage();
	}

	// Every day I wish I'd made a home in the super for this..
	private void runsTagSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
