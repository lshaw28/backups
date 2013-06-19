package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jcr.Node;
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
	
	public ImageDenialWorkflowProcessFixture() {
		item = mock(WorkItem.class);
		session = mock(WorkflowSession.class);
		args = mock(MetaDataMap.class);
		
		WorkflowData workflowData = mock(WorkflowData.class);
		when(item.getWorkflowData()).thenReturn(workflowData);
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

	public WorkItem getItem() {
		return item;
	}

	public void setItem(WorkItem item) {
		this.item = item;
	}

	public WorkflowSession getSession() {
		return session;
	}

	public void setSession(WorkflowSession session) {
		this.session = session;
	}

	public MetaDataMap getArgs() {
		return args;
	}

	public void setArgs(MetaDataMap args) {
		this.args = args;
	}
}
