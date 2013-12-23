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
			.getLogger(PSSearchServlet.class);

	private String modelNumber;
	private String offset;
	private String limit;
	private String sortType;
	private String brand;
	private String productType;
	private String flag;
	
	

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		modelNumber = request.getParameter("modelnumber");
		flag = request.getParameter("flag");
		offset = request.getParameter("offset") != null ? request.getParameter("offset") : null;
		limit = request.getParameter("limit") != null ? request.getParameter("limit") : null;
		sortType = request.getParameter("sortType") != null ? request.getParameter("sortType") : null;
		brand = request.getParameter("brand") != null ? request.getParameter("brand") : null;
		productType = request.getParameter("productType") != null ? request.getParameter("productType") : null;

		JSONObject jsonObject = new JSONObject();
		response.setHeader("Content-Type", "application/json");

		if (StringUtils.isNotEmpty(modelNumber) && StringUtils.isNotEmpty(flag)){
			try {
				if(StringUtils.equals(flag, "0")){
					jsonObject = populateBrandProductList(modelNumber);
				}
				else if((StringUtils.equals(flag, "1") || StringUtils.equals(flag, "2")) && StringUtils.isNotEmpty(offset) && StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sortType)){
					jsonObject = populateModelSearchResults(modelNumber, offset, limit, sortType);
				}
				else if(StringUtils.equals(flag, "3") && StringUtils.isNotEmpty(brand)){
					jsonObject = populateProductBasedOnBrand(modelNumber, brand);
				}
				else if(StringUtils.equals(flag, "4") && StringUtils.isNotEmpty(productType)){
					jsonObject = populateBrandBasedOnProduct(modelNumber, productType);
				}
				
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
	private JSONObject populateProductBasedOnBrand(String modelNumber, String brand) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String PRODUCT_BRAND_URL = "http://pdapp301p.dev.ch3.s.com:8580/pd-services/models/product-types?modelNumber="+modelNumber+"&brand="+brand;
		GetMethod method = new GetMethod(PRODUCT_BRAND_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int resultStatusCode = 200;

			if (resultStatusCode != HttpStatus.SC_OK) {
				log.error("populateProductBasedOnBrand() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				int statusCode = client.executeMethod(method);
				byte[] responseBody = method.getResponseBody();

				JSONArray jsa = new JSONArray(new String(responseBody));
				result.put("productList", jsa.toString());
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
	
	@SuppressWarnings("unused")
	private JSONObject populateBrandBasedOnProduct(String modelNumber, String product) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String BRAND_PRODUCT_URL = "http://pdapp301p.dev.ch3.s.com:8580/pd-services/models/brands?modelNumber="+modelNumber+"&productType="+product;
		GetMethod method = new GetMethod(BRAND_PRODUCT_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int resultStatusCode = 200;

			if (resultStatusCode != HttpStatus.SC_OK) {
				log.error("populateProductBasedOnBrand() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				int statusCode = client.executeMethod(method);
				byte[] responseBody = method.getResponseBody();

				JSONArray jsa = new JSONArray(new String(responseBody));
				result.put("brandList", jsa.toString());
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
	
	@SuppressWarnings("unused")
	private JSONObject populateBrandProductList(String modelNumber) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String BRAND_DETAILS_URL = "http://pdapp301p.dev.ch3.s.com:8580/pd-services/models/brands?modelNumber="
				+ modelNumber;
		final String PRODUCT_DETAILS_URL = "http://pdapp301p.dev.ch3.s.com:8580/pd-services/models/product-types?modelNumber="
				+ modelNumber;
		result = getList(BRAND_DETAILS_URL, result);
		result = getList(PRODUCT_DETAILS_URL, result);
		
		return result;
	}
	
	@SuppressWarnings("unused")
	private JSONObject getList(String URL, JSONObject result) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		
		HttpClient client = new HttpClient();

		GetMethod method = new GetMethod(URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int resultStatusCode = 200;

			if (resultStatusCode != HttpStatus.SC_OK) {
				log.error("getList failed-Status Code: "
						+ method.getStatusLine());
			} else {
				int statusCode = client.executeMethod(method);
				byte[] responseBody = method.getResponseBody();
				
				JSONArray jsa = new JSONArray(new String(responseBody));
				String jsonKey = StringUtils.containsIgnoreCase(URL, "brand") ? "brand" : "product";
				result.put(jsonKey, jsa.toString());
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

	@SuppressWarnings("unused")
	private JSONObject populateModelSearchResults(String modelNumber,
			String offset, String limit, String sort) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String PRODUCT_DETAILS_URL = "http://pdapp301p.dev.ch3.s.com:8580/pd-services/models?modelNumber="
				+ modelNumber + "&offset=" + offset + "&limit=" + limit + "&sortType=" + sort;
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
				Header headerTotalCount = method
						.getResponseHeader("X-Total-Count");
				Header headerSYWCount = method
						.getResponseHeader("X-Total-SYW-Count");

				JSONArray jsa = new JSONArray(new String(responseBody));

				result.put("X-Total-Count", headerTotalCount.getValue());
				result.put("X-Total-SYW-Count", headerSYWCount.getValue());
				result.put("jsonData", jsa.toString());
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