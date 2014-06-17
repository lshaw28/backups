package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.helpers.PSFlagStatus;
import com.spd.cq.searspartsdirect.common.helpers.PSSettingsHelper;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.model.ModelPart;
import com.spd.cq.searspartsdirect.common.model.ModelWithPartList;
import com.spd.cq.searspartsdirect.common.helpers.Constants;


@SuppressWarnings("serial")
public class ModelPartListTag extends CQBaseTag{
	protected final static Logger log=LoggerFactory.getLogger(ModelPartListTag.class);
	private String modelNumber;
	private String brandId;
	private String productCategoryId;
	private String diagramPageId;
	private String documentId;
	private String API_ROOT;
	private PartsDirectAPIHelper apiHelper;
	
	@Override
	public int doStartTag() throws JspException {

		try {
			PSSettingsHelper helper = sling.getService(PSSettingsHelper.class);
			API_ROOT = helper.getPartsDirectProductAPI();
			apiHelper = new PartsDirectAPIHelper();

			String jsonModelWithPartList = getModelWithPartList();
			
			Gson gson = new Gson();
			ModelWithPartList modelPartList = gson.fromJson(jsonModelWithPartList,
					ModelWithPartList.class);
			
			PSFlagStatus flagStatus = sling.getService(PSFlagStatus.class);
			JSONObject flagStatusJson = flagStatus
					.getStockAvailabilityMessage();
			
			ModelPart[] modelPart = modelPartList.getModelPart();
			
			for (int i = 0; i < modelPart.length; i++) {
				modelPart[i].getPriceAndAvailability().setAvailabilityStatusMessage(
						flagStatusJson.getString(modelPart[i]
								.getPriceAndAvailability()
								.getAvailabilityStatus()));
                modelPart[i].getPartCompositeKey().setKeyId(modelPart[i].getPartCompositeKey().getKeyId().replaceFirst("^0*", Constants.EMPTY));
			}
            
			pageContext.setAttribute("jsonResponse", modelPartList);
		} catch (IOException e) {
			log.error("IO Exception: ModelWithPartListTag: doStartTag(): ",
					e.getMessage());
		} catch (JSONException e) {
			log.error("JSON Exception: ModelWithPartListTag: doStartTag(): ",
					e.getMessage());
		}
		return SKIP_BODY;
	}

	private String getModelWithPartList() throws IOException {
		String API_URL = API_ROOT+ "models/" + modelNumber + "?brandId=" + brandId + "&productCategoryId="
				+ productCategoryId + "&diagramPageId=" + diagramPageId + "&documentId="
				+ documentId;
		
		return apiHelper.readJsonData(API_URL);
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getDiagramPageId() {
		return diagramPageId;
	}

	public void setDiagramPageId(String diagramPageId) {
		this.diagramPageId = diagramPageId;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

}
