package com.spd.cq.searspartsdirect.common.foundation;

import java.io.IOException;

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

@SlingServlet(paths="/bin/searspartsdirect/modelsearch", methods = "GET", metatype=true)

public class ModelSearchServlet extends SlingAllMethodsServlet {
	private static final Logger log = LoggerFactory.getLogger(ModelSearchServlet.class);
    private static final String ENCODING = "UTF-8";
    
	@Reference
    private SlingRepository repository;
 
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    {
    	ResourceResolver resourceResolver = request.getResourceResolver();
    	String brand = request.getParameter("brand");
    	String category = request.getParameter("category");
    	String model = request.getParameter("model");
    	String link = request.getParameter("link");

    	String categoryResourcePath = Constants.ASSETS_PATH + "/" + Constants.ASSETS_PRODUCT_CATEGORY_PATH + "/" + category;
    	Resource categoryResource = resourceResolver.getResource(categoryResourcePath);
    	if (categoryResource != null) {
    		response.sendRedirect("/" + brand + "/" + category + "/model-" + model + "-repair.html");
    	}
    	else {
    		response.sendRedirect(link);
    	}
    }
}
