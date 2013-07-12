package com.spd.cq.searspartsdirect.common.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class CartLineModelTest extends TestCase {
	
	private Part part;
	private int quantity;
	private Part part2;
	private int quantity2;
	
	private CartLinesModel model;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		part = new Part("somePart", "someGroupID", "someSupplierID", "someDescription");
		part2 = new Part("someOtherPart", "someOtherGroupID", "someOtherSupplierID",  "someDescription");
		quantity = 5;
		quantity2 = 10;
	}

	@Test
	public void testCartLineModel() {
		model = new CartLinesModel(part, quantity);
		assertThat(model.getPart(),is(part));
		assertThat(model.getQuantity(),is(quantity));


		model.setPart(part2);
		model.setQuantity(quantity2);

		assertThat(model.getPart(),is(part2));
		assertThat(model.getQuantity(),is(quantity2));
	}

}
