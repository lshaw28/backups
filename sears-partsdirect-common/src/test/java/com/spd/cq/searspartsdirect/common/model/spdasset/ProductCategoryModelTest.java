package com.spd.cq.searspartsdirect.common.model.spdasset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

public class ProductCategoryModelTest extends TestCase {
	private ProductCategoryModel productCategoryModel;
	private String path = "path";
	private String title = "title";
	private String singular = "singular";
	private String description = "description";
	private String articleIndexCopy = "articleIndexCopy";
	private String category101 = "category101";
	private String imagePath = "imagePath";
	private String path2 = "path2";
	private String title2 = "title2";
	private String singular2 = "singular2";
	private String description2 = "description2";
	private String imagePath2 = "imagePath2";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		productCategoryModel = new ProductCategoryModel(path,path,title,singular,description,articleIndexCopy,category101,imagePath);
	}
	
	@Test
	public void testTagUsage() {
		try {
			assertThat(productCategoryModel.getPath(),is(path));
			assertThat(productCategoryModel.getTitle(),is(title));
			assertThat(productCategoryModel.getPluralTitle(),is(singular));
			assertThat(productCategoryModel.getDescription(),is(description));
			assertThat(productCategoryModel.getImagePath(),is(imagePath));
			productCategoryModel.setPath(path2);
			productCategoryModel.setTitle(title2);
			productCategoryModel.setPluralTitle(singular2);
			productCategoryModel.setDescription(description2);
			productCategoryModel.setImagePath(imagePath2);
			assertThat(productCategoryModel.getPath(),is(path2));
			assertThat(productCategoryModel.getTitle(),is(title2));
			assertThat(productCategoryModel.getPluralTitle(),is(singular2));
			assertThat(productCategoryModel.getDescription(),is(description2));
			assertThat(productCategoryModel.getImagePath(),is(imagePath2));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}