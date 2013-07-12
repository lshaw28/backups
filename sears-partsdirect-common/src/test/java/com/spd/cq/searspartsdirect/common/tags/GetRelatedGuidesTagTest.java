package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetRelatedGuidesFixture;

public class GetRelatedGuidesTagTest extends MocksTag {

	private GetRelatedGuidesFixture fixture;
	private GetRelatedGuidesTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetRelatedGuidesFixture(pageManager,currentPage);
		tag = new GetRelatedGuidesTag();
		tag.setMaxOutput(4);
	}

	@Test
	public void testNoHits() {
		try {
			fixture.setUpNoDirectRelations();
			fixture.makeNHits(0, resourceResolver);
			tag.setPageContext(pageContext);
			tag.setCategoryPath("/content/searspartsdirect/en/somepage");
			
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			
			@SuppressWarnings("unchecked")
			List<Object> result = (List<Object>)pageContext.getAttribute("guides");
			assertThat(result,isA(List.class));
			assertThat(result,is(empty()));
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testTwoHits() {
		try {
			fixture.setUpNoDirectRelations();
			fixture.andSetUpBadRelations(7);
			fixture.makeNHits(2, resourceResolver);

			tag.setPageContext(pageContext);
			tag.setCategoryPath("/content/searspartsdirect/en/somepage");
			
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			
			@SuppressWarnings("unchecked")
			List<Object> result = (List<Object>)pageContext.getAttribute("guides");
			assertThat(result,isA(List.class));
			assertThat(result,hasSize(2));
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testTwoHitsAndTwoDirect() {
		try {
			fixture.setUpDirectRelations(2);
			fixture.makeNHits(2, resourceResolver);

			tag.setPageContext(pageContext);
			tag.setCategoryPath("/content/searspartsdirect/en/somepage");
			
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			
			@SuppressWarnings("unchecked")
			List<Object> result = (List<Object>)pageContext.getAttribute("guides");
			assertThat(result,isA(List.class));
			assertThat(result,hasSize(4));
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testFiveHits() {
		try {
			fixture.setUpNoDirectRelations();
			fixture.makeNHits(5, resourceResolver);

			tag.setPageContext(pageContext);
			tag.setCategoryPath("/content/searspartsdirect/en/somepage");
			
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			
			@SuppressWarnings("unchecked")
			List<Object> result = (List<Object>)pageContext.getAttribute("guides");
			assertThat(result,isA(List.class));
			assertThat(result,hasSize(4));
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testFiveDirect() {
		try {
			fixture.setUpDirectRelations(5);

			tag.setPageContext(pageContext);
			tag.setCategoryPath("/content/searspartsdirect/en/somepage");
			
			int startResult = tag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = tag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			
			@SuppressWarnings("unchecked")
			List<Object> result = (List<Object>)pageContext.getAttribute("guides");
			assertThat(result,isA(List.class));
			assertThat(result,hasSize(4));
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
