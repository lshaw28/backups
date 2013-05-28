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
		ProductCategoryModel bike = new ProductCategoryModel("Bike", "Bike description", "/etc/spdAssets/scaffolding/productCategory/bike/jcr:content/image");
		ProductCategoryModel musicPlayer = new ProductCategoryModel("Music Player", "Music Player description", "/etc/spdAssets/scaffolding/productCategory/music_player/jcr:content/image");
        models.add(bike);
        models.add(musicPlayer);
        pageContext.setAttribute("models", models);
		return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
