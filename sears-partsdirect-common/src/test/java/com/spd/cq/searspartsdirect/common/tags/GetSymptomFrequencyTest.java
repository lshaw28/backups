package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;

import java.util.List;

import javax.servlet.jsp.JspException;

import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetSymptomFrequencyFixture;

public class GetSymptomFrequencyTest extends MocksTag {

	@Test
	public void testDoStartTag() throws JspException {
		GetSymptomFrequency tag = new GetSymptomFrequency();
		GetSymptomFrequencyFixture fixture = new GetSymptomFrequencyFixture();
		tag.setSymptomList(fixture.getSymptoms());
		assertThat(tag.getSymptomList(),isA(List.class));
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.doEndTag();
	}
}
