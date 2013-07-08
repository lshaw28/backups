package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetSymptomDetailTagFixture;
import com.spd.cq.searspartsdirect.common.model.ModelSymptomModel;

public class GetSymptomDetailTagTest extends MocksTag {

	private GetSymptomDetailTag tag;
	private GetSymptomDetailTagFixture fixture;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		tag = new GetSymptomDetailTag();
		fixture = new GetSymptomDetailTagFixture(slingRequest,
				resourceResolver, pageManager);
	}

	@Test
	public void testDostartTag() throws RepositoryException, JspException {
		fixture.setupFixtureComplete();
		tag.setPageContext(pageContext);
		tag.setPartsRequired(true);
		assertThat(tag.isPartsRequired(),is(true));
		tag.doStartTag();
		
		Assert.assertNotNull(pageContext.getAttribute("modelSymptom"));
		ModelSymptomModel model = (ModelSymptomModel) pageContext.getAttribute("modelSymptom");
		Assert.assertNotNull(model);
		Assert.assertNotNull(model.getJobCodeModels());
		Assert.assertNotNull(model.getJobCodeModels().size() > 0);
		Assert.assertNotNull(model.getSymptomModel());
		tag.setPartsRequired(false);
		assertThat(tag.isPartsRequired(),is(false));
		runTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testNoHitProps() throws RepositoryException {
		fixture.setUpNoHitProps();
		runTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testEmptyHitProps() throws RepositoryException {
		fixture.setUpEmptyHitProps();
		runTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testNoJobCodeProps() throws RepositoryException {
		fixture.setUpNoJobCodeProps();
		runTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testNoJobCodePages() throws RepositoryException {
		fixture.setUpNoJobCodePages();
		runTagSkipsBodyEvalsPage();
	}
	
	@Test
	public void testNoJobCodePageProps() throws RepositoryException {
		fixture.setUpNoJobCodePageProps();
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
		assertThat(startResult, is(TagSupport.SKIP_BODY));
		assertThat(endResult, is(TagSupport.EVAL_PAGE));
	}
}
