package com.spd.cq.searspartsdirect.common.tags;

import java.util.List;

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
		
		fixture = new GuideNavigationTagFixture(currentPage);
		guideNavigationTag = new GuideNavigationTag();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDoStartTag() {
		try {
			guideNavigationTag.setPageContext(pageContext);
			guideNavigationTag.doStartTag();
			assertThat((String)pageContext.getAttribute(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR),is(fixture.getJumpToValue()));
			assertThat((List<List<String>>)pageContext.getAttribute(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR),instanceOf(List.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
