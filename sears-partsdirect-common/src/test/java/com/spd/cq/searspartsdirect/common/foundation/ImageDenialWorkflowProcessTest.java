package com.spd.cq.searspartsdirect.common.foundation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import javax.jcr.RepositoryException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.adobe.granite.workflow.WorkflowException;
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
	public void testCorrectPayload() {
		try {
			fixture.setUpCorrectPayload();
			process.execute(fixture.getItem(), fixture.getSession(), fixture.getArgs());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testIncorrectPayload() {
		try {
			fixture.setUpIncorrectPayload();
			process.execute(fixture.getItem(), fixture.getSession(), fixture.getArgs());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testNullPayload() {
		try {
			fixture.setUpNullPayload();
			process.execute(fixture.getItem(), fixture.getSession(), fixture.getArgs());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testExceptions() {
		try {
			fixture.setUpExceptions();
			process.execute(fixture.getItem(), fixture.getSession(), fixture.getArgs());
		}
		catch (WorkflowException we) {
			assertThat(we,is(not(nullValue())));
			assertThat((RepositoryException)we.getCause(),isA(RepositoryException.class));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}