package com.spd.cq.searspartsdirect.common.fixture;

import org.apache.sling.api.resource.ValueMap;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

public class GetIconTagFixture {

	private ValueMap pageProperties;
	private PageManager pageManager;
	
	public GetIconTagFixture(Page currentPage, PageManager pageManager) {
		this.pageManager = pageManager;
		pageProperties = mock(ValueMap.class);
		when(currentPage.getProperties()).thenReturn(pageProperties);
	}
	
	public String getDefaultIcon() {
		return "svg-icon-er";
	}
	
	public String getCategoryIcon() {
		return "svg-icon-category";
	}
	
	public String getCurrentPageIcon() {
		return "svg-icon-currentpage";
	}
	
	public String getOtherPageIcon() {
		return "svg-icon-otherpage";
	}

	public ProductCategoryModel getTestCategory() {
		ProductCategoryModel testCategory = mock(ProductCategoryModel.class);
		when(testCategory.getIconClass()).thenReturn(getCategoryIcon());
		return testCategory;
	}
	
	public void setupCurrentPageHasIcon() {
		when(pageProperties.get(any(String.class),any(String.class))).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				String response = null;
				if ("iconImages".equals(args[0])) {
					return getCurrentPageIcon();
				}
				return response;
			}
		});
	}
	
	public void setupCurrentPageHasNoIcon() {
		when(pageProperties.get(any(String.class),any(String.class))).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				String response = null;
				if ("iconImages".equals(args[0])) {
					return (String)args[1];
				}
				return response;
			}
		});
	}
	
	public void setupOtherPageHasIcon() {
		ValueMap otherProps = setupOtherPage();
		when(otherProps.get(any(String.class),any(String.class))).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				String response = null;
				if ("iconImages".equals(args[0])) {
					return getOtherPageIcon();
				}
				return response;
			}
		});
	}
	
	public void setupOtherPageHasNoIcon() {
		ValueMap otherProps = setupOtherPage();
		when(otherProps.get(any(String.class),any(String.class))).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				String response = null;
				if ("iconImages".equals(args[0])) {
					return (String)args[1];
				}
				return response;
			}
		});
	}

	public String getOtherPagePath() {
		return "other";
	}
	
	private ValueMap setupOtherPage() {
		Page otherPage = mock(Page.class);
		when(pageManager.getPage(getOtherPagePath())).thenReturn(otherPage);
		ValueMap otherProps = mock(ValueMap.class);
		when(otherPage.getProperties()).thenReturn(otherProps);
		return otherProps;
	}
	
}
