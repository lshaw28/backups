package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetSymptomDetailTagFixture;
import com.spd.cq.searspartsdirect.common.model.SymptomDetailsModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;

public class GetSymptomDetailTagTest extends MocksTag {

	private GetSymptomDetailTag tag;
	private GetSymptomDetailTagFixture fixture;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		tag = new GetSymptomDetailTag();
		fixture = new GetSymptomDetailTagFixture(slingRequest, resourceResolver, pageManager);
	}
	
	@Test
	public void testTagMinimus() throws Exception {
		fixture.setUpMinimus();
		runTagSkipsBodyEvalsPage();
		validEmptyResult();
	}
	
	@Test
	public void testTagAccessors() throws Exception {
		assertThat(tag.getId(),nullValue());
		tag.setId(fixture.getSymptomId());
		assertThat(tag.getId(),is(fixture.getSymptomId()));
	}
	
	@Test
	public void testTagComplete() throws Exception {
		fixture.setUpComplete();
		runTagSkipsBodyEvalsPage();
		validCompleteResultWithDetails();
	}
	
	@Test
	public void testTagNoHitProps() throws Exception {
		fixture.setUpComplete();
		fixture.removeHitProps();
		runTagSkipsBodyEvalsPage();
		validEmptyResult();
	}
	
	@Test
	public void testTagExplodingHitProps() throws Exception {
		fixture.setUpComplete();
		fixture.makeHitPropsExplode();
		runTagSkipsBodyEvalsPage();
		validEmptyResult();
	}
	
	@Test
	public void testTagNoHitPagesProp() throws Exception {
		fixture.setUpComplete();
		fixture.removeHitPagesProp();
		runTagSkipsBodyEvalsPage();
		validResultNullJobCodes();
	}
	
	@Test
	public void testTagInvalidJobCodes() throws Exception {
		fixture.setUpComplete();
		fixture.makeJobCodePagesInvalid();
		runTagSkipsBodyEvalsPage();
		validResultNoJobCodes();
	}
	
	@Test
	public void testTagMissingJobCodePages() throws Exception {
		fixture.setUpComplete();
		fixture.makeJobCodePagesMissing();
		runTagSkipsBodyEvalsPage();
		validResultNoJobCodes();
	}
	
	@Test
	public void testTagJobCodePagesMissingProps() throws Exception {
		fixture.setUpComplete();
		fixture.makeJobCodePagesMissingProps();
		runTagSkipsBodyEvalsPage();
		validResultNoJobCodes();
	}
	
	@Test
	public void testTagJobCodePagesPropsEmpty() throws Exception {
		fixture.setUpComplete();
		fixture.makeJobCodePagesPropsEmpty();
		runTagSkipsBodyEvalsPage();
		validCompleteResult();
	}
	
	private void validCompleteResultWithDetails() {
		/*
		 * This is executed after complete runs - add any additional validation here.
		 */
		validCompleteResult();
	}
	
	private void validEmptyResult() {
		SymptomDetailsModel symptomDetailsModel = (SymptomDetailsModel)pageContext.getAttribute("symptom");
		assertThat(symptomDetailsModel,not(nullValue()));
		List<JobCodeModel> jobCodeModels = symptomDetailsModel.getJobCodeModels();
		assertThat(jobCodeModels,nullValue());
		assertThat(symptomDetailsModel.getTitle(),nullValue());
		assertThat(symptomDetailsModel.getDescription(), nullValue());
	}
	
	private void validCompleteResult() {
		SymptomDetailsModel symptomDetailsModel = (SymptomDetailsModel)pageContext.getAttribute("symptom");
		assertThat(symptomDetailsModel,not(nullValue()));
		List<JobCodeModel> jobCodeModels = symptomDetailsModel.getJobCodeModels();
		assertThat(jobCodeModels,not(nullValue()));
		assertThat(jobCodeModels,hasSize(1));
		JobCodeModel jobCode = jobCodeModels.get(0);
		assertThat(jobCode,not(nullValue()));
		assertThat(symptomDetailsModel.getTitle(),not(nullValue()));
		assertThat(symptomDetailsModel.getDescription(),not(nullValue()));
	}
	
	private void validResultNullJobCodes() {
		SymptomDetailsModel symptomDetailsModel = (SymptomDetailsModel)pageContext.getAttribute("symptom");
		assertThat(symptomDetailsModel,not(nullValue()));
		List<JobCodeModel> jobCodeModels = symptomDetailsModel.getJobCodeModels();
		assertThat(jobCodeModels,nullValue());
		assertThat(symptomDetailsModel.getTitle(),not(nullValue()));
		assertThat(symptomDetailsModel.getDescription(),not(nullValue()));
	}
	
	private void validResultNoJobCodes() {
		SymptomDetailsModel symptomDetailsModel = (SymptomDetailsModel)pageContext.getAttribute("symptom");
		assertThat(symptomDetailsModel,not(nullValue()));
		List<JobCodeModel> jobCodeModels = symptomDetailsModel.getJobCodeModels();
		assertThat(jobCodeModels,not(nullValue()));
		assertThat(jobCodeModels,hasSize(0));
		assertThat(symptomDetailsModel.getTitle(),not(nullValue()));
		assertThat(symptomDetailsModel.getDescription(),not(nullValue()));
	}

	private void runTagSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = Integer.MIN_VALUE;
		int endResult = Integer.MIN_VALUE;
		
		startResult = tag.doStartTag();
		endResult = tag.doEndTag();
		
		assertThat(startResult, is(TagSupport.SKIP_BODY));
		assertThat(endResult, is(TagSupport.EVAL_PAGE));
	}
}
