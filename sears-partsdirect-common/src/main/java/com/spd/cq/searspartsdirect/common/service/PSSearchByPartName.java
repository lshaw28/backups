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
import java.net.URLEncoder;

@Component
@Service
@Properties({
		@Property(name = "sling.servlet.extensions", value = "json"),
		@Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/model/searchbypartname"),
		@Property(name = "sling.servlet.methods", value = "GET") })
public class PSSearchByPartName extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(PSSearchByPartName.class);

	private String modelNumber;
	private String brandId;
	private String productCategoryId;
	private String partDescription;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		modelNumber = request.getParameter("modelnumber");
		brandId = request.getParameter("brandId") != null ? request.getParameter("brandId") : null;
		productCategoryId = request.getParameter("productCategoryId") != null ? request.getParameter("productCategoryId") : null;
		partDescription = request.getParameter("partDescription") != null ? request.getParameter("partDescription") : null;

		JSONObject jsonObject = new JSONObject();
		response.setHeader("Content-Type", "application/json");
		
		if (StringUtils.isNotEmpty(modelNumber) && StringUtils.isNotEmpty(brandId) && StringUtils.isNotEmpty(productCategoryId) && StringUtils.isNotEmpty(partDescription)){
			try {
				searchByPartName(jsonObject, modelNumber, brandId, productCategoryId, partDescription);
				response.getWriter().print(jsonObject.toString());
			} catch (RepositoryException e) {
				log.error("Error occured in PSSearchByPartName:doGet() "
						+ e.getMessage() + " Exception: ");
			} catch (JSONException e) {
				log.error("Error occured in PSSearchByPartName:doGet() "
						+ e.getMessage() + " Exception: ");
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void searchByPartName(JSONObject jsonObject, String modelNumber, String brandId, String productCategoryId, String partDescription)
			throws JSONException, ValueFormatException, PathNotFoundException, RepositoryException {
		HttpClient client = new HttpClient();
		
		String API_URL = "http://partsapivip.qa.ch3.s.com/pd-services/models/"+modelNumber+"/components/parts"
				+"?description="+partDescription+"&brandId="+brandId+"&productCategoryId="+productCategoryId;
		
		GetMethod method = new GetMethod(API_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				log.error("modelAllDiagram() failed-Status Code: "
						+ method.getStatusLine());
			}else {
				// Read the response body.
				byte[] responseBody = method.getResponseBody();
				JSONArray jsa = new JSONArray(new String(responseBody));
				jsonObject.put("searchByPartName", jsa.toString());
			}
		} catch (HttpException e) {
			log.error("Error occured in PSSearchByPartName:searchByPartName() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSSearchByPartName:searchByPartName() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}
}