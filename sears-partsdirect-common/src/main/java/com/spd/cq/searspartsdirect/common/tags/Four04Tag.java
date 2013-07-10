package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.sling.api.auth.Authenticator;
import org.apache.sling.api.auth.NoAuthenticationHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.PathStringUtils;

@SuppressWarnings("serial")
public class Four04Tag extends CQBaseTag {
	
	public static final String INCLUDE_VAR = Constants.ident("mustInclude");
	public static final String REDIRECT_VAR = Constants.ident("mustRedirect");
	
	private final static Pattern targetedExtension = Pattern.compile("\\.(?:html|jsp)$");
	
	protected static Logger log = LoggerFactory.getLogger(Four04Tag.class);
	protected static PathStringUtils psu = new PathStringUtils();
	/* 
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
	  
	*/

	private HttpServletRequest jspRequest = null;
	private HttpServletResponse jspResponse = null;
	
	private int endResult;
	
	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
		jspRequest = (HttpServletRequest) pageContext.getRequest();
		jspResponse = (HttpServletResponse) pageContext.getResponse();
	}
	
	@Override  
	public int doStartTag() {
		Integer scObject = (Integer) jspRequest.getAttribute("javax.servlet.error.status_code");
	    
	    int statusCode = (scObject != null)
	            ? scObject.intValue()
	            : HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	    ((HttpServletResponse) pageContext.getResponse()).setStatus(statusCode);
	    // decide whether to redirect to the (wcm) login page, or to continue as 404
	    if (needsAuthentication(jspRequest) 
	    		&& isBrowserRequest(jspRequest)
	    		&& isAnonymousUser(jspRequest)
	    		) {
	        
	        Authenticator auth = bindings.getSling().getService(Authenticator.class);
	        if (auth != null) {
	            try {
	                // per JSP auth.login(request, response);
	            	pageContext.setAttribute(REDIRECT_VAR,Constants.EMPTY);
		            pageContext.setAttribute(INCLUDE_VAR,Constants.EMPTY);
	                auth.login(jspRequest, jspResponse);
	               
	                // login has been requested, nothing more to do
	                // per JSP, return, so we specify skipping entire remainder
	                endResult = SKIP_PAGE;
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
	    		|| !isTargetedExtension(jspRequest.getRequestURI())
	    		) {  
	    	// per JSP <%@include file="/libs/sling/servlet/errorhandler/default.jsp"%><%
	    	pageContext.setAttribute(REDIRECT_VAR,Constants.EMPTY);
	    	pageContext.setAttribute(INCLUDE_VAR, Constants.CQ_DEFAULT_ERROR_PAGE);
	    } else {
	    	// per JSP response.sendRedirect(error404PageUrl);
	    	pageContext.setAttribute(INCLUDE_VAR,Constants.EMPTY);
	    	pageContext.setAttribute(REDIRECT_VAR, error404PageUrl);
	    }
	    
	    endResult = EVAL_PAGE;
	    return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() {
		return endResult;
	}
    
    boolean isAnonymousUser(HttpServletRequest request) {
        return request.getAuthType() == null
            || request.getRemoteUser() == null;
    }
    
    boolean isBrowserRequest(HttpServletRequest request) {
        // check if user agent contains "Mozilla" or "Opera" or is the Maven SCR plugin
        final String userAgent = request.getHeader("User-Agent");
        return userAgent != null
            && (userAgent.indexOf("Mozilla") > -1
                || userAgent.indexOf("Opera") > -1
                || userAgent.indexOf("Jakarta Commons-HttpClient") > -1); 
    }
    
    boolean isTargetedExtension(String uri) {
    	return targetedExtension.matcher(uri).find();
    }
    
    private final Set<String> authenticatedRequestRoots = initAuthenticatedRoots();
    private final static Set<String> initAuthenticatedRoots() {
    	Set<String> authRoots = new HashSet<String>();
    	// If insufficient refactor to OSGI config load via psu
    	authRoots.add("/libs/sling/install");
    	authRoots.add("/welcome");
    	authRoots.add("/system");
    	authRoots.add("/libs/cq/core/content");
    	return authRoots;
    }
    
    boolean needsAuthentication(HttpServletRequest request) {
    	final String requestUri = request.getRequestURI();
    	boolean requiresAuth = psu.hasAnyParentIn(authenticatedRequestRoots,requestUri);
    	return requiresAuth;
    }
}
