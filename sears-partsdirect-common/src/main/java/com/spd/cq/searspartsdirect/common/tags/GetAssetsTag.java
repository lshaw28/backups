package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ErrorCodeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.HazardModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.PartTypeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.TipModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.WarningModel;

/**
 * Custom tag to draw out a list of SPDAsset objects based on filters
 * Defaults to current page
 * @author Joseph
 *
 */
public class GetAssetsTag extends CQBaseTag {

	protected static Logger log = LoggerFactory.getLogger(GetAssetsTag.class);
	
	//Hard-coded for temporary development
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<BrandModel> brands = new ArrayList<BrandModel>();
		ArrayList<ErrorCodeModel> errorCodes = new ArrayList<ErrorCodeModel>();
		ArrayList<HazardModel> hazards = new ArrayList<HazardModel>();
		ArrayList<JobCodeModel> jobCodes = new ArrayList<JobCodeModel>();
		ArrayList<PartTypeModel> partTypes = new ArrayList<PartTypeModel>();
		ArrayList<ProductCategoryModel> productCategories = new ArrayList<ProductCategoryModel>();
		ArrayList<TipModel> tips = new ArrayList<TipModel>();
		ArrayList<WarningModel> warnings = new ArrayList<WarningModel>();
        
		//Product Categories
		Resource r = resourceResolver.getResource("/etc/spdAssets/scaffolding/productCategory");
		if (r != null) {
			Iterator<Resource> iter = r.listChildren();
			while (iter.hasNext()) {
				Resource child = iter.next();
				Page p = child.adaptTo(Page.class);
				ValueMap properties = p.getProperties();
				productCategories.add(new ProductCategoryModel(properties.get("jcr:title",""),
						properties.get("jcr:description",""),
						p.getPath() + "/jcr:content/image"));
			}
		}
		
        pageContext.setAttribute("productCategories", productCategories);
		return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
