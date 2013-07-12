package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class ResolveHtwFixture {
	
	private ResourceResolver resourceResolver;
	private Node currentNode;
	
	private Property adhocProperty;
	private Property choiceProperty;
	private Resource choiceResource;
	private Node choiceNode;
	private Property choiceTitleProperty;
	private Node choiceImageNode;

	public ResolveHtwFixture(ResourceResolver resourceResolver, Node currentNode) {
		this.resourceResolver = resourceResolver;
		this.currentNode = currentNode;
		adhocProperty = mock(Property.class);
		choiceProperty = mock(Property.class);
		choiceResource = mock(Resource.class);
		choiceNode = mock(Node.class);
		choiceTitleProperty = mock(Property.class);
		choiceImageNode = mock(Node.class);
	}
	
	
	
	public void setupAdhocExists() throws PathNotFoundException, RepositoryException {
		when(currentNode.getProperty(getHazardAdhocField())).thenReturn(adhocProperty);
		when(currentNode.hasProperty(getHazardAdhocField())).thenReturn(true);
	}
	
	public void setupAdhocValue() throws ValueFormatException, RepositoryException {
		when(adhocProperty.getString()).thenReturn(getAdhocHazardText());
	}
	
	public void setupAdhocEmpty() throws ValueFormatException, RepositoryException {
		when(adhocProperty.getString()).thenReturn(Constants.EMPTY);
	}
	
	public void setupAdhocExplodes() throws ValueFormatException, RepositoryException {
		when(adhocProperty.getString()).thenThrow(new RepositoryException());
	}
	
	public void setupChoiceExists() throws PathNotFoundException, RepositoryException {
		when(currentNode.getProperty(getHazardChoiceField())).thenReturn(choiceProperty);
		when(currentNode.hasProperty(getHazardChoiceField())).thenReturn(true);
	}
	
	public void setupChoiceValue() throws ValueFormatException, RepositoryException {
		when(choiceProperty.getString()).thenReturn(getChoiceValue());
	}
	
	public void setupChoiceValueContents() throws RepositoryException {
		when(resourceResolver.resolve(getChoiceValue())).thenReturn(choiceResource);
		when(choiceResource.adaptTo(Node.class)).thenReturn(choiceNode);
		when(choiceNode.getPath()).thenReturn(getChoiceValue());
		when(choiceNode.hasProperty(Constants.ASSETS_TITLE_REL_PATH)).thenReturn(true);
		when(choiceNode.getProperty(Constants.ASSETS_TITLE_REL_PATH)).thenReturn(choiceTitleProperty);
		when(choiceTitleProperty.getString()).thenReturn(getChoiceHazardText());
		when(choiceNode.hasNode(Constants.ASSETS_IMAGE_REL_PATH)).thenReturn(true);
		when(choiceNode.getNode(Constants.ASSETS_IMAGE_REL_PATH)).thenReturn(choiceImageNode);
	}
	
	public void setupChoiceValueEmpty() throws RepositoryException {
		when(resourceResolver.resolve(getChoiceValue())).thenReturn(choiceResource);
		when(choiceResource.adaptTo(Node.class)).thenReturn(choiceNode);
		when(choiceNode.getPath()).thenReturn(getChoiceValue());
		when(choiceNode.hasProperty(Constants.ASSETS_TITLE_REL_PATH)).thenReturn(false);
		when(choiceNode.hasNode(Constants.ASSETS_IMAGE_REL_PATH)).thenReturn(false);
	}
	
	public void setupChoiceExplodes() throws ValueFormatException, RepositoryException {
		when(choiceProperty.getString()).thenThrow(new RepositoryException());
	}
	
	public String getHazardAdhocField() {
		return "hazardTextEntered";
	}
	public String getHazardChoiceField() {
		return "hazardChosen";
	}
	public String getHazardPlaceholder() {
		return "NO HAZARD FOUND.";
	}
	public String getAdhocHazardText() {
		return "This is unexpectedly hazardous.";
	}
	public String getChoiceValue() {
		return "/etc/fakepath";
	}
	public String getChoiceHazardText() {
		return "This is predictably hazardous.";
	}
	public String getChoiceHazardImage() {
		return "/etc/fakepath/jcr:content/image";
	}



	

	

	
}
