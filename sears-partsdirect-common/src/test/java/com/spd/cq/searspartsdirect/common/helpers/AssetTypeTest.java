package com.spd.cq.searspartsdirect.common.helpers;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.AssetTypeFixture;
import com.spd.cq.searspartsdirect.common.fixture.ExternalLinksFixture;
import com.spd.cq.searspartsdirect.common.model.spdasset.*;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Verifies that the attributes on the AssetType enum are set up correctly. Current
 * as of 17-June-2013 with 10 members.
 * 
 * Added asserts for AssetType.ACCESSORY, adding tests for abstract methods.
 */
public class AssetTypeTest extends TestCase {

	private AssetTypeFixture fixture;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new AssetTypeFixture();
	}

	@Test
	public void testGetMixedCaseName() {
		assertThat(AssetType.ACCESSORY.getMixedCaseName(),is("accessory"));
		assertThat(AssetType.AUTHOR.getMixedCaseName(),is("author"));
		assertThat(AssetType.BRAND.getMixedCaseName(),is("brand"));
		assertThat(AssetType.ERRORCODE.getMixedCaseName(),is("errorCode"));
		assertThat(AssetType.HAZARD.getMixedCaseName(),is("hazard"));
		assertThat(AssetType.JOBCODE.getMixedCaseName(),is("jobCode"));
		assertThat(AssetType.PARTTYPE.getMixedCaseName(),is("partType"));
		assertThat(AssetType.PRODUCTCATEGORY.getMixedCaseName(),is("productCategory"));
		assertThat(AssetType.SYMPTOM.getMixedCaseName(),is("symptom"));
		assertThat(AssetType.TIP.getMixedCaseName(),is("tip"));
		assertThat(AssetType.WARNING.getMixedCaseName(),is("warning"));
	}
	
	@Test
	public void testGetModelClass() {
		// we use olde-style assertEquals here since hamcrest gives special treatment to Class for "is"
		assertEquals(AssetType.ACCESSORY.getModelClass(),AccessoryModel.class);
		assertEquals(AssetType.AUTHOR.getModelClass(),AuthorModel.class);
		assertEquals(AssetType.BRAND.getModelClass(),BrandModel.class);
		assertEquals(AssetType.ERRORCODE.getModelClass(),ErrorCodeModel.class);
		assertEquals(AssetType.HAZARD.getModelClass(),HazardModel.class);
		assertEquals(AssetType.JOBCODE.getModelClass(),JobCodeModel.class);
		assertEquals(AssetType.PARTTYPE.getModelClass(),PartTypeModel.class);
		assertEquals(AssetType.PRODUCTCATEGORY.getModelClass(),ProductCategoryModel.class);
		assertEquals(AssetType.SYMPTOM.getModelClass(),SymptomModel.class);
		assertEquals(AssetType.TIP.getModelClass(),TipModel.class);
		assertEquals(AssetType.WARNING.getModelClass(),WarningModel.class);
	}

	@Test
	public void testResolvedCreate() {
		for (AssetType aType : AssetType.values()) {
			Object modelInstance;
			modelInstance = aType.createModelInstance(fixture.getPage(), fixture.getProperties());
			assertThat(modelInstance,is(not(nullValue())));
			assertTrue(modelInstance.getClass().equals(aType.getModelClass()));
		}
	}
	
	@Test
	public void testUnresolvedCreate() {
		for (AssetType aType : AssetType.values()) {
			Object modelInstance;
			modelInstance = aType.createModelInstance(fixture.getPagePath(), fixture.getResourceResolver());
			assertThat(modelInstance,is(not(nullValue())));
			assertTrue(modelInstance.getClass().equals(aType.getModelClass()));
		}
	}
}
