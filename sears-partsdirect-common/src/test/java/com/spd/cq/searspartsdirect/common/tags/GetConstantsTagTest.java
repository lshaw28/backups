package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.ConstantModel;


public class GetConstantsTagTest extends MocksTag {
	
	private GetConstantsTag tag;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tag = new GetConstantsTag();
	}

	@Test
	public void testTag() throws JspException {
		// The following 2 lines actually cover Constants initialization - which currently, never happens.
		Constants localConstants = new Constants();
		assertThat(localConstants,is(not(nullValue())));
		
		tag.setPageContext(pageContext);
		assertThat(tag.doStartTag(),is(TagSupport.SKIP_BODY));
		assertThat(tag.doEndTag(),is(TagSupport.EVAL_PAGE));
		ConstantModel jspConstants = (ConstantModel)pageContext.getAttribute("Constants");
		assertThat(jspConstants,is(not(nullValue())));
		
		assertThat(jspConstants.getARTICLES_ROOT(),is(Constants.ARTICLES_ROOT));
		assertThat(jspConstants.getASSETS_IMAGE_PATH(),is(Constants.ASSETS_IMAGE_PATH));
		assertThat(jspConstants.getCATEGORIES_ROOT(),is(Constants.CATEGORIES_ROOT));
		assertThat(jspConstants.getEMPTY(),is(Constants.EMPTY));
		assertThat(jspConstants.getEN_ROOT(),is(Constants.EN_ROOT));
		assertThat(jspConstants.getGUIDES_ROOT(),is(Constants.GUIDES_ROOT));
		assertThat(jspConstants.getMODELNO_SFX(),is(Constants.MODELNO_SFX));
		assertThat(jspConstants.getNO_ICON(),is(Constants.NO_ICON));
	}
	
}
