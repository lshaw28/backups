package com.spd.cq.searspartsdirect.common.foundation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;


@Component
@Service
@Properties({
        @Property(name = org.osgi.framework.Constants.SERVICE_DESCRIPTION, value = ""),
        @Property(name = "process.label", value = "SSO Filter"),
        @Property(name = "sling.filter.scope", value = "request"),
        @Property(name = "service.ranking", value = "500")})

public class SSOFilter implements Filter{
	
		private static final Logger log = LoggerFactory.getLogger(SSOFilter.class);
		
		/** The cas referrers. */
	    private List<String> casReferrers = Collections.emptyList();
		
		public static final String REDIRECT_URL = Constants.SSO_CAS_LOGIN_URL + "?" + Constants.GATEWAY_FIELD + "=true&" + Constants.SERVICE_FIELD + "=" + Constants.SSO_SERVICE_FIELD;
		
		public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
				FilterChain fc) throws IOException, ServletException {
			boolean allowContinue = true;
			SlingHttpServletRequest httpRequest = (SlingHttpServletRequest)servletRequest;
	        SlingHttpServletResponse httpResponse = (SlingHttpServletResponse)servletResponse;
	        httpResponse.sendRedirect(REDIRECT_URL);
	        
	        log.debug("Executed SSO");
	        
	        if (!skipSSO(httpRequest)) {
                httpRequest.getSession().setAttribute(Constants.CAS_VISITED_PARAM, Boolean.TRUE);
                httpRequest.getSession().setAttribute(Constants.CAS_AUTOLOGIN_STARTED_PARAM, Boolean.TRUE);
                /* PD-8600 - to avoid landing on loginFail page we add parameter specifically indicating that this will not result in failure */
                httpRequest.getSession().setAttribute(Constants.CAS_NOFAIL_PARAMETER, Boolean.TRUE);
               // SavedRequest savedRequest = new SavedRequest(httpRequest, new PortResolverImpl());
               // httpRequest.getSession().setAttribute(Constants.AUTHENTICATION_FAILURE_URL, savedRequest.getRequestUrl());
                //httpRequest.getSession().setAttribute(Constants.AUTHENTICATION_SUCCESS_URL, savedRequest.getRequestUrl());
                allowContinue = false;
                httpResponse.sendRedirect(REDIRECT_URL);
            }
	        
	        if (allowContinue) {
	        	fc.doFilter(httpRequest, httpResponse);
	        }
		    return;
			
		}

		 /**
	     * Skip sso.
	     * 
	     * @param httpRequest the http request
	     * 
	     * @return true, if successful
	     */
	   private boolean skipSSO(ServletRequest servletRequest) {
		   SlingHttpServletRequest httpRequest = (SlingHttpServletRequest)servletRequest;
	        Object casVisited = httpRequest.getSession(true).getAttribute(Constants.CAS_VISITED_PARAM);
	        String referrer = getReferer(httpRequest);
	        String referrerHost = null;
	        URL referrerUrl = null;
	        if (StringUtils.isNotEmpty(referrer)) {
	            try {
	            	referrerUrl = new URL(referrer);
	                referrerHost = StringUtils.lowerCase(referrerUrl.getHost());
	            } catch (MalformedURLException e) {
	                log.error("Not a valid URL", e);
	            }
	        }

	        	return !(casVisited == null && (StringUtils.isEmpty(referrer) || isCASReferrer(referrerHost) || StringUtils.contains(referrerHost, StringUtils.lowerCase(httpRequest.getServerName()))));
	    }
		
	   private String getReferer(ServletRequest request) {     
		   SlingHttpServletRequest httpRequest = (SlingHttpServletRequest)request;
	        return StringUtils.lowerCase(httpRequest.getHeader("referer"));
	    }
	   
	    /**
	     * Checks if is cAS referrer.
	     * 
	     * @param referrerHost the referrer host
	     * 
	     * @return true, if is cAS referrer
	     */
	    private boolean isCASReferrer(String  referrerHost) {
	        return casReferrers.contains(referrerHost);
	    }
		
		public void init(FilterConfig arg0) throws ServletException {
		}

		public void destroy() {
		}

}
