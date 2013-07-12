package com.spd.cq.searspartsdirect.common.model.spdasset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class JobCodeModelTest extends TestCase {
	private JobCodeModel jobCodeModel;
	private String path = "path";
	private String title = "title";
	private String description = "description";
	private String path2 = "path2";
	private String title2 = "title2";
	private String description2 = "description2";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		jobCodeModel = new JobCodeModel(path,title,description);
	}
	
	@Test
	public void testTagUsage() {
		try {
			assertThat(jobCodeModel.getPath(),is(path));
			assertThat(jobCodeModel.getTitle(),is(title));
			assertThat(jobCodeModel.getDescription(),is(description));
			jobCodeModel.setPath(path2);
			jobCodeModel.setTitle(title2);
			jobCodeModel.setDescription(description2);
			assertThat(jobCodeModel.getPath(),is(path2));
			assertThat(jobCodeModel.getTitle(),is(title2));
			assertThat(jobCodeModel.getDescription(),is(description2));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}