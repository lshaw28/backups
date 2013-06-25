package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetSymptomFrequencyFixture;

public class GetSymptomFrequencyTest extends MocksTag {

	@Test
	public void testDoStartTag() throws JspException {
		GetSymptomFrequency tag = new GetSymptomFrequency();
		GetSymptomFrequencyFixture fixture = new GetSymptomFrequencyFixture();
		tag.setSymptomList(fixture.getSymptoms());
		tag.setPageContext(pageContext);
		tag.doStartTag();
	}
}
