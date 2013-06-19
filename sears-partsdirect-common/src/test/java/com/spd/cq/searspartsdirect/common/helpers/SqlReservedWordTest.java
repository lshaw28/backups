package com.spd.cq.searspartsdirect.common.helpers;


import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SqlReservedWordTest extends TestCase {
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testGetWords() {
		assertThat(SqlReservedWord.getWords(),isA(Set.class));
	}

}
