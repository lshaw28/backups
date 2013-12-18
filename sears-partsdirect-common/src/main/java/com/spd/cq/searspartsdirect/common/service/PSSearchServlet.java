package com.spd.cq.searspartsdirect.common.service;

import java.io.IOException;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
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
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
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

		//modelNumber = request.getParameter("modelnumber");
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject = populateProductInfo();
		} catch (ValueFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().print(jsonObject.toString());
		response.setHeader("Content-Type", "application/json");

		/*if (StringUtils.isNotEmpty(modelNumber)) {
			try {
				ResourceResolver resourceResolver = request
						.getResourceResolver();
				Session session = resourceResolver.adaptTo(Session.class);
				jsonObject = populateProductInfo(modelNumber);
				response.getWriter().print(jsonObject.toString());
			} catch (RepositoryException e) {
				log.error("Error occured in ContainerServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			} catch (JSONException e) {
				log.error("Error occured in ContainerServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			}
		}*/
	}

	@SuppressWarnings("unused")
	private JSONObject populateProductInfo()
			throws JSONException, ValueFormatException, PathNotFoundException,
			RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String PRODUCT_DETAILS_URL = "http://pdapp301p.dev.ch3.s.com:8580/pd-services/models?modelNumber=123&offset=0&limit=5&sortType=Relevance&brand=Amana&productType=refrigerator";
		GetMethod method = new GetMethod(PRODUCT_DETAILS_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int resultStatusCode = 200;

			if (resultStatusCode != HttpStatus.SC_OK) {
				log.error("pankajrohira populateProductInfo() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				int statusCode = client.executeMethod(method);
				// Read the response body.
				byte[] responseBody = method.getResponseBody();
				//Header header = method.getResponseHeader("totalCount");

				JSONObject productDetailsJsonObj = new JSONObject(new String(
						responseBody));
				// JSONArray jsa = new JSONArray();
				result.put("totalCount", "pankajrohira");
				result.put("jsonData", productDetailsJsonObj.toString());
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