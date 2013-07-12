package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetErrorCodesListTagFixture;

public class GetErrorCodesListTagTest extends MocksTag {
	
	private GetErrorCodesListTag tag;
	private GetErrorCodesListTagFixture fixture;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		tag = new GetErrorCodesListTag();
		fixture = new GetErrorCodesListTagFixture(slingRequest,resourceResolver,pageManager);
	}
	
	@Test
	public void testNoCategory() {
		runTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testWithCategory() throws RepositoryException {
		fixture.setUpWithCategory();
		tag.setCategoryPath(fixture.getCategoryPath());
		assertThat(tag.getCategoryPath(),is(fixture.getCategoryPath()));
		runTagSkipsBodyEvalsPage();
	}
	
	private void runTagSkipsBodyEvalsPage() {
		tag.setPageContext(pageContext);
		int startResult = Integer.MIN_VALUE;
		int endResult = Integer.MIN_VALUE;
		try {
		startResult = tag.doStartTag();
		endResult = tag.doEndTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
