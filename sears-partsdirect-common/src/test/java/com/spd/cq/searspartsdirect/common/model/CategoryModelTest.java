package com.spd.cq.searspartsdirect.common.model;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class CategoryModelTest extends TestCase {

	private static final String URL = "url";
	private static final String IMAGE_PATH = "imagePath";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	
	private static final String ANOTHER = "another_";
	
	private CategoryModel model;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testCategoryModel() {
		model = new CategoryModel(URL, IMAGE_PATH, TITLE, DESCRIPTION);
		assertThat(model.getUrl(),is(URL));
		assertThat(model.getImagePath(),is(IMAGE_PATH));
		assertThat(model.getTitle(),is(TITLE));
		assertThat(model.getDescription(),is(DESCRIPTION));

		model.setUrl(ANOTHER+URL);
		model.setImagePath(ANOTHER+IMAGE_PATH);
		model.setTitle(ANOTHER+TITLE);
		model.setDescription(ANOTHER+DESCRIPTION);
		
		assertThat(model.getUrl(),is(ANOTHER+URL));
		assertThat(model.getImagePath(),is(ANOTHER+IMAGE_PATH)); // note - this concatenation happens at compile time
		assertThat(model.getTitle(),is(ANOTHER+TITLE));
		assertThat(model.getDescription(),is(ANOTHER+DESCRIPTION));
	}

}
