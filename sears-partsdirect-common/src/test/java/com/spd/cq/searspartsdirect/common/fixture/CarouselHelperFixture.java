package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class CarouselHelperFixture {

	private Node node;
	
	public CarouselHelperFixture() {
		setUpEmptyNode();
	}
	
	public void setUpEmptyNode() {
		node = mock(Node.class);
	}
	
	public Node getNode() {
		return node;
	}

	public void setUpPropIsEmpty() throws RepositoryException {
		setUpEmptyNode();
		Property emptyProp = makePrimaryProp();
		// we get isMultiple false for free...
		when(emptyProp.getString()).thenReturn(Constants.EMPTY);
	}
	
	public void setUpPropExplodes() throws PathNotFoundException, RepositoryException {
		Property boom = makePrimaryProp();
		when(boom.isMultiple()).thenThrow(new RepositoryException());
	}

	public void setUpValidSingleProp(String imageName) throws PathNotFoundException, RepositoryException {
		setUpEmptyNode();
		Property singleProp = makePrimaryProp();
		when(singleProp.getString()).thenReturn("{\"one\":\""+imageName+"\"}");
	}
	
	public void setUpValidMultiProp(String... imageName) throws PathNotFoundException, RepositoryException {
		setUpEmptyNode();
		Property multiProp = makePrimaryProp();
		when(multiProp.isMultiple()).thenReturn(true);
		Value[] values = new Value[imageName.length];
		for (int i = 0; i < imageName.length; i++) {
			values[i] = mock(Value.class);
			when(values[i].getString()).thenReturn("{\"image\":\""+imageName[i]+"\"}");
		}
		when(multiProp.getValues()).thenReturn(values);
	}

	private Property makePrimaryProp() throws PathNotFoundException, RepositoryException {
		when(node.hasProperty("primary")).thenReturn(true);
		Property emptyProp = mock(Property.class);
		when(node.getProperty("primary")).thenReturn(emptyProp);
		return emptyProp;
	}

	
}
