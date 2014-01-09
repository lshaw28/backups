package com.spd.cq.searspartsdirect.common.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.ModelSubcomponentAPIHelper;
import com.spd.cq.searspartsdirect.common.model.PDModelSubcomponentModel;

@Component
@Service
@Properties({
                @Property(name = "sling.servlet.extensions", value = "json"),
                @Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/symptom-service/check"),
                @Property(name = "sling.servlet.methods", value = "GET")
        })
public class SymptomServlet extends SlingSafeMethodsServlet {
    
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SymptomServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException,
                    IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        Map<String, String> repairHelpInfo = new HashMap<String, String>();
        String model = request.getParameter("model");
        String brand = request.getParameter("brand");
        String category = request.getParameter("category");
        String repairHelpPageUrl = "";
        String repairHelpCount = "";
        
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
            ModelSubcomponentAPIHelper apiHelper = new ModelSubcomponentAPIHelper(model);
            PDModelSubcomponentModel subcomponents = apiHelper.getModelSubcomponents(request);
            try {
                    String brandTrueName = "";
                    if (brandResult.getHits().size() > 0) {
                            brandTrueName = brandResult.getHits().get(0).getNode().getName();
                    } else {
                            brandTrueName = JcrUtil.createValidName(brand);
                    }
                    String categoryTrueName = categoryResult.getHits().get(0).getNode().getName();
                    repairHelpPageUrl = "/" + brandTrueName + "/" + categoryTrueName + "/model-" + model + "-repair.html";
                    repairHelpCount = String.valueOf(subcomponents.getSymptomsArr().length);
                    repairHelpInfo.put("pageUrl", repairHelpPageUrl);
                    repairHelpInfo.put("count", repairHelpCount);
            }
            catch (Exception e) {
                    log.error(ExceptionUtils.getFullStackTrace(e));
            }
    }        
        response.setHeader("Content-Type", "application/json");

        Gson gson = new Gson();
        String jsonString = gson.toJson(repairHelpInfo);

        response.getWriter().print(jsonString);
        
    }
}
