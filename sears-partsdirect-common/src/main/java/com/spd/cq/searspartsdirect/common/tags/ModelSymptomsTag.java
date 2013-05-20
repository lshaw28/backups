package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.CommonSymptomsModel;

public class ModelSymptomsTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(ModelSymptomsTag.class);

	private String modelNumber;

	@Override
	public int doStartTag() throws JspException {

		List<CommonSymptomsModel> modelSymptoms = new ArrayList<CommonSymptomsModel>();
		try {
		JspWriter out = pageContext.getOut();
		// Make the API call to get the repair symptoms for the given model no.
		if (modelSymptoms.size() > 0) {
			pageContext.setAttribute("modelSymptoms", modelSymptoms);
		} else {
			out.print("There are no repair symptoms available for the model number "
					+ currentPage.getTitle());
			pageContext.setAttribute("modelSymptoms", modelSymptoms);
		}
		} catch (IOException e) {
			log.error("Error in calling model symptoms API" + e.getStackTrace());
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}



}
