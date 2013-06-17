package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
	public void testWithValidID(){
		try {
			fixture.setupValidID();
			tag.setPageContext(pageContext);
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			String youTubeID = (String) pageContext.getAttribute("youTubeID");
			assertThat(youTubeID, is(not("")));
			
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
		
	}
	
	@Test
	public void testWithInvalidID(){
		try{
		fixture.setupInvalidID();

		tag.setPageContext(pageContext);
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
		String youTubeID = (String) pageContext.getAttribute("youTubeID");
		assertThat(youTubeID, is(""));
		
		
	} catch (Exception e) {
		throw new RuntimeException(e);
	} 
	
	
	}
	
	
}













