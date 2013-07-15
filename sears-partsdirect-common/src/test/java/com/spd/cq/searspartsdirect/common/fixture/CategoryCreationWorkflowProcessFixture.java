package com.spd.cq.searspartsdirect.common.fixture;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import com.spd.cq.searspartsdirect.common.foundation.CategoryCreationWorkflowProcess;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.mockito.Mockito.*;

public class CategoryCreationWorkflowProcessFixture {

	private WorkItem workItem;
	private WorkflowSession session;
	private Session jcrSession;
	private MetaDataMap args;
	private PageManager pageManager;
	private ResourceResolverFactory resourceResolverFactory;
	
	public CategoryCreationWorkflowProcessFixture() throws WCMException, LoginException {
		pageManager = mock(PageManager.class);
		when(pageManager.create(anyString(), anyString(), anyString(), anyString())).thenAnswer(new Answer<Page>() {
			public Page answer(InvocationOnMock invocation) throws Throwable {
				//Node pageContents = page.getContentResource().adaptTo(Node.class);
				Page page = mock(Page.class);
				Resource pageContent = mock(Resource.class);
				when(page.getContentResource()).thenReturn(pageContent);
				Node contentNode = mock(Node.class);
				when(pageContent.adaptTo(Node.class)).thenReturn(contentNode);
				return page;
			}
		});
		
		resourceResolverFactory = mock(ResourceResolverFactory.class);
		ResourceResolver resourceResolver = mock(ResourceResolver.class);
		when(resourceResolverFactory.getAdministrativeResourceResolver(null)).thenReturn(resourceResolver);
		when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		
		session = mock(WorkflowSession.class);
		jcrSession = mock(Session.class);
		when(session.adaptTo(Session.class)).thenReturn(jcrSession);
		
		
	}
	
	public void setUpPayloadWrong() {
		workItem = createPayload();
		WorkflowData data = workItem.getWorkflowData();
		when(data.getPayloadType()).thenReturn("irrelevant");
	}
	
	public void setUpPayloadComplete(String catName) throws PathNotFoundException, RepositoryException {
		workItem = createPayload();
		WorkflowData data = workItem.getWorkflowData();
		when(data.getPayloadType()).thenReturn(CategoryCreationWorkflowProcess.TYPE_JCR_PATH);
		String payloadPath = Constants.ASSETS_PATH + "/" + Constants.ASSETS_PRODUCT_CATEGORY_PATH + "/" + catName;
		when(data.getPayload()).thenReturn(payloadPath);
		Node workNode = mock(Node.class);
		when(jcrSession.getItem(payloadPath)).thenReturn(workNode);
		when(workNode.getPath()).thenReturn(payloadPath);
		//String categoryTitle = workNode.getNode("jcr:content").getProperty("jcr:title").getString();
		Node contentNode = mock(Node.class);
		when(workNode.getNode("jcr:content")).thenReturn(contentNode);
		Property titleProperty = mock(Property.class);
		when(contentNode.getProperty("jcr:title")).thenReturn(titleProperty);
		when(titleProperty.getString()).thenReturn(catName.substring(0,1).toUpperCase()+catName.substring(1));
	}
	
	public void setUpPagesAlreadyExist() {
		Page existingPage = mock(Page.class);
		when(pageManager.getPage(anyString())).thenReturn(existingPage);
	}

	public WorkItem getWorkItem() {
		return workItem;
	}

	public WorkflowSession getSession() {
		return session;
	}

	public MetaDataMap getArgs() {
		return args;
	}

	public PageManager getPageManager() {
		return pageManager;
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getRelations() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ResourceResolverFactory getResourceResolverFactory() {
		return resourceResolverFactory;
	}

	private WorkItem createPayload() {
		WorkItem workItem = mock(WorkItem.class);
		WorkflowData workflowData = mock(WorkflowData.class);
		when(workItem.getWorkflowData()).thenReturn(workflowData);
		return workItem;
	}

}
