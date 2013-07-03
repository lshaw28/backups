package com.spd.cq.searspartsdirect.common.tags;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.PartModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetTopPartsTagTest extends MocksTag {

	private GetTopPartsTag tag;

	private final static String TEST_BRAND = "kenmore";
	private final static String TEST_CATEGORY = "dishwasher";
	private final static String TEST_MODEL = "66517722k900";
	
	private final static String FIAT_JSON = "[{\"partName\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0071_247_736-0242\",\"partDesc\":\"Bell Washer\",\"partDetailsPageURL\":\"http://www.searspartsdirect.com/partsdirect/part-number/736-0242/0071/247\"},{\"partName\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0071_247_736-0242\",\"partDesc\":\"Bell Washer\",\"partDetailsPageURL\":\"http://www.searspartsdirect.com/partsdirect/part-number/736-0242/0071/247\"},{\"partName\":null,\"imageURL\":\"http://s.sears.com/is/image/Sears/PD_0071_247_736-0242\",\"partDesc\":\"Bell Washer\",\"partDetailsPageURL\":\"http://www.searspartsdirect.com/partsdirect/part-number/736-0242/0071/247\"},{\"partName\":null,\"imageURL\":\"\",\"partDesc\":\"Screw\",\"partDetailsPageURL\":\"http://www.searspartsdirect.com/partsdirect/part-number/710-0599/0071/247\"},{\"partName\":null,\"imageURL\":\"\",\"partDesc\":\"Screw\",\"partDetailsPageURL\":\"http://www.searspartsdirect.com/partsdirect/part-number/710-0599/0071/247\"}]";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tag = new GetTopPartsTag();
	}

	@Test
	public void testDoStartTag() throws JspException {
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<PartModel> topParts = (List<PartModel>)pageContext.getAttribute("topParts");
		assertThat(topParts,hasSize(0));
	}
	
	@Test
	public void testDoStartTagWithIds() throws JspException {
		tag.setModelName(TEST_MODEL);
		tag.setBrandName(TEST_BRAND);
		tag.setCategoryName(TEST_CATEGORY);
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<PartModel> topParts = (List<PartModel>)pageContext.getAttribute("topParts");
		assertThat(topParts,is(not(nullValue())));
	}
	
	@Test
	public void testDoStartTagWithOnlyModel() throws JspException {
		tag.setModelName(TEST_MODEL);
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<PartModel> topParts = (List<PartModel>)pageContext.getAttribute("topParts");
		assertThat(topParts,hasSize(0));
	}
	
	@Test
	public void testDoStartTagWithoutCategory() throws JspException {
		tag.setModelName(TEST_MODEL);
		tag.setBrandName(TEST_BRAND);
		runsSkipsBodyEvalsPage();
		@SuppressWarnings("unchecked")
		List<PartModel> topParts = (List<PartModel>)pageContext.getAttribute("topParts");
		assertThat(topParts,hasSize(0));
	}
	
	@Test
	public void testParseJsonAndFixImages() throws JspException {
		List<PartModel> parts = new ArrayList<PartModel>(tag.getPartsFromJson(FIAT_JSON));
		assertThat(parts,hasSize(2));
		assertThat(parts.get(1).getImageUrl(),is(Constants.EMPTY));
		tag.addNoImageUrl(parts);
		assertThat(parts.get(1).getImageUrl(),is(GetTopPartsTag.NO_IMAGE_URL));
		assertThat(parts.get(0).getText(),is("Bell Washer"));
		assertThat(parts.get(1).getText(),is("Screw"));
	}

	private void runsSkipsBodyEvalsPage() throws JspException {
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
	}
}
