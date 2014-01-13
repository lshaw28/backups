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
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URLEncoder;
import com.spd.cq.searspartsdirect.common.helpers.PSsettingsHelper;

@Component
@Service(value = { javax.servlet.Servlet.class,
        javax.servlet.ServletConfig.class, java.io.Serializable.class,
        PSSearchServlet.class })
@Properties({
		@Property(name = "sling.servlet.extensions", value = "json"),
		@Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/search/searchservlet"),
		@Property(name = "sling.servlet.methods", value = "GET") })


public class PSSearchServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(PSSearchServlet.class);

	@Reference
	PSsettingsHelper settingsHelperObj;
	
	
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

		String felixApi =  settingsHelperObj.getPartsDirectProductAPI();
		
		modelNumber = request.getParameter("modelnumber");
		flag = request.getParameter("flag");
		offset = request.getParameter("offset") != null ? request.getParameter("offset") : null;
		limit = request.getParameter("limit") != null ? request.getParameter("limit") : null;
		sortType = request.getParameter("sortType") != null ? request.getParameter("sortType") : null;
		brand = request.getParameter("brand") != null ? URLEncoder.encode(request.getParameter("brand"), "UTF-8") : null;
		productType = request.getParameter("productType") != null ? URLEncoder.encode(request.getParameter("productType"), "UTF-8") : null;

		JSONObject jsonObject = new JSONObject();
		response.setHeader("Content-Type", "application/json");
		
		if (StringUtils.isNotEmpty(modelNumber) && StringUtils.isNotEmpty(flag)){
			try {
				if((StringUtils.equals(flag, "0") || StringUtils.equals(flag, "1") || StringUtils.equals(flag, "2")) && StringUtils.isNotEmpty(offset) && StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sortType)){
					jsonObject = populateModelSearchResults(felixApi,modelNumber, offset, limit, sortType, "", "");
				}
				else if((StringUtils.equals(flag, "3") || StringUtils.equals(flag, "4")) && StringUtils.isNotEmpty(offset) && StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sortType)){
					if((StringUtils.isNotEmpty(brand) && StringUtils.isNotEmpty(productType))){
						jsonObject = populateModelSearchResults(felixApi,modelNumber, offset, limit, sortType, brand, productType);
					}
					else if(StringUtils.isNotEmpty(brand)){
						jsonObject = populateModelSearchResults(felixApi,modelNumber, offset, limit, sortType, brand, "");
					}
					else if(StringUtils.isNotEmpty(productType)){
						jsonObject = populateModelSearchResults(felixApi,modelNumber, offset, limit, sortType, "", productType);
					}
				}
				else if(StringUtils.equals(flag, "5")){
					if(StringUtils.isNotEmpty(brand)){
						jsonObject = populateProductBasedOnBrand(felixApi,modelNumber, brand);
					}
					else if(StringUtils.isNotEmpty(productType)){
						jsonObject = populateBrandBasedOnProduct(felixApi,modelNumber, productType);
					}
				}
				else if(StringUtils.equals(flag, "6")){
					jsonObject = populateBrandProductList(felixApi,modelNumber);
				}
				else if(StringUtils.equals(flag, "99")){
					jsonObject = populateNoModelsFoundBrandProductList(felixApi);
				}
				response.getWriter().print(jsonObject.toString());
			} catch (RepositoryException e) {
				log.error("Error occured in PSSearchServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			} catch (JSONException e) {
				log.error("Error occured in PSSearchServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private JSONObject populateNoModelsFoundBrandProductList(String felixApi) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String BRAND_DETAILS_URL = felixApi+"brands";
		final String PRODUCT_DETAILS_URL = felixApi+"product-types";
		result = getList(BRAND_DETAILS_URL, result);
		result = getList(PRODUCT_DETAILS_URL, result);
		
		return result;
	}
	
	
	
	
	
	@SuppressWarnings("unused")
	private JSONObject populateProductBasedOnBrand(String felixApi,String modelNumber, String brand) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String PRODUCT_BRAND_URL = felixApi+"product-types?modelNumber="+modelNumber+"&brand="+brand;
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
			log.error("Error occured in PSSearchServlet:populateProductBasedOnBrand() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSSearchServlet:populateProductBasedOnBrand() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	private JSONObject populateBrandBasedOnProduct(String felixApi,String modelNumber, String product) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String BRAND_PRODUCT_URL = felixApi+"brands?modelNumber="+modelNumber+"&productType="+product;
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
			log.error("Error occured in PSSearchServlet:populateBrandBasedOnProduct() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSSearchServlet:populateBrandBasedOnProduct() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	private JSONObject populateBrandProductList(String felixApi,String modelNumber) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String BRAND_DETAILS_URL = felixApi+"brands?modelNumber="
				+ modelNumber;
		final String PRODUCT_DETAILS_URL = felixApi+"product-types?modelNumber="
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
			log.error("Error occured in PSSearchServlet:getList() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSSearchServlet:getList() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}

	@SuppressWarnings("unused")
	private JSONObject populateModelSearchResults(String felixApi,String modelNumber,
			String offset, String limit, String sort, String brand, String productType) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		String PRODUCT_DETAILS_URL = felixApi+"?modelNumber="
				+ modelNumber + "&offset=" + offset + "&limit=" + limit + "&sortType=" + sort;
		if(StringUtils.isNotEmpty(brand)){
			PRODUCT_DETAILS_URL = PRODUCT_DETAILS_URL + "&brand="+brand;
		}
		if(StringUtils.isNotEmpty(productType)){
			PRODUCT_DETAILS_URL = PRODUCT_DETAILS_URL + "&productType="+productType;
		}
		
		GetMethod method = new GetMethod(PRODUCT_DETAILS_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int statusCode = client.executeMethod(method);
			
			if (statusCode != HttpStatus.SC_OK) {
				log.error("populateModelSearchResults() failed-Status Code: "
						+ method.getStatusLine());
			}else {
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
			log.error("Error occured in PSSearchServlet:populateModelSearchResults() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSSearchServlet:populateModelSearchResults() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}
}