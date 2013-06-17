package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

public class ModelSymptomsTagFixture {

	public ModelSymptomsTagFixture(PageContext pageContext) {
		JspWriter writer = mock(JspWriter.class); // We could hook a StringWriter to this if we want a look at the output.
		when(pageContext.getOut()).thenReturn(writer);
	}

	public String getTestModelNumber() {
		return "ZZ9PZA";
	}
}
