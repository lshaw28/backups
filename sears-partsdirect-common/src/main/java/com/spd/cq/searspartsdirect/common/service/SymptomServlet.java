package com.spd.cq.searspartsdirect.common.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.ModelSubcomponentAPIHelper;
import com.spd.cq.searspartsdirect.common.model.PDModelSubcomponentModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;

@Component
@Service
@Properties({ @Property(name = "sling.servlet.extensions", value = "json"),
                @Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/symptom-service/check"),
                @Property(name = "sling.servlet.methods", value = "GET") })
public class SymptomServlet extends SlingSafeMethodsServlet {


        private static final long serialVersionUID = 1L;
        private static final Logger log = LoggerFactory.getLogger(SymptomServlet.class);

        @Override
        protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

                Map<String, String> repairHelpInfo = new HashMap<String, String>();
                String model = request.getParameter("modelNumber");
                String brand = request.getParameter("brand");
                String category = request.getParameter("category");
                String repairHelpPageUrl = "";
                String repairHelpCount = "";

                if (StringUtils.isNotBlank(model) && StringUtils.isNotBlank(brand) && StringUtils.isNotBlank(category)) {

                        model = StringUtils.capitalize(model.toLowerCase());
                        brand = StringUtils.capitalize(brand.toLowerCase());
                        category = StringUtils.capitalize(category.toLowerCase());
                        Map<String, String> categoryFilters = new HashMap<String, String>();
                        categoryFilters.put("property", "jcr:content/jcr:title");
                        categoryFilters.put("property.value", category);
                        SearchResult categoryResult = getDataFromCQRepository(Constants.ASSETS_PRODUCT_CATEGORY_PATH,categoryFilters, request.getResourceResolver());
                        Map<String, String> brandFilters = new HashMap<String, String>();
                        brandFilters.put("property", "jcr:content/jcr:title");
                        brandFilters.put("property.value", brand);
                        log.debug("Done Category Search");
                        SearchResult brandResult = getDataFromCQRepository(Constants.ASSETS_BRAND_PATH, brandFilters, request.getResourceResolver());
                        log.debug("Done Brand Search");
                        if (categoryResult.getHits().size() > 0 && brandResult.getHits().size() > 0) {
                                ModelSubcomponentAPIHelper apiHelper = new ModelSubcomponentAPIHelper(model);
                                PDModelSubcomponentModel subcomponents = apiHelper.getModelSubcomponents(request);
                                log.debug("Done Symptom search beginings");
                                List<SymptomModel> symptoms = getCQSymptomsForCategory(subcomponents, request.getResourceResolver());
                                log.debug("Done Symptom search");
                                if (!symptoms.isEmpty()) {
                                        try {
                                                String brandTrueName = "";
                                                if (brandResult.getHits().size() > 0) {
                                                        brandTrueName = brandResult.getHits().get(0).getNode().getName();
                                                } else {
                                                        brandTrueName = JcrUtil.createValidName(brand);
                                                }
                                                String categoryTrueName = categoryResult.getHits().get(0).getNode().getName();
                                                repairHelpPageUrl = "/" + brandTrueName + "/" + categoryTrueName + "/model-" + model
                                                                                                + "-repair.html";
                                                repairHelpCount = String.valueOf(symptoms.size());
                                                repairHelpInfo.put("pageUrl", repairHelpPageUrl);
                                                repairHelpInfo.put("count", repairHelpCount);
                                        } catch (Exception e) {
                                                log.error(ExceptionUtils.getFullStackTrace(e));
                                        }
                                } else {
                                        log.debug("Symptoms not found for this Category/Brand/Model combination!!! " + category + "/"
                                                                + brand + "/" + model);
                                }
                        }
                }
                response.setHeader("Content-Type", "application/json");

                Gson gson = new Gson();
                String jsonString = gson.toJson(repairHelpInfo);
                response.getWriter().print(jsonString);
                request.getResourceResolver().close();
                log.debug("application end");
        }

        private SearchResult getDataFromCQRepository(String path, Map<String, String> filterProperties, ResourceResolver resourceResolver) {

                Map<String, String> propertyMap = new HashMap<String, String>();
                propertyMap.put("path", Constants.ASSETS_PATH + "/" + path);
                propertyMap.put("type", Constants.CQ_PAGE);

                for (Entry<String, String> entry : filterProperties.entrySet()) {
                        propertyMap.put(entry.getKey(), entry.getValue());
                }
                SearchResult searchResult = null;
       
                if (resourceResolver.isLive()) {
                    Session session = resourceResolver.adaptTo(Session.class);
                    QueryBuilder queryyBuilder = resourceResolver.adaptTo(QueryBuilder.class);
                    Query categoryQuery = queryyBuilder.createQuery(PredicateGroup.create(propertyMap), session);
                    searchResult = categoryQuery.getResult();
                } else {
                    log.error("ResourceResolver is not live!!!");
                }
                return searchResult;
        }

        private List<SymptomModel> getCQSymptomsForCategory(PDModelSubcomponentModel subcomponents, ResourceResolver resourceResolver) {
                List<SymptomModel> symptoms = new ArrayList<SymptomModel>();
                if (subcomponents != null) {

                        Map<String, String> symptomFilters = new HashMap<String, String>();
                        for (int i = 0; i < subcomponents.getSymptomsArr().length; i++) {

                                symptomFilters.clear();
                                symptomFilters.put("property", Constants.ASSETS_ID_REL_PATH);
                                symptomFilters.put("property.value", subcomponents.getSymptomsArr()[i].getId());
                                log.debug("Done Symptom check Before");
                                SearchResult SymptomResult = getDataFromCQRepository(Constants.ASSETS_SYMPTOM_PATH, symptomFilters, resourceResolver);

                                for (Hit hit : SymptomResult.getHits()) {
                                        try {
                                                ValueMap props = hit.getProperties();
                                                if (props != null) {
                                                       
                                                        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
                                                        Page symptomPage = pageManager.getPage(hit.getPath());
                                                        if (symptomPage != null) {
                                                                SymptomModel symptomModel = new SymptomModel(symptomPage.getPath(), props.get(
                                                                                Constants.ASSETS_TITLE_PATH, String.class), props.get(
                                                                                Constants.ASSETS_DESCRIPTION_PATH, String.class), props.get(
                                                                                Constants.ASSETS_ID, String.class));
                                                                if (subcomponents.getSymptomsArr()[i].getSuccessfulFrequency() != null) {
                                                                        symptomModel.setFrequency(Math.round(subcomponents.getSymptomsArr()[i]
                                                                                        .getSuccessfulFrequency().doubleValue()));
                                                                }
                                                                symptoms.add(symptomModel);
                                                        } else {
                                                                log.debug("symptom page is not found");
                                                        }
                                                }
                                        } catch (RepositoryException e) {
                                                log.error("error while getting model symptoms, ", e.fillInStackTrace());
                                        }
                                }
                        }
                } else {
                        log.debug("no subcomponent data found from the model api call");
                }
                return symptoms;
        }
}
