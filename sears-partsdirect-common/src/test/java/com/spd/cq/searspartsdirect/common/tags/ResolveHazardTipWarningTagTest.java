package com.spd.cq.searspartsdirect.common.tags;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ResolveHtwFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author bzethmayr
 *
 */
public class ResolveHazardTipWarningTagTest extends MocksTag {
	
	private ResolveHtwFixture fixture;
	private ResolveHazardTipWarningTag resolveHtwTag;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ResolveHtwFixture(resourceResolver,currentNode);
		resolveHtwTag = new ResolveHazardTipWarningTag();
	}

	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#doStartTag()}.
	 * @throws RepositoryException 
	 * @throws PathNotFoundException 
	 */
	@Test
	public void testDoStartTagAdhocInvalidHazard() throws PathNotFoundException, RepositoryException {
		fixture.setupAdhocExists();
		fixture.setupAdhocValue();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getAdhocHazardText()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(Constants.EMPTY));
	}
	
	@Test
	public void testDoStartTagAdhocEmptyHazard() throws PathNotFoundException, RepositoryException {
		fixture.setupAdhocExists();
		fixture.setupAdhocEmpty();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getHazardPlaceholder()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(Constants.EMPTY));
	}
	
	@Test
	public void testDoStartTagAdhocExplodingHazard() throws PathNotFoundException, RepositoryException {
		fixture.setupAdhocExists();
		fixture.setupAdhocExplodes();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getHazardPlaceholder()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(Constants.EMPTY));
	}
	
	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#doStartTag()}.
	 * @throws RepositoryException 
	 */
	@Test
	public void testDoStartTagAdhocValidHazard() throws RepositoryException {
		fixture.setupAdhocExists();
		fixture.setupAdhocValue();
		fixture.setupChoiceExists();
		fixture.setupChoiceValue();
		fixture.setupChoiceValueContents();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getAdhocHazardText()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(fixture.getChoiceHazardImage()));
	}
	
	@Test
	public void testDoStartTagAdhocValidEmptyHazard() throws RepositoryException {
		fixture.setupAdhocExists();
		fixture.setupAdhocValue();
		fixture.setupChoiceExists();
		fixture.setupChoiceValue();
		fixture.setupChoiceValueEmpty();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getAdhocHazardText()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(Constants.EMPTY));
	}
	
	@Test
	public void testDoStartTagAdhocValidExplodingHazard() throws RepositoryException {
		fixture.setupAdhocExists();
		fixture.setupAdhocValue();
		fixture.setupChoiceExists();
		fixture.setupChoiceExplodes();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getAdhocHazardText()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(Constants.EMPTY));
	}
	
	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#doStartTag()}.
	 * @throws RepositoryException 
	 * @throws ValueFormatException 
	 */
	@Test
	public void testDoStartTagInvalidHazard() throws ValueFormatException, RepositoryException {
		fixture.setupAdhocExists();
		fixture.setupChoiceExists();
		fixture.setupChoiceValue();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getHazardPlaceholder()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(""));
	}
	
	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#doStartTag()}.
	 * @throws RepositoryException 
	 */
	@Test
	public void testDoStartTagValidHazard() throws RepositoryException {
		fixture.setupAdhocExists();
		fixture.setupChoiceExists();
		fixture.setupChoiceValue();
		fixture.setupChoiceValueContents();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getChoiceHazardText()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(fixture.getChoiceHazardImage()));
	}

	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#getAdhocField()}.
	 */
	@Test
	public void testGetAdhocField() {
		assertThat(resolveHtwTag.getAdhocField(),is((String)null));
		resolveHtwTag.setAdhocField("1");
		assertThat(resolveHtwTag.getAdhocField(),is("1"));
	}

	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#setAdhocField(java.lang.String)}.
	 */
	@Test
	public void testSetAdhocField() {
		resolveHtwTag.setAdhocField("2");
		assertThat(resolveHtwTag.getAdhocField(),is("2"));
	}

	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#getChoiceField()}.
	 */
	@Test
	public void testGetChoiceField() {
		assertThat(resolveHtwTag.getChoiceField(),is((String)null));
		resolveHtwTag.setChoiceField("3");
		assertThat(resolveHtwTag.getChoiceField(),is("3"));
	}

	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#setChoiceField(java.lang.String)}.
	 */
	@Test
	public void testSetChoiceField() {
		resolveHtwTag.setChoiceField("4");
		assertThat(resolveHtwTag.getChoiceField(),is("4"));
	}

	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#getPlaceholder()}.
	 */
	@Test
	public void testGetPlaceholder() {
		assertThat(resolveHtwTag.getPlaceholder(),is((String)null));
		resolveHtwTag.setPlaceholder("5");
		assertThat(resolveHtwTag.getPlaceholder(),is("5"));
	}

	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#setPlaceholder(java.lang.String)}.
	 */
	@Test
	public void testSetPlaceholder() {
		resolveHtwTag.setPlaceholder("6");
		assertThat(resolveHtwTag.getPlaceholder(),is("6"));
	}

	private void setupForHazardTest() {
		resolveHtwTag.setAdhocField(fixture.getHazardAdhocField());
		resolveHtwTag.setChoiceField(fixture.getHazardChoiceField());
		resolveHtwTag.setPlaceholder(fixture.getHazardPlaceholder());
		resolveHtwTag.setPageContext(pageContext);
	}

}
