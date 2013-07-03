package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.model.ConstantModel;


public class GetConstantsTagTest extends MocksTag {
	
	private GetConstantsTag tag;
	private ConstantModel Constants;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tag = new GetConstantsTag();
	}

	@Test
	public void testTag() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));	
	}
	
}
