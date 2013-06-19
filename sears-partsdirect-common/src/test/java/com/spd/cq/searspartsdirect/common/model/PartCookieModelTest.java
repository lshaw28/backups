package com.spd.cq.searspartsdirect.common.model;


import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PartCookieModelTest extends TestCase {

	private final static String ITEM_DESC = "itemDescription";
	private final static String ITEM_IMG_URL = "itemImageURL";
	private final static String ITEM_NAME = "itemName";
	private static final String ITEM_URL = "itemURL";
	
	private static final String ANOTHER = "another_";
	
	private PartCookieModel model1;
	private PartCookieModel model2;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testModel() {
		model1 = new PartCookieModel();
		
		assertThat(model1.getItemDescription(),is((String)null));
		assertThat(model1.getItemImageURL(),is((String)null));
		assertThat(model1.getItemName(),is((String)null));
		assertThat(model1.getItemURL(),is((String)null));
		
		assertThat(model1.equals(null),is(false));
		assertThat(model1.equals(this),is(false));
		assertTrue(model1.equals(model1));
		
		model2 = new PartCookieModel();
		bothAreEqual();
		model1.setItemDescription(ITEM_DESC);
		neitherAreEqual();
		model2.setItemDescription(ANOTHER+ITEM_DESC);
		neitherAreEqual();
		model2.setItemDescription(ITEM_DESC);
		bothAreEqual();
		model1.setItemName(ITEM_NAME);
		neitherAreEqual();
		model2.setItemName(ANOTHER+ITEM_NAME);
		neitherAreEqual();
		model2.setItemName(ITEM_NAME);
		bothAreEqual();
		model1.setItemURL(ITEM_URL);
		neitherAreEqual();
		model2.setItemURL(ANOTHER+ITEM_URL);
		neitherAreEqual();
		model2.setItemURL(ITEM_URL);
		bothAreEqual();
		// We could at this point explicitly test accessors.
	}

	private void bothAreEqual() {
		assertTrue(model1.equals(model2));
		assertTrue(model2.equals(model1));
		// If this is not true, strange behavior could be seen in some collection implementations using these as keys
		assertTrue(model1.hashCode() == model2.hashCode());
	}
	
	private void neitherAreEqual() {
		assertThat(model1.equals(model2),is(false));
		assertThat(model2.equals(model1),is(false));
		// The hashcode contract does not state that distinct objects must have distinct hashcodes
		assertThat(model1.hashCode(),isA(Integer.class));
		assertThat(model2.hashCode(),isA(Integer.class));
	}
	
	
}
