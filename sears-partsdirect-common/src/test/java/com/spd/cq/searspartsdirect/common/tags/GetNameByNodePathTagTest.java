package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetNameByNodePathTagTest extends MocksTag {

	GetNameByNodePathTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tag = new GetNameByNodePathTag();
	}

	@Test
	public void testDoStartTag() throws JspException {
		tag.setPageContext(pageContext);
		tag.setNodePath("a/b/c/d");
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));	
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
		assertThat((String)pageContext.getAttribute("nodeName"),is("d"));
	}
	
	@Test
	public void testNoSlashes() throws JspException {
		tag.setPageContext(pageContext);
		tag.setNodePath("onepath");
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));	
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
		assertThat((String)pageContext.getAttribute("nodeName"),is("onepath"));
	}

}
