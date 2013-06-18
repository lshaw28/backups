package com.spd.cq.searspartsdirect.common.model.spdasset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.model.spdasset.PartTypeModel;

public class PartTypeModelTest extends TestCase {
	private PartTypeModel partTypeModel;
	private String path = "path";
	private String title = "title";
	private String description = "description";
	private String imagePath = "imagePath";
	private String path2 = "path2";
	private String title2 = "title2";
	private String description2 = "description2";
	private String imagePath2 = "imagePath2";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		partTypeModel = new PartTypeModel(path,title,description,imagePath);
	}
	
	@Test
	public void testModelUsage() {
		try {
			assertThat(partTypeModel.getPath(),is(path));
			assertThat(partTypeModel.getTitle(),is(title));
			assertThat(partTypeModel.getDescription(),is(description));
			assertThat(partTypeModel.getImagePath(),is(imagePath));
			partTypeModel.setPath(path2);
			partTypeModel.setTitle(title2);
			partTypeModel.setDescription(description2);
			partTypeModel.setImagePath(imagePath2);
			assertThat(partTypeModel.getPath(),is(path2));
			assertThat(partTypeModel.getTitle(),is(title2));
			assertThat(partTypeModel.getDescription(),is(description2));
			assertThat(partTypeModel.getImagePath(),is(imagePath2));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}