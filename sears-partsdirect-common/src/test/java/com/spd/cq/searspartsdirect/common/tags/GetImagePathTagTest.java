package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetImagePathTagFixture;

public class GetImagePathTagTest extends MocksTag {

	private GetImagePathTagFixture fixture;
	private GetImagePathTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetImagePathTagFixture(pageContext,resourceResolver);
		tag = new GetImagePathTag();
	}

	@Test
	public void testFullResponsiveImage() throws JspException {
		fixture.setUpFullResponsiveImage();
		
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.doEndTag();
		
		assertThat(pageContext.getAttribute("displayWidth"),is(nullValue()));
		assertThat(pageContext.getAttribute("displayHeight"),is(nullValue()));
		assertThat(pageContext.getAttribute("linkAlt"),is(nullValue()));
		assertThat(pageContext.getAttribute("linkURL"),is(nullValue()));
		assertThat(pageContext.getAttribute("linkTarget"),is(nullValue()));
		assertThat(pageContext.getAttribute("imageCaption"),is(nullValue()));
		assertThat(pageContext.getAttribute("photoCredit"),is(nullValue()));
	}
	
	@Test
	public void testEmptyResponsiveImage() throws JspException {
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.doEndTag();
	}
	
	@Test
	public void testNonlocalResponsiveImage() throws JspException, RepositoryException {
		fixture.setUpNonlocalResponsiveImage();
		tag.setResourcePath(fixture.getNonlocalResourcePath());
		
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.doEndTag();
		
		assertThat(pageContext.getAttribute("displayWidth"),is(not(nullValue())));
		assertThat(pageContext.getAttribute("displayHeight"),is(not(nullValue())));
		assertThat(pageContext.getAttribute("linkAlt"),is(not(nullValue())));
		assertThat(pageContext.getAttribute("linkURL"),is(not(nullValue())));
		assertThat(pageContext.getAttribute("linkTarget"),is(not(nullValue())));
		assertThat(pageContext.getAttribute("imageCaption"),is(not(nullValue())));
		assertThat(pageContext.getAttribute("photoCredit"),is(not(nullValue())));
	}

	@Test
	public void testRepairHref() {
		String targetName = fixture.getTargetName();
		String pngOriginalPath = fixture.getPngOriginalPath();
		String pngFixedPath = tag.repairHref(pngOriginalPath, targetName);
		assertThat(pngFixedPath, endsWith(targetName + ".img.png"));
		String gifOriginalPath = fixture.getGifOriginalPath();
		String gifFixedPath = tag.repairHref(gifOriginalPath, targetName);
		assertThat(gifFixedPath, endsWith(targetName + ".img.gif"));
		String jpgOriginalPath = fixture.getJpgOriginalPath();
		String jpgFixedPath = tag.repairHref(jpgOriginalPath, targetName);
		assertThat(jpgFixedPath, endsWith(targetName + ".img.jpg"));
	}

}
