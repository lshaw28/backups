package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.AssetType;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.spdasset.AccessoryModel;
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
 * Custom tag to draw out a related asset or selector string from page selectors
 * @author Ben
 *
 */
public class GetUrlRelationTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetUrlRelationTag.class);
	protected String relationType;
	
	private static final Set<String> assetRelations = initAssetRelations();
	private static final Set<String> initAssetRelations() {
		Set<String> assetRelations = new HashSet<String>();
		assetRelations.add("productCategory");
		assetRelations.add("brand");
		return assetRelations;
	}
	
	private static final Map<String,Integer> relationToSelectorIndex = initRelationToSelectorIndex();
	private static final Map<String,Integer> initRelationToSelectorIndex() {
		Map<String,Integer> relationToSelectorIndex = new HashMap<String,Integer>();
		relationToSelectorIndex.put("productCategory", Constants.CATEGORY_SELECTOR);
		relationToSelectorIndex.put("brand", Constants.BRAND_SELECTOR);
		relationToSelectorIndex.put("model", Constants.MODEL_SELECTOR);
		return relationToSelectorIndex;
	}
	
	@Override
	public int doStartTag() throws JspException {
		String[] selectors = slingRequest.getRequestPathInfo().getSelectors();
		
		if (selectors.length > 2) {
			if (relationToSelectorIndex.containsKey(relationType)) {
				
				int selectorIndex = relationToSelectorIndex.get(relationType);
				String selectorValue = selectors[selectorIndex];
				
				if (assetRelations.contains(relationType)) {
					AssetType assetTypeEnum = AssetType.valueOf(relationType.toUpperCase());
					
					String relatedAssetPath = Constants.ASSETS_PATH + "/" + relationType + "/" + selectorValue;
					Page p = pageManager.getPage(relatedAssetPath);
					ValueMap properties = p.getProperties();
					
					Object relatedAsset = assetTypeEnum.createModelInstance(p,properties);
					pageContext.setAttribute(relationType + "Relation", relatedAsset);
				} else {
					pageContext.setAttribute(relationType + "Relation", selectorValue);
				}
				
			} else {
				throw new IllegalArgumentException("Invalid relationType "+relationType);
			}
		}
		
		return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
}
