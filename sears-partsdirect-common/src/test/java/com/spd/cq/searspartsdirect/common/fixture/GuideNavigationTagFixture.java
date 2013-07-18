package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

public class GuideNavigationTagFixture {
	
	private final static String JUMP_TO_VALUE = "Jump to...";
	private final static String TEST_PAGE_PATH = "/content/searspartsdirect/en/test";
	private final static String H2_CONTENTS = "Before you begin";
	
	private Page currentPage;
	private SlingHttpServletRequest slingRequest;
	private ResourceResolver resourceResolver;
	
	private Resource pageContentResource;
	private Node pageNode;
	private Node setupNode;
	private Property sectionsProperty;
	private Property jumpProperty;
	private OneStringArrayHolder sectionsHolder;
	
	
	public GuideNavigationTagFixture(Page currentPage, SlingHttpServletRequest slingRequest, ResourceResolver resourceResolver) throws Exception {
		this.currentPage = currentPage;
		this.slingRequest = slingRequest;
		this.resourceResolver = resourceResolver;
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
	
	public void breakCommentsResource() {
		when(resourceResolver.resolve(Constants.USERGEN_ROOT+getCurrentPagePath()+Constants.GUIDE_COMMENTS_PATH)).thenReturn(null);
		
	}
	
	public void breakCommentsSystem() {
		Resource commentsResource = resourceResolver.resolve(Constants.USERGEN_ROOT+getCurrentPagePath()+Constants.GUIDE_COMMENTS_PATH);
		when(commentsResource.adaptTo(CommentSystem.class)).thenReturn(null);
	}
	
	public void setupSingleTypeAndLabel() throws RepositoryException {
		when(sectionsProperty.isNew()).thenReturn(false);
//		PropertyDefinition sectionsPropDefinition = mock(PropertyDefinition.class);
//		when(sectionsPropDefinition.isMultiple()).thenReturn(true);
//		when(sectionsProperty.getDefinition()).thenReturn(sectionsPropDefinition);
		PropertyDefinition sectionsPropDefinition = sectionsProperty.getDefinition();
		when(sectionsPropDefinition.isMultiple()).thenReturn(false);
	}
	
	public Resource getExistingResource() {
		return mock(Resource.class);
	}

	public Resource getNonExistingResource() {
		Resource nonexistent = getExistingResource();
		when(nonexistent.isResourceType(Resource.RESOURCE_TYPE_NON_EXISTING)).thenReturn(true);
		return nonexistent;
	}
	
	public void removeBeforeYouBeginResource() {
		when(currentPage.getContentResource(GuideNavigationTag.BEFORE_YOU_BEGIN)).thenReturn(null);
	}
	
	public void beforeYouBeginHasNoHeaderNow() throws PathNotFoundException, RepositoryException {
//		when(currentPage.getContentResource(GuideNavigationTag.BEFORE_YOU_BEGIN)).thenReturn(textResource);
//		
//		when(textResource.adaptTo(Node.class)).thenReturn(textNode);
//		
//		when(textNode.getProperty(Constants.GUIDE_TEXT_LABEL_PROP)).thenReturn(textProperty);
//		when(textProperty.getString()).thenReturn("<h2>"+getTextHeader()+"</h2><p>Read the source code</p>");
		Resource textResource = currentPage.getContentResource(GuideNavigationTag.BEFORE_YOU_BEGIN);
		Node textNode = textResource.adaptTo(Node.class);
		Property textProperty = textNode.getProperty(Constants.GUIDE_TEXT_LABEL_PROP);
		when(textProperty.getString()).thenReturn(getTextHeader());
	}
	
	public void setupBYBLabelPropExplodes() throws PathNotFoundException, RepositoryException {
		Resource textResource = currentPage.getContentResource(GuideNavigationTag.BEFORE_YOU_BEGIN);
		Node textNode = textResource.adaptTo(Node.class);
		when(textNode.getProperty(Constants.GUIDE_TEXT_LABEL_PROP)).thenThrow(new PathNotFoundException());
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
	
	public void setupPageNodeGetSessionExplodes() throws RepositoryException {
		when(pageNode.getSession()).thenThrow(new RepositoryException());
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
		return TEST_PAGE_PATH;
	}
	
	public String getJumpToValue() {
		return JUMP_TO_VALUE;
	}

	public String getTextHeader() {
		return H2_CONTENTS;
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
