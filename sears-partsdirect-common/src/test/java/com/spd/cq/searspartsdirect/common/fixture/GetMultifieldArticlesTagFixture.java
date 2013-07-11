package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetMultifieldArticlesTagFixture {

	private PageManager pageManager;
	private ResourceResolver resourceResolver;

	public GetMultifieldArticlesTagFixture(ValueMap properties, PageManager pageManager, ResourceResolver resourceResolver) {
		this.pageManager = pageManager;
		this.resourceResolver = resourceResolver;
		String [] multiJsons = new String[]{
				"{\"url\":\"url1\"}",
				"{\"url\":\"url2\"}",
				"{\"url\":\"url3\"}",
				"{\"url\":\"url4\"}",
				"{\"url\":\"url5\"}",
		};
		when(properties.get("multipaths",new String[0])).thenReturn(multiJsons);
	}
	
	public void setUpTestPages() throws RepositoryException {
		createTestPage("url1");
		createTestPage("url2");
		createTestPage("url3");
		createTestPage("url4");
		createTestPage("url5");
		/*
		String imagePath = page.getPath() + Constants.ASSETS_IMAGE_PATH;
		 37	 2	
		                         Resource imageResource = resourceResolver.getResource(imagePath);
		 38	 2	
		                         if (imageResource == null) {
		 39	 	
		                                 // If we cannot resolve to an image, we return a blank string
		 40	 2	
		                                 imagePath = Constants.EMPTY;
		 41	 	
		                         } else {
		 42	 0	
		                                 Node imageNode = imageResource.adaptTo(Node.class);
		 43	 0	
		                                 if (!(imageNode.hasProperty("fileReference") || imageNode.hasNode("file"))) {
		 44	 	
		                                         // If the image is not set up one way or another, we return a blank string
		 45	 0	
		                                         imagePath = Constants.EMPTY;
		 46	 	
		                                 }
		 47	 	
		                         }
		 48	 	
		                                 
		 49	 2	
		                         if(page.getProperties().containsKey("abstracttext")){
		 50	 0	
		                                 description = page.getProperties().get("abstracttext").toString();
		*/
	}
	
// checking image
	Page createTestPage(String path) throws RepositoryException {
		Page page = mock(Page.class);
		when(pageManager.getPage(path)).thenReturn(page);
		when(page.getPath()).thenReturn(path);
		//page.getProperties().get("abstracttext").toString()
		ValueMap pageProperties = mock(ValueMap.class);
		when(page.getProperties()).thenReturn(pageProperties);
		if (path.endsWith("1")) {
			when(pageProperties.containsKey("abstracttext")).thenReturn(true);
			when(pageProperties.get("abstracttext")).thenReturn("description");
		}
		String imagePath = path + Constants.ASSETS_IMAGE_PATH;
		//Resource imageResource = resourceResolver.getResource(imagePath);
		Resource imageResource = mock(Resource.class);
		if (!path.endsWith("4")) {
			when(resourceResolver.getResource(imagePath)).thenReturn(imageResource);
		}
		Node imageNode = mock(Node.class);
		when(imageResource.adaptTo(Node.class)).thenReturn(imageNode);
		
		if(path.endsWith("2") || path.endsWith("5")){
			when(imageNode.hasProperty("fileReference")).thenReturn(true);
		}
		if(path.endsWith("3")  || path.endsWith("5")) {
			when(imageNode.hasNode("file")).thenReturn(true);
		}
		
		return page;
	}
	
}
