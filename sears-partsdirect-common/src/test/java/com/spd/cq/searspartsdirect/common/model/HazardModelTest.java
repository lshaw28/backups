package com.spd.cq.searspartsdirect.common.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.model.spdasset.HazardModel;

public class HazardModelTest extends TestCase {
	private HazardModel hazardModel;
	private String path = "path";
	private String title = "title";
	private String imagePath = "imagePath";
	private String path2 = "path2";
	private String title2 = "title2";
	private String imagePath2 = "imagePath2";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		hazardModel = new HazardModel(path,title,imagePath);
	}
	
	@Test
	public void testTagUsage() {
		try {
			assertThat(hazardModel.getPath(),is(path));
			assertThat(hazardModel.getTitle(),is(title));
			assertThat(hazardModel.getImagePath(),is(imagePath));
			hazardModel.setPath(path2);
			hazardModel.setTitle(title2);
			hazardModel.setImagePath(imagePath2);
			assertThat(hazardModel.getPath(),is(path2));
			assertThat(hazardModel.getTitle(),is(title2));
			assertThat(hazardModel.getImagePath(),is(imagePath2));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}