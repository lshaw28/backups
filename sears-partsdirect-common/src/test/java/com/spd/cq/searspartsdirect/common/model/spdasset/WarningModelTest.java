package com.spd.cq.searspartsdirect.common.model.spdasset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class WarningModelTest extends TestCase {
	private WarningModel warningModel;
	private String path = "path";
	private String title = "title";
	private String imagePath = "imagePath";
	private String path2 = "path2";
	private String title2 = "title2";
	private String imagePath2 = "imagePath2";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		warningModel = new WarningModel(path,title,imagePath);
	}
	
	@Test
	public void testTagUsage() {
		try {
			assertThat(warningModel.getPath(),is(path));
			assertThat(warningModel.getTitle(),is(title));
			assertThat(warningModel.getImagePath(),is(imagePath));
			warningModel.setPath(path2);
			warningModel.setTitle(title2);
			warningModel.setImagePath(imagePath2);
			assertThat(warningModel.getPath(),is(path2));
			assertThat(warningModel.getTitle(),is(title2));
			assertThat(warningModel.getImagePath(),is(imagePath2));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}