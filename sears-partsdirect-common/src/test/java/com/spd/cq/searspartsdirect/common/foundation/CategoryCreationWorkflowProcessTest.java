package com.spd.cq.searspartsdirect.common.foundation;


import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.junit.Before;
import org.junit.Test;

import com.adobe.granite.workflow.WorkflowException;
import com.day.cq.wcm.api.WCMException;
import com.spd.cq.searspartsdirect.common.fixture.CategoryCreationWorkflowProcessFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CategoryCreationWorkflowProcessTest extends TestCase {

	private CategoryCreationWorkflowProcess process;
	private CategoryCreationWorkflowProcessFixture fixture;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		process = new CategoryCreationWorkflowProcess();
		fixture = new CategoryCreationWorkflowProcessFixture();
	}

	@Test
	public void testExecuteNullArgs() throws WorkflowException {
		Exception thrown = null;
		try {
			process.execute(fixture.getWorkItem(), fixture.getSession(), fixture.getArgs());
		} catch (Exception e) {
			thrown = e;
		}
		assertThat(thrown,not(nullValue()));
		assertTrue(thrown.getClass().equals(WorkflowException.class));
	}
	
	@Test
	public void testExecuteWithWrongPayload() throws WorkflowException {
		fixture.setUpPayloadWrong();
		process.execute(fixture.getWorkItem(), fixture.getSession(), fixture.getArgs());
	}
	
	@Test
	public void testExecuteWithCompletePayload() throws WorkflowException, PathNotFoundException, RepositoryException {
		fixture.setUpPayloadComplete("tiddles");
		process.setTestResourceResolverFactory(fixture.getResourceResolverFactory());
		process.execute(fixture.getWorkItem(), fixture.getSession(), fixture.getArgs());
	}

	@Test
	public void testCreatePage() throws WCMException, RepositoryException {
		process.createPage(fixture.getPageManager(), fixture.getPath(), fixture.getName(), fixture.getTemplate(), fixture.getTitle(), fixture.getRelations());
	}
	
	@Test
	public void testCreatePageAlreadyExists() throws WCMException, RepositoryException {
		fixture.setUpPagesAlreadyExist();
		process.createPage(fixture.getPageManager(), fixture.getPath(), fixture.getName(), fixture.getTemplate(), fixture.getTitle(), fixture.getRelations());
	}

}
