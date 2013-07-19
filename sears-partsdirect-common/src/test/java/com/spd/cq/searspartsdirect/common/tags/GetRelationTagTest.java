package com.spd.cq.searspartsdirect.common.tags;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetRelationTagFixture;
import com.spd.cq.searspartsdirect.common.helpers.AssetType;

public class GetRelationTagTest extends MocksTag {

	private GetRelationTag tag;
	private GetRelationTagFixture fixture;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetRelationTagFixture(pageManager,currentPage);
		tag = new GetRelationTag();
	}
	
	public void doStartAndEnd() throws Exception {
		int startResult = tag.doStartTag();
		assertThat(startResult,is(TagSupport.SKIP_BODY));		
		int endResult = tag.doEndTag();
		assertThat(endResult,is(TagSupport.EVAL_PAGE));
	}
	
	@Test
	public void testSingleThisPage() {
		try {
			AssetType[] allTypes = AssetType.values();
			for (AssetType aType : allTypes) {
				
				tag.setPageContext(pageContext);
				String assetTypeName = aType.getMixedCaseName();
				tag.setAssetType(assetTypeName);
				tag.setSingle("true");
				doStartAndEnd();
				Object modeled = pageContext.getAttribute(assetTypeName+"Relation");
				assertThat(modeled,is(not(nullValue())));
				assertEquals(modeled.getClass(),aType.getModelClass()); 
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testSingleThisPageWhenNoneAreSetUp() {
		fixture.breakThisPageRelations();
		try {
			AssetType[] allTypes = AssetType.values();
			for (AssetType aType : allTypes) {
				
				tag.setPageContext(pageContext);
				String assetTypeName = aType.getMixedCaseName();
				tag.setAssetType(assetTypeName);
				tag.setSingle("true");
				doStartAndEnd();
				Object modeled = pageContext.getAttribute(assetTypeName+"Relation");
				assertThat(modeled,is(nullValue()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testSingleThatPage() {
		try {
			AssetType[] allTypes = AssetType.values();
			for (AssetType aType : allTypes) {
				
				tag.setPageContext(pageContext);
				String assetTypeName = aType.getMixedCaseName();
				tag.setAssetType(assetTypeName);
				tag.setPagepath(fixture.getThatPagepath());
				tag.setSingle("true");
				doStartAndEnd();
				Object modeled = pageContext.getAttribute(assetTypeName+"Relation");
				assertThat(modeled,is(not(nullValue())));
				assertEquals(modeled.getClass(),aType.getModelClass());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testMultipleThisPage() {
		try {
			AssetType[] allTypes = AssetType.values();
			for (AssetType aType : allTypes) {
				
				tag.setPageContext(pageContext);
				String assetTypeName = aType.getMixedCaseName();
				tag.setAssetType(assetTypeName);
				//tag.setSingle("false");
				doStartAndEnd();
				List<?> modeled = (List<?>)pageContext.getAttribute(assetTypeName+"RelationList");
				assertThat(modeled,is(not(nullValue())));
				assertThat(modeled,hasSize(3));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testMultipleThatPage() {
		try {
			AssetType[] allTypes = AssetType.values();
			for (AssetType aType : allTypes) {
				
				tag.setPageContext(pageContext);
				String assetTypeName = aType.getMixedCaseName();
				tag.setAssetType(assetTypeName);
				tag.setPagepath(fixture.getThatPagepath());
				tag.setSingle("false");
				doStartAndEnd();
				List<?> modeled = (List<?>)pageContext.getAttribute(assetTypeName+"RelationList");
				assertThat(modeled,is(not(nullValue())));
				assertThat(modeled,hasSize(3));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
