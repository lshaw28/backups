package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.tagging.InvalidTagFormatException;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;


public class TagGenerationServletFixture {
	private SlingHttpServletRequest request;
	private SlingHttpServletResponse response;
	private TagManager tm;
	private ResourceResolver resourceResolver;
	private String csv;
	private RequestParameter requestParameter;
	
	public TagGenerationServletFixture() {
		request = mock(SlingHttpServletRequest.class);
		response = mock(SlingHttpServletResponse.class);
		resourceResolver = mock(ResourceResolver.class);
		when(request.getResourceResolver()).thenReturn(resourceResolver);
		requestParameter = mock(RequestParameter.class);
		when(request.getRequestParameter("file")).thenReturn(requestParameter);
	}
	
	public void setUpNamespace() {
		StringBuilder sb = new StringBuilder();
		sb.append("Product Categories,a,a,a\r\n");
		sb.append("Product Categories,b,b,n/a\r\n");
		sb.append("Product Categories,b,b,b\r\n");
		sb.append("Product Categories,c,c,c\r\n");
		sb.append("Not Product Categories,a,b,n/a");
		csv = sb.toString();
		
		InputStream inputStream = new ByteArrayInputStream(csv.getBytes());
		try {
			when(requestParameter.getInputStream()).thenReturn(inputStream);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		tm = mock(TagManager.class);
		when(resourceResolver.adaptTo(TagManager.class)).thenReturn(tm);
		Tag topLevel = mock(Tag.class);
		Tag parentCategories = mock(Tag.class);
		Tag subcategories = mock(Tag.class);
		when(tm.resolve("searspartsdirect:")).thenReturn(topLevel);
		when(tm.resolve("searspartsdirect:parent_categories")).thenReturn(parentCategories);
		when(tm.resolve("searspartsdirect:subcategories")).thenReturn(subcategories);
		
		Tag check = mock(Tag.class);
		when(tm.resolve("searspartsdirect:parent_categories/a")).thenReturn(check);
		when(tm.resolve("searspartsdirect:parent_categories/b")).thenReturn(null);
		when(tm.resolve("searspartsdirect:parent_categories/c")).thenReturn(null);
		
		when(tm.resolve("searspartsdirect:subcategories/a")).thenReturn(check);
		when(tm.resolve("searspartsdirect:subcategories/b")).thenReturn(null);
		when(tm.resolve("searspartsdirect:subcategories/c")).thenReturn(null);
		try {
			when(tm.createTag("searspartsdirect:parent_categories/b", "b", "", true)).thenReturn(check);
			when(tm.createTag("searspartsdirect:subcategories/b", "b", "", true)).thenReturn(check);
			when(tm.createTag("searspartsdirect:parent_categories/c", "c", "", true)).thenThrow(new InvalidTagFormatException("test"));
			when(tm.createTag("searspartsdirect:subcategories/c", "c", "", true)).thenThrow(new InvalidTagFormatException("test"));
			when(check.getTagID()).thenReturn("b");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setUpNullNamespace() {
		csv = "";
		
		InputStream inputStream = new ByteArrayInputStream(csv.getBytes());
		try {
			when(requestParameter.getInputStream()).thenReturn(inputStream);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		tm = mock(TagManager.class);
		when(resourceResolver.adaptTo(TagManager.class)).thenReturn(tm);
		Tag topLevel = mock(Tag.class);
		Tag parentCategories = mock(Tag.class);
		Tag subcategories = mock(Tag.class);
		when(tm.resolve("searspartsdirect:")).thenReturn(null);
		when(tm.resolve("searspartsdirect:parent_categories")).thenReturn(null);
		when(tm.resolve("searspartsdirect:subcategories")).thenReturn(null);
		try {
			when(tm.createTag("searspartsdirect:", "Sears Parts Direct", "Namespace for Sears Parts Direct")).thenReturn(topLevel);
			when(tm.createTag("searspartsdirect:parent_categories", "Parent Categories", "Tag for Parent Categories")).thenReturn(parentCategories);
			when(tm.createTag("searspartsdirect:subcategories", "Subcategories", "Tag for Subcategories")).thenReturn(subcategories);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setUpExceptionOne() {
		csv = "";
		
		InputStream inputStream = new ByteArrayInputStream(csv.getBytes());
		try {
			when(requestParameter.getInputStream()).thenReturn(inputStream);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		tm = mock(TagManager.class);
		when(resourceResolver.adaptTo(TagManager.class)).thenReturn(tm);
		Tag topLevel = mock(Tag.class);
		Tag parentCategories = mock(Tag.class);
		Tag subcategories = mock(Tag.class);
		when(tm.resolve("searspartsdirect:")).thenReturn(null);
		when(tm.resolve("searspartsdirect:parent_categories")).thenReturn(null);
		when(tm.resolve("searspartsdirect:subcategories")).thenReturn(null);
		try {
			when(tm.createTag("searspartsdirect:", "Sears Parts Direct", "Namespace for Sears Parts Direct")).thenThrow(new InvalidTagFormatException("test"));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setUpExceptionTwo() {
		csv = "";
		
		InputStream inputStream = new ByteArrayInputStream(csv.getBytes());
		try {
			when(requestParameter.getInputStream()).thenReturn(inputStream);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		tm = mock(TagManager.class);
		when(resourceResolver.adaptTo(TagManager.class)).thenReturn(tm);
		Tag topLevel = mock(Tag.class);
		Tag parentCategories = mock(Tag.class);
		Tag subcategories = mock(Tag.class);
		when(tm.resolve("searspartsdirect:")).thenReturn(null);
		when(tm.resolve("searspartsdirect:parent_categories")).thenReturn(null);
		when(tm.resolve("searspartsdirect:subcategories")).thenReturn(null);
		try {
			when(tm.createTag("searspartsdirect:", "Sears Parts Direct", "Namespace for Sears Parts Direct")).thenReturn(topLevel);
			when(tm.createTag("searspartsdirect:parent_categories", "Parent Categories", "Tag for Parent Categories")).thenThrow(new InvalidTagFormatException("test"));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setUpExceptionThree() {
		csv = "";
		
		InputStream inputStream = new ByteArrayInputStream(csv.getBytes());
		try {
			when(requestParameter.getInputStream()).thenReturn(inputStream);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		tm = mock(TagManager.class);
		when(resourceResolver.adaptTo(TagManager.class)).thenReturn(tm);
		Tag topLevel = mock(Tag.class);
		Tag parentCategories = mock(Tag.class);
		Tag subcategories = mock(Tag.class);
		when(tm.resolve("searspartsdirect:")).thenReturn(null);
		when(tm.resolve("searspartsdirect:parent_categories")).thenReturn(null);
		when(tm.resolve("searspartsdirect:subcategories")).thenReturn(null);
		try {
			when(tm.createTag("searspartsdirect:", "Sears Parts Direct", "Namespace for Sears Parts Direct")).thenReturn(topLevel);
			when(tm.createTag("searspartsdirect:parent_categories", "Parent Categories", "Tag for Parent Categories")).thenReturn(parentCategories);
			when(tm.createTag("searspartsdirect:subcategories", "Subcategories", "Tag for Subcategories")).thenThrow(new InvalidTagFormatException("test"));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public SlingHttpServletRequest getRequest() {
		return request;
	}
	public SlingHttpServletResponse getResponse() {
		return response;
	}
}