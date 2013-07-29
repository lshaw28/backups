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
import com.spd.cq.searspartsdirect.common.helpers.PDUtils;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

public class GetMultifieldArticlesTagFixture {

	private PageManager pageManager;
	private ResourceResolver resourceResolver;

	public GetMultifieldArticlesTagFixture(ValueMap properties, PageManager pageManager, ResourceResolver resourceResolver) {
		this.pageManager = pageManager;
		this.resourceResolver = resourceResolver;
		
		Page categoryPage = mock(Page.class);
		when(pageManager.getPage(getCategory().getPath())).thenReturn(categoryPage);
		ValueMap categoryProps = mock(ValueMap.class);
		when(categoryPage.getProperties()).thenReturn(categoryProps);
		
		String [] multiJsons = new String[]{
				"{\"url\":\"url1\"}",
				"{\"url\":\"url2\"}",
				"{\"url\":\"url3\"}",
				"{\"url\":\"url4\"}",
				"{\"url\":\"url5\"}",
		};
		//String header = assetProperties.get("header", "");
		//String link = assetProperties.get("viewAllLinkText", "");
		when(categoryProps.get("multipaths",new String[0])).thenReturn(multiJsons);
		when(categoryProps.get("header", "")).thenReturn(getHeader());
		when(categoryProps.get("viewAllLinkText", "")).thenReturn(getViewAllLinkText());
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
	
	public void setupAuxPages() {
		Page categoryPage = mock(Page.class);
		when(pageManager.getPage(getCategory().getPath())).thenReturn(categoryPage);
		ValueMap categoryProps = mock(ValueMap.class);
		when(categoryPage.getProperties()).thenReturn(categoryProps);
		when(categoryProps.get("includeCommonParts", "")).thenReturn("true");
		when(categoryProps.get("includeMaintenanceTips", "")).thenReturn("true");
		when(categoryProps.get("includeCommonQuestions", "")).thenReturn("true");
		
		Page auxPage = mock(Page.class);
		when(pageManager.getPage(getCommonPartsPath(getCategory().getTrueName()))).thenReturn(auxPage);
		when(pageManager.getPage(getCommonQnsPath(getCategory().getTrueName()))).thenReturn(auxPage);
		when(pageManager.getPage(getMaintTipsPath(getCategory().getTrueName()))).thenReturn(auxPage);
		when(PDUtils.getPageForCategoryByPath(pageManager, getCommonPartsPath(getCategory().getTrueName()))).thenReturn(true);
		when(PDUtils.getPageForCategoryByPath(pageManager, getCommonQnsPath(getCategory().getTrueName()))).thenReturn(true);
		when(PDUtils.getPageForCategoryByPath(pageManager, getMaintTipsPath(getCategory().getTrueName()))).thenReturn(true);
		
		ValueMap auxPageProps = mock(ValueMap.class);
		when(auxPage.getProperties()).thenReturn(auxPageProps);
		when(auxPageProps.get("pages", String[].class)).thenReturn(new String[]{"clinker"});
	}

	public ProductCategoryModel getCategory() {
		ProductCategoryModel category = new ProductCategoryModel("category", Constants.ASSETS_PATH+"/"+Constants.ASSETS_PRODUCT_CATEGORY_PATH+"/reciprobopulator", 
						"Category", "Categories", "description", null, null, null);
		return category;
	}
	
	public String getHeader() {
		return "Header";
	}
	
	public String getViewAllLinkText() {
		return "View All";
	}
	String getCommonPartsPath(String categoryName) {
		return Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" +categoryName+Constants.COMMON_PARTS_PATH_SUFFIX;
	}
	
	String getCommonQnsPath(String categoryName) {
		return Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" +categoryName+Constants.COMMON_QUESTIONS_PATH_SUFFIX;
	}
	
	String getMaintTipsPath(String categoryName) {
		return Constants.CATEGORIES_ROOT + "/" + categoryName + Constants.CATEGORY_PATH_SUFFIX + "/" +categoryName+Constants.MAINTENANCE_TIPS_PATH_SUFFIX;
	}
}
