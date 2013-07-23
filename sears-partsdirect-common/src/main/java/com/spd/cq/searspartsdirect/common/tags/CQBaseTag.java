package com.spd.cq.searspartsdirect.common.tags;

import javax.jcr.Node;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.slf4j.Logger;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.components.ComponentContext;
import com.day.cq.wcm.api.components.EditContext;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Designer;
import com.day.cq.wcm.api.designer.Style;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

/**
 * Class that has base functionality needed to access CQ features
 * @extends TagSupport
 *
 */
@SuppressWarnings("serial")
public class CQBaseTag extends TagSupport {
	
	
	protected Node currentNode = null;
	protected SlingScriptHelper sling = null;
	protected SlingHttpServletRequest slingRequest = null;
	protected Page currentPage = null;
    
	protected SlingHttpServletResponse slingResponse = null;
	protected SlingBindings bindings = null;
	
	protected Resource resource = null;
	protected ResourceResolver resourceResolver = null;
	protected Logger log = null;
	protected ComponentContext componentContext = null;
	protected EditContext editContext = null;
	protected ValueMap properties = null;
	protected PageManager pageManager = null;
	protected Page resourcePage = null;

	//TODO: figure this out....if we ever need it
	//protected InheritanceValueMap pageProperties = null;  
	
	protected Component component = null;
	protected Designer designer = null;
	protected Design currentDesign = null;
	protected Design resourceDesign = null;
	protected Style currentStyle = null;
	protected HttpServletRequest request = null;
	protected String contextPath = Constants.EMPTY;
	
	@Override
	/**
	 * Set variables to be accessible via Page Context
	 * @param pageContext
	 */
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
		currentNode = (Node) pageContext.findAttribute("currentNode");
		sling = (SlingScriptHelper) pageContext.findAttribute("sling");
		slingRequest = (SlingHttpServletRequest) pageContext.findAttribute("slingRequest");
		slingResponse = (SlingHttpServletResponse) pageContext.findAttribute("slingResponse");
		request = (HttpServletRequest) pageContext.getRequest();

		if (request.getContextPath() != null) {
			contextPath = request.getContextPath() ;
		}	
		
		resource = (Resource) pageContext.findAttribute("resource");
		resourceResolver = (ResourceResolver) pageContext.findAttribute("resourceResolver");
		log = (Logger) pageContext.findAttribute("log");
		bindings = (SlingBindings) pageContext.findAttribute("bindings");
		componentContext = (ComponentContext) pageContext.findAttribute("componentContext");
		editContext = (EditContext) pageContext.findAttribute("editContext");
		properties = (ValueMap) pageContext.findAttribute("properties");
		pageManager = (PageManager) pageContext.findAttribute("pageManager");
		currentPage = (Page) pageContext.findAttribute("currentPage");
		resourcePage = (Page) pageContext.findAttribute("resourcePage");
		component = (Component) pageContext.findAttribute("component");
		designer = (Designer) pageContext.findAttribute("designer");
		currentDesign = (Design) pageContext.findAttribute("currentDesign");
		resourceDesign = (Design) pageContext.findAttribute("resourceDesign");
		currentStyle = (Style) pageContext.findAttribute("currentStyle");		
	}
}	