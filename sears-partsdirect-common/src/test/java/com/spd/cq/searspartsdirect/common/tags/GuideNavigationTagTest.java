package com.spd.cq.searspartsdirect.common.tags;

import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyType;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.nodetype.PropertyDefinition;

import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
		
		Session jcrSession = mock(Session.class);
		when(pageNode.getSession()).thenReturn(jcrSession);
		when(pageNode.hasNode(Constants.GUIDE_NAV_PATH)).thenReturn(true);
		setupNode = mock(Node.class);
		when(setupNode.hasProperty(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR)).thenReturn(true);
		sectionsProperty = mock(Property.class);
		when(setupNode.getProperty(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR)).thenReturn(sectionsProperty);
		when(sectionsProperty.isNew()).thenReturn(true);
		PropertyDefinition sectionsPropDefinition = mock(PropertyDefinition.class);
		when(sectionsPropDefinition.isMultiple()).thenReturn(true);
		when(sectionsProperty.getDefinition()).thenReturn(sectionsPropDefinition);
		// We need to give the sectionsProperty mock some memory.
		// Saying isNew above means the tag will try to write initial setup here
		class OneStringArrayHolder {
			public String[] held = null;
		}
		final OneStringArrayHolder sectionsHolder = new OneStringArrayHolder();
		when(setupNode.setProperty(
					eq(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR),
					Mockito.any(String[].class),
					eq(PropertyType.STRING))
				).thenAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				sectionsHolder.held = (String[])arguments[1];
				return null;
			}	
		});
		when(sectionsProperty.getValues()).thenAnswer(new Answer<Value[]>(){
			public Value[] answer(InvocationOnMock invocation) throws Throwable {
				Value[] derValues = new Value[sectionsHolder.held.length];
				for (int i = 0; i < sectionsHolder.held.length; i++) {
					Value wrapper = mock(Value.class); 
					when(wrapper.getString()).thenReturn(sectionsHolder.held[i]);
					derValues[i] = wrapper;
				}
				return derValues;
			}
		});
		
		when(pageNode.getNode(Constants.GUIDE_NAV_PATH)).thenReturn(setupNode);
		jumpProperty = mock(Property.class);
		when(pageNode.getProperty(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR)).thenReturn(jumpProperty);
		when(jumpProperty.getString()).thenReturn("Jump to...");
		
		Resource parsysResource = mock(Resource.class);
		when(currentPage.getContentResource(Constants.GUIDE_TOP_PARSYS_NAME)).thenReturn(parsysResource);
		@SuppressWarnings("unchecked")
		Iterator<Resource> parsysChildren = mock(Iterator.class);
		when(parsysResource.listChildren()).thenReturn(parsysChildren);
		
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
