package com.spd.cq.searspartsdirect.common.fixture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyType;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.nodetype.PropertyDefinition;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.hamcrest.Matcher;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.adobe.cq.social.commons.CommentSystem;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.WCMMode;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.mockito.Mockito.*;

public class GuideNavigationTagFixture {
	
	private final static String jumpToValue = "Jump to...";
	private final static String testPagePath = "/content/searspartsdirect/en/test";
	private final static String h2Contents = "Before you begin";
	private Resource pageContentResource;
	
	private Node pageNode;
	private Node setupNode;
	private Property sectionsProperty;
	private Property jumpProperty;
	
	
	public GuideNavigationTagFixture(Page currentPage, SlingHttpServletRequest slingRequest, ResourceResolver resourceResolver) throws Exception {
		pageContentResource = mock(Resource.class);
		when(currentPage.getContentResource()).thenReturn(pageContentResource);
		when(currentPage.getPath()).thenReturn(getCurrentPagePath());
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
		when(jumpProperty.getString()).thenReturn(getJumpToValue());
		/* // included as example. will remove on next commit.
		when(slingRequest.getAttribute(any(String.class))).thenAnswer(new Answer<Void>() {

			public Void answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				throw new RuntimeException((String)invocation.getArguments()[0]);
			}
			
		});
		*/
		when(slingRequest.getAttribute("com.day.cq.wcm.api.WCMMode")).thenReturn(WCMMode.EDIT);
		Resource parsysResource = mock(Resource.class);
		when(currentPage.getContentResource(Constants.GUIDE_TOP_PARSYS_NAME)).thenReturn(parsysResource);
		final List<Resource> parsysChildren = new ArrayList<Resource>();
		Resource instructionsComponent = mock(Resource.class);
		when(instructionsComponent.getResourceType()).thenReturn(Constants.INSTRUCTIONS_COMPONENT);
		parsysChildren.add(instructionsComponent);
		Resource textResource = mock(Resource.class);
		when(textResource.getResourceType()).thenReturn(Constants.TEXT_COMPONENT);
		Node textNode = mock(Node.class);
		when(textResource.adaptTo(Node.class)).thenReturn(textNode);
		Property textProperty = mock(Property.class);
		when(textNode.getProperty(Constants.GUIDE_TEXT_LABEL_PROP)).thenReturn(textProperty);
		when(textProperty.getString()).thenReturn("<h2>"+getTextHeader()+"</h2><p>Read the source code</p>");
		parsysChildren.add(textResource);
		when(parsysResource.listChildren()).thenAnswer(new Answer<Iterator<Resource>>() {

			public Iterator<Resource> answer(InvocationOnMock invocation)
					throws Throwable {
				return parsysChildren.iterator();
			}
			
		});
		Resource commentsResource = mock(Resource.class);
		when(resourceResolver.resolve(Constants.USERGEN_ROOT+getCurrentPagePath()+Constants.GUIDE_COMMENTS_PATH)).thenReturn(commentsResource);
		CommentSystem commentSystem = mock(CommentSystem.class);
		when(commentsResource.adaptTo(CommentSystem.class)).thenReturn(commentSystem);
		when(commentSystem.countComments()).thenReturn(getCommentCount());
	}
	
	public String getCurrentPagePath() {
		return testPagePath;
	}
	
	public String getJumpToValue() {
		return jumpToValue;
	}

	public String getTextHeader() {
		return h2Contents;
	}

	public int getCommentCount() {
		// TODO Auto-generated method stub
		return 301;
	}
}
