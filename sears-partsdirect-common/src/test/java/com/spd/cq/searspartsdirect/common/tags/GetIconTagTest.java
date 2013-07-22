package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetIconTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class GetIconTagTest extends MocksTag {

	private GetIconTagFixture fixture;
	private GetIconTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetIconTagFixture(currentPage, pageManager);
		tag = new GetIconTag();
	}

	@Test
	public void testTagMinimus() throws JspException {
		runsSkipsBodyEvalsPage();
		String iconName = (String)pageContext.getAttribute("iconName");
		assertThat(iconName,is(fixture.getDefaultIcon()));
	}
	
	@Test
	public void testIconFromCategory() throws JspException {
		fixture.setupCurrentPageHasNoIcon();
		tag.setCategory(fixture.getTestCategory());
		runsSkipsBodyEvalsPage();
		String iconName = (String)pageContext.getAttribute("iconName");
		assertThat(iconName,is(fixture.getCategoryIcon()));
	}
	
	@Test
	public void testIconFromCurrentPage() throws JspException {
		fixture.setupCurrentPageHasIcon();
		runsSkipsBodyEvalsPage();
		String iconName = (String)pageContext.getAttribute("iconName");
		assertThat(iconName,is(fixture.getCurrentPageIcon()));
	}

	@Test
	public void testIconFromCategoryOverriddenByCurrentPage() throws JspException {
		fixture.setupCurrentPageHasIcon();
		tag.setCategory(fixture.getTestCategory());
		runsSkipsBodyEvalsPage();
		String iconName = (String)pageContext.getAttribute("iconName");
		assertThat(iconName,is(fixture.getCurrentPageIcon()));
	}
	
	@Test
	public void testIconFromOtherPage() throws JspException {
		fixture.setupOtherPageHasIcon();
		tag.setPagePath(fixture.getOtherPagePath());
		runsSkipsBodyEvalsPage();
		String iconName = (String)pageContext.getAttribute("iconName");
		assertThat(iconName,is(fixture.getOtherPageIcon()));
	}
	
	@Test
	public void testIconFromOtherPageHavingNoIcon() throws JspException {
		fixture.setupOtherPageHasNoIcon();
		tag.setPagePath(fixture.getOtherPagePath());
		runsSkipsBodyEvalsPage();
		String iconName = (String)pageContext.getAttribute("iconName");
		assertThat(iconName,is(fixture.getDefaultIcon()));
		
	}
	
	@Test
	public void testIconFromOtherPageNotExistingCurrentPageHasNone() throws JspException {
		tag.setPagePath(fixture.getOtherPagePath());
		runsSkipsBodyEvalsPage();
		String iconName = (String)pageContext.getAttribute("iconName");
		assertThat(iconName,is(fixture.getDefaultIcon()));
	}
	
	@Test
	public void testIconFromOtherPageNotExistingCurrentPageHasIcon() throws JspException {
		fixture.setupCurrentPageHasIcon();
		tag.setPagePath(fixture.getOtherPagePath());
		runsSkipsBodyEvalsPage();
		String iconName = (String)pageContext.getAttribute("iconName");
		assertThat(iconName,is(fixture.getCurrentPageIcon()));
	}
	
	@Test
	public void testIconFromCategoryOverriddenByOtherPage() throws JspException {
		fixture.setupOtherPageHasIcon();
		tag.setCategory(fixture.getTestCategory());
		tag.setPagePath(fixture.getOtherPagePath());
		runsSkipsBodyEvalsPage();
		String iconName = (String)pageContext.getAttribute("iconName");
		assertThat(iconName,is(fixture.getOtherPageIcon()));
	}
	
	@Test
	public void testIconFromCategoryNotOverriddenByOtherPage() throws JspException {
		fixture.setupOtherPageHasNoIcon();
		tag.setCategory(fixture.getTestCategory());
		tag.setPagePath(fixture.getOtherPagePath());
		runsSkipsBodyEvalsPage();
		String iconName = (String)pageContext.getAttribute("iconName");
		assertThat(iconName,is(fixture.getCategoryIcon()));
	}
	
	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}

}
