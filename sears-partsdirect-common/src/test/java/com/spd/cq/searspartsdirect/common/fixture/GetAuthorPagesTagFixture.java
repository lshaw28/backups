package com.spd.cq.searspartsdirect.common.fixture;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.mockito.Mockito.*;

public class GetAuthorPagesTagFixture {

	private Node authorsNode;
	private Property authorsProp;
	private PageManager pageManager;
	private Page authorPage;
	private ValueMap authorProps;
	private ResourceResolver resourceResolver;
	
	public GetAuthorPagesTagFixture(PageContext pageContext, PageManager pageManager, ResourceResolver resourceResolver) throws PathNotFoundException, RepositoryException {
		authorsNode = mock(Node.class);
		when(pageContext.findAttribute("currentNode")).thenReturn(authorsNode);
		authorsProp = mock(Property.class);
		when(authorsNode.getProperty("authors")).thenReturn(authorsProp);
		Value[] empty = new Value[0];
		when(authorsProp.getValues()).thenReturn(empty);
		this.pageManager = pageManager;
		this.resourceResolver = resourceResolver;
	}

	public void setUpAnAuthorInArray(String authorName) throws ValueFormatException, IllegalStateException, RepositoryException {
		Value[] oneAuthor = new Value[1];
		oneAuthor[0] = mock(Value.class);
		when(oneAuthor[0].getString()).thenReturn(authorName);
		when(authorsProp.getValues()).thenReturn(oneAuthor);
		//Page p = pageManager.getPage(path.getString());
		authorPage = mock(Page.class);
		when(pageManager.getPage(authorName)).thenReturn(authorPage);
		when(authorPage.getPath()).thenReturn(authorName);
		authorProps = mock(ValueMap.class);
		when(authorPage.getProperties()).thenReturn(authorProps);
	}

	public Node setUpEmptyImage() {
		String imagePath = authorPage.getPath() + Constants.ASSETS_IMAGE_PATH;
		Resource imageResource = mock(Resource.class);
		when(resourceResolver.getResource(imagePath)).thenReturn(imageResource);
		Node imageNode = mock(Node.class);
		when(imageResource.adaptTo(Node.class)).thenReturn(imageNode);
		return imageNode;
	}

	public void setUpAbstract(String text) {
		when(authorProps.containsKey("abstracttext")).thenReturn(true);
		when(authorProps.get("abstracttext")).thenReturn(text);
	}

	public void setUpFileImage() throws RepositoryException {
		Node imageNode = setUpEmptyImage();
		when(imageNode.hasNode("file")).thenReturn(true);
	}
	
	public void setUpFileReferenceImage() throws RepositoryException {
		Node imageNode = setUpEmptyImage();
		when(imageNode.hasProperty("fileReference")).thenReturn(true);
	}

	public void setUpExplodesVfe() throws ValueFormatException, RepositoryException {
		when(authorsProp.getValues()).thenThrow(new ValueFormatException());
	}

	public void setUpExplodesPnfe() throws PathNotFoundException, RepositoryException {
		when(authorsNode.getProperty("authors")).thenThrow(new PathNotFoundException());
	}

	public void setUpExplodesRe() throws PathNotFoundException, RepositoryException {
		when(authorsNode.getProperty("authors")).thenThrow(new RepositoryException());
	}

}
