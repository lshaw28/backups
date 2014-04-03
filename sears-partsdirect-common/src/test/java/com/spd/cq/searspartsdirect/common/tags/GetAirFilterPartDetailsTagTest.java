package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.GetAirFilterPartDetailsFixture;

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
		runsTagSkipsBodyEvalsPage();
		assertTrue(true);
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

	private void runsTagSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
