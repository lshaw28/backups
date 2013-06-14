package com.spd.cq.searspartsdirect.common.tags;


import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetAssetsTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.AssetType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetAssetsTagTest extends MocksTag {

	private GetAssetsTagFixture fixture;
	private GetAssetsTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetAssetsTagFixture(resourceResolver);
		tag = new GetAssetsTag();
	}

	@Test
	public void testAllAssetTypesAllFilters() {
		try {
			for (AssetType type : AssetType.values()) {
				tag.setPageContext(pageContext);
				tag.setAssetType(type.toString());
				tag.setBrandFilter("Acme");
				tag.setProductCategoryFilter("Portable holes");
				tag.setTagFilter("Freeze");
				int startResult = tag.doStartTag();
				assertThat(startResult,is(TagSupport.SKIP_BODY));
				int endResult = tag.doEndTag();
				assertThat(endResult,is(TagSupport.EVAL_PAGE));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
