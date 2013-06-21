package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import java.util.List;
import javax.servlet.jsp.tagext.TagSupport;
import org.junit.Before;
import org.junit.Test;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.fixture.GetRelatedPagesTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.AssetType;

public class GetRelatedPagesTagTest extends MocksTag {
	
	private GetRelatedPagesTagFixture fixture;
	private GetRelatedPagesTag tag;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetRelatedPagesTagFixture(resourceResolver, pageManager);
		tag = new GetRelatedPagesTag();
	}
	
	@Test
	public void testAllAssetTypesAllFilters() {
		try {
				tag.setPageContext(pageContext);
				tag.setAssetPath("/etc/spdAssets/scaffolding/commonasset");
				tag.setRootPath("/content/searspartsdirect/en/somepage");
				
				int startResult = tag.doStartTag();
				assertThat(startResult,is(TagSupport.SKIP_BODY));
				int endResult = tag.doEndTag();
				assertThat(endResult,is(TagSupport.EVAL_PAGE));
				
				@SuppressWarnings("unchecked")
				List<Page> result = (List<Page>)pageContext.getAttribute("relatedPages");
				assertThat(result,isA(List.class));
				assertThat(result,hasSize(3));
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
