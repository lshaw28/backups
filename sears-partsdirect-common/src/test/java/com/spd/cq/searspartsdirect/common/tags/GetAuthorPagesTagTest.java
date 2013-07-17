package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetAuthorPagesTagFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.AuthorModel;

public class GetAuthorPagesTagTest extends MocksTag {

	GetAuthorPagesTagFixture fixture;
	GetAuthorPagesTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetAuthorPagesTagFixture(currentPage, pageManager, resourceResolver);
		tag = new GetAuthorPagesTag();
	}	
	
	@Test
	public void testMinimusDoStartTag() throws JspException {
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testWhenArrayHasOneAuthor() throws JspException {
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<AuthorModel> authors = (List<AuthorModel>)pageContext.getAttribute("authors");
		assertThat(authors,hasSize(1));
		AuthorModel author = authors.get(0);
		assertThat(author.getPath(),is("someAuthor"));
	}
	
	@Test
	public void testWhenArrayHasOneAuthorEmptyImageAndAnAbstract() throws JspException {
		fixture.setUpEmptyImage();
		fixture.setUpAbstract("isisnt");
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<AuthorModel> authors = (List<AuthorModel>)pageContext.getAttribute("authors");
		assertThat(authors,hasSize(1));
		AuthorModel author = authors.get(0);
		assertThat(author.getPath(),is("someAuthor"));
		assertThat(author.getDescription(),is("isisnt"));
	}
	
	@Test
	public void testWhenArrayHasOneAuthorFileImage() throws RepositoryException, JspException {
		fixture.setUpFileImage();
		runsSkipsBodyEvalsPage();
	}
	
	@Test
	public void testWhenArrayHasOneAuthorFileReferenceImage() throws RepositoryException, JspException {
		fixture.setUpFileReferenceImage();
		runsSkipsBodyEvalsPage();
	}
	
	
	@Test
	public void testWhenExplodes() throws Exception {
		fixture.setUpExplodes();
		runsSkipsBodyEvalsPage();
	}

	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
