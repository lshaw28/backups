package com.spd.cq.searspartsdirect.common.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.model.spdasset.TipModel;

public class TipModelTest extends TestCase {
	private TipModel tipModel;
	private String path = "path";
	private String title = "title";
	private String imagePath = "imagePath";
	private String path2 = "path2";
	private String title2 = "title2";
	private String imagePath2 = "imagePath2";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tipModel = new TipModel(path,title,imagePath);
	}
	
	@Test
	public void testTagUsage() {
		try {
			assertThat(tipModel.getPath(),is(path));
			assertThat(tipModel.getTitle(),is(title));
			assertThat(tipModel.getImagePath(),is(imagePath));
			tipModel.setPath(path2);
			tipModel.setTitle(title2);
			tipModel.setImagePath(imagePath2);
			assertThat(tipModel.getPath(),is(path2));
			assertThat(tipModel.getTitle(),is(title2));
			assertThat(tipModel.getImagePath(),is(imagePath2));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}