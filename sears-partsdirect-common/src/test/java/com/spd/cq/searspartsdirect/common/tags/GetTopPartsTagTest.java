package com.spd.cq.searspartsdirect.common.tags;


import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.model.PartModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetTopPartsTagTest extends MocksTag {

	private GetTopPartsTag tag;

	private final static String TEST_BRAND = "kenmore";
	private final static String TEST_CATEGORY = "dishwasher";
	private final static String TEST_MODEL = "66517722k900";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tag = new GetTopPartsTag();
	}

	@Test
	public void testDoStartTag() throws JspException {
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<PartModel> topParts = (List<PartModel>)pageContext.getAttribute("topParts");
		assertThat(topParts,hasSize(0));
	}
	
	@Test
	public void testDoStartTagWithIds() throws JspException {
		tag.setModelName(TEST_MODEL);
		tag.setBrandName(TEST_BRAND);
		tag.setCategoryName(TEST_CATEGORY);
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<PartModel> topParts = (List<PartModel>)pageContext.getAttribute("topParts");
		assertThat(topParts,is(not(nullValue())));
	}
	
	@Test
	public void testDoStartTagWithOnlyModel() throws JspException {
		tag.setModelName(TEST_MODEL);
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<PartModel> topParts = (List<PartModel>)pageContext.getAttribute("topParts");
		assertThat(topParts,hasSize(0));
	}
	
	@Test
	public void testDoStartTagWithoutCategory() throws JspException {
		tag.setModelName(TEST_MODEL);
		tag.setBrandName(TEST_BRAND);
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<PartModel> topParts = (List<PartModel>)pageContext.getAttribute("topParts");
		assertThat(topParts,hasSize(0));
	}

	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
