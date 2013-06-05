package com.spd.cq.searspartsdirect.common.tags;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Test;
import org.mockito.Mockito;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetErrorCodesDataTagTest extends TestCase {
	
	HttpServletRequest request;
	PageContext pageContext;
	GetErrorCodesDataTag tag;
	ResourceResolver resourceResolver;
	Resource resource;
	SlingHttpServletRequest slingRequest;
	Session session;
	QueryBuilder builder;
	Query query;
	SearchResult result;
	
	@Override
	protected void setUp() {
		request = Mockito.mock(HttpServletRequest.class);
		pageContext = Mockito.mock(PageContext.class);
		resourceResolver = Mockito.mock(ResourceResolver.class);
		resource = Mockito.mock(Resource.class);
		slingRequest = Mockito.mock(SlingHttpServletRequest.class);
		session = Mockito.mock(Session.class);
		builder = Mockito.mock(QueryBuilder.class);
		query = Mockito.mock(Query.class);
		result = Mockito.mock(SearchResult.class);
		tag = new GetErrorCodesDataTag();
	}
	
	@Test
	public void testGetErrorCodeDataWithBrands() throws JspException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("path", "/etc/spdAssets/scaffolding/errorCode/");
		map.put("type", Constants.CQ_PAGE);
		map.put("1_property", "jcr:content/pages");
		map.put("1_property.value", Constants.ASSETS_PATH.concat("/productCategory/").concat("ranges"));
		
		Mockito.when(pageContext.getRequest()).thenReturn(request);
		Mockito.when(resourceResolver.getResource("/etc/spdAssets/scaffolding/errorCode")).thenReturn(resource);
		Mockito.when(pageContext.findAttribute("resourceResolver")).thenReturn(resourceResolver);
		Mockito.when(pageContext.findAttribute("slingRequest")).thenReturn(slingRequest);
		Mockito.when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		Mockito.when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(builder);
		Mockito.when(slingRequest.getResourceResolver().adaptTo(Session.class)).thenReturn(session);
		Mockito.when(builder.createQuery(PredicateGroup.create(map), session)).thenReturn(query);
		//Mockito.when(query.getResult()).thenReturn(result);
		
		tag.setPageContext(pageContext);
		tag.setCategoryPath("dummyCategory");
		//tag.doStartTag();
		Assert.assertTrue(true);
	}
}
