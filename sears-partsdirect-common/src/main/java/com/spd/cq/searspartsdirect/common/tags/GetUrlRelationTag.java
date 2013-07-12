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
	
	
	public final static String CATEGORY = Constants.ident("productCategory");
	public final static String BRAND = Constants.ident("brand");
	public final static String MODEL = Constants.ident("model");
	public final static String SYMPTOM = Constants.ident("symptom");
	
	private static final Set<String> assetRelations = initAssetRelations();
	private static final Set<String> initAssetRelations() {
		Set<String> assetRelations = new HashSet<String>();
		assetRelations.add(CATEGORY);
		assetRelations.add(BRAND);
		assetRelations.add(SYMPTOM);
		return assetRelations;
	}

	private static final Map<Integer,Map<String,Integer>> selectorCountToScheme = initSelectorCountToScheme();
	private static final Map<Integer,Map<String,Integer>> initSelectorCountToScheme() {
		Map<Integer,Map<String,Integer>> countToScheme = new HashMap<Integer,Map<String,Integer>>();
		countToScheme.put(1, initSymptomOnlyScheme(new HashMap<String,Integer>()));
		countToScheme.put(3, initBrandCategoryModelScheme());
		countToScheme.put(4, initSymptomOnlyScheme(initBrandCategoryModelScheme()));
		return countToScheme;
	}
	
	private static final Map<String,Integer> initSymptomOnlyScheme(Map<String,Integer> schemeSoFar) {
		schemeSoFar.put(SYMPTOM, schemeSoFar.size());
		return schemeSoFar;
	}
	
	private static final Map<String,Integer> initBrandCategoryModelScheme() {
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
	
	protected String relationType;
	protected Map<String,Integer> relationToSelectorIndex;
	
	@Override
	public int doStartTag() throws JspException {
		relationToSelectorIndex = selectorCountToScheme.get(slingRequest.getRequestPathInfo().getSelectors().length);
		
		if (!StringUtils.isBlank(relationType)) {
			lookUpRelation();
		} else {
			if (relationToSelectorIndex != null) {
				for (String possibleRelation : relationToSelectorIndex.keySet()) {
					relationType = possibleRelation;
					lookUpRelation();
					relationType = null;
				}
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
	
	private String extractFromUri(Pattern pattern) {
		Matcher extractor = pattern.matcher(slingRequest.getRequestURI());
		if (extractor.find()) {
			return extractor.group(1);
		} else {
			return null;
		}
	}
	
	private void lookUpRelation() {
		String[] selectors = slingRequest.getRequestPathInfo().getSelectors();

		if (relationToSelectorIndex != null && relationToSelectorIndex.containsKey(relationType)) {

			int selectorIndex = relationToSelectorIndex.get(relationType);
			String selectorValue = selectors[selectorIndex];
			pokeRelationIntoContext(selectorValue);
			
		} else {
			if (relationToUriExtractor.containsKey(relationType)) {
				String uriValue = extractFromUri(relationToUriExtractor.get(relationType));
				if (uriValue != null) {
					pokeRelationIntoContext(uriValue);
				}
			} 
		}
	}

	private void pokeRelationIntoContext(String relationValue) {
		if (assetRelations.contains(relationType)) {
			AssetType assetTypeEnum = AssetType.valueOf(relationType.toUpperCase());
			
			String relatedAssetPath = Constants.ASSETS_PATH + "/" + relationType + "/" + relationValue;
			log.debug(relatedAssetPath);
			Page p = pageManager.getPage(relatedAssetPath);
			if (p != null) {
				ValueMap properties = p.getProperties();
				
				Object relatedAsset = assetTypeEnum.createModelInstance(p,properties);
				pageContext.setAttribute(relationType + "Relation", relatedAsset);
			} else {
				log.warn("No asset for "+relationType+" "+relationValue);
			}
		} else {
			pageContext.setAttribute(relationType + "Relation", relationValue);
		}
	}
}
