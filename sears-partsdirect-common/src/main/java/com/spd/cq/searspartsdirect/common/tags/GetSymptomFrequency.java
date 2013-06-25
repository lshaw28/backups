package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import com.spd.cq.searspartsdirect.common.model.spdasset.SymptomModel;

public class GetSymptomFrequency extends CQBaseTag {
	
	private static final long serialVersionUID = 1L;
	private List<SymptomModel> symptomList;
	
	@Override
	public int doStartTag() throws JspException {
		List<String> symptomIds = new ArrayList<String>();
		
		for (SymptomModel symptom : symptomList) {
			symptomIds.add(symptom.getId());
		}
		
		//now make the PD API call
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public List<SymptomModel> getSymptomList() {
		return symptomList;
	}

	public void setSymptomList(List<SymptomModel> symptomList) {
		this.symptomList = symptomList;
	}

}
