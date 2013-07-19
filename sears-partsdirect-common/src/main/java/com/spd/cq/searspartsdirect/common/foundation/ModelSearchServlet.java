package com.spd.cq.searspartsdirect.common.foundation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.commons.lang.exception.ExceptionUtils;
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

import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
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
    	Session session = resourceResolver.adaptTo(Session.class);
    	String brand = request.getParameter("brand");
    	String category = request.getParameter("category");
    	String model = request.getParameter("model");
    	String link = request.getParameter("link");

    	Map<String, String> categoryMap = new HashMap<String, String>();
    	categoryMap.put("path", Constants.ASSETS_PATH + "/" + Constants.ASSETS_PRODUCT_CATEGORY_PATH);
    	categoryMap.put("type", Constants.CQ_PAGE);
    	categoryMap.put("property", "jcr:content/jcr:title");
    	categoryMap.put("property.value", category);

		QueryBuilder categoryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
		Query categoryQuery = categoryBuilder.createQuery(PredicateGroup.create(categoryMap), session);
		SearchResult categoryResult = categoryQuery.getResult();
		
		Map<String, String> brandMap = new HashMap<String, String>();
		brandMap.put("path", Constants.ASSETS_PATH + "/brand");
		brandMap.put("type", Constants.CQ_PAGE);
		brandMap.put("property", "jcr:content/jcr:title");
		brandMap.put("property.value", brand);
		
		QueryBuilder brandBuilder = resourceResolver.adaptTo(QueryBuilder.class);
		Query brandQuery = brandBuilder.createQuery(PredicateGroup.create(brandMap), session);
		SearchResult brandResult = brandQuery.getResult();
		
		if (categoryResult.getHits().size() > 0) {
			try {
				String brandTrueName = "";
				if (brandResult.getHits().size() > 0) {
					brandTrueName = brandResult.getHits().get(0).getNode().getName();
				} else {
					brandTrueName = JcrUtil.createValidName(brand);
				}
				String categoryTrueName = categoryResult.getHits().get(0).getNode().getName();
				response.sendRedirect("/" + brandTrueName + "/" + categoryTrueName + "/model-" + model + "-repair.html");
			}
			catch (Exception e) {
				log.error(ExceptionUtils.getFullStackTrace(e));
			}
		}
    	else {
    		response.sendRedirect(link);
    	}
    }
}
