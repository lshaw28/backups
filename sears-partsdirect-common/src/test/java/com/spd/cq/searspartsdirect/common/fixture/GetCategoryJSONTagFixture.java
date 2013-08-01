package com.spd.cq.searspartsdirect.common.fixture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

public class GetCategoryJSONTagFixture {

	private StringBuilder snoop;
	private ResourceResolver resourceResolver;
	
	public GetCategoryJSONTagFixture(PageContext pageContext, ResourceResolver resourceResolver) throws IOException {
		this.resourceResolver = resourceResolver;
		JspWriter fakeWriter = mock(JspWriter.class);
		when(pageContext.getOut()).thenReturn(fakeWriter);
		snoop = new StringBuilder();
		when(fakeWriter.append(any(String.class))).thenAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				String appendum = (String)invocation.getArguments()[0];
				snoop.append(appendum);
				return null;
			}
		});
	}
	
	public String getOutput() {
		return snoop.toString();
	}
	
	public void setUpNResults(int n) throws RepositoryException {
		Node categoriesNode = setUpCategoriesNode();
		final List<Node> results = new ArrayList<Node>();
		when(categoriesNode.getNodes()).thenAnswer(new Answer<NodeIterator>() {
			public NodeIterator answer(InvocationOnMock invocation)
					throws Throwable {
				NodeIterator wrapper = mock(NodeIterator.class);
				final Iterator<Node> wrapped = results.iterator();
				when(wrapper.hasNext()).thenAnswer(new Answer<Boolean>() {
					public Boolean answer(InvocationOnMock invocation)
							throws Throwable {
						return wrapped.hasNext();
					}
				});
				when(wrapper.next()).thenAnswer(new Answer<Node>() {
					public Node answer(InvocationOnMock invocation)
							throws Throwable {
						return wrapped.next();
					}
				});
				return wrapper;
			}
			
		});
		for (int i = 0; i < n; i++) {
			Node child = mock(Node.class);
			results.add(child);
			Node content = mock(Node.class);
			when(child.getNode("jcr:content")).thenReturn(content);
			when(child.getName()).thenReturn("name"+i);
			Property contentTitleProp = mock(Property.class);
			when(content.getProperty("jcr:title")).thenReturn(contentTitleProp);
			when(contentTitleProp.getString()).thenReturn("Title "+i);
		}
	}

	public void setUpExplodes() throws RepositoryException {
		Node categoriesNode = setUpCategoriesNode();
		when(categoriesNode.getNodes()).thenThrow(new RepositoryException());
	}
	
	private Node setUpCategoriesNode() {
		Resource categoriesResource = mock(Resource.class);
		when(resourceResolver.getResource(Constants.ASSETS_PATH + "/" + Constants.ASSETS_PRODUCT_CATEGORY_PATH)).thenReturn(categoriesResource);
		
		Node categoriesNode = mock(Node.class);
		when(categoriesResource.adaptTo(Node.class)).thenReturn(categoriesNode);
		return categoriesNode;
	}

}
