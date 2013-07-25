package com.spd.cq.searspartsdirect.common.helpers;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.model.spdasset.AccessoryModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.HazardModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.PartTypeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.ProductCategoryModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.TipModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.WarningModel;

public enum AssetType {
	BRAND(BrandModel.class) {
		@Override
		public Object createModelInstance(Page p, ValueMap properties) {
			String title = properties.get(Constants.ASSETS_TITLE_PATH,"");
			String description = properties.get(Constants.ASSETS_DESCRIPTION_PATH,"");
			return new BrandModel(p.getName(),
					p.getPath(),
					title,
					description,
					p.getPath() + Constants.ASSETS_LOGO_PATH);
		}
	},
	// Not sure why these aren't updated to use icons here. Possible TODO.
	HAZARD(HazardModel.class) {
		@Override
		public Object createModelInstance(Page p, ValueMap properties) {
			String title = properties.get(Constants.ASSETS_TITLE_PATH,"");
			return new HazardModel(p.getPath(),
					title,
					p.getPath() + Constants.ASSETS_IMAGE_PATH);
		}
	},
	JOBCODE(JobCodeModel.class,"jobCode") {
		@Override
		public Object createModelInstance(Page p, ValueMap properties) {
			String id = properties.get(Constants.ID_REL_PATH, "");
			String title = properties.get(Constants.ASSETS_TITLE_PATH, "");
			String description = properties.get(Constants.ASSETS_DESCRIPTION_PATH, "");
			return new JobCodeModel(id, p.getPath(), title, description);
		}
	},
	PARTTYPE(PartTypeModel.class,"partType") {
		@Override
		public Object createModelInstance(Page p, ValueMap properties) {
			String title = properties.get(Constants.ASSETS_TITLE_PATH,"");
			String description = properties.get(Constants.ASSETS_DESCRIPTION_PATH,"");
			return new PartTypeModel(p.getPath(),
					title,
					description,
					p.getPath() + Constants.ASSETS_IMAGE_PATH, properties.get("titlePlural",""));
		}
	},
	PRODUCTCATEGORY(ProductCategoryModel.class,"productCategory") {
		@Override
		public Object createModelInstance(Page p, ValueMap properties) {
			String title = properties.get(Constants.ASSETS_TITLE_PATH,"");
			String description = properties.get(Constants.ASSETS_DESCRIPTION_PATH,"");
			String articleIndexCopy = properties.get(Constants.ASSETS_ARTICLE_COPY_PATH,"");
			String category101 = properties.get(Constants.ASSETS_CATEGORY_101_PATH,"");
			String iconClass = properties.get(Constants.ASSETS_ICON_ATTR,"");
			return new ProductCategoryModel(p.getName(),
					p.getPath(),
					title,
					properties.get("titlePlural",""),
					description,
					articleIndexCopy,
					category101,
					//p.getPath() + Constants.ASSETS_IMAGE_PATH);
					iconClass);
		}
	},
	TIP(TipModel.class) {
		@Override
		public Object createModelInstance(Page p, ValueMap properties) {
			String title = properties.get(Constants.ASSETS_TITLE_PATH,"");
			return new TipModel(p.getPath(),
					title,
					p.getPath() + Constants.ASSETS_IMAGE_PATH);
		}
	},
	WARNING(WarningModel.class) {
		@Override
		public Object createModelInstance(Page p, ValueMap properties) {
			String title = properties.get(Constants.ASSETS_TITLE_PATH,"");
			return new WarningModel(p.getPath(),
					title,
					p.getPath() + Constants.ASSETS_IMAGE_PATH);
		}
	},
	SYMPTOM(SymptomModel.class) {
		@Override
		public Object createModelInstance(Page p, ValueMap properties) {
			String title = properties.get(Constants.ASSETS_TITLE_PATH,"");
			String description = properties.get(Constants.ASSETS_DESCRIPTION_PATH,"");
			return new SymptomModel(p.getPath(),
					title,
					description,
					properties.get("id",""));
		}
	},
	ACCESSORY(AccessoryModel.class) {
		@Override
		public Object createModelInstance(Page p, ValueMap properties) {
			String title = properties.get(Constants.ASSETS_TITLE_PATH,"");
			String description = properties.get(Constants.ASSETS_DESCRIPTION_PATH,"");
			return new AccessoryModel(p.getPath(),
					title,
					description,
					p.getPath() + Constants.ASSETS_IMAGE_PATH,
					properties.get("partNumber",""),
					properties.get("linkUrl",""));
		}
	};

	private final Class<?> modelClass;
	private final String mixedCaseName;

	AssetType(final Class<?> modelClass) {
		this.modelClass = modelClass;
		mixedCaseName = this.name().toLowerCase();
	}

	AssetType(final Class<?> modelClass, final String mixedCaseName) {
		this.modelClass = modelClass;
		this.mixedCaseName = mixedCaseName;
	}

	public Class<?> getModelClass() {
		return modelClass;
	}

	public String getMixedCaseName() {
		return mixedCaseName;
	}

	public abstract Object createModelInstance(Page p, ValueMap properties);

	public Object createModelInstance(String path, ResourceResolver rr) {
		Page p = rr.getResource(path).adaptTo(Page.class);
		return createModelInstance(p,p.getProperties());
	}
}