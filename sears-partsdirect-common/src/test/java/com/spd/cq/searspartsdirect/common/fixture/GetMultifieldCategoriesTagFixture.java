package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.CategoryModel;

public class GetMultifieldCategoriesTagFixture {
	
	private ValueMap properties;
	private PageManager pageManager;
	private ResourceResolver resourceResolver;

	public GetMultifieldCategoriesTagFixture(ValueMap properties, PageManager pageManager) throws JSONException {
		this.properties = properties;
		this.pageManager = pageManager;
		String [] multiJsons = new String[]{
				"{\"url\":\"url1\"}",
				"{\"url\":\"url2\"}",
		};
		when(properties.get("multipaths",new String[0])).thenReturn(multiJsons);
	}
	
// checking image
	Page createTestPage(String path) throws RepositoryException {
		Page page = mock(Page.class);
		when(pageManager.getPage(path)).thenReturn(page);
		//page.getProperties().get("abstracttext").toString()
		ValueMap pageProperties = mock(ValueMap.class);
		when(page.getProperties()).thenReturn(pageProperties);
		when(pageProperties.get("abstracttext",Constants.EMPTY)).thenReturn("description");
			String imagePath = path + Constants.ASSETS_IMAGE_PATH;
			//Resource imageResource = resourceResolver.getResource(imagePath);
			Resource imageResource = mock(Resource.class);
			when(resourceResolver.getResource(imagePath)).thenReturn(imageResource);
			Node imageNode = mock(Node.class);
			when(imageResource.adaptTo(Node.class)).thenReturn(imageNode);
			
			if(path.endsWith("2")){
				when(imageNode.hasProperty("fileReference")).thenReturn(true);
			}
			else{
				when(imageNode.hasNode("file")).thenReturn(true);
			}
		
		return page;
	}
	
}
