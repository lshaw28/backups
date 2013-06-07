package com.spd.cq.searspartsdirect.common.tags;

import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;

import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class GuideNavigationTagTest extends MocksTag {

	private Resource pageContentResource;
	private Node pageNode;
	private Node setupNode;
	private Property sectionsProperty;
	private Property jumpProperty;
	
	private GuideNavigationTag guideNavigationTag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		
		pageContentResource = mock(Resource.class);
		when(currentPage.getContentResource()).thenReturn(pageContentResource);
		pageNode = mock(Node.class);
		when(pageContentResource.adaptTo(Node.class)).thenReturn(pageNode);
		when(pageNode.hasNode(Constants.GUIDE_NAV_PATH)).thenReturn(true);
		setupNode = mock(Node.class);
		when(setupNode.hasProperty("sections")).thenReturn(true);
		sectionsProperty = mock(Property.class);
		when(setupNode.getProperty("sections")).thenReturn(sectionsProperty);
		when(sectionsProperty.isNew()).thenReturn(false);
		when(pageNode.getNode(Constants.GUIDE_NAV_PATH)).thenReturn(setupNode);
		jumpProperty = mock(Property.class);
		when(pageNode.getProperty(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR)).thenReturn(jumpProperty);
		when(jumpProperty.getString()).thenReturn("Jump to...");
		
		guideNavigationTag = new GuideNavigationTag();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDoStartTag() {
		try {
			guideNavigationTag.setPageContext(pageContext);
			guideNavigationTag.doStartTag();
			assertThat((String)pageContext.getAttribute(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR),is("Jump to..."));
			assertThat((List<List<String>>)pageContext.getAttribute(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR),instanceOf(List.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
