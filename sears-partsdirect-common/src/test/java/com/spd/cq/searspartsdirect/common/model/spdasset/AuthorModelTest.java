package com.spd.cq.searspartsdirect.common.model.spdasset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class AuthorModelTest  extends TestCase {
	private AuthorModel model; // toolModel
	
	private final static String DESCRIPTION = "description";
	private final static String IMAGE_PATH = "ImagePath";
	private final static String PATH = "path";
	private final static String TITLE = "title";
	
	private final static String NEW_ = "new_";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		model = new AuthorModel(PATH, TITLE, DESCRIPTION, IMAGE_PATH);
	}
	
	@Test
	public void testModel() {
		try {
			assertThat(model.getDescription(),is(DESCRIPTION));
			assertThat(model.getImagePath(),is(IMAGE_PATH));
			assertThat(model.getPath(),is(PATH));
			assertThat(model.getTitle(),is(TITLE));
			model.setDescription(NEW_+DESCRIPTION);
			model.setImagePath(NEW_+IMAGE_PATH);
			model.setPath(NEW_+PATH);
			model.setTitle(NEW_+TITLE);
			assertThat(model.getPath(),is(NEW_+PATH));
			assertThat(model.getDescription(),is(NEW_+DESCRIPTION));
			assertThat(model.getTitle(),is(NEW_+TITLE));
			assertThat(model.getImagePath(),is(NEW_+IMAGE_PATH));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}