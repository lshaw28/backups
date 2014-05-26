package com.spd.cq.searspartsdirect.common.model;

public class Component {
	private Integer id;
	private String componentDescription;
	private DiagramImage diagramImage;
	private String diagramImageUrl;
	private String diagramPageId;
	private Object parts;
	private String partCount;


	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public String getComponentDescription() {
	return componentDescription;
	}

	public void setComponentDescription(String componentDescription) {
	this.componentDescription = componentDescription;
	}

	public DiagramImage getDiagramImage() {
	return diagramImage;
	}

	public void setDiagramImage(DiagramImage diagramImage) {
	this.diagramImage = diagramImage;
	}

	public String getDiagramImageUrl() {
	return diagramImageUrl;
	}

	public void setDiagramImageUrl(String diagramImageUrl) {
	this.diagramImageUrl = diagramImageUrl;
	}

	public String getDiagramPageId() {
	return diagramPageId;
	}

	public void setDiagramPageId(String diagramPageId) {
	this.diagramPageId = diagramPageId;
	}

	public Object getParts() {
	return parts;
	}

	public void setParts(Object parts) {
	this.parts = parts;
	}

	public String getPartCount() {
	return partCount;
	}

	public void setPartCount(String partCount) {
	this.partCount = partCount;
	}


}
