package com.spd.cq.searspartsdirect.common.model;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

/**
 * Model Object for jsp-accessible constants
 * update as necessary
 * @author dmartinez
 *
 */
public class ConstantModel {
	
	private String ARTICLES_ROOT = Constants.ARTICLES_ROOT;
	private String GUIDES_ROOT = Constants.ARTICLES_ROOT;
	private String CATEGORIES_ROOT = Constants.ARTICLES_ROOT;
	private String EN_ROOT = Constants.EN_ROOT;

	public String getARTICLES_ROOT() {
		return ARTICLES_ROOT;
	}

	public String getGUIDES_ROOT() {
		return GUIDES_ROOT;
	}

	public String getCATEGORIES_ROOT() {
		return CATEGORIES_ROOT;
	}

	public String getEN_ROOT() {
		return EN_ROOT;
	}

}
