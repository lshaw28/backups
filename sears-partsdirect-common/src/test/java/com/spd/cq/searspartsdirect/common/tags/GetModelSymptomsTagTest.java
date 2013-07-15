package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import junit.framework.Assert;

import org.apache.sling.api.SlingHttpServletRequest;
import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetModelSymptomsTagFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;

public class GetModelSymptomsTagTest extends MocksTag {
	
	private GetModelSymptomsTag tag;
	private GetModelSymptomsTagFixture fixture;
	private SlingHttpServletRequest slingRequest;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		tag = new GetModelSymptomsTag();
		fixture = new GetModelSymptomsTagFixture(slingRequest,
				resourceResolver, pageManager);
	}

	@Test
	public void test() throws JspException, RepositoryException {
		//TODO -- need to write the test
		Assert.assertTrue(true);
	}
}
