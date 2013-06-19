package com.spd.cq.searspartsdirect.common.model;


import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
		assertThat(model.getTitle(),is(TITLE));
		model.setUrl(ANOTHER+URL);
		model.setTitle(ANOTHER+TITLE);
		assertThat(model.getUrl(),is(ANOTHER+URL));
		assertThat(model.getTitle(),is(ANOTHER+TITLE)); // note - this concatenation happens at compile time
	}

}
