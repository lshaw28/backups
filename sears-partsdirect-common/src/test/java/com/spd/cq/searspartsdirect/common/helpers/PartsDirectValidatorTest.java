package com.spd.cq.searspartsdirect.common.helpers;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.model.CategoryModel;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PartsDirectValidatorTest  extends TestCase {
	
	private static final String IS_ALL_NUMERIC = "1234567890";
	private static final String IS_NOT_NUMERIC = "onetwothree";
	private static final String IS_ALPHANUMERIC = "one2three4";
	private static final String IS_NOT_ALPHANUMERIC = "!@#$%^*&";
	private static final String IS_11_CHARS = "elevenChars";
	private static final String HAS_NO_SQL_WORD = "this was actually difficult";
	private static final String HAS_SQL_WORD = "select from sql where join";

	private PartsDirectValidator model;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testPartsDirectValidator() {
		
		model = new PartsDirectValidator();
		
		assertTrue(model.isAllNumeric(IS_ALL_NUMERIC));
		assertFalse(model.isAllNumeric(IS_NOT_NUMERIC));
		
		assertTrue(model.isAlphanumeric(IS_ALPHANUMERIC));
		assertFalse(model.isAlphanumeric(IS_NOT_ALPHANUMERIC));
		
		assertTrue(model.isNoLongerThan(IS_11_CHARS, 12));
		assertFalse(model.isNoLongerThan(IS_11_CHARS, 10));
		
		assertTrue(model.containsNoSqlWords(HAS_NO_SQL_WORD));
		assertFalse(model.containsNoSqlWords(HAS_SQL_WORD));
		
		assertTrue(model.containsNoneOf(IS_NOT_NUMERIC, "1", "2", "3"));
		assertFalse(model.containsNoneOf(IS_ALPHANUMERIC, "one", "two", "three"));
		
		
//		assertThat(model.getUrl(),is(URL));
//		assertThat(model.getImagePath(),is(IMAGE_PATH));
//		assertThat(model.getTitle(),is(TITLE));
//		assertThat(model.getDescription(),is(DESCRIPTION));
//
//		model.setUrl(ANOTHER+URL);
//		model.setImagePath(ANOTHER+IMAGE_PATH);
//		model.setTitle(ANOTHER+TITLE);
//		model.setDescription(ANOTHER+DESCRIPTION);
//		
//		assertThat(model.getUrl(),is(ANOTHER+URL));
//		assertThat(model.getImagePath(),is(ANOTHER+IMAGE_PATH)); // note - this concatenation happens at compile time
//		assertThat(model.getTitle(),is(ANOTHER+TITLE));
//		assertThat(model.getDescription(),is(ANOTHER+DESCRIPTION));
	}


}





























