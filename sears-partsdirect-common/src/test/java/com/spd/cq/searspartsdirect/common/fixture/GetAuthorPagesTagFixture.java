package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetAuthorPagesTagFixture {

	private Page authorPage;
	private ValueMap pageProperties;
	private Property authorsProp;
	private ResourceResolver resourceResolver;
	private ValueMap authorProps;
	private Page currentPage;
	
	public GetAuthorPagesTagFixture(Page currentPage, PageManager pageManager, ResourceResolver resourceResolver) throws ValueFormatException, IllegalStateException, RepositoryException {

		ValueMap pageProperties = mock(ValueMap.class);
		authorsProp = mock(Property.class);
		when(currentPage.getProperties()).thenReturn(pageProperties);
		String authorPathName = "someAuthor";
		Value[] authorValues = new Value[1];
		authorValues[0] = mock(Value.class);
		when(authorValues[0].getString()).thenReturn(authorPathName);
		when(pageProperties.get("authors", new Value[0])).thenReturn(authorValues);
		
		//Page p = pageManager.getPage(path.getString());
		authorPage = mock(Page.class);
		when(pageManager.getPage(authorPathName)).thenReturn(authorPage);
		when(authorPage.getPath()).thenReturn(authorPathName);
		authorProps = mock(ValueMap.class);
		when(authorPage.getProperties()).thenReturn(authorProps);
		this.resourceResolver = resourceResolver;
		this.currentPage = currentPage;

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

	public void setUpExplodes() {
		when(currentPage.getProperties()).thenThrow(new RuntimeException());
	}

}
