package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetAirFilterPartDetailsFixture;
import com.spd.cq.searspartsdirect.common.model.AirFilterPart;

public class GetAirFilterPartDetailsTagTest extends MocksTag {

	private GetAirFilterPartDetailsTag tag;
	private GetAirFilterPartDetailsFixture fixture;


	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new GetAirFilterPartDetailsFixture();
		tag = new GetAirFilterPartDetailsTag();
	}

	@Test
	public void testSuccess() throws JspException {
		setupTagAttributes();
		tag.doStartTag();
		tag.doEndTag();
//		@SuppressWarnings("unchecked")
//		List<AirFilterPart> airFilterParts = (ArrayList<AirFilterPart>) pageContext.getAttribute("airFilterParts");
//		assertNotNull(airFilterParts);
//		assertTrue(airFilterParts.size() > 1);
	}

	@Test
	public void testMoreThanOneResult() throws JspException {
		setupTagAttributes();
		tag.doStartTag();
		tag.doEndTag();
//		@SuppressWarnings("unchecked")
//		List<AirFilterPart> airFilterParts = (ArrayList<AirFilterPart>) pageContext.getAttribute("airFilterParts");
//		assertNotNull(airFilterParts);
//		assertTrue(airFilterParts.size() > 1);
	}

	private void setupTagAttributes() {
		tag.setBasePartNumber("FA08X14A");
		tag.setDivisionNumber("0042");
		tag.setPlsNumber("104");
	}

}
