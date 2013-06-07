package com.spd.cq.searspartsdirect.common.foundation;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.acegisecurity.ui.cas.CasProcessingFilter;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;

import org.apache.commons.lang.StringUtils;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class CustomCasProcessingFilter extends CasProcessingFilter{

	private static final Logger log = LoggerFactory.getLogger(CustomCasProcessingFilter.class);
	
	public static final String TICKET = "ticket";

	 @Override
	    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	                                            Authentication authResult) throws IOException {
	

		 //****************************************
		 // CALL PD API HERE
		 //****************************************
		 	request.getSession().removeAttribute(Constants.LOGON_PASSWORD_FIELD);
		 	request.getSession().removeAttribute(Constants.LOGIN_ID_FIELD);
		 	request.getSession().removeAttribute(Constants.AUTHENTICATION_FAILURE_URL);
		 	//createCookie(response, user);        
		 	super.successfulAuthentication(request, response, authResult);
	 }
	 
	 @Override
	    protected String determineFailureUrl(HttpServletRequest request, AuthenticationException failed) {
		 String url = super.determineFailureUrl(request, failed);
		//Empty errorCode means that SSO server didn't receive any credentials to validate against
	        String errorCode = request.getParameter(Constants.ERROR_CODE_FIELD);
	        int errorCount = request.getParameter(Constants.ERROR_COUNT_FIELD)== null ? 0:Integer.parseInt(request.getParameter(Constants.ERROR_COUNT_FIELD)) + 1;
	        
	        //SlingHttpServletRequest httpRequest = (SlingHttpServletRequest)request;
	        //If SSO no fail parameter present then go to previous requested page (or default, if session is not available anymore) instead of failing.
	        if (request.getSession().getAttribute(Constants.CAS_NOFAIL_PARAMETER) != null || request.getSession().getAttribute(Constants.AUTHENTICATION_FAILURE_URL) != null
	        		|| StringUtils.isBlank(errorCode)) {
	        	request.getSession().removeAttribute(Constants.CAS_NOFAIL_PARAMETER);
	        	//url = getReturnUrl(request);
	        }
	        	
	        if (!StringUtils.isEmpty(request.getParameter(Constants.ERROR_CODE_FIELD))) {
	            url = url + (url.contains("?") ? "&" : "?") + Constants.ERROR_CODE_FIELD + "=" + errorCode + "&errorCount=" + errorCount;
	        }
		 return url;
	 }
	 
	 @Override
	    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
//		 	SlingHttpServletRequest request = (SlingHttpServletRequest)httpRequest;
//	        SlingHttpServletResponse response = (SlingHttpServletResponse)httpResponse;
		 	request.getSession().removeAttribute(Constants.LOGON_PASSWORD_FIELD);
	        request.getSession().removeAttribute(Constants.LOGIN_ID_FIELD);
	        request.getSession().removeAttribute(Constants.AUTHENTICATION_SUCCESS_URL);
	        super.unsuccessfulAuthentication(request, response, failed);
		 
	    }
	 
	 @Override
	    public Authentication attemptAuthentication(HttpServletRequest request) throws AuthenticationException {
	        String username = CAS_STATEFUL_IDENTIFIER;
	        String password = request.getParameter(TICKET);
	        
	        //save error code from CAS
	        request.getSession().setAttribute(Constants.CAS_ERROR_CODE_FIELD,
	                request.getParameter(Constants.ERROR_CODE_FIELD));

	        if (!StringUtils.isEmpty((String) request.getSession().getAttribute(Constants.LOGON_PASSWORD_FIELD)) &&
	                !StringUtils.isEmpty((String) request.getSession().getAttribute(Constants.LOGIN_ID_FIELD))) {
	            if (StringUtils.isEmpty(request.getParameter(TICKET))) { //ticket is empty, means unsuccessful login
	                if (Constants.FIRST_TIME_LOGON_RESET_PASSWORD_STATE_CODE.equals(//first time user should try to login via DB
	                        request.getParameter(Constants.ERROR_CODE_FIELD))) {
	                    username = (String) request.getSession().getAttribute(Constants.LOGIN_ID_FIELD);
	                    password = (String) request.getSession().getAttribute(Constants.LOGON_PASSWORD_FIELD);
	                }
	            }
	        }        
	        
	        if (password == null) {
	            password = "";
	        }

	        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

	        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

	        return this.getAuthenticationManager().authenticate(authRequest);
	    }
	
}
