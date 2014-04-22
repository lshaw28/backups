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
        TopAccessoryPartsServlet.class })
@Properties({
		@Property(name = "sling.servlet.extensions", value = "json"),
		@Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/search/topAccessoryparts"),
		@Property(name = "sling.servlet.methods", value = "GET") })

public class TopAccessoryPartsServlet extends SlingSafeMethodsServlet {
	private static final Logger log = LoggerFactory
			.getLogger(TopAccessoryPartsServlet.class);

	private String productGroupId;
	private String supplierId;
	private String partNumber;
	private String flag;

	@Reference
    PSSettingsHelper settingsHelper;
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		flag = request.getParameter("flag");
		productGroupId = request.getParameter("productGroupId") != null ? request
				.getParameter("productGroupId") : null;
		supplierId = request.getParameter("supplierId") != null ? request
				.getParameter("supplierId") : null;
		partNumber = request.getParameter("partNumber") != null ? request
				.getParameter("partNumber") : null;		
		JSONObject jsonObject = new JSONObject();
		response.setHeader("Content-Type", "application/json");
		String PART_DETAIL_URL= settingsHelper.getPartsDirectProductAPI()+"parts/";

		if (StringUtils.isNotEmpty(flag)) {
			try {
				if (StringUtils.equals(flag, "1")) {
					jsonObject = populatePartDetail(PART_DETAIL_URL);
				} 
				response.getWriter().print(jsonObject.toString());
			} catch (RepositoryException e) {
				log.error("Error occured in TopAccessoryPartsServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			} catch (JSONException e) {
				log.error("Error occured in TopAccessoryPartsServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			}
		}
	}

	@SuppressWarnings("unused")
	private JSONObject populatePartDetail(String PART_DETAIL_URL) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {

		JSONObject result = new JSONObject();
		HttpClient client = new HttpClient();
        String partDetailUrl = PART_DETAIL_URL+partNumber+"?productGroupId="+productGroupId+"&supplierId="+supplierId;
        log.info("PartDetailUrlin TopAccessoryPartsServlet:populatePartDetail() "+partDetailUrl);
		GetMethod method = new GetMethod(partDetailUrl);

		try {
			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(2, false));
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				log.error("populatePartDetail() failed-Status Code: "
						+ method.getStatusLine());
			} else {
				byte[] responseBody = method.getResponseBody();
				String accessoryParts =  new String(responseBody);
				result.put("topAccessoryparts", accessoryParts);
			}
		} catch (HttpException e) {

			log.error("Error occured in PSPartDetailServlet:populatePartDetail() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSPartDetailServlet:populatePartDetail() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}


	
}
