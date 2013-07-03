package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.ValueMap;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetUrlRelationTagFixture {

	private final List<String> selectors;
	
	private final String BRAND = "brand";
	private final String CATEGORY = "category";
	private final String MODEL = "model";

	private PageManager pageManager;
	private SlingHttpServletRequest slingRequest;
	
	public GetUrlRelationTagFixture(SlingHttpServletRequest slingRequest, PageManager pageManager) {
		this.pageManager = pageManager;
		this.slingRequest = slingRequest;
		selectors = new ArrayList<String>();
		RequestPathInfo rpi = mock(RequestPathInfo.class);
		when(slingRequest.getRequestPathInfo()).thenReturn(rpi);
		when(rpi.getSelectors()).thenAnswer(new Answer<String[]>() {
			public String[] answer(InvocationOnMock invocation)
					throws Throwable {
				return selectors.toArray(new String[0]);
			}
		});
	}

	public void setUpSelectors() {
		selectors.clear();
		selectors.add(BRAND);
		selectors.add(CATEGORY);
		selectors.add(MODEL);
	}
	
	public void setUpProductCategory() {
		String relatedAssetPath = Constants.ASSETS_PATH + "/productCategory/" + CATEGORY;
		Page p = mock(Page.class);
		when(pageManager.getPage(relatedAssetPath)).thenReturn(p);
		ValueMap properties = mock(ValueMap.class);
		when(p.getProperties()).thenReturn(properties);
	}
	
	public void setUpProductCategoryUri() {
		when(slingRequest.getRequestURI()).thenReturn(CATEGORY+"-repair/");
	}
	
	public void setUpJRandomUri() {
		when(slingRequest.getRequestURI()).thenReturn(CATEGORY+"-aupair/");
	}

	public void setUpBrand() {
		String relatedAssetPath = Constants.ASSETS_PATH + "/brand/" + BRAND;
		Page p = mock(Page.class);
		when(pageManager.getPage(relatedAssetPath)).thenReturn(p);
		ValueMap properties = mock(ValueMap.class);
		when(p.getProperties()).thenReturn(properties);
	}

	public Object getModel() {
		return MODEL;
	}

}
