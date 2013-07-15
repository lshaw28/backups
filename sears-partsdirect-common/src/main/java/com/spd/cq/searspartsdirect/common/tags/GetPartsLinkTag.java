package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.ModelSubcomponentAPIHelper;
import com.spd.cq.searspartsdirect.common.model.PDModelSubcomponentModel;
import com.spd.cq.searspartsdirect.common.model.PDTab;

public class GetPartsLinkTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	protected final static Logger log = LoggerFactory.getLogger(GetPartsLinkTag.class);
	private String brandName;
	private String categoryName;
	private String modelNumber;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			ModelSubcomponentAPIHelper apiHelper = new ModelSubcomponentAPIHelper();
			apiHelper.setBrand(brandName);
			apiHelper.setCategory(categoryName);
			apiHelper.setModel(modelNumber);
			PDModelSubcomponentModel subcomponents = apiHelper.getModelSubcomponents(slingRequest);
			PDTab[] apiTabs = subcomponents.getTabsArr();
			for (PDTab tab : apiTabs) {
				if (tab.getTabsName().equals("Parts")) {
					pageContext.setAttribute("findPartUrl", tab.getTabsLink());
					break;
				}
			}
		} catch (Exception e) {
			log.error("Reading model header tabs from API, ",e.fillInStackTrace());
		}
		return SKIP_BODY;
	}
}
