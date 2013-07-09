package com.spd.cq.searspartsdirect.common.foundation;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

/**
 * Service to post to when making searches for a Model
 * @author Joseph
 *
 */

@SlingServlet(paths="/bin/searspartsdirect/modelsearch", methods = "POST", metatype=true)

public class ModelSearchServlet extends SlingAllMethodsServlet {
	private static final Logger log = LoggerFactory.getLogger(ModelSearchServlet.class);
    private static final String ENCODING = "UTF-8";
    
	@Reference
    private SlingRepository repository;
 
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    {
    	ResourceResolver resourceResolver = request.getResourceResolver();
    	String query = request.getParameter("query");
    	boolean found = Boolean.valueOf(request.getParameter("found"));
    	
		Writer out = response.getWriter();
		
    	//Make the API call to see if a model matches the input query
		if (found) {
			out.write("Found " + query);
		} else {
			out.write(Constants.MODEL_NOT_FOUND);
		}
		out.flush();
		
		//Commented out potential result
		//response.sendRedirect("/content/searspartsdirect/models/" + apiResponseModel);
    }
}
