package com.spd.cq.searspartsdirect.common.foundation;
 
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
 
/**
 * Workflow that generates pages for a Category based on the creation of a Category Asset
 */
@Component
@Service
public class CategoryCreationWorkflowProcess implements WorkflowProcess {
 
	protected static Logger log = LoggerFactory.getLogger(CategoryCreationWorkflowProcess.class);
	
    @Property(value = "A workflow process creates helper pages for Category Assets.")
    static final String DESCRIPTION = org.osgi.framework.Constants.SERVICE_DESCRIPTION; 
    @Property(value = "Sears")
    static final String VENDOR = org.osgi.framework.Constants.SERVICE_VENDOR;
    @Property(value = "Category Creation")
    static final String LABEL="process.label";
 
 
    public static final String TYPE_JCR_PATH = "JCR_PATH";

    public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
    	WorkflowData workflowData = item.getWorkflowData();
        if (workflowData.getPayloadType().equals(TYPE_JCR_PATH)) {
            String path = workflowData.getPayload().toString();
            try {
                Session jcrSession = session.adaptTo(Session.class); 
                Workspace workspace = jcrSession.getWorkspace();
                Node node = (Node) jcrSession.getItem(path);
                
                //CODE
                
            } catch (RepositoryException e) {
                throw new WorkflowException(e.getMessage(), e);
            }
        }
    }
}