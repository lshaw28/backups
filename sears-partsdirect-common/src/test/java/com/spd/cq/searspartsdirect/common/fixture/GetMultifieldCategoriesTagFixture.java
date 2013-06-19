package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.CategoryModel;

public class GetMultifieldCategoriesTagFixture {
	
	private PageManager pageManager;
	
	public GetMultifieldCategoriesTagFixture(PageContext pageContext, PageManager pageManager) throws JSONException {
		this.pageManager = pageManager;
		ArrayList<CategoryModel> categories = new ArrayList<CategoryModel>();
		for(int i = 0; i < 3; i++){
			Page page = mock(Page.class);
			String categoryPath = "/pathToCategory"+i;
			JSONObject jsob = mock(JSONObject.class);
			when(jsob.getString("urlField")).thenReturn(categoryPath);
			when(pageManager.getPage(jsob.getString("urlField"))).thenReturn(page);
			ValueMap properties = mock(ValueMap.class);
			when(page.getProperties()).thenReturn(properties);
			when(page.getProperties().get("abstracttext", "")).thenReturn("Some description ");
			when(page.getPath()).thenReturn(categoryPath);
			when(page.getTitle()).thenReturn(categoryPath);
			String imagePath = page.getPath() + Constants.ASSETS_IMAGE_PATH;
    		String description = (String) page.getProperties().get("abstracttext");
			CategoryModel category = new CategoryModel(
					page.getPath(),
					imagePath,
					page.getTitle(),
					description);
			categories.add(category);
		}
		pageContext.setAttribute("categories", categories);
	}
	
}
