package com.spd.cq.searspartsdirect.common.tags;

import java.util.Locale;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.Singularizer;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

public class FixSingularTitleTag  extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(FixSingularTitleTag.class);
	private ProductCategoryModel modelInstance;

	@Override
	public int doStartTag() throws JspException {
		if (modelInstance.getSingularTitle().equals(Constants.EMPTY)) {
			modelInstance.setSingularTitle(Singularizer.INSTANCE.singularize(Locale.ENGLISH,modelInstance.getTitle()));
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
	public void setModelInstance(ProductCategoryModel modelInstance) {
		this.modelInstance = modelInstance;
	}

}
