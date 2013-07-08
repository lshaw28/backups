package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ParentPageTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ParentPageTagTest extends MocksTag {

	private ParentPageTagFixture fixture;
	private ParentPageTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ParentPageTagFixture(currentPage);
		tag = new ParentPageTag();
	}

	@Test
	public void testWithParent() throws JspException {
		
		fixture.setUpParent();
		
		runsSkipsBodyEvalsPage();
		
		String parent = (String)pageContext.getAttribute("parentPage");
		assertThat(parent,is(fixture.getParent()));
	}

	@Test
	public void testWithoutParent() throws JspException {

		fixture.setUpNoParent();
		
		runsSkipsBodyEvalsPage();
		
		String parent = (String)pageContext.getAttribute("parentPage");
		assertThat(parent,is(nullValue()));
	}
	
	@Test
	public void testWithNullPathParent() throws JspException {

		fixture.setUpNullPathParent();
		
		runsSkipsBodyEvalsPage();
		
		String parent = (String)pageContext.getAttribute("parentPage");
		assertThat(parent,is(nullValue()));
	}
	
	@Test
	public void testWithEmptyPathParent() throws JspException {

		fixture.setUpEmptyPathParent();
		
		runsSkipsBodyEvalsPage();
		
		String parent = (String)pageContext.getAttribute("parentPage");
		assertThat(parent,is(nullValue()));
	}

	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
