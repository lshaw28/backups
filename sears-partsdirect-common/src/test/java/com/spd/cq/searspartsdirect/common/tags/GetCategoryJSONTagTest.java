package com.spd.cq.searspartsdirect.common.tags;


import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetCategoryJSONTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThan;

public class GetCategoryJSONTagTest extends MocksTag {

	GetCategoryJSONTag tag;
	GetCategoryJSONTagFixture fixture;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetCategoryJSONTagFixture(pageContext,resourceResolver);
		tag = new GetCategoryJSONTag();
	}

	@Test
	public void testMinimus() throws JspException, JSONException {
		runsSkipsBodyEvalsPage();
		parsesAsArray();
	}
	
	@Test
	public void testWithZeroResults() throws JspException, RepositoryException, JSONException {
		fixture.setUpNResults(0);
		runsSkipsBodyEvalsPage();
		parsesAsArray();
	}
	
	@Test
	public void testWithOneResult() throws JspException, RepositoryException, JSONException {
		fixture.setUpNResults(1);
		runsSkipsBodyEvalsPage();
		parsesAsArray();
	}
	
	@Test
	public void testExplodes() throws JspException, RepositoryException, JSONException {
		fixture.setUpExplodes();
		runsSkipsBodyEvalsPage();
	}
	
	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
	
	private void parsesAsArray() throws JSONException {
		JSONArray parsed = new JSONArray(fixture.getOutput());
		assertThat(parsed.length(),greaterThan(0));
	}

}
