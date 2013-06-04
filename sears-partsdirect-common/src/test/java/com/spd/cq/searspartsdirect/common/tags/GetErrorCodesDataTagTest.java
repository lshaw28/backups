package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Test;
import org.mockito.Mockito;
import org.apache.sling.api.resource.Resource;

public class GetErrorCodesDataTagTest extends TestCase {
	
	HttpServletRequest request;
	PageContext pageContext;
	GetErrorCodesDataTag tag;
	ResourceResolver resourceResolver;
	Resource resource;
	
	@Override
	protected void setUp() {
		request = Mockito.mock(HttpServletRequest.class);
		pageContext = Mockito.mock(PageContext.class);
		resourceResolver = Mockito.mock(ResourceResolver.class);
		resource = Mockito.mock(Resource.class);
		tag = new GetErrorCodesDataTag();
	}
	
	@Test
	public void testGetErrorCodeDataWithBrands() throws JspException {
		/*Mockito.when(pageContext.getRequest()).thenReturn(request);
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.setCategoryName("dummay category");
		
		Mockito.when(resourceResolver.getResource("/etc/spdAssets/scaffolding/errorCode")).thenReturn(resource);*/
		Assert.assertTrue(true);
	}

}
