package com.spd.cq.searspartsdirect.common.model.spdasset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.model.ToolModel;

public class ToolModelTest  extends TestCase {
	private ToolModel toolModel; // toolModel
	private String text = "text";
	private String id = "id";
	private String text2 = "text2";
	private String id2 = "id2";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		toolModel = new ToolModel(text,id, "");
	}
	
	@Test
	public void testTagUsage() {
		try {
			assertThat(toolModel.getText(),is(text));
			assertThat(toolModel.getId(),is(id));
			toolModel.setText(text2);
			toolModel.setId(id2);
			assertThat(toolModel.getText(),is(text2));
			assertThat(toolModel.getId(),is(id2));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}