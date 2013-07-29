package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetCommonPartsTagFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.PartTypeModel;

public class GetCommonPartsTagTest extends MocksTag {
	
	private GetCommonPartsTag tag;
	private GetCommonPartsTagFixture fixture;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tag = new GetCommonPartsTag();
		fixture = new GetCommonPartsTagFixture(slingRequest, resourceResolver, pageManager);
		tag.setCategoryPath("categoryPath");
	}

	@Test
	public void testDoStart() throws RepositoryException, JspException {
		fixture.setUpComplete();
		runTagSkipsBodyEvalsPage();
		validCompleteResultWithDetails();
	}
	
	private void runTagSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		int startResult = Integer.MIN_VALUE;
		int endResult = Integer.MIN_VALUE;
		
		startResult = tag.doStartTag();
		endResult = tag.doEndTag();
		
		assertThat(startResult, is(TagSupport.SKIP_BODY));
		assertThat(endResult, is(TagSupport.EVAL_PAGE));
	}
	
	private void validCompleteResultWithDetails() {
		Assert.assertNotNull(pageContext.getAttribute("commonParts"));
		List<PartTypeModel> partTypes = (List<PartTypeModel>) pageContext.getAttribute("commonParts");
		Assert.assertNotNull(partTypes);
		Assert.assertTrue(partTypes.size() > 0);
	}

}
