package com.spd.cq.searspartsdirect.common.model.spdasset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;

public class SymptomModelTest extends TestCase {
	private SymptomModel symptomModel;
	private String path = "path";
	private String title = "title";
	private String url = "url";
	private String description = "description";
	private String id = "id";
	private String path2 = "path2";
	private String title2 = "title2";
	private String url2 = "url2";
	private String description2 = "description2";
	private String id2 = "id2";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		symptomModel = new SymptomModel(path,title,url,description,id);
	}
	
	@Test
	public void testTagUsage() {
		try {
			assertThat(symptomModel.getPath(),is(path));
			assertThat(symptomModel.getTitle(),is(title));
			assertThat(symptomModel.getSeoURL(),is(url));
			assertThat(symptomModel.getDescription(),is(description));
			assertThat(symptomModel.getId(),is(id));
			symptomModel.setPath(path2);
			symptomModel.setTitle(title2);
			symptomModel.setSeoURL(url2);
			symptomModel.setDescription(description2);
			symptomModel.setId(id2);
			assertThat(symptomModel.getPath(),is(path2));
			assertThat(symptomModel.getTitle(),is(title2));
			assertThat(symptomModel.getSeoURL(),is(url2));
			assertThat(symptomModel.getDescription(),is(description2));
			assertThat(symptomModel.getId(),is(id2));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}