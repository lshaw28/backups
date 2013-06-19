package com.spd.cq.searspartsdirect.common.foundation;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ImageDenialWorkflowProcessFixture;

public class ImageDenialWorkflowProcessTest extends TestCase {
	private ImageDenialWorkflowProcess process;
	private ImageDenialWorkflowProcessFixture fixture;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ImageDenialWorkflowProcessFixture();
		process = new ImageDenialWorkflowProcess();
	}

	@Test
	public void testExecute() {
		try {
			process.execute(fixture.getItem(), fixture.getSession(), fixture.getArgs());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}