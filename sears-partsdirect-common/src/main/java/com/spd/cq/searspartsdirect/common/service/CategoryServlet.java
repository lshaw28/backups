package com.spd.cq.searspartsdirect.common.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
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

import com.day.cq.replication.ReplicationStatus;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

@Component
@Service
@Properties({
		@Property(name = "sling.servlet.extensions", value = "json"),
		@Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/category-service/check"),
		@Property(name = "sling.servlet.methods", value = "GET")
	})
public class CategoryServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(CategoryServlet.class);
	private static final String CATEGORY_NAMES = "category";

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException,
			IOException {

		Map<String, Boolean> categories = new HashMap<String, Boolean>();
		String[] categoryNames = request.getParameterValues(CATEGORY_NAMES);

		response.setHeader("Content-Type", "application/json");
		if (categoryNames != null && categoryNames.length > 0) {
			for (String categoryName : categoryNames) {
				if (StringUtils.isNotBlank(categoryName)) {
					categories.put(categoryName, checkIfCategoryExists(request, categoryName));
				}
			}
		}

		Gson gson = new Gson();
		String jsonString = gson.toJson(categories);

		response.getWriter().print(jsonString);
	}

	private Boolean checkIfCategoryExists(SlingHttpServletRequest slingRequest, String categoryName) {

		boolean isActive = false;

		ResourceResolver resourceResolver = slingRequest.getResourceResolver();
		Session session = resourceResolver.adaptTo(Session.class);

		Map<String, String> map = new HashMap<String, String>();
		map.put("path", Constants.CATEGORIES_ROOT);
		map.put("type", Constants.CQ_PAGE);
		map.put("1_property", Constants.TEMPLATE_REL_PATH);
		map.put("1_property.value", Constants.CATEGORY_TEMPLATE);
		map.put("2_property", Constants.ASSETS_TITLE_REL_PATH);
		map.put("2_property.value", categoryName);

		QueryBuilder builder = resourceResolver.adaptTo(QueryBuilder.class);
		Query query = builder.createQuery(PredicateGroup.create(map), session);

		SearchResult result = query.getResult();

		for (Hit hit : result.getHits()) {
			PageManager pageManager;
			Page page;
			try {
				pageManager = resourceResolver.adaptTo(PageManager.class);
				page = pageManager.getPage(hit.getPath());
				if (page != null) {
					ReplicationStatus replicationStatus = page.adaptTo(ReplicationStatus.class);
					isActive = replicationStatus.isActivated();
				}
			} catch (RepositoryException e) {
				log.error("Error while checking replicationStatus of " + categoryName + " page: " + e.getMessage());
			}
		}

		return isActive;
	}
}
