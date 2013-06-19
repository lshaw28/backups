package com.spd.cq.searspartsdirect.common.fixture;

import javax.jcr.Item;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.mockito.Mockito.*;

public class ImageApprovalWorkflowProcessFixture {
	private WorkItem item;
	private WorkflowSession session;
	private MetaDataMap args;
	
	private WorkflowData workflowData;
	private Session jcrSession;

	private final static String TYPE_JCR_PATH = "JCR_PATH"; // could move out of both, or not
	private final static String PATH = Constants.DAM_PENDING_APPROVAL_PATH + "/anAsset";
	
	public ImageApprovalWorkflowProcessFixture() {
		item = mock(WorkItem.class);
		session = mock(WorkflowSession.class);
		args = mock(MetaDataMap.class);
		
		// WorkflowData workflowData = item.getWorkflowData();
		workflowData = mock(WorkflowData.class);
		when(item.getWorkflowData()).thenReturn(workflowData);
	}

	public void setUpWrongPayloadType() {
		// if (workflowData.getPayloadType().equals(TYPE_JCR_PATH)) {
		when(workflowData.getPayloadType()).thenReturn(Constants.EMPTY);
	}
	
	private void setUpMinConditions() {
		// if (workflowData.getPayloadType().equals(TYPE_JCR_PATH)) {
		when(workflowData.getPayloadType()).thenReturn(TYPE_JCR_PATH);
		// String path = workflowData.getPayload().toString();
		Object payload = mock(Object.class);
		when(workflowData.getPayload()).thenReturn(payload);
		when(payload.toString()).thenReturn(PATH);
		//Session jcrSession = session.adaptTo(Session.class);
		jcrSession = mock(Session.class);
		when(session.adaptTo(Session.class)).thenReturn(jcrSession);
        //Workspace workspace = jcrSession.getWorkspace();
		Workspace workspace = mock(Workspace.class);
		when(jcrSession.getWorkspace()).thenReturn(workspace);
	}
	
	public void setUpFullTest() throws RepositoryException {
		setUpMinConditions();
		Node node = mock(Node.class);
		when(node.getPath()).thenReturn(PATH);
		when(jcrSession.getItem(PATH)).thenReturn(node);
	}

	public void setUpNoSuchNode() {
		setUpMinConditions();
	}
	
	public void setUpException() throws PathNotFoundException, RepositoryException {
		setUpMinConditions();
		when(jcrSession.getItem(PATH)).thenThrow(new RepositoryException());
	}
	
	public WorkItem getTestItem() {
		return item;
	}

	public WorkflowSession getTestSession() {
		return session;
	}

	public MetaDataMap getArgs() {
		return args;
	}

	
}
