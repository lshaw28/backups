package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.AssetType;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.TooManyRelatedObjectsException;
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
 * Custom tag to draw out a list of Related Assets from a given page, filtered by Asset type
 * Defaults to current page
 * @author Joseph
 *
 */
public class GetRelationTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetRelationTag.class);
	protected String pagepath;
	protected String assetType;
	protected String single;
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Object> result = new ArrayList<Object>();
		
		AssetType assetTypeEnum = AssetType.valueOf(assetType.toUpperCase());
		
		boolean isSingle = false;
		if (single != null) {
			isSingle = single.equals("true");
		}
		String[] empty = new String[0];
		Page workingPage = pagepath != null ? pageManager.getPage(pagepath) : currentPage;
		String[] relations = workingPage.getProperties().get("pages", empty);
		for (int i = 0; i < relations.length; i++) {
			if (Pattern.matches(Constants.ASSETS_PATH + "/" + assetType + "/[^/]+", relations[i])) {
				Page p = pageManager.getPage(relations[i]);
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
					case AUTHOR:
						result.add(new AuthorModel(p.getPath(),
								title,
								description,
								properties.get("id","")));
								
					default:
						break;
				}
			}
		}
		if (isSingle) {
			if (result.size() > 1) {
				//If the result is supposed to be single and is not, throw an exception
				throw new TooManyRelatedObjectsException();
			}
			else if (result.size() > 0) {
				pageContext.setAttribute(assetType + "Relation", result.get(0));
			}
		}
		else {
			pageContext.setAttribute(assetType + "RelationList", result);
		}
		
		return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public void setSingle(String single) {
		this.single = single;
	}
}
