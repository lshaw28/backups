package com.spd.cq.searspartsdirect.common.foundation;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ImageApprovalWorkflowProcessFixture;

public class ImageApprovalWorkflowProcessTest extends TestCase {
	private ImageApprovalWorkflowProcess process;
	private ImageApprovalWorkflowProcessFixture fixture;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ImageApprovalWorkflowProcessFixture();
		process = new ImageApprovalWorkflowProcess();
	}

	@Test
	public void testExecute() {
		try {
			fixture.setUpFullTest();
			process.execute(fixture.getTestItem(), fixture.getTestSession(), fixture.getArgs());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testWrongPayloadType() {
		try {
			fixture.setUpWrongPayloadType();
			process.execute(fixture.getTestItem(), fixture.getTestSession(), fixture.getArgs());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testNoSuchNode() {
		try {
			fixture.setUpNoSuchNode();
			process.execute(fixture.getTestItem(), fixture.getTestSession(), fixture.getArgs());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
