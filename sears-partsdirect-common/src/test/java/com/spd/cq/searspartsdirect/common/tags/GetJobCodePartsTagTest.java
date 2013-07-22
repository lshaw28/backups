package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetJobCodePartsTagFixture;
import com.spd.cq.searspartsdirect.common.model.JobCodePartModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;

public class GetJobCodePartsTagTest extends MocksTag {

	private GetJobCodePartsTagFixture fixture;
	private GetJobCodePartsTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetJobCodePartsTagFixture();
		tag = new GetJobCodePartsTag();
	}

	@Test
	public void testSuccess() throws JspException {
		setupTagAttributes();
		runsTagSkipsBodyEvalsPage();
		assertNonEmptyResult();
	}

	@Test
	public void testEmptyJobCodes() throws JspException {
		setupEmptyJobCodes();
		runsTagSkipsBodyEvalsPage();
		assertEmptyResult();
		assertNull(tag.buildApiUrl());
	}

	@Test
	public void testNullJobCodes() throws JspException {
		setupNullJobCodes();
		runsTagSkipsBodyEvalsPage();
		assertEmptyResult();
	}

	@Test
	public void testEmptyModelNumber() throws JspException {
		setupEmptyModelNumber();
		runsTagSkipsBodyEvalsPage();
		assertNonEmptyResult();
		assertFalse(tag.buildApiUrl().contains("modelNumber"));
	}

	@Test
	public void testGetJobCodePartMethodWithValidJson() {
		String jsonResponse = fixture.getSuccessfulJsonResponse();
		Map<String, List<JobCodePartModel>> jobCodeParts = tag.getJobCodeParts(jsonResponse);
		assertThat(jobCodeParts, not(nullValue()));
		assertEquals(jobCodeParts.size(), 2);
	}

	@Test
	public void testGetJobCodePartMethodWithEmptyJson() {
		assertNotNull(tag.getJobCodeParts(StringUtils.EMPTY));
		assertTrue(tag.getJobCodeParts(StringUtils.EMPTY).size() == 0);
	}

	@Test
	public void testGetJobCodes() {
		tag.setJobCodes(fixture.mockValidJobCodes());
		assertNotNull(tag.getJobCodes());
		assertThat(tag.getJobCodes(), hasSize(2));
	}

	@Test
	public void testGetModelNumber() {
		tag.setModelNumber("someModelNumber");
		assertNotNull(tag.getModelNumber());
		assertThat(tag.getModelNumber(), is("someModelNumber"));
	}

	private void assertEmptyResult() {
		@SuppressWarnings("unchecked")
		Map<String, List<JobCodePartModel>> jobCodeParts = (Map<String, List<JobCodePartModel>>) pageContext.getAttribute("jobCodeParts");
		assertThat(jobCodeParts, not(nullValue()));
		assertEquals(jobCodeParts.size(), 0);
	}

	private void assertNonEmptyResult() {
		@SuppressWarnings("unchecked")
		Map<String, List<JobCodePartModel>> jobCodeParts = (Map<String, List<JobCodePartModel>>) pageContext.getAttribute("jobCodeParts");
		assertThat(jobCodeParts, not(nullValue()));
		assertEquals(jobCodeParts.size(), 2);
	}

	private void setupTagAttributes() {
		List<JobCodeModel> jobCodes = fixture.mockValidJobCodes();
		tag.setJobCodes(jobCodes);
		String modelNumber = "someModelNumber";
		tag.setModelNumber(modelNumber);
	}

	public void setupEmptyJobCodes() {
		List<JobCodeModel> jobCodes = fixture.mockEmptyJobCodes();
		tag.setJobCodes(jobCodes);
	}

	public void setupNullJobCodes() {
		tag.setJobCodes(null);
	}

	public void setupEmptyModelNumber() {
		List<JobCodeModel> jobCodesMock = fixture.mockValidJobCodes();
		tag.setJobCodes(jobCodesMock);
		tag.setModelNumber(StringUtils.EMPTY);
	}

	private void runsTagSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
