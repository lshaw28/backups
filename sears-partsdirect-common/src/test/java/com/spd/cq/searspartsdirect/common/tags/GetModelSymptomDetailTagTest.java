package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetModelSymptomDetailTagFixture;
import com.spd.cq.searspartsdirect.common.model.ModelSymptomModel;

public class GetModelSymptomDetailTagTest extends MocksTag {

	private GetModelSymptomDetailTag tag;
	private GetModelSymptomDetailTagFixture fixture;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		tag = new GetModelSymptomDetailTag();
		fixture = new GetModelSymptomDetailTagFixture(slingRequest,
				resourceResolver, pageManager);
	}

	@Test
	public void testDostartTag() throws RepositoryException, JspException {
		fixture.setupFixture();
		tag.setPageContext(pageContext);
		tag.doStartTag();
		
		Assert.assertNotNull(pageContext.getAttribute("modelSymptom"));
		ModelSymptomModel model = (ModelSymptomModel) pageContext.getAttribute("modelSymptom");
		Assert.assertNotNull(model);
		Assert.assertNotNull(model.getJobCodeModels());
		Assert.assertNotNull(model.getJobCodeModels().size() > 0);
		Assert.assertNotNull(model.getSymptomModel());
		runTagSkipsBodyEvalsPage();
	}

	private void runTagSkipsBodyEvalsPage() {
		int startResult = Integer.MIN_VALUE;
		int endResult = Integer.MIN_VALUE;
		try {
			startResult = tag.doStartTag();
			endResult = tag.doEndTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		assertThat(startResult, is(TagSupport.SKIP_BODY));
		assertThat(endResult, is(TagSupport.EVAL_PAGE));
	}
}
