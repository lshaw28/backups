package com.spd.cq.searspartsdirect.common.service;

import java.io.IOException;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.servlet.ServletException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Service
@Properties({
		@Property(name = "sling.servlet.extensions", value = "json"),
		@Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/search/searchservlet"),
		@Property(name = "sling.servlet.methods", value = "GET") })
public class PSSearchServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(CategoryServlet.class);

	private String modelNumber;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		modelNumber = request.getParameter("modelnumber");
		JSONObject jsonObject = new JSONObject();
		response.setHeader("Content-Type", "application/json");

		if (StringUtils.isNotEmpty(modelNumber)) {
			try {
				jsonObject = populateModelSearchResults(modelNumber);
				response.getWriter().print(jsonObject.toString());
			} catch (RepositoryException e) {
				log.error("Error occured in ContainerServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			} catch (JSONException e) {
				log.error("Error occured in ContainerServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			}
		}
	}

	@SuppressWarnings("unused")
	private JSONObject populateModelSearchResults(String modelNumber)
			throws JSONException, ValueFormatException, PathNotFoundException,
			RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String PRODUCT_DETAILS_URL = "http://partsapivip.qa.ch3.s.com/pd-services/models?modelNumber="
				+ modelNumber;
		GetMethod method = new GetMethod(PRODUCT_DETAILS_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int resultStatusCode = 200;

			if (resultStatusCode != HttpStatus.SC_OK) {
				log.error("populateProductInfo() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				int statusCode = client.executeMethod(method);
				// Read the response body.
				byte[] responseBody = method.getResponseBody();
				Header headerTotalCount = method.getResponseHeader("X-Total-Count");
				Header headerSYWCount = method.getResponseHeader("X-Total-SYW-Count");
				
				JSONArray jsa = new JSONArray(new String(responseBody));
				
				result.put("X-Total-Count", headerTotalCount.getValue());
				result.put("X-Total-SYW-Count", headerSYWCount.getValue());
				result.put("jsonData", jsa.toJSONObject(jsa));
			}
		} catch (HttpException e) {
			log.error("Error occured in ContainerServlet:getProductInfo() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in ContainerServlet:getProductInfo() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}
}