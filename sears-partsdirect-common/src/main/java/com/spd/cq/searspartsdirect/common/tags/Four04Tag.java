package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import java.util.HashSet;
import java.util.Set;

import org.apache.sling.api.auth.Authenticator;
import org.apache.sling.api.auth.NoAuthenticationHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.cq.wcm.api.WCMMode;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.PathStringUtils;

@SuppressWarnings("serial")
public class Four04Tag extends CQBaseTag {
	protected static Logger log = LoggerFactory.getLogger(Four04Tag.class);
	protected static PathStringUtils psu = new PathStringUtils();
	/* //<%--
	  Copyright 1997-2008 Day Management AG
	  Barfuesserplatz 6, 4001 Basel, Switzerland
	  All Rights Reserved.

	  This software is the confidential and proprietary information of
	  Day Management AG, ("Confidential Information"). You shall not
	  disclose such Confidential Information and shall use it only in
	  accordance with the terms of the license agreement you entered into
	  with Day.

	  ==============================================================================

	  Generic 404 error handler

	  Important note:  
	  Since Sling uses the user from the request (depending on the authentication
	  handler but typically HTTP basic auth) to login to the repository and JCR/CRX
	  will simply say "resource not found" if the user does not have a right to
	  access a certain node, everything ends up in this 404 handler, both access
	  denied ("401", eg. for non-logged in, anonymous users) and really-not-existing
	  scenarios ("404", eg. logged in, but does not exist in repository).
	  
	*///--%><%
	/*%><%@ page session="false" %><%
	%><%@ page import="
		java.util.Set,
		java.util.HashSet,
	    java.net.URLEncoder,
	    org.slf4j.Logger,
	    org.slf4j.LoggerFactory,
	    org.apache.sling.api.scripting.SlingBindings,
	    org.apache.sling.engine.auth.Authenticator,
	    org.apache.sling.engine.auth.NoAuthenticationHandlerException,
	    com.day.cq.wcm.api.WCMMode,
	    com.ge.cq.gecapitalbank.common.environment.EnvironmentSettings" %><%!*/

	private HttpServletRequest jspRequest = null;
	private HttpServletResponse jspResponse = null;
	
	private int endResult;
	
	@Override
	public void setPageContext(PageContext pageContext) {
		if (log.isDebugEnabled()) log.debug("Setting context for 404");
		super.setPageContext(pageContext);
		jspRequest = (HttpServletRequest) pageContext.getRequest();
		jspResponse = (HttpServletResponse) pageContext.getResponse();
	}
	
	@Override  
	public int doStartTag() {
		if (log.isDebugEnabled()) log.debug("doStartTag called");

		Integer scObject = (Integer) jspRequest.getAttribute("javax.servlet.error.status_code");
	    
	    int statusCode = (scObject != null)
	            ? scObject.intValue()
	            : HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	    ((HttpServletResponse) pageContext.getResponse()).setStatus(statusCode);
	    if (log.isDebugEnabled()) log.debug("statusCode is "+statusCode);
		
		//Default value limits action to paths in our content tree
	    /*
		String errorActionRoot = EnvironmentSettings.getErrorActionRoot();
		if (errorActionRoot == null || errorActionRoot.matches(" *")) {
			errorActionRoot = "/content/gecapitalbank/";
			if (contextPath != null && !"".equals(contextPath)) {
		    	errorActionRoot = contextPath + errorActionRoot;
		 	}
		}
		*/
		//log.debug("errorActionRoot is "+errorActionRoot);
	    if (log.isDebugEnabled()) log.debug("request path is "+jspRequest.getRequestURI());
	    // decide whether to redirect to the (wcm) login page, or to send a plain 404
	    if (needsAuthentication(jspRequest) 
	    		&& isBrowserRequest(jspRequest)
	    		&& isAnonymousUser(jspRequest)
	    		) {
	        
	        //SlingBindings bindings = (SlingBindings) request.getAttribute("org.apache.sling.api.scripting.SlingBindings");
	    	if (log.isDebugEnabled()) log.debug("attempting authentication");
	        Authenticator auth = bindings.getSling().getService(Authenticator.class);
	        if (auth != null) {
	            try {
	                //auth.login(request, response);
	            	pageContext.setAttribute("mustRedirect", "");
		            pageContext.setAttribute("mustInclude","");
	                auth.login(jspRequest, jspResponse);
	               
	                // login has been requested, nothing more to do
	                //return; // when converting this logic to tag, will need to account for this.
	                endResult = SKIP_PAGE;
	                if (log.isDebugEnabled()) log.debug("auth attempted");
	                return SKIP_BODY;
	                
	            } catch (NoAuthenticationHandlerException nahe) {
	                log.warn("Cannot login: No Authentication Handler is willing to authenticate");
	            }
	        } else {
	            log.warn("Cannot login: Missing Authenticator service");
	        }
	        
	    } 
	         
	    // get here if authentication should not take place or if
	    // no Authenticator service is available or if no
	    // AuthenticationHandler is willing to authenticate
	    // So we fall back to our custom 404/NOT FOUND handler
	    
	 	String error404PageUrl = EnvironmentSettings.get404HandlerURL();
	    
	    // We use the page name to prevent a circular redirect
	    String pageName = error404PageUrl.substring(error404PageUrl.lastIndexOf("/"));
	    if (needsAuthentication(jspRequest) 
	    		|| jspRequest.getRequestURI().endsWith(pageName)
	    		) {  
	    	//%><%@include file="/libs/sling/servlet/errorhandler/default.jsp"%><%
	    	pageContext.setAttribute("mustRedirect","");
	    	pageContext.setAttribute("mustInclude", "/libs/sling/servlet/errorhandler/default.jsp");
	
	    } else {
//	        response.sendRedirect(error404PageUrl);
	    	pageContext.setAttribute("mustInclude","");
	    	pageContext.setAttribute("mustRedirect", error404PageUrl);
	    }
	    
	//%>
	    endResult = EVAL_PAGE;
	    return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() {
		if (log.isDebugEnabled()) log.debug("doEndTag called, returning "+endResult);
		return endResult;
	}
    
    private boolean isAnonymousUser(HttpServletRequest request) {
        return request.getAuthType() == null
            || request.getRemoteUser() == null;
    }
    
    private boolean isBrowserRequest(HttpServletRequest request) {
        // check if user agent contains "Mozilla" or "Opera"
        final String userAgent = request.getHeader("User-Agent");
        if (log.isDebugEnabled()) log.debug("userAgent is "+userAgent);
        return userAgent != null
            && (userAgent.indexOf("Mozilla") > -1
                || userAgent.indexOf("Opera") > -1
                || userAgent.indexOf("Jakarta Commons-HttpClient") > -1); 
    }
    
    private final Set<String> authenticatedRequestRoots = initAuthenticatedRoots();
    private final static Set<String> initAuthenticatedRoots() {
    	Set<String> authRoots = new HashSet<String>();
    	// We will just determine this via testing..
    	authRoots.add("/libs/sling/install");
    	authRoots.add("/welcome");
    	authRoots.add("/system");
    	authRoots.add("/libs/cq/core/content");
    	return authRoots;
    }
    
    private boolean needsAuthentication(HttpServletRequest request) {
    	final String requestUri = request.getRequestURI();
    	if (log.isDebugEnabled()) log.debug("they requested "+requestUri);
    	boolean requiresAuth = psu.hasAnyParentIn(authenticatedRequestRoots,requestUri);
    	if (log.isDebugEnabled()) log.debug("we are saying it "+(requiresAuth?"does":"doesn't")+" want authentication");
    	return requiresAuth;
    }
}
