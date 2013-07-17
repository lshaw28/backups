package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetRepairGuideJobCodeTagFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;

public class GetRepairGuideJobCodeTagTest extends MocksTag{

	private GetRepairGuideJobCodeTagFixture fixture;
	private GetRepairGuideJobCodeTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetRepairGuideJobCodeTagFixture(resourceResolver, currentPage);
		tag = new GetRepairGuideJobCodeTag();
	}

	@Test
	public void testTag() throws JspException {
		fixture.setup();
		runsTagSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<JobCodeModel> repairGuideJobCodes = (List<JobCodeModel>) pageContext.getAttribute("repairGuideJobCodes");
		assertThat(repairGuideJobCodes, not(nullValue()));
		assertThat(repairGuideJobCodes, hasSize(2));
	}

	@Test
	public void testTagWithNullCurrentPagePath() throws JspException {
		fixture.setupNullCurrentPagePath();
		runsTagSkipsBodyEvalsPage();
		assertEmptyResult();
	}

	private void assertEmptyResult() {
		@SuppressWarnings("unchecked")
		List<JobCodeModel> repairGuideJobCodes = (List<JobCodeModel>) pageContext.getAttribute("repairGuideJobCodes");
		assertThat(repairGuideJobCodes, not(nullValue()));
		assertThat(repairGuideJobCodes, hasSize(0));
	}

	private void assertNullResult() {
		@SuppressWarnings("unchecked")
		List<JobCodeModel> repairGuideJobCodes = (List<JobCodeModel>) pageContext.getAttribute("repairGuideJobCodes");
		assertThat(repairGuideJobCodes, nullValue());
	}

	@Test
	public void testTagWithNullCurrentPageAndProperties() throws JspException {
		fixture.setupCurrentPageWithNullProperties();
		runsTagSkipsBodyEvalsPage();
		assertEmptyResult();
	}

	@Test
	public void testTagWithCurrentPageAndNullProperties() throws JspException {
		fixture.setupCurrentPageWithNullProperties();
		runsTagSkipsBodyEvalsPage();
		assertEmptyResult();
	}

	@Test
	public void testTagWithoutPagesKey() throws JspException {
		fixture.setupCurrentPageWithoutPagesKey();
		runsTagSkipsBodyEvalsPage();
		assertEmptyResult();
	}

	@Test
	public void testTagWithNullResource() throws JspException {
		fixture.setupNullResource();
		runsTagSkipsBodyEvalsPage();
		assertEmptyResult();
	}

	@Test
	public void testTagWithResource() throws JspException {
		fixture.setupResource();
		runsTagSkipsBodyEvalsPage();
		assertEmptyResult();
	}

	@Test
	public void testTagWithNullPage() throws JspException {
		fixture.setupNullPage();
		runsTagSkipsBodyEvalsPage();
		assertEmptyResult();
	}

	@Test
	public void testTagWhenExceptionIsThrown() throws JspException {
		fixture.setupException();
		runsTagSkipsBodyEvalsPage();
		assertNullResult();
	}

	private void runsTagSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}

}
