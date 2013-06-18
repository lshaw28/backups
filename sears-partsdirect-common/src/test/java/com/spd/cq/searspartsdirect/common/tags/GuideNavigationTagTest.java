package com.spd.cq.searspartsdirect.common.tags;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GuideNavigationTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GuideNavigationTagTest extends MocksTag {

	private GuideNavigationTagFixture fixture;
	private GuideNavigationTag guideNavigationTag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		
		fixture = new GuideNavigationTagFixture(currentPage,slingRequest,resourceResolver);
		guideNavigationTag = new GuideNavigationTag();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDoStartTag() {
		try {
			guideNavigationTag.setPageContext(pageContext);
			int startResult = guideNavigationTag.doStartTag();
			assertThat(startResult,is(TagSupport.SKIP_BODY));
			int endResult = guideNavigationTag.doEndTag();
			assertThat(endResult,is(TagSupport.EVAL_PAGE));
			assertThat((String)pageContext.getAttribute(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR),is(fixture.getJumpToValue()));
			List<List<String>> result = (List<List<String>>)pageContext.getAttribute(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR);
			assertThat(result,instanceOf(List.class));
			assertThat(result,hasSize(5));
			for (List<String> link : result) {
				assertThat(link,isA(List.class));
				assertThat(link,hasSize(2));
				assertThat(link.get(0),not(nullValue()));
				assertThat(link.get(0),not(""));
				assertThat(link.get(1),not(nullValue()));
				assertThat(link.get(1),not(""));
				assertThat(link.get(1),containsString("_"));
			}
			assertThat(result.get(0).get(0),is(Constants.TOOLS_REQ_DEF_GUIDE_NAV_LINK));
			assertThat(result.get(1).get(0),is(Constants.PARTS_REQ_DEF_GUIDE_NAV_LINK));
			assertThat(result.get(2).get(0),is(Constants.INSTRUCTIONS_DEF_GUIDE_NAV_LINK));
			assertThat(result.get(3).get(0),is(fixture.getTextHeader()));
			assertThat(result.get(4).get(0),is(Constants.COMMENTS_GUIDE_NAV_LINK_PREFIX+" ("+fixture.getCommentCount()+")"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
