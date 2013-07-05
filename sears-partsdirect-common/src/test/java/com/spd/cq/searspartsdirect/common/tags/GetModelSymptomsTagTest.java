package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetModelSymptomsTagFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;

public class GetModelSymptomsTagTest extends MocksTag {
	
	private GetModelSymptomsTag tag;
	private GetModelSymptomsTagFixture fixture;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		tag = new GetModelSymptomsTag();
		fixture = new GetModelSymptomsTagFixture(slingRequest,
				resourceResolver, pageManager);
	}

	@Test
	public void test() throws JspException, RepositoryException {
		fixture.setupFixture();
		tag.setPageContext(pageContext);
		tag.setCategoryPath("categoryPath");
		tag.doStartTag();
		Assert.assertNotNull(pageContext.getAttribute("categorySymptoms"));
		List<SymptomModel> symptomModels = (List<SymptomModel>) pageContext.getAttribute("categorySymptoms");
		assertTrue(symptomModels.size() > 0);
		assertEquals(2, symptomModels.size());
		
		int startResult = tag.doStartTag();
		int endResult = tag.doEndTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
