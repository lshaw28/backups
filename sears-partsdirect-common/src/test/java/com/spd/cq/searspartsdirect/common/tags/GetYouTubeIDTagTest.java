package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetYouTubeIDTagFixture;

public class GetYouTubeIDTagTest extends MocksTag {

	private GetYouTubeIDTagFixture fixture;
	private GetYouTubeIDTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetYouTubeIDTagFixture(properties);
		tag = new GetYouTubeIDTag();
	}

	@Test
	public void testWithValidID() throws JspException {
		fixture.setupValidID();

		runsSkipsBodyEvalsPage();
		
		String youTubeID = (String) pageContext.getAttribute("youTubeID");
		assertThat(youTubeID, is(not("")));

	}

	@Test
	public void testWithInvalidID() throws JspException {

		fixture.setupInvalidID();

		runsSkipsBodyEvalsPage();
		
		String youTubeID = (String) pageContext.getAttribute("youTubeID");
		assertThat(youTubeID, is(""));
		
	}
	
	@Test
	public void testWithExplodingID() throws JspException {
		
		fixture.setupExplodingID();
		
		runsSkipsBodyEvalsPage();
		
		String youTubeID = (String) pageContext.getAttribute("youTubeID");
		assertThat(youTubeID, is(""));
		
	}
	
	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult, is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult, is(TagSupport.EVAL_PAGE));
	}

}
