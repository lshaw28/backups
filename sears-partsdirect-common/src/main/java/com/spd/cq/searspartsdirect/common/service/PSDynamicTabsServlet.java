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
		PSDynamicTabsServlet.class })
@Properties({
		@Property(name = "sling.servlet.extensions", value = "json"),
		@Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/model/dynamictabs"),
		@Property(name = "sling.servlet.methods", value = "GET") })
public class PSDynamicTabsServlet extends SlingSafeMethodsServlet {

	private static final Logger log = LoggerFactory
			.getLogger(PSDynamicTabsServlet.class);

	private String modelNumber;
	private String brandId;
	private String categoryId;

	@Reference
	PSSettingsHelper settingsHelper;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		modelNumber = request.getParameter("modelNumber");
		brandId = request.getParameter("brandId");
		categoryId = request.getParameter("categoryId");
		JSONObject jsonObject = new JSONObject();
		response.setHeader("Content-Type", "application/json");

		String API_URL = settingsHelper.getAPIRoot()
				+ "model-tabs-details?modelNumber=";

		if (StringUtils.isNotEmpty(modelNumber)
				&& StringUtils.isNotEmpty(brandId)
				&& StringUtils.isNotEmpty(categoryId)) {
			try {
				getDynamicTabs(API_URL, jsonObject, modelNumber, brandId,
						categoryId);
				response.getWriter().print(jsonObject.toString());
			} catch (RepositoryException e) {
				log.error("Error occured in PSDynamicTabsServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			} catch (JSONException e) {
				log.error("Error occured in PSDynamicTabsServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			}
		}
	}

	@SuppressWarnings("unused")
	private void getDynamicTabs(String API_URL, JSONObject jsonObject,
			String modelNumber, String brandId, String categoryId)
			throws JSONException, ValueFormatException, PathNotFoundException,
			RepositoryException, UnsupportedEncodingException {
		HttpClient client = new HttpClient();
		API_URL = API_URL + URLEncoder.encode(modelNumber, "UTF-8")
				+ "&brandId=" + URLEncoder.encode(brandId, "UTF-8")
				+ "&categoryId=" + URLEncoder.encode(categoryId, "UTF-8");
		API_URL = StringUtils.replaceEach(API_URL, new String[] { "+" },
				new String[] { "%20" });

		GetMethod method = new GetMethod(API_URL);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				log.error("getDynamicTabs() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				// Read the response body.
				byte[] responseBody = method.getResponseBody();
				jsonObject.put("jsonDynamicTabs",
						method.getResponseBodyAsString());
			}
		} catch (HttpException e) {
			log.error("Error occured in PSDynamicTabsServlet:getDynamicTabs() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSDynamicTabsServlet:getDynamicTabs() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}
}
