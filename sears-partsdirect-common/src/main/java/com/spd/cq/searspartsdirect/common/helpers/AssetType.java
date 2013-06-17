package com.spd.cq.searspartsdirect.common.helpers;

import com.spd.cq.searspartsdirect.common.model.spdasset.*;

public enum AssetType {
	BRAND(BrandModel.class), 
	ERRORCODE(ErrorCodeModel.class,"errorCode"), 
	HAZARD(HazardModel.class), 
	JOBCODE(JobCodeModel.class,"jobCode"), 
	PARTTYPE(PartTypeModel.class,"partType"), 
	PRODUCTCATEGORY(ProductCategoryModel.class,"productCategory"), 
	TIP(TipModel.class), 
	WARNING(WarningModel.class), 
	SYMPTOM(SymptomModel.class), 
	AUTHOR(AuthorModel.class);
	
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
}