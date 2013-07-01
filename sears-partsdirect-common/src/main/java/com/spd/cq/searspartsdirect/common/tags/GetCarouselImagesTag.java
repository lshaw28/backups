package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.CarouselHelper;

public class GetCarouselImagesTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory
			.getLogger(GetCarouselImagesTag.class);

	@Override
	public int doStartTag() throws JspException {
		List<String> images;
		if (currentNode != null) {
			images = CarouselHelper.getImageList(currentNode);
		} else {
			images = new ArrayList<String>();
		}
		pageContext.setAttribute("carouselImages",images);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}