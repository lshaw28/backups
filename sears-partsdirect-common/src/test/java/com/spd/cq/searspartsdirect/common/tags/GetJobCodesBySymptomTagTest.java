package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetJobCodesBySymptomTagFixture;
import com.spd.cq.searspartsdirect.common.model.JobCodesModel;

public class GetJobCodesBySymptomTagTest extends MocksTag {
	
	private GetJobCodesBySymptomTag tag;
	private GetJobCodesBySymptomTagFixture fixture;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		tag = new GetJobCodesBySymptomTag();
		fixture = new GetJobCodesBySymptomTagFixture(slingRequest, resourceResolver, pageManager);
	}
	
	@Test
	public void testDostartTag() throws JspException, RepositoryException {
		fixture.setupFixture();
		tag.setPageContext(pageContext);
		tag.doStartTag();
		Assert.assertNotNull(pageContext.getAttribute("symptomJobCodes"));
		JobCodesModel updatedJobCodesModel = (JobCodesModel) pageContext.getAttribute("symptomJobCodes");
		Assert.assertNotNull(updatedJobCodesModel);
		Assert.assertNotNull(updatedJobCodesModel.getDescription());
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
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}

}
