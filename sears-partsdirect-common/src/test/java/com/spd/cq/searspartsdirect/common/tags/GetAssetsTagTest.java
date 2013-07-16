package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetAssetsTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.AssetType;

public class GetAssetsTagTest extends MocksTag {

	private GetAssetsTagFixture fixture;
	private GetAssetsTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetAssetsTagFixture(resourceResolver,pageManager);
		tag = new GetAssetsTag();
	}

	@Test
	public void testAllAssetTypesAllFilters() throws RepositoryException {
		fixture.setUpTestHits();
		try {
			for (AssetType type : AssetType.values()) {
				tag.setPageContext(pageContext);
				tag.setAssetType(type.toString());
				tag.setBrandFilter("Acme");
				tag.setProductCategoryFilter("Portable holes");
				tag.setTagFilter("Freeze");
				tag.setAuthorFilter("Staff");
				int startResult = tag.doStartTag();
				assertThat(startResult,is(TagSupport.SKIP_BODY));
				int endResult = tag.doEndTag();
				assertThat(endResult,is(TagSupport.EVAL_PAGE));
				@SuppressWarnings("unchecked")
				List<Object> result = (List<Object>)pageContext.getAttribute(type.toString()+"List");
				assertThat(result,isA(List.class));
				assertThat(result,hasSize(3));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testAllAssetTypesAllExploding() throws RepositoryException {
		fixture.setUpExplodingHits();
		try {
			for (AssetType type : AssetType.values()) {
				tag.setPageContext(pageContext);
				tag.setAssetType(type.toString());
				tag.setBrandFilter("Acme");
				tag.setProductCategoryFilter("Portable holes");
				tag.setTagFilter("Freeze");
				tag.setAuthorFilter("Staff");
				int startResult = tag.doStartTag();
				assertThat(startResult,is(TagSupport.SKIP_BODY));
				int endResult = tag.doEndTag();
				assertThat(endResult,is(TagSupport.EVAL_PAGE));
				@SuppressWarnings("unchecked")
				List<Object> result = (List<Object>)pageContext.getAttribute(type.toString()+"List");
				assertThat(result,isA(List.class));
				assertThat(result,hasSize(0));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testAllAssetTypesNoFilters() throws RepositoryException {
		fixture.setUpTestHits();
		try {
			for (AssetType type : AssetType.values()) {
				tag.setPageContext(pageContext);
				tag.setAssetType(type.toString());
				int startResult = tag.doStartTag();
				assertThat(startResult,is(TagSupport.SKIP_BODY));
				int endResult = tag.doEndTag();
				assertThat(endResult,is(TagSupport.EVAL_PAGE));
				@SuppressWarnings("unchecked")
				List<Object> result = (List<Object>)pageContext.getAttribute(type.toString()+"List");
				assertThat(result,isA(List.class));
				assertThat(result,hasSize(3));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
