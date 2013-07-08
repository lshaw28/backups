package com.spd.cq.searspartsdirect.common.tags;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.AssetType;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

/**
 * Custom tag to draw out a related asset or selector string from page selectors. For productCategory,
 * when no selectors are present will attempt to extract relation from URI. When no relationType is
 * specified, pulls all relations.
 * @author Ben
 *
 */
public class GetUrlRelationTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetUrlRelationTag.class);
	protected String pRelationType;
	
	public final static String CATEGORY = Constants.ident("productCategory");
	public final static String BRAND = Constants.ident("brand");
	public final static String MODEL = Constants.ident("model");
	
	private static final Set<String> assetRelations = initAssetRelations();
	private static final Set<String> initAssetRelations() {
		Set<String> assetRelations = new HashSet<String>();
		assetRelations.add(CATEGORY);
		assetRelations.add(BRAND);
		return assetRelations;
	}

	private static final Map<String,Integer> relationToSelectorIndex = initRelationToSelectorIndex();
	private static final Map<String,Integer> initRelationToSelectorIndex() {
		Map<String,Integer> relationToSelectorIndex = new HashMap<String,Integer>();
		relationToSelectorIndex.put(CATEGORY, Constants.CATEGORY_SELECTOR);
		relationToSelectorIndex.put(BRAND, Constants.BRAND_SELECTOR);
		relationToSelectorIndex.put(MODEL, Constants.MODEL_SELECTOR);
		return relationToSelectorIndex;
	}
	
	private static final Map<String,Pattern> relationToUriExtractor = initRelationToUriExtractor();
	private static final Map<String,Pattern> initRelationToUriExtractor() {
		Map<String,Pattern> relationToUriExtractor = new HashMap<String,Pattern>();
		relationToUriExtractor.put(CATEGORY, Pattern.compile("(?:^|/)([^/]*)-repair[/\\.]"));
		return relationToUriExtractor;
	}
	
	@Override
	public int doStartTag() throws JspException {
		if (!StringUtils.isBlank(pRelationType)) {
			lookUpRelation(pRelationType);
		} else {
			for (String relationType : relationToSelectorIndex.keySet()) {
				lookUpRelation(relationType);
			}
		}

		return SKIP_BODY;
	}

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

	public void setRelationType(String relationType) {
		pRelationType = relationType;
	}
	
	private String extractFromUri(Pattern pattern) {
		Matcher extractor = pattern.matcher(slingRequest.getRequestURI());
		if (extractor.find()) {
			return extractor.group(1);
		} else {
			return null;
		}
	}
	
	private void lookUpRelation(String relationType) {
		String[] selectors = slingRequest.getRequestPathInfo().getSelectors();

		if (selectors.length > 2) {
			if (relationToSelectorIndex.containsKey(relationType)) {

				int selectorIndex = relationToSelectorIndex.get(relationType);
				String selectorValue = selectors[selectorIndex];
				pokeRelationIntoContext(relationType, selectorValue);
				
			} else {
				throw new IllegalArgumentException("Invalid relationType "+relationType);
			}
		} else {
			if (relationToUriExtractor.containsKey(relationType)) {
				String uriValue = extractFromUri(relationToUriExtractor.get(relationType));
				if (uriValue != null) {
					pokeRelationIntoContext(relationType, uriValue);
				}
			} // We don't throw here else, b/c we don't have a full set of extractors.
		}
	}

	private void pokeRelationIntoContext(String relationType, String relationValue) {
		if (assetRelations.contains(relationType)) {
			AssetType assetTypeEnum = AssetType.valueOf(relationType.toUpperCase());
			
			String relatedAssetPath = Constants.ASSETS_PATH + "/" + relationType + "/" + relationValue;
			log.debug(relatedAssetPath);
			Page p = pageManager.getPage(relatedAssetPath);
			ValueMap properties = p.getProperties();
			
			Object relatedAsset = assetTypeEnum.createModelInstance(p,properties);
			pageContext.setAttribute(relationType + "Relation", relatedAsset);
		} else {
			pageContext.setAttribute(relationType + "Relation", relationValue);
		}
	}
}
