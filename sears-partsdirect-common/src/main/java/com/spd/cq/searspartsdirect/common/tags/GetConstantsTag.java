package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.ConstantModel;

/**
 * allows access to various Constants on jsp through 
 * model object pageContext
 * @author dmartinez
 *
 */
public class GetConstantsTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetConstantsTag.class);
	private static final ConstantModel constants = new ConstantModel();

	@Override
	public int doStartTag() throws JspException {
		pageContext.setAttribute("Constants", constants);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
