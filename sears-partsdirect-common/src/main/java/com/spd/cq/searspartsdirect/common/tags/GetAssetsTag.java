package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.AssetType;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.spdasset.AuthorModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ErrorCodeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.HazardModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.PartTypeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;
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
	protected String assetType;
	protected String brandFilter;
	protected String productCategoryFilter;
	protected String tagFilter;
	protected String authorFilter;
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Object> result = new ArrayList<Object>();
		
		//ASSUME INPUTSTRING IS COMING FROM THE TAG ATTRIBUTE
		AssetType assetTypeEnum = AssetType.valueOf(assetType.toUpperCase());
		
		QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
		HashMap<String, String> props = new HashMap<String, String>();
        props.put("type", "cq:Page");
        props.put("path", Constants.ASSETS_PATH + "/" + assetType);
        if (brandFilter != null) {
        	props.put("1_property", "jcr:content/pages");
        	props.put("1_property.value", brandFilter);
        }
        if (productCategoryFilter != null) {
        	props.put("2_property", "jcr:content/pages");
        	props.put("2_property.value", productCategoryFilter);
        }
        if (tagFilter != null) {
        	props.put("3_property", "jcr:content/cq:tags");
        	props.put("3_property.value", tagFilter);
        }
        if (authorFilter != null) {
        	props.put("4_property", "jcr:content/cq:tags");
        	props.put("4_property.value", authorFilter);
        }
        List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();
		try {
	        for (Hit hit: hits) {
	        	Page p = pageManager.getPage(hit.getPath());
				ValueMap properties = p.getProperties();
				String title = properties.get(Constants.ASSETS_TITLE_PATH,"");
				String description = properties.get(Constants.ASSETS_DESCRIPTION_PATH,"");
				switch (assetTypeEnum) {
					case BRAND: 
						result.add(new BrandModel(p.getPath(),
								title,
								description,
								p.getPath() + Constants.ASSETS_LOGO_PATH));
						break;
					case ERRORCODE: 
						result.add(new ErrorCodeModel(p.getPath(),
								title,
								description,
								properties.get("repairPath","")));
						break;
					case HAZARD:
						result.add(new HazardModel(p.getPath(),
								title,
								p.getPath() + Constants.ASSETS_IMAGE_PATH));
						break;
					case JOBCODE:
						result.add(new JobCodeModel(p.getPath(),
								title,
								description));
						break;
					case PARTTYPE:
						result.add(new PartTypeModel(p.getPath(),
								title,
								description,
								p.getPath() + Constants.ASSETS_IMAGE_PATH));
						break;
					case PRODUCTCATEGORY:
						result.add(new ProductCategoryModel(p.getPath(),
								title,
								description,
								p.getPath() + Constants.ASSETS_IMAGE_PATH));
						break;
					case TIP:
						result.add(new TipModel(p.getPath(),
								title,
								p.getPath() + Constants.ASSETS_IMAGE_PATH));
						break;
					case WARNING:
						result.add(new WarningModel(p.getPath(),
								title,
								p.getPath() + Constants.ASSETS_IMAGE_PATH));
						break;
					case SYMPTOM:
						result.add(new SymptomModel(p.getPath(),
								title,
								description,
								properties.get("id","")));
						break;
					case AUTHOR:
						result.add(new AuthorModel(p.getPath(),
								title,
								description,
								p.getPath() + Constants.ASSETS_IMAGE_PATH));
						break;
					default:
						break;
				}
			}
		}
		catch (Exception e) {
			log.error("Error querying pages by tag: " + e.toString());
		}
		
		pageContext.setAttribute(assetType + "List", result);
		return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	
	public void setBrandFilter(String brandFilter) {
		this.brandFilter = brandFilter;
	}

	public void setProductCategoryFilter(String productCategoryFilter) {
		this.productCategoryFilter = productCategoryFilter;
	}

	public void setTagFilter(String tagFilter) {
		this.tagFilter = tagFilter;
	}
	
	public void setAuthorFilter(String authorFilter) {
		this.authorFilter = authorFilter;
	}
}
