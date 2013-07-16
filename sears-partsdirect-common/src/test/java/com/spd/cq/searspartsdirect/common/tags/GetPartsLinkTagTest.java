package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetPartsLinkTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetPartsLinkTagTest extends MocksTag {

	private GetPartsLinkTagFixture fixture;
	private GetPartsLinkTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetPartsLinkTagFixture();
		tag = new GetPartsLinkTag();
	}

	@Test
	public void testDoStartTagNoSetup() throws JspException {
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testDoStartTagWithEmptyParams() throws JspException {
		tag.setBrandName(Constants.EMPTY);
		tag.setCategoryName(Constants.EMPTY);
		tag.setModelNumber(Constants.EMPTY);
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testDoStartTagWithInvalidParams() throws JspException {
		tag.setBrandName("Invalid");
		tag.setCategoryName("Invalid");
		tag.setModelNumber("Invalid");
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testDoStartTagWithRealParams() throws JspException {
		testAccessors();
		runsSkipsBodyEvalsPage();
		String findPartUrl = (String)pageContext.getAttribute("findPartUrl");
		if (findPartUrl != null) {
			assertThat(findPartUrl,startsWith("http"));
		}
	}

	@Test
	public void testAccessors() {
		assertThat(tag.getBrandName(),is(nullValue()));
		assertThat(tag.getCategoryName(),is(nullValue()));
		assertThat(tag.getModelNumber(),is(nullValue()));
		tag.setBrandName(fixture.getBrandName());
		tag.setCategoryName(fixture.getCategoryName());
		tag.setModelNumber(fixture.getModelNumber());
		assertThat(tag.getBrandName(),is(fixture.getBrandName()));
		assertThat(tag.getCategoryName(),is(fixture.getCategoryName()));
		assertThat(tag.getModelNumber(),is(fixture.getModelNumber()));
	}
	
	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(), is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}

}
