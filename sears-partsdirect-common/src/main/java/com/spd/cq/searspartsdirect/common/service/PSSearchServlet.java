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
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
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
import com.spd.cq.searspartsdirect.common.helpers.PSSettingsHelper;

@Component
@Service(value = {javax.servlet.Servlet.class,
        		javax.servlet.ServletConfig.class, java.io.Serializable.class,
        		PSSearchServlet.class })
@Properties({
		@Property(name = "sling.servlet.extensions", value = "json"),
		@Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/search/searchservlet"),
		@Property(name = "sling.servlet.methods", value = "GET") })
public class PSSearchServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(PSSearchServlet.class);

	private String modelNumber;
	private String offset;
	private String limit;
	private String sortType;
	private String brand;
	private String productType;
	private String flag;
	private String partNumber;
	private String brandId;
	private String productCategoryId;
	private String cookieId;
	private String API_ROOT;
	
	@Reference
	PSSettingsHelper serviceHelper;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		// request parameters
		modelNumber = request.getParameter("modelnumber");
		flag = request.getParameter("flag");
		offset = request.getParameter("offset") != null ? request.getParameter("offset") : null;
		limit = request.getParameter("limit") != null ? request.getParameter("limit") : null;
		sortType = request.getParameter("sortType") != null ? request.getParameter("sortType") : null;
		brand = request.getParameter("brand") != null ? URLEncoder.encode(request.getParameter("brand"), "UTF-8") : null;
		productType = request.getParameter("productType") != null ? URLEncoder.encode(request.getParameter("productType"), "UTF-8") : null;
		partNumber = request.getParameter("partnumber");
		
		cookieId = request.getParameter("cookieId") != null ? request.getParameter("cookieId") : null;
		brandId = request.getParameter("brandId") != null ? request.getParameter("brandId") : null;
		productCategoryId = request.getParameter("productCategoryId") != null ? request.getParameter("productCategoryId") : null;

		JSONObject jsonObject = new JSONObject();
		response.setHeader("Content-Type", "application/json");
		
		// getting api root from felix
		API_ROOT = serviceHelper.getPartsDirectProductAPI();
		
		if (StringUtils.isNotEmpty(modelNumber) && StringUtils.isNotEmpty(flag)){
			try {
				if((StringUtils.equals(flag, "0") || StringUtils.equals(flag, "1") || StringUtils.equals(flag, "2")) && StringUtils.isNotEmpty(offset) && StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sortType)){
					populateModelSearchResults(jsonObject, modelNumber, offset, limit, sortType, "", "");
					populatePartSearchResults(jsonObject, modelNumber);
				}
				else if((StringUtils.equals(flag, "3") || StringUtils.equals(flag, "4")) && StringUtils.isNotEmpty(offset) && StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sortType)){
					if((StringUtils.isNotEmpty(brand) && StringUtils.isNotEmpty(productType))){
						populateModelSearchResults(jsonObject, modelNumber, offset, limit, sortType, brand, productType);
					}
					else if(StringUtils.isNotEmpty(brand)){
						populateModelSearchResults(jsonObject, modelNumber, offset, limit, sortType, brand, "");
					}
					else if(StringUtils.isNotEmpty(productType)){
						populateModelSearchResults(jsonObject, modelNumber, offset, limit, sortType, "", productType);
					}
				}
				else if(StringUtils.equals(flag, "5")){
					if(StringUtils.isNotEmpty(brand)){
						jsonObject = populateProductBasedOnBrand(modelNumber, brand);
					}
					else if(StringUtils.isNotEmpty(productType)){
						jsonObject = populateBrandBasedOnProduct(modelNumber, productType);
					}
				}
				else if(StringUtils.equals(flag, "6")){
					jsonObject = populateBrandProductList(modelNumber);
				}
				else if(StringUtils.equals(flag, "107")){
					jsonObject = getValuesForIOwnModels(modelNumber,brandId,productCategoryId);
				}
					else if(StringUtils.equals(flag, "108")){
					jsonObject = returnContentIOwnModels(modelNumber,brandId,productCategoryId,cookieId);
				}
				// JSON output
				response.getWriter().print(jsonObject.toString());
			} catch (RepositoryException e) {
				log.error("Error occured in PSSearchServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			} catch (JSONException e) {
				log.error("Error occured in PSSearchServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			}
		} else if (StringUtils.isNotEmpty(partNumber)){
			try{
				populatePartSearchResults(jsonObject, partNumber);
				populateModelSearchResults(jsonObject, partNumber, "0", "25", "relevance", "", "");
				// JSON output
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
	private JSONObject populateProductBasedOnBrand(String modelNumber, String brand) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		/* This will populate Product dropdown on Model Search Results page w.r.t Brand selected */
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String PRODUCT_BRAND_URL = API_ROOT + "models/product-types?modelNumber="+modelNumber+"&brand="+brand;
		GetMethod method = new GetMethod(PRODUCT_BRAND_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int statusCode = client.executeMethod(method);
			
			if (statusCode != HttpStatus.SC_OK) {
				log.error("populateProductBasedOnBrand() failed-Status Code: "
						+ method.getStatusLine());
			} else {
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
	private JSONObject populateBrandBasedOnProduct(String modelNumber, String product) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		/* This will populate Brand dropdown on Model Search Results page w.r.t Product selected */
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String BRAND_PRODUCT_URL = API_ROOT + "models/brands?modelNumber="+modelNumber+"&productType="+product;
		GetMethod method = new GetMethod(BRAND_PRODUCT_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int statusCode = client.executeMethod(method);
			
			if (statusCode != HttpStatus.SC_OK) {
				log.error("populateBrandBasedOnProduct() failed-Status Code: "
						+ method.getStatusLine());
			} else {
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
	private JSONObject populateBrandProductList(String modelNumber) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		final String BRAND_DETAILS_URL = API_ROOT + "models/brands?modelNumber="
				+ modelNumber;
		final String PRODUCT_DETAILS_URL = API_ROOT + "models/product-types?modelNumber="
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
			int statusCode = client.executeMethod(method);
			
			if (statusCode != HttpStatus.SC_OK) {
				log.error("PSSearchServlet:getList() failed-Status Code: "
						+ method.getStatusLine());
			} else {
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
	private void populateModelSearchResults(JSONObject jsonObject, String modelNumber,
			String offset, String limit, String sort, String brand, String productType) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		HttpClient client = new HttpClient();
		String PRODUCT_DETAILS_URL = API_ROOT + "models?modelNumber="
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

				jsonObject.put("totalCount", headerTotalCount.getValue());
				jsonObject.put("totalSYWCount", headerSYWCount.getValue());
				jsonObject.put("modelResults", jsa.toString());
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
	}
	
	@SuppressWarnings("unused")
	private void populatePartSearchResults(JSONObject jsonObject, String partNumber) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		HttpClient client = new HttpClient();
		String PRODUCT_DETAILS_URL = API_ROOT + "parts?partNumber="+partNumber;
		GetMethod method = new GetMethod(PRODUCT_DETAILS_URL);
		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				log.error("populatePartSearchResults() failed-Status Code: "
						+ method.getStatusLine());
			}else {
				// Read the response body.
				byte[] responseBody = method.getResponseBody();

				JSONArray jsa = new JSONArray(new String(responseBody));

				jsonObject.put("partResults", jsa.toString());
			}
		} catch (HttpException e) {
			log.error("Error occured in PSSearchServlet:populatePartSearchResults() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSSearchServlet:populatePartSearchResults() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}
	
	@SuppressWarnings("unused")
	private JSONObject returnContentIOwnModels(String modelNumber,
			String brandId, String productCategoryId, String cookieId)
			throws JSONException, ValueFormatException, PathNotFoundException,
			RepositoryException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();
		final String PRODUCT_BRAND_URL = API_ROOT + "my-owned-models?modelNumber="
				+ modelNumber
				+ "&brandId="
				+ brandId
				+ "&productCategoryId="
				+ productCategoryId + "&cookieId=" + cookieId;
		PostMethod method = new PostMethod(PRODUCT_BRAND_URL);
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_CREATED) {
				log.error("populateModelSearchResults() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				log.info("completed: forwarded content ");
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
	private JSONObject getValuesForIOwnModels(String modelNumber,
			String brandId, String productCategoryId) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		log.info("IF I am being called at all");
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();
		final String PRODUCT_BRAND_URL = API_ROOT + "my-owned-models?modelNumber="
				+ modelNumber
				+ "&brandId="
				+ brandId
				+ "&productCategoryId="
				+ productCategoryId;
		PostMethod method = new PostMethod(PRODUCT_BRAND_URL);
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_CREATED) {
				log.error("populateModelSearchResults() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				Header contentId = method.getResponseHeader("myProfileModels");
				result.put("myProfileModels", contentId.getValue());
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


}
