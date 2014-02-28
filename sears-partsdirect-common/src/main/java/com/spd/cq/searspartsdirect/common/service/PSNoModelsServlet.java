//mingle story id: 7922

package com.spd.cq.searspartsdirect.common.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.PSSettingsHelper;

import java.net.URLEncoder;

@Component
@Service(value = { javax.servlet.Servlet.class,
        javax.servlet.ServletConfig.class, java.io.Serializable.class,
        PSNoModelsServlet.class })
@Properties({
		@Property(name = "sling.servlet.extensions", value = "json"),
		@Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/search/nomodelsservlet"),
		@Property(name = "sling.servlet.methods", value = "GET") })
public class PSNoModelsServlet extends SlingSafeMethodsServlet {

	private static final Logger log = LoggerFactory
			.getLogger(PSNoModelsServlet.class);

	private String category;
	private String productType;
	private String flag;

	@Reference
    PSSettingsHelper settingsHelper;
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		flag = request.getParameter("flag");
		category = request.getParameter("category") != null ? request
				.getParameter("category") : null;
		productType = request.getParameter("productType") != null ? request
				.getParameter("productType") : null;
		JSONObject jsonObject = new JSONObject();
		response.setHeader("Content-Type", "application/json");
		
		String PRODUCT_BRAND_URL= settingsHelper.getAPIRoot()+"categories/";
		 

		if (StringUtils.isNotEmpty(flag)) {
			try {
				if (StringUtils.equals(flag, "1")) {
					jsonObject = populateCategoryList(PRODUCT_BRAND_URL);
				} else if (StringUtils.equals(flag, "2") && category != null) {
					jsonObject = populateProductList(PRODUCT_BRAND_URL,category);
				} else if (StringUtils.equals(flag, "3") && category != null
						&& productType != null) {
					jsonObject = populateBrandList(PRODUCT_BRAND_URL,category, productType);
				}
				response.getWriter().print(jsonObject.toString());
			} catch (RepositoryException e) {
				log.error("Error occured in PSNoModelsServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			} catch (JSONException e) {
				log.error("Error occured in PSNoModelsServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			}
		}
	}

	@SuppressWarnings("unused")
	private JSONObject populateCategoryList(String PRODUCT_BRAND_URL) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {

		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		GetMethod method = new GetMethod(PRODUCT_BRAND_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				log.error("populateCategoryList() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				byte[] responseBody = method.getResponseBody();

				JSONArray jsa = new JSONArray(new String(responseBody));
				result.put("category", jsa.toString());
			}
		} catch (HttpException e) {

			log.error("Error occured in PSNoModelsServlet:populateCategoryList() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSNoModelsServlet:populateCategoryList() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}

	@SuppressWarnings("unused")
	private JSONObject populateProductList(String PRODUCT_BRAND_URL,String category)
			throws JSONException, ValueFormatException, PathNotFoundException,
			RepositoryException, UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();
		String PRODUCT_URL = PRODUCT_BRAND_URL
				+ URLEncoder.encode(category, "UTF-8") + "/product-types";
		PRODUCT_URL = StringUtils.replaceEach(PRODUCT_URL,
				new String[] { "+" }, new String[] { "%20" });
		GetMethod method = new GetMethod(PRODUCT_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				log.error("populateProductList() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				byte[] responseBody = method.getResponseBody();

				JSONArray jsa = new JSONArray(new String(responseBody));
				result.put("category", jsa.toString());
			}
		} catch (HttpException e) {
			log.error("Error occured in PSNoModelsServlet:populateProductList() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSNoModelsServlet:populateProductList() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}

	@SuppressWarnings("unused")
	private JSONObject populateBrandList(String PRODUCT_BRAND_URL, String category, String productType)
			throws JSONException, ValueFormatException, PathNotFoundException,
			RepositoryException, UnsupportedEncodingException {

		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();

		String PRODUCT_URL = PRODUCT_BRAND_URL
				+ URLEncoder.encode(category, "UTF-8") + "/"
				+ URLEncoder.encode(productType, "UTF-8") + "/brands";

		PRODUCT_URL = StringUtils.replaceEach(PRODUCT_URL,
				new String[] { "+" }, new String[] { "%20" });

		GetMethod method = new GetMethod(PRODUCT_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				log.error("populateProductList() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				byte[] responseBody = method.getResponseBody();

				JSONArray jsa = new JSONArray(new String(responseBody));
				result.put("category", jsa.toString());
			}
		} catch (HttpException e) {
			log.error("Error occured in PSNoModelsServlet:populateBrandList() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSNoModelsServlet:populateBrandList() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}

}
