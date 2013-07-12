package com.spd.cq.searspartsdirect.common.tags;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.ModelSubcomponentAPIHelper;
import com.spd.cq.searspartsdirect.common.model.ExternalLinkModel;
import com.spd.cq.searspartsdirect.common.model.PDModelSubcomponentModel;
import com.spd.cq.searspartsdirect.common.model.PDTab;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

/**
 * Custom Tag to display a model header spoofed onto another page based on a request parameter
 * @author Joseph
 *
 */
public class GetModelHeaderTag extends CQBaseTag {
	
	protected final static Logger log = LoggerFactory.getLogger(GetModelHeaderTag.class);
	private BrandModel brand;
	private ProductCategoryModel productCategory;
	private String model;
	
	@Override
	public int doStartTag() throws JspException {
		
		List<ExternalLinkModel> pseudoTabs = new LinkedList<ExternalLinkModel>();
		
		try {
			ModelSubcomponentAPIHelper apiHelper = new ModelSubcomponentAPIHelper();
			apiHelper.setBrand(brand.getTitle());
			apiHelper.setCategory(productCategory.getTitle());
			apiHelper.setModel(model);
			PDModelSubcomponentModel subcomponents = apiHelper.getModelSubcomponents(slingRequest);
			PDTab[] apiTabs = subcomponents.getTabsArr();
			for (PDTab tab : apiTabs) {
				pseudoTabs.add(new ExternalLinkModel(tab.getTabsLink(),tab.getTabsName()));
			}
		} catch (Exception e) {
			log.error("Reading model header tabs from API, ",e);
		}
		
		ExternalLinkModel repairHelp = new ExternalLinkModel("#","Repair Help");
		if (pseudoTabs.size() > 0) {
			pseudoTabs.add(pseudoTabs.size() - 1, repairHelp);
		} else {
			pseudoTabs.add(repairHelp);
		}
		
		pageContext.setAttribute("pseudoTabs", pseudoTabs);
		
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setBrand(BrandModel brand) {
		this.brand = brand;
	}
	
	public void setProductCategory(ProductCategoryModel productCategory) {
		this.productCategory = productCategory;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
}
