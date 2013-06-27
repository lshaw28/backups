package com.spd.cq.searspartsdirect.common.tags;


import java.util.List;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetMaintenanceTopicJumpTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.AnchorLinkModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetMaintenanceTopicJumpTagTest extends MocksTag {

	private GetMaintenanceTopicJumpTagFixture fixture;
	private GetMaintenanceTopicJumpTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetMaintenanceTopicJumpTagFixture(currentPage);
		tag = new GetMaintenanceTopicJumpTag();
	}

	@Test
	public void testNoContent() throws JspException {
		fixture.setupNoContent();
		runsSkipsBodyEvalsPage();
		topicsHasContent(0);
	}
	
	@Test
	public void testEmptyParsys() throws JspException {
		fixture.setupEmptyParsys();
		runsSkipsBodyEvalsPage();
		topicsHasContent(0);
	}
	
	@Test
	public void testOneTopic() throws JspException, PathNotFoundException, RepositoryException {
		fixture.setupOneTopic();
		runsSkipsBodyEvalsPage();
		topicsHasContent(0);
	}
	
	@Test
	public void testTwoTopics() throws JspException, PathNotFoundException, RepositoryException {
		fixture.setupTwoTopics();
		runsSkipsBodyEvalsPage();
		topicsHasContent(2);
	}
	
	@Test
	public void testExplodingTopic() throws JspException, PathNotFoundException, RepositoryException {
		fixture.setupExplodingTopic();
		runsSkipsBodyEvalsPage();
		topicsHasContent(0);
	}

	@Test
	public void testIsParsys() throws PathNotFoundException, RepositoryException {
		assertThat(tag.isParsys(fixture.getParsys()),is(true));
		assertThat(tag.isParsys(fixture.getTopic("quux")),is(false));
	}

	@Test
	public void testIsTopic() throws PathNotFoundException, RepositoryException {
		assertThat(tag.isTopic(fixture.getTopic("foo")),is(true));
		assertThat(tag.isTopic(fixture.getParsys()),is(false));
	}

	@Test
	public void testGetAName() throws PathNotFoundException, RepositoryException {
		String aName = tag.getAName(fixture.getParsys(), fixture.getTopic("bar"));
		assertThat(aName,isA(String.class));
		assertThat(aName,is(not(nullValue())));
		assertThat(aName,is(not(Constants.EMPTY)));
	}

	@Test
	public void testGetTopicTitle() throws PathNotFoundException, RepositoryException {
		String title = tag.getTopicTitle(fixture.getTopic("baz"));
		assertThat(title,isA(String.class));
		assertThat(title,is(not(nullValue())));
		assertThat(title,is(not(Constants.EMPTY)));
	}
	
	private void topicsHasContent(int size) {
		List<AnchorLinkModel> jumpTopics = retrieveAndCheckTopics();
		assertThat(jumpTopics,hasSize(size));
	}
	
	private List<AnchorLinkModel> retrieveAndCheckTopics() {
		@SuppressWarnings("unchecked")
		List<AnchorLinkModel> jumpTopics = (List<AnchorLinkModel>)pageContext.getAttribute("jumpTopics");
		assertThat(jumpTopics,isA(List.class));
		assertThat(jumpTopics,is(not(nullValue())));
		assertThat(jumpTopics,not(hasSize(1))); // This should never contain exactly 1 element
		return jumpTopics;
	}
	
	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}

}
