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
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

public class GetMultifieldArticlesTagFixture {

	private PageManager pageManager;
	private ResourceResolver resourceResolver;
	private Page currentPage;

	public GetMultifieldArticlesTagFixture(ValueMap properties, PageManager pageManager, ResourceResolver resourceResolver, Page currentPage) {
		this.pageManager = pageManager;
		this.resourceResolver = resourceResolver;
		this.currentPage = currentPage;
		
		Page categoryPage = mock(Page.class);
		when(pageManager.getPage(getCategory().getPath())).thenReturn(categoryPage);
		ValueMap categoryProps = mock(ValueMap.class);
		when(categoryPage.getProperties()).thenReturn(categoryProps);
		when(categoryProps.get("includeCommonParts", "")).thenReturn("true");
		when(categoryProps.get("includeMaintenanceTips", "")).thenReturn("true");
		when(categoryProps.get("includeCommonQuestions", "")).thenReturn("true");
		
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
		Page auxPage = mock(Page.class);
		ValueMap auxPageProps = mock(ValueMap.class);
		when(auxPage.getProperties()).thenReturn(auxPageProps);
		when(auxPageProps.get("pages", String[].class)).thenReturn(new String[]{"/productCategory/clinker"});
		when(auxPage.getPath()).thenReturn("/somePath");
		
		when(pageManager.getPage(getCommonPartsPath(getCategory().getTrueName()))).thenReturn(auxPage);
		when(pageManager.getPage(getCommonQnsPath(getCategory().getTrueName()))).thenReturn(auxPage);
		when(pageManager.getPage(getMaintTipsPath(getCategory().getTrueName()))).thenReturn(auxPage);
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
	
	public void setCurrentPagePath() {
		when(currentPage.getPath()).thenReturn("/mypath");
	}
}
