package com.spd.cq.searspartsdirect.common.fixture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
import javax.jcr.nodetype.PropertyDefinition;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.adobe.cq.social.commons.CommentSystem;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.WCMMode;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.tags.GuideNavigationTag;

import static org.mockito.Mockito.*;

public class GuideNavigationTagFixture {
	
	private final static String jumpToValue = "Jump to...";
	private final static String testPagePath = "/content/searspartsdirect/en/test";
	private final static String h2Contents = "Before you begin";
	
	private Page currentPage;
	private SlingHttpServletRequest slingRequest;
	
	private Resource pageContentResource;
	private Node pageNode;
	private Node setupNode;
	private Property sectionsProperty;
	private Property jumpProperty;
	private OneStringArrayHolder sectionsHolder;
	
	
	public GuideNavigationTagFixture(Page currentPage, SlingHttpServletRequest slingRequest, ResourceResolver resourceResolver) throws Exception {
		this.currentPage = currentPage;
		this.slingRequest = slingRequest;
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
		
		sectionsHolder = new OneStringArrayHolder();
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
				return valuesFromStringArray(sectionsHolder.held);
			}
		});
		
		when(pageNode.getNode(Constants.GUIDE_NAV_PATH)).thenReturn(setupNode);
		jumpProperty = mock(Property.class);
		when(pageNode.getProperty(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR)).thenReturn(jumpProperty);
		when(jumpProperty.getString()).thenReturn(getJumpToValue());
		// following is what WCMMode.fromRequest looks at
		when(slingRequest.getAttribute("com.day.cq.wcm.api.WCMMode")).thenReturn(WCMMode.EDIT);
		
		Resource textResource = mock(Resource.class);
		when(currentPage.getContentResource(GuideNavigationTag.BEFORE_YOU_BEGIN)).thenReturn(textResource);
		when(textResource.getResourceType()).thenReturn(Constants.TEXT_COMPONENT);
		Node textNode = mock(Node.class);
		when(textResource.adaptTo(Node.class)).thenReturn(textNode);
		Property textProperty = mock(Property.class);
		when(textNode.getProperty(Constants.GUIDE_TEXT_LABEL_PROP)).thenReturn(textProperty);
		when(textProperty.getString()).thenReturn("<h2>"+getTextHeader()+"</h2><p>Read the source code</p>");
		
		Resource commentsResource = mock(Resource.class);
		when(resourceResolver.resolve(Constants.USERGEN_ROOT+getCurrentPagePath()+Constants.GUIDE_COMMENTS_PATH)).thenReturn(commentsResource);
		CommentSystem commentSystem = mock(CommentSystem.class);
		when(commentsResource.adaptTo(CommentSystem.class)).thenReturn(commentSystem);
		when(commentSystem.countComments()).thenReturn(getCommentCount());
	}
	
	public Resource getExistingResource() {
		return mock(Resource.class);
	}

	public Resource getNonExistingResource() {
		Resource nonexistent = getExistingResource();
		when(nonexistent.isResourceType(Resource.RESOURCE_TYPE_NON_EXISTING)).thenReturn(true);
		return nonexistent;
	}
	
	public void breakJumpText() throws ValueFormatException, RepositoryException {
		when(jumpProperty.getString()).thenThrow(new RepositoryException());
	}
	
	public void setupNoSetupProperty() throws RepositoryException {
		when(setupNode.hasProperty(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR)).thenReturn(false);
	}
	
	public void setupNoSetupNode() throws PathNotFoundException, RepositoryException {
		when(pageNode.hasNode(Constants.GUIDE_NAV_PATH)).thenReturn(false);
		when(pageNode.addNode(Constants.GUIDE_NAV_PATH,Constants.UNSTRUCTURED)).thenReturn(setupNode);
	}
	
	public void setupCannotSetupDisabled() {
		when(slingRequest.getAttribute("com.day.cq.wcm.api.WCMMode")).thenReturn(WCMMode.DISABLED);
	}
	
	public void setupCannotSetupReadonly() {
		when(slingRequest.getAttribute("com.day.cq.wcm.api.WCMMode")).thenReturn(WCMMode.READ_ONLY);
	}
	
	public void setupAlreadySetUp() {
		when(sectionsProperty.isNew()).thenReturn(false);
	}
	
	public void setupBlankLabels() throws ValueFormatException, IllegalStateException, RepositoryException {
		setupAlreadySetUp();
		sectionsHolder.held = new String[]{
				"{\"link\":\""+Constants.EMPTY+"\",\"resType\":\""+Constants.PARTS_REQ_R_COMPONENT+"\"}",
				"{\"link\":\""+Constants.EMPTY+"\",\"resType\":\""+Constants.TOOLS_REQ_R_COMPONENT+"\"}",
				"{\"link\":\""+Constants.EMPTY+"\",\"resType\":\""+Constants.TEXT_COMPONENT+"\"}",
				"{\"link\":\""+Constants.EMPTY+"\",\"resType\":\""+Constants.INSTRUCTIONS_COMPONENT+"\"}",
				"{\"link\":\""+Constants.EMPTY+"\",\"resType\":\""+Constants.COMMENTS_COMPONENT+"\"}",
			};
	}
	
	public void setupNoPageNode() {
		when(pageContentResource.adaptTo(Node.class)).thenReturn(null);
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
		return 301;
	}

	private static Value[] valuesFromStringArray(String[] strings) throws ValueFormatException, IllegalStateException, RepositoryException {
		Value[] derValues = new Value[strings.length];
		for (int i = 0; i < strings.length; i++) {
			Value wrapper = mock(Value.class); 
			when(wrapper.getString()).thenReturn(strings[i]);
			derValues[i] = wrapper;
		}
		return derValues;
	}
	
	private static class OneStringArrayHolder {
		public String[] held = null;
	}
}
