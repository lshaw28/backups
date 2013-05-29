package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;

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
		ArrayList<ProductCategoryModel> models = new ArrayList<ProductCategoryModel>();
		ProductCategoryModel chainsaws = new ProductCategoryModel("Chainsaws", "chainsaws description", "/etc/spdAssets/scaffolding/productCategory/chainsaws/jcr:content/image");
		ProductCategoryModel dishwashers = new ProductCategoryModel("Dishwashers", "dishwashers description", "/etc/spdAssets/scaffolding/productCategory/dishwashers/jcr:content/image");
		ProductCategoryModel microwaves = new ProductCategoryModel("Microwaves", "microwaves description", "/etc/spdAssets/scaffolding/productCategory/microwaves/jcr:content/image");
		ProductCategoryModel ranges = new ProductCategoryModel("Ranges", "ranges description", "/etc/spdAssets/scaffolding/productCategory/ranges/jcr:content/image");
		ProductCategoryModel refrigerators = new ProductCategoryModel("Refrigerators", "refrigerators description", "/etc/spdAssets/scaffolding/productCategory/refrigerators/jcr:content/image");
		
		models.add(chainsaws);
        models.add(dishwashers);
		models.add(microwaves);
        models.add(ranges);
        models.add(refrigerators);  
        
        pageContext.setAttribute("models", models);
		return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
