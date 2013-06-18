package com.spd.cq.searspartsdirect.common.tags;


import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetImagePathTagFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetImagePathTagTest extends MocksTag {

	private GetImagePathTagFixture fixture;
	private GetImagePathTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetImagePathTagFixture(pageContext);
		tag = new GetImagePathTag();
	}

	@Test
	public void testFullResponsiveImage() {
		fixture.setUpFullResponsiveImage();
		try {
			tag.setPageContext(pageContext);
			tag.doStartTag();
			tag.doEndTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testEmptyResponsiveImage() {
		try {
			tag.setPageContext(pageContext);
			tag.doStartTag();
			tag.doEndTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
