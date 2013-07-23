package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.tags.GetTopicJumpTag;

public class GetTopicJumpTagFixture {

	private Resource pageContent;
	private List<Resource> pageChildren;
	private List<Resource> parsysChildren;
	
	public GetTopicJumpTagFixture(Page currentPage) {
		pageContent = mock(Resource.class);
		when(currentPage.getContentResource()).thenReturn(pageContent);
		pageChildren = new ArrayList<Resource>();
		when(pageContent.getChildren()).thenAnswer(new Answer<Iterable<Resource>>() {
			public Iterable<Resource> answer(InvocationOnMock invocation)
					throws Throwable {
				return getPageChildren();
			}
		});
	}
	
	public void setupNoContent() {
		pageChildren = new ArrayList<Resource>();
	}
	
	public void setupEmptyParsys() {
		setupNoContent();
		Resource parsys = getParsys();
		pageChildren.add(parsys);
		parsysChildren = new ArrayList<Resource>();
		when(parsys.getChildren()).thenAnswer(new Answer<Iterable<Resource>>() {
			public Iterable<Resource> answer(InvocationOnMock invocation)
					throws Throwable {
				return parsysChildren;
			}
		});
	}
	
	public void setupOneTopic() throws PathNotFoundException, RepositoryException {
		setupEmptyParsys();
		pageChildren.add(getTopic("foo")); // wrong place, to cover a branch
		parsysChildren.add(getTopic("foo"));
		parsysChildren.add(getParsys()); // wrong place, to cover a branch
	}
	
	public void setupTwoTopics() throws PathNotFoundException, RepositoryException {
		setupOneTopic();
		parsysChildren.add(getTopic("bar"));
	}
	
	public void setupExplodingTopic() throws PathNotFoundException, RepositoryException {
		setupEmptyParsys();
		Resource aTopic = getTopic("foo");
		parsysChildren.add(aTopic);
		Node node = aTopic.adaptTo(Node.class);
		when(node.getProperty(anyString())).thenThrow(new RepositoryException());
	}
	
	public Resource getParsys() {
		Resource parsys = mock(Resource.class);
		when(parsys.getResourceType()).thenReturn(GetTopicJumpTag.PARSYS_TYPE);
		when(parsys.getName()).thenReturn("parsys");
		return parsys;
	}

	public Resource getTopic(String string) throws PathNotFoundException, RepositoryException {
		Resource topic = mock(Resource.class);
		when(topic.getResourceType()).thenReturn(GetTopicJumpTag.TOPIC_TYPE);
		when(topic.getName()).thenReturn(string);
		Node topicNode = mock(Node.class);
		when(topic.adaptTo(Node.class)).thenReturn(topicNode);
		Property titleProp = mock(Property.class);
		when(topicNode.getProperty(GetTopicJumpTag.TITLE_PROP)).thenReturn(titleProp);
		when(titleProp.getString()).thenReturn(string+string+string);
		return topic;
	}

	private List<Resource> getPageChildren() {
		return pageChildren;
	}

}
