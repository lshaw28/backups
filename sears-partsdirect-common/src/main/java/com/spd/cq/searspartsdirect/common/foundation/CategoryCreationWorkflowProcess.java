package com.spd.cq.searspartsdirect.common.foundation;
 
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
 
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
    
    @Reference
    private ResourceResolverFactory resourceResolverFactory;
    
    public static final String TYPE_JCR_PATH = "JCR_PATH";

    public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
    	Session jcrSession = null;
    	ResourceResolver resourceResolver = null;
    	try {
        	WorkflowData workflowData = item.getWorkflowData();
	        if (workflowData.getPayloadType().equals(TYPE_JCR_PATH)) {
	            String path = workflowData.getPayload().toString();
                jcrSession = session.adaptTo(Session.class);
                resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
                PageManager pm = resourceResolver.adaptTo(PageManager.class);
                Node node = (Node) jcrSession.getItem(path);
                
                String nodePath = node.getPath();
                String categoryTrueName = nodePath.substring(nodePath.lastIndexOf("/") + 1);
                String categoryTitle = node.getNode("jcr:content").getProperty("jcr:title").getString();
                String[] tag = new String[1];
                tag[0] = node.getPath();
                
                createPage(pm, "/content/searspartsdirect/en/categories", categoryTrueName + "-repair", "/apps/searspartsdirect/templates/category", categoryTitle, tag);
                createPage(pm, "/content/searspartsdirect/en/categories/" + categoryTrueName + "-repair", "repair-articles", "/apps/searspartsdirect/templates/articleIndex", "Article Index", tag);
                createPage(pm, "/content/searspartsdirect/en/categories/" + categoryTrueName + "-repair", "symptom", "/apps/searspartsdirect/templates/categorySymptom", "Symptoms", tag);
                createPage(pm, "/content/searspartsdirect/en/categories/" + categoryTrueName + "-repair", "error-codes", "/apps/searspartsdirect/templates/errorCodes", "Error Codes", tag);
                createPage(pm, "/content/searspartsdirect/en/categories/" + categoryTrueName + "-repair", "repair-guides", "/apps/searspartsdirect/templates/categoryGuideList", categoryTitle + " Guide List", tag);
                createPage(pm, "/content/searspartsdirect/en/categories/" + categoryTrueName + "-repair", categoryTrueName+"-common-parts", "/apps/searspartsdirect/templates/commonParts", categoryTitle + " Common Parts", tag);
                jcrSession.save();
	        }
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            throw new WorkflowException(e);
        }
    }
    
    void setTestResourceResolverFactory(ResourceResolverFactory resourceResolverFactory) {
    	this.resourceResolverFactory = resourceResolverFactory;
    }
    
    public void createPage(PageManager pm, String path, String name, String template, String title, String[] relation) throws WCMException, RepositoryException {
    	Page page = pm.getPage(path + "/" + name);
    	if (page == null) {
	    	page = pm.create(path, name, template, title);
	        Node pageContents = page.getContentResource().adaptTo(Node.class);
	        pageContents.setProperty("pages", relation);
    	}
    }
}