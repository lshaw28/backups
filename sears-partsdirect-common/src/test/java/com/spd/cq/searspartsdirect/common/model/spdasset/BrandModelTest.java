package com.spd.cq.searspartsdirect.common.model.spdasset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class BrandModelTest  extends TestCase {
	private BrandModel model;
	private BrandModel modelPrime;
	
	private final static String TRUENAME = "truename";
	private final static String DESCRIPTION = "description";
	private final static String LOGO_PATH = "logoPath";
	private final static String PATH = "path";
	private final static String TITLE = "title";
	
	private final static String NEW_ = "new_";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		model = null;
		modelPrime = null;
	}
	
	@Test
	public void testModel() {
		try {
			model = new BrandModel(TRUENAME, PATH, TITLE, DESCRIPTION, LOGO_PATH);
			assertThat(model.getDescription(),is(DESCRIPTION));
			assertThat(model.getLogoPath(),is(LOGO_PATH));
			assertThat(model.getPath(),is(PATH));
			assertThat(model.getTitle(),is(TITLE));
			model.setDescription(NEW_+DESCRIPTION);
			model.setLogoPath(NEW_+LOGO_PATH);
			model.setPath(NEW_+PATH);
			model.setTitle(NEW_+TITLE);
			assertThat(model.getPath(),is(NEW_+PATH));
			assertThat(model.getDescription(),is(NEW_+DESCRIPTION));
			assertThat(model.getTitle(),is(NEW_+TITLE));
			assertThat(model.getLogoPath(),is(NEW_+LOGO_PATH));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testEqualsAndHashcode() {
		model = new BrandModel(null,null,null,null,null);
		assertThat(model.equals(null),is(false));
		assertThat(model.equals(this),is(false));
		assertThat(model.equals(model),is(true));
		modelPrime = new BrandModel(null,null,null,null,null);
		bothAreEqual();
		
		model.setDescription(DESCRIPTION);
		neitherAreEqual();
		modelPrime.setDescription(NEW_+DESCRIPTION);
		neitherAreEqual();
		modelPrime.setDescription(DESCRIPTION);
		bothAreEqual();
		
		model.setLogoPath(LOGO_PATH);
		neitherAreEqual();
		modelPrime.setLogoPath(NEW_+LOGO_PATH);
		neitherAreEqual();
		modelPrime.setLogoPath(LOGO_PATH);
		bothAreEqual();
		
		model.setPath(PATH);
		neitherAreEqual();
		modelPrime.setPath(NEW_+PATH);
		neitherAreEqual();
		modelPrime.setPath(PATH);
		bothAreEqual();
		
		model.setTitle(TITLE);
		neitherAreEqual();
		modelPrime.setTitle(NEW_+TITLE);
		neitherAreEqual();
		modelPrime.setTitle(TITLE);
		bothAreEqual();
	}
	
	void neitherAreEqual() {
		assertThat(model.equals(modelPrime),is(false));
		assertThat(modelPrime.equals(model),is(false));
		assertThat(modelPrime.hashCode(),isA(Integer.class));
		assertThat(model.hashCode(),isA(Integer.class));
	}
	
	void bothAreEqual() {
		assertThat(model.equals(modelPrime),is(true));
		assertThat(modelPrime.equals(model),is(true));
		assertTrue(modelPrime.hashCode() == model.hashCode());
	}
}