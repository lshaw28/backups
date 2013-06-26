package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.FixSingularTitleTagFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FixSingularTitleTagTest extends MocksTag {

	private FixSingularTitleTag tag;
	private FixSingularTitleTagFixture fixture;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tag = new FixSingularTitleTag();
		fixture = new FixSingularTitleTagFixture();
	}

	@Test
	public void testDoStartTagNoSingular() throws JspException {
		ProductCategoryModel noSingular = new ProductCategoryModel("", "Plurals", "", "", "");
		assertThat(noSingular.getTitle(),is("Plurals"));
		assertThat(noSingular.getSingularTitle(),is(""));
		tag.setModelInstance(noSingular);
		tagRunsSkipsBodyEvalsPage();
		assertThat(noSingular.getSingularTitle(),is("Plural"));
	}
	
	@Test
	public void testDoStartTagHasSingular() throws JspException {
		ProductCategoryModel hasSingular = new ProductCategoryModel("", "Plurals", "Singular", "", "");
		assertThat(hasSingular.getTitle(),is("Plurals"));
		assertThat(hasSingular.getSingularTitle(),is("Singular"));
		tag.setModelInstance(hasSingular);
		tagRunsSkipsBodyEvalsPage();
		assertThat(hasSingular.getSingularTitle(),is("Singular"));
	}
	
	private void tagRunsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}

}
