package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.DisplayImageTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.ImageBuilder;

public class DisplayImageTagTest extends MocksTag {
	
	private DisplayImageTagFixture fixture;
	private DisplayImageTag tag; // tag
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new DisplayImageTagFixture(resourceResolver);
		tag = new DisplayImageTag();
	}
	
	@Test
	public void testTagUsage() {
		try {
			tag.setPageContext(pageContext);
			tag.setPath(fixture.getTestPath());
			tag.setDecorated(true);
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
