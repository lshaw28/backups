package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
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
	protected String assetType;
	
	protected static enum AssetType {
		BRAND, ERRORCODE, HAZARD, JOBCODE, PARTTYPE, PRODUCTCATEGORY, TIP, WARNING
	}
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Object> result = new ArrayList<Object>();
		
		//ASSUME INPUTSTRING IS COMING FROM THE TAG ATTRIBUTE
		AssetType assetTypeEnum = AssetType.valueOf(assetType.toUpperCase());
		
		QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
		HashMap<String, String> props = new HashMap<String, String>();
        props.put("type", "cq:Page");
        props.put("path", Constants.ASSETS_PATH + "/" + assetType);
        List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();
		try {
	        for (Hit hit: hits) {
	        	Page p = pageManager.getPage(hit.getPath());
				ValueMap properties = p.getProperties();
				switch (assetTypeEnum) {
					case BRAND: 
						result.add(new BrandModel(properties.get("jcr:title",""),
								properties.get("jcr:description",""),
								p.getPath() + "/jcr:content/logo"));
						break;
					case ERRORCODE: 
						result.add(new ErrorCodeModel(properties.get("jcr:title",""),
								properties.get("jcr:description",""),
								""));
						break;
					case HAZARD:
						result.add(new HazardModel(properties.get("jcr:title",""),
								p.getPath() + "/jcr:content/image"));
						break;
					case JOBCODE:
						result.add(new JobCodeModel(properties.get("jcr:title",""),
								properties.get("jcr:description","")));
						break;
					case PARTTYPE:
						result.add(new PartTypeModel(properties.get("jcr:title",""),
								properties.get("jcr:description",""),
								p.getPath() + "/jcr:content/image"));
						break;
					case PRODUCTCATEGORY:
						result.add(new ProductCategoryModel(properties.get("jcr:title",""),
								properties.get("jcr:description",""),
								p.getPath() + "/jcr:content/image"));
						break;
					case TIP:
						result.add(new TipModel(properties.get("jcr:title",""),
								p.getPath() + "/jcr:content/image"));
						break;
					case WARNING:
						result.add(new WarningModel(properties.get("jcr:title",""),
								p.getPath() + "/jcr:content/image"));
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
}
