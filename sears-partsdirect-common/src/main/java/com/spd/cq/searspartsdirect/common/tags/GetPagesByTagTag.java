package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import com.spd.cq.searspartsdirect.common.model.ErrorCodeModel;

public class GetPagesByTagTag extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	private String categoryName;
	private String subCategoryName;
	private String brandName;
	
	@Override
	public int doStartTag() throws JspException {
		if (categoryName != null && subCategoryName != null && brandName != null) {
			//get the pages from scaffolding page by passing category, subcategory and brand and loop thru them to extract and store in
			// errorCodeTable with error-code-type as a key and code, desc, condition, repair in the form of an object
			
			Map<String, ArrayList<ErrorCodeModel>> errorCodeTable = new LinkedHashMap<String, ArrayList<ErrorCodeModel>>();
			ArrayList<ErrorCodeModel> errorCodeModels = new ArrayList<ErrorCodeModel>();
			ErrorCodeModel errorCodeModel1 = new ErrorCodeModel();
			errorCodeModel1.setCode("101");
			errorCodeModel1.setDescription("recalled by the manufacturer");
			
			ErrorCodeModel errorCodeModel2 = new ErrorCodeModel();
			errorCodeModel2.setCode("102");
			errorCodeModel2.setDescription("major issue with the engine");
			errorCodeModel2.setCondition("poor");
			errorCodeModel2.setRepair("call kenmore at 1-800-KENMORE");
			
			errorCodeModels.add(errorCodeModel1);
			errorCodeModels.add(errorCodeModel2);
			
			errorCodeTable.put("Error Code Type 1", errorCodeModels);
			pageContext.setAttribute("errorCodeTable", errorCodeTable);
		} else if (categoryName != null) {
			//get all the page (group by brand if possible) by passing the category name and populate errorCodeList
			//loop thru the pages and store brand as a key and subcategories list as a value in the map
			
			Map<String, ArrayList<String>> errorCodeList = new LinkedHashMap<String, ArrayList<String>>();
			
			ArrayList<String> errorCodes = new ArrayList<String>();
			errorCodes.add("door is broken");
			errorCodes.add("engine error");
			errorCodeList.put("101", errorCodes);
			pageContext.setAttribute("errorCodeList", errorCodeList);
		} else {
			//get all the scaffolding pages
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}