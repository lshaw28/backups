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
				PSModelDiagramPartListServlet.class })
@Properties({
		@Property(name = "sling.servlet.extensions", value = "json"),
		@Property(name = "sling.servlet.paths", value = "/bin/searspartsdirect/model/partlist"),
		@Property(name = "sling.servlet.methods", value = "GET") })
public class PSModelDiagramPartListServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(PSModelDiagramPartListServlet.class);

	private String modelNumber;
	private String brandId;
	private String productCategoryId;
	private String diagramPageId;
	private String documentId;
	private String API_ROOT;
	
	@Reference
	PSSettingsHelper serviceHelper;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		modelNumber = request.getParameter("modelnumber");
		brandId = request.getParameter("brandId") != null ? request.getParameter("brandId") : null;
		productCategoryId = request.getParameter("productCategoryId") != null ? request.getParameter("productCategoryId") : null;
		diagramPageId = request.getParameter("diagramPageId") != null ? request.getParameter("diagramPageId") : null;
		documentId = request.getParameter("documentId") != null ? request.getParameter("documentId") : null;

		JSONObject jsonObject = new JSONObject();
		response.setHeader("Content-Type", "application/json");
		
		API_ROOT = serviceHelper.getAPIRoot();
		
		if (StringUtils.isNotEmpty(modelNumber) && StringUtils.isNotEmpty(brandId) && StringUtils.isNotEmpty(productCategoryId)
				&& StringUtils.isNotEmpty(diagramPageId) && StringUtils.isNotEmpty(documentId)){
			try {
				modelDiagramPartList(jsonObject, modelNumber, brandId, productCategoryId, diagramPageId, documentId);
				response.getWriter().print(jsonObject.toString());
			} catch (RepositoryException e) {
				log.error("Error occured in PSModelAllDiagramServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			} catch (JSONException e) {
				log.error("Error occured in PSModelAllDiagramServlet:doGet() "
						+ e.getMessage() + " Exception: ");
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void modelDiagramPartList(JSONObject jsonObject, String modelNumber, String brandId, String productCategoryId,
			String diagramPageId, String documentId)
			throws JSONException, ValueFormatException, PathNotFoundException, RepositoryException {
		HttpClient client = new HttpClient();
		
		String API_URL = API_ROOT + "models/" + modelNumber + "?brandId=" + brandId + "&productCategoryId="
				+ productCategoryId + "&diagramPageId=" + diagramPageId + "&documentId="
				+ documentId;
		log.info("Rohira: "+API_URL);
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
				jsonObject.put("jsonPartList", method.getResponseBodyAsString());
			}
		} catch (HttpException e) {
			log.error("Error occured in PSModelAllDiagramServlet:modelAllDiagram() "
					+ e.getMessage() + " Exception: ");
		} catch (IOException e) {
			log.error("Error occured in PSModelAllDiagramServlet:modelAllDiagram() "
					+ e.getMessage() + " Exception: ");
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}
}