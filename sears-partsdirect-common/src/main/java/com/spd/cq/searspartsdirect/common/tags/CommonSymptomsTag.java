package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.CommonSymptomsModel;

public class CommonSymptomsTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(CommonSymptomsTag.class);

	private String categoryName;

	@Override
	public int doStartTag() throws JspException {

		List<CommonSymptomsModel> commonSymptomsModel = new ArrayList<CommonSymptomsModel>();
		try {
		JspWriter out = pageContext.getOut();
		// Make the API call to get the recently viewed model and parts
		if (commonSymptomsModel.size() > 0) {
			pageContext.setAttribute("commonSymptoms", commonSymptomsModel);
		} else {
			out.print("There are not any commons symptoms available for this category "
					+ currentPage.getTitle());
			pageContext.setAttribute("commonSymptoms", commonSymptomsModel);
		}
		} catch (IOException e) {
			log.error("Error in getting common symptoms API call" + e.getStackTrace());
		}

		//log.error("categoryName" + categoryName);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
