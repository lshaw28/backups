package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.LinkResolverTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LinkResolverTagTest extends MocksTag {

	private LinkResolverTagFixture fixture;
	private LinkResolverTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new LinkResolverTagFixture();
		tag = new LinkResolverTag();
	}

	@Test
	public void testNeedsExtension() throws JspException {
		tag.setValue(fixture.getNeedsHtmlExtension());
		runTagSkipsBodyEvalsPage();
		String url = (String)pageContext.getAttribute("url");
		assertThat(url,is(fixture.getHasHtmlExtension()));
	}
	
	@Test
	public void testHasExtension() throws JspException {
		tag.setValue(fixture.getHasHtmlExtension());
		runTagSkipsBodyEvalsPage();
		String url = (String)pageContext.getAttribute("url");
		assertThat(url,is(fixture.getHasHtmlExtension()));
	}
	
	@Test
	public void testHasPngExtension() throws JspException {
		tag.setValue(fixture.getHasPngExtension());
		runTagSkipsBodyEvalsPage();
		String url = (String)pageContext.getAttribute("url");
		assertThat(url,is(fixture.getHasPngExtension()));
	}
	
	@Test
	public void testRelativePath() throws JspException {
		tag.setValue(fixture.getRelativePath());
		runTagSkipsBodyEvalsPage();
		String url = (String)pageContext.getAttribute("url");
		assertThat(url,is(fixture.getRelativePath()));
	}
	
	@Test
	public void testTwoSlashesWhatReally() throws JspException {
		tag.setValue(fixture.getTwoSlashes());
		runTagSkipsBodyEvalsPage();
		String url = (String)pageContext.getAttribute("url");
		assertThat(url,is(fixture.getTwoSlashes()));
	}
	
	@Test
	public void testEndsWithSlash() throws JspException {
		tag.setValue(fixture.getEndsWithSlash());
		runTagSkipsBodyEvalsPage();
		String url = (String)pageContext.getAttribute("url");
		assertThat(url,is(fixture.getEndsWithSlash()));
	}
		
	private void runTagSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
