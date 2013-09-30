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

/**
 * Custom tag to draw out a list of SPDAsset objects based on filters
 * Defaults to current page
 * @author Joseph
 *
 */
@SuppressWarnings("serial")
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
			props.put("1_property", Constants.ASSETS_PAGES_REL_PATH);
			props.put("1_property.value", brandFilter);
		}
		if (productCategoryFilter != null) {
			props.put("2_property", Constants.ASSETS_PAGES_REL_PATH);
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
		
		//props.put("p.limit", "-1"); //fetch all the results
		props.put("p.offset", "0");
		props.put("p.limit", "100"); //this can be updated to a higher number 
			    
		List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();
		try {
			for (Hit hit: hits) {
				Page p = pageManager.getPage(hit.getPath());
				ValueMap properties = p.getProperties();
				result.add(assetTypeEnum.createModelInstance(p, properties));
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
