package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetGuideListingTagFixture;
import com.spd.cq.searspartsdirect.common.model.ArticleModel;

public class GetGuideListingTagTest extends MocksTag{
	
	GetGuideListingTag tag;
	GetGuideListingTagFixture fixture;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		fixture = new GetGuideListingTagFixture(pageContext,resourceResolver,pageManager,currentPage);
		tag = new GetGuideListingTag();
	}

	@Test
	public void testTagComplete() throws RepositoryException {
		fixture.setUpComplete();
		try {
			tag.setPageContext(pageContext);
			tag.setCategoryPath("/category");
			tag.doStartTag();
			tag.doEndTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		@SuppressWarnings("unchecked")
		HashMap<String, List<ArticleModel>> guides = (HashMap<String, List<ArticleModel>>)pageContext.getAttribute("guides");
		assertThat(guides,is(instanceOf(HashMap.class)));
	}
	
	@Test
	public void testTagNoSetup() throws RepositoryException, JspException {
		tag.setPageContext(pageContext);
		tag.setCategoryPath("/category");
		tag.doStartTag();
		tag.doEndTag();
	}
}
