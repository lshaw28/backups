package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.spd.cq.searspartsdirect.common.foundation.ImageDenialWorkflowProcess;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class ImageDenialWorkflowProcessFixture {
	
	private WorkItem item;
	private WorkflowSession session;
	private MetaDataMap args;
	private WorkflowData workflowData;
	
	public ImageDenialWorkflowProcessFixture() {
		item = mock(WorkItem.class);
		session = mock(WorkflowSession.class);
		args = mock(MetaDataMap.class);
		
		workflowData = mock(WorkflowData.class);
		when(item.getWorkflowData()).thenReturn(workflowData);
	}

	public void setUpCorrectPayload() {
		when(workflowData.getPayloadType()).thenReturn(ImageDenialWorkflowProcess.TYPE_JCR_PATH);
		String payload = "payload";
		when(workflowData.getPayload()).thenReturn(payload);
		Session jcrSession = mock(Session.class);
		when(session.adaptTo(Session.class)).thenReturn(jcrSession);
		Workspace workspace = mock(Workspace.class);
		when(jcrSession.getWorkspace()).thenReturn(workspace);
		Node node = mock(Node.class);
		try {
			when(node.getPath()).thenReturn(Constants.DAM_PENDING_APPROVAL_PATH + "/some/destination");
			when(jcrSession.getItem(payload.toString())).thenReturn(node);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setUpIncorrectPayload() {
		when(workflowData.getPayloadType()).thenReturn("Not Type JCR Path");
	}
	
	public void setUpNullPayload() {
		when(workflowData.getPayloadType()).thenReturn(ImageDenialWorkflowProcess.TYPE_JCR_PATH);
		String payload = "payload";
		when(workflowData.getPayload()).thenReturn(payload);
		Session jcrSession = mock(Session.class);
		when(session.adaptTo(Session.class)).thenReturn(jcrSession);
		Workspace workspace = mock(Workspace.class);
		when(jcrSession.getWorkspace()).thenReturn(workspace);
		try {
			when(jcrSession.getItem(payload.toString())).thenReturn(null);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setUpExceptions() {
		when(workflowData.getPayloadType()).thenReturn(ImageDenialWorkflowProcess.TYPE_JCR_PATH);
		String payload = "payload";
		when(workflowData.getPayload()).thenReturn(payload);
		Session jcrSession = mock(Session.class);
		when(session.adaptTo(Session.class)).thenReturn(jcrSession);
		Workspace workspace = mock(Workspace.class);
		when(jcrSession.getWorkspace()).thenReturn(workspace);
		Node node = mock(Node.class);
		try {
			when(node.getPath()).thenThrow(new RepositoryException());
			when(jcrSession.getItem(payload.toString())).thenReturn(node);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public WorkItem getItem() {
		return item;
	}
	
	public WorkflowSession getSession() {
		return session;
	}

	public MetaDataMap getArgs() {
		return args;
	}
}
