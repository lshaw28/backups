package com.spd.cq.searspartsdirect.common.tags;

import java.util.List;

import javax.servlet.jsp.JspException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.BrowseGlossaryTagFixture;

public class BrowseGlossaryTagTest extends MocksTag {
	
	private BrowseGlossaryTagFixture fixture;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new BrowseGlossaryTagFixture();
	}

	@Test
	public void testDoStartTag() throws JspException {
		fixture.setUpProperties(properties);
		BrowseGlossaryTag  tag = new BrowseGlossaryTag();
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.doEndTag();
		
		Assert.assertNotNull(pageContext.getAttribute("glossary"));
		@SuppressWarnings("unchecked")
		List<String> glossary = (List<String>) pageContext.getAttribute("glossary");
		Assert.assertNotNull(glossary);
		Assert.assertTrue(glossary.size() > 0);
	}
	
	@Test
	public void testDoStartTagNoProperties() throws JspException {
		BrowseGlossaryTag tag = new BrowseGlossaryTag();
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.doEndTag();
	}

}
