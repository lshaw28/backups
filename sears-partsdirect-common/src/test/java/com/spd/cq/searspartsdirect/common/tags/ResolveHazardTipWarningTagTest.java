package com.spd.cq.searspartsdirect.common.tags;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ResolveHtwFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * @author bzethmayr
 *
 */
public class ResolveHazardTipWarningTagTest extends MocksTag {
	
	private ResolveHtwFixture fixture;
	
	private Property adhocProperty;
	private Property choiceProperty;
	private Resource choiceResource;
	private Node choiceNode;
	private Property choiceTitleProperty;
	private Node choiceImageNode;
	
	private ResolveHazardTipWarningTag resolveHtwTag;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ResolveHtwFixture();
		
		adhocProperty = mock(Property.class);
		choiceProperty = mock(Property.class);
		choiceResource = mock(Resource.class);
		choiceNode = mock(Node.class);
		choiceTitleProperty = mock(Property.class);
		choiceImageNode = mock(Node.class);
		
		resolveHtwTag = new ResolveHazardTipWarningTag();
	}

	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#doStartTag()}.
	 */
	@Test
	public void testDoStartTagAdhocInvalidHazard() {
		setupAdhocExists();
		setupAdhocValue();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getAdhocHazardText()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(""));
	}
	
	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#doStartTag()}.
	 */
	@Test
	public void testDoStartTagAdhocValidHazard() {
		setupAdhocExists();
		setupAdhocValue();
		setupChoiceExists();
		setupChoiceValue();
		setupChoiceValueContents();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getAdhocHazardText()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(fixture.getChoiceHazardImage()));
	}
	
	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#doStartTag()}.
	 */
	@Test
	public void testDoStartTagInvalidHazard() {
		setupAdhocExists();
		setupChoiceExists();
		setupChoiceValue();
		setupForHazardTest();
		resolveHtwTag.doStartTag();
		assertThat((String)pageContext.getAttribute("htwText"),is(fixture.getHazardPlaceholder()));
		assertThat((String)pageContext.getAttribute("htwImage"),is(""));
	}
	
	/**
	 * Test method for {@link com.spd.cq.searspartsdirect.common.tags.ResolveHazardTipWarningTag#doStartTag()}.
	 */
	@Test
	public void testDoStartTagValidHazard() {
		setupAdhocExists();
		setupChoiceExists();
		setupChoiceValue();
		setupChoiceValueContents();
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

	private void setupAdhocExists() {
		try {
			when(currentNode.getProperty(fixture.getHazardAdhocField())).thenReturn(adhocProperty);
			when(currentNode.hasProperty(fixture.getHazardAdhocField())).thenReturn(true);
		} catch (RepositoryException re) {
			fail("Mock threw unexpected exception");
		}
	}
	
	private void setupChoiceExists() {
		try {
			when(currentNode.getProperty(fixture.getHazardChoiceField())).thenReturn(choiceProperty);
			when(currentNode.hasProperty(fixture.getHazardChoiceField())).thenReturn(true);
		} catch (RepositoryException re) {
			fail("Mock threw unexpected exception");
		}
	}
	
	private void setupForHazardTest() {
		resolveHtwTag.setAdhocField(fixture.getHazardAdhocField());
		resolveHtwTag.setChoiceField(fixture.getHazardChoiceField());
		resolveHtwTag.setPlaceholder(fixture.getHazardPlaceholder());
		resolveHtwTag.setPageContext(pageContext);
	}
	
	private void setupAdhocValue() {
		try {
			when(adhocProperty.getString()).thenReturn(fixture.getAdhocHazardText());
		} catch (RepositoryException re) {
			fail("Mock threw unexpected exception");
		}
	}
	
	private void setupChoiceValue() {
		try {
			when(choiceProperty.getString()).thenReturn(fixture.getChoiceValue());
		} catch (RepositoryException re) {
			fail("Mock threw unexpected exception");
		}
	}
	
	private void setupChoiceValueContents() {
		when(resourceResolver.resolve(fixture.getChoiceValue())).thenReturn(choiceResource);
		when(choiceResource.adaptTo(Node.class)).thenReturn(choiceNode);
		try {
			when(choiceNode.getPath()).thenReturn(fixture.getChoiceValue());
			when(choiceNode.hasProperty(Constants.ASSETS_TITLE_REL_PATH)).thenReturn(true);
			when(choiceNode.getProperty(Constants.ASSETS_TITLE_REL_PATH)).thenReturn(choiceTitleProperty);
			when(choiceTitleProperty.getString()).thenReturn(fixture.getChoiceHazardText());
			when(choiceNode.hasNode(Constants.ASSETS_IMAGE_REL_PATH)).thenReturn(true);
			when(choiceNode.getNode(Constants.ASSETS_IMAGE_REL_PATH)).thenReturn(choiceImageNode);
		} catch (RepositoryException re) {
			fail("Mock threw unexpected exception.");
		}
	}
}
