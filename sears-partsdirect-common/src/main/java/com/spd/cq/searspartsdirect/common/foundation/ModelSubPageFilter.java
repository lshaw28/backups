package com.spd.cq.searspartsdirect.common.foundation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Service
@Properties({
        @Property(name = org.osgi.framework.Constants.SERVICE_DESCRIPTION, value = "Loads guides as sub-pages of a model if the path exists below the model."),
        @Property(name = org.osgi.framework.Constants.SERVICE_VENDOR, value = "Siteworx"),
        @Property(name = "process.label", value = "Model Sub Page Filter"),
        @Property(name = "sling.filter.scope", value = "request"),
        @Property(name = "service.ranking", value = "500")})
public class ModelSubPageFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(ModelSubPageFilter.class);
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain fc) throws IOException, ServletException {
		
		SlingHttpServletRequest request = (SlingHttpServletRequest)servletRequest;
        SlingHttpServletResponse response = (SlingHttpServletResponse)servletResponse;
        ResourceResolver resourceResolver = request.getResourceResolver();
        
        RequestPathInfo rpi = request.getRequestPathInfo();      
        String ext = rpi.getExtension(), resPath = rpi.getResourcePath();        
        Resource res = resourceResolver.getResource(resPath);
        Pattern p = Pattern.compile("/(.*)/(.*)/model-(.*)-repair(.*)");
        Matcher m = p.matcher(resPath);
        if (m.find()) {
        	//String forwardUrl = m.group(2) + "?model=" + m.group(1);
        	String forwardUrl = m.group(4).replace(".html","." + m.group(1) + "." + m.group(2) + "." + m.group(3) + ".html");
        	request.getRequestDispatcher(forwardUrl).forward(request, response);
        	return;
        }
        
	    fc.doFilter(request, response);
	    return;
		
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}
	
}
