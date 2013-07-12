package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GuideNavigationTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.AnchorLinkModel;

public class GuideNavigationTagTest extends MocksTag {

	private GuideNavigationTagFixture fixture;
	private GuideNavigationTag guideNavigationTag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GuideNavigationTagFixture(currentPage,slingRequest,resourceResolver);
		guideNavigationTag = new GuideNavigationTag();
	}

	@Test
	public void testPopulatedForDefaultSetup() {
		try {
			runTagSkipsBodyEvalsPage();
			assertThat((String)pageContext.getAttribute(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR),is(fixture.getJumpToValue()));
			@SuppressWarnings("unchecked")
			List<AnchorLinkModel> result = (List<AnchorLinkModel>)pageContext.getAttribute(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR);
			assertThat(result,instanceOf(List.class));
			assertThat(result,hasSize(5));
			for (AnchorLinkModel link : result) {
				assertThat(link,isA(AnchorLinkModel.class));
				assertThat(link.getLinkText(),not(nullValue()));
				assertThat(link.getLinkText(),not(""));
				assertThat(link.getAnchorName(),not(nullValue()));
				assertThat(link.getAnchorName(),not(""));
				assertThat(link.getAnchorName(),containsString("_"));
				assertTrue(!link.getAnchorName().startsWith("#"));
			}
			assertThat(result.get(0).getLinkText(),is(Constants.TOOLS_REQ_DEF_GUIDE_NAV_LINK));
			assertThat(result.get(1).getLinkText(),is(Constants.PARTS_REQ_DEF_GUIDE_NAV_LINK));
			assertThat(result.get(2).getLinkText(),is(fixture.getTextHeader()));
			assertThat(result.get(3).getLinkText(),is(Constants.INSTRUCTIONS_DEF_GUIDE_NAV_LINK));
			assertThat(result.get(4).getLinkText(),is(Constants.COMMENTS_GUIDE_NAV_LINK_PREFIX+" ("+fixture.getCommentCount()+")"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testJumpTextIsBroken() {
		try {
			fixture.breakJumpText();
			runTagSkipsBodyEvalsPage();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testWithNoSetupProperty() {
		try {
			fixture.setupNoSetupProperty();
			runTagSkipsBodyEvalsPage();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testWithNoSetupNode() {
		try {
			fixture.setupNoSetupNode();
			runTagSkipsBodyEvalsPage();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testMissingMiscellany() {
		try {
			fixture.setupNoPageNode();
			runTagSkipsBodyEvalsPage();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testCannotSetupDisabled() {
		try {
			fixture.setupCannotSetupDisabled();
			runTagSkipsBodyEvalsPage();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testCannotSetupReadonly() {
		try {
			fixture.setupCannotSetupReadonly();
			runTagSkipsBodyEvalsPage();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testAlreadySetUp() {
		try {
			fixture.setupAlreadySetUp();
			runTagSkipsBodyEvalsPage();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testBlankLabels() {
		try {
			fixture.setupBlankLabels();
			runTagSkipsBodyEvalsPage();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testLabelIsBlank() {
		assertThat(GuideNavigationTag.labelIsBlank(null),is(true));
		assertThat(GuideNavigationTag.labelIsBlank(""),is(true));
		assertThat(GuideNavigationTag.labelIsBlank(" "),is(true));
		assertThat(GuideNavigationTag.labelIsBlank(" a"),is(false));
	}
	
	public void testResourceExists() {
		assertThat(GuideNavigationTag.resourceExists(null),is(false));
		assertThat(GuideNavigationTag.resourceExists(fixture.getExistingResource()),is(true));
		assertThat(GuideNavigationTag.resourceExists(fixture.getNonExistingResource()),is(false));
	}
	
	private void runTagSkipsBodyEvalsPage() throws JspException {
		guideNavigationTag.setPageContext(pageContext);
		int startResult = guideNavigationTag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));
		int endResult = guideNavigationTag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
}
