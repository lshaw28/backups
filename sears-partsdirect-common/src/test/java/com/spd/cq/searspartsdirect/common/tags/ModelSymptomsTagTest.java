package com.spd.cq.searspartsdirect.common.tags;


import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ModelSymptomsTagFixture;
import com.spd.cq.searspartsdirect.common.model.CommonSymptomsModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ModelSymptomsTagTest extends MocksTag {

	private ModelSymptomsTagFixture fixture;
	private ModelSymptomsTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ModelSymptomsTagFixture(pageContext);
		tag = new ModelSymptomsTag();
	}

	@Test
	public void testWithModelNumber() {
		try {
			tag.setPageContext(pageContext);
			tag.setModelNumber(fixture.getTestModelNumber());
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			//pageContext.setAttribute("modelSymptoms", modelSymptoms);
			@SuppressWarnings("unchecked")
			List<CommonSymptomsModel> symptoms = (List<CommonSymptomsModel>)pageContext.getAttribute("modelSymptoms");
			assertThat(symptoms,not(nullValue()));
			assertThat(symptoms,isA(List.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * Normally we would not test the accessors directly, but there is very little else about this
	 * tag that is testable - the doStart currently does not even look at the model number.
	 */
	@Test
	public void testGetSetModelNumber() {
		String modelNumber = tag.getModelNumber();
		assertThat(modelNumber,is(nullValue()));
		tag.setModelNumber(fixture.getTestModelNumber());
		modelNumber = tag.getModelNumber();
		assertThat(modelNumber,is(fixture.getTestModelNumber()));
	}

}
