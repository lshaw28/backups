package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.fixture.GetJobCodesBySymptomTagFixture;
import com.spd.cq.searspartsdirect.common.model.JobCodesModel;

public class GetJobCodesBySymptomTagTest extends MocksTag {

	private GetJobCodesBySymptomTag tag;
	private GetJobCodesBySymptomTagFixture fixture;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		tag = new GetJobCodesBySymptomTag();
		fixture = new GetJobCodesBySymptomTagFixture(slingRequest,
				resourceResolver, pageManager);
	}

	@Test
	public void testDostartTag() throws RepositoryException, JspException {
		fixture.setupFixture();
		tag.setPageContext(pageContext);
		
		tag.doStartTag();
		Assert.assertNotNull(pageContext.getAttribute("symptomJobCodes"));
		JobCodesModel updatedJobCodesModel = (JobCodesModel) pageContext.getAttribute("symptomJobCodes");
		Assert.assertNotNull(updatedJobCodesModel);
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
