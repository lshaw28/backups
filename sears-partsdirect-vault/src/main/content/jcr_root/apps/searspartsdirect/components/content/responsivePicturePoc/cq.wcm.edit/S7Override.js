console.log("S7 Overrides In PLace...");

CQ.form.S7SmartImage = CQ.Ext.extend(CQ.scene7.S7SmartImage, {

        
        
      
        constructor: function (config) {
            config = config || {};
  
	var defaults = {
		"fullTab" : true,
		"mimeTypesDescription" : CQ.I18n.getMessage("Images"),
		"ddAccept" : "image/",
		"uploadPanelCls" : null,
		"removeUploadPanelClsOnProgress" : false,
		"allowFileNameEditing" : false,
		"transferFileName" : false,
		"uploadIconCls" : "cq-image-placeholder",
		"uploadTextReference" : CQ.I18n.getMessage("Drop an image"),
		"uploadTextFallback" : CQ.I18n.getMessage("Upload image"),
		"uploadText" : CQ.I18n
				.getMessage("Drop an image or click to upload"),
		"height" : "auto",
		"anchor" : null,
		"pathProvider" : CQ.html5.form.SmartImage.defaultPathProvider,
		"hideMainToolbar" : false,
		"allowUpload": false
	};
	
	
	// Create tool defs
	this.imageToolDefs = [];
	if (config.mapParameter) {
		this.imageToolDefs.push(new CQ.form.ImageMap.Sears(
				config.mapParameter));
		delete config.mapParameter;
	}
	if (config.cropParameter) {
		this.imageToolDefs
				.push(new CQ.form.ImageCrop(config.cropParameter));
		delete config.cropParameter;
	}
	if (config.rotateParameter) {
		this.imageToolDefs.push(new CQ.form.SmartImage.Tool.Rotate(
				config.rotateParameter));
		delete config.rotateParameter;
	}
	
	

    CQ.Util.applyDefaults(config, defaults);
    
    // initialize crop req sizes to cropDefaultDimensio
    this.cropReqWidth = this.cropDefaultDimension;
    this.cropReqHeight = this.cropDefaultDimension;
    

	CQ.html5.form.SmartImage.superclass.constructor.call(this, config);
    
    // override serialize and deserialize crop methods to map max 400 x 400 dimensions to image original dimensions
    var imageTools = this.imageToolDefs;
    var smartImage = this;
    if (imageTools) {
        for (var imageToolIdx = 0 ; imageToolIdx < imageTools.length ; imageToolIdx ++) {
            if (imageTools[imageToolIdx] instanceof CQ.form.ImageCrop) {
                var originalSerialize = imageTools[imageToolIdx].serialize;
                var originalDeserialize = imageTools[imageToolIdx].deserialize;
                
                // overwrite the serialize and deserialize, to make sure we're being called to re-map the values to the scaled image
                imageTools[imageToolIdx].serialize = function() {
                    // call original
                    var serializedCrop = originalSerialize.call(this);
                    
                    // translate the crop to the original sizes
                    return smartImage.cropSerialize(serializedCrop);
                };
                
                imageTools[imageToolIdx].deserialize = function(cropDefStr) {
                    // translate the crop from original sizes to scaled dimensions
                    var translatedCrop = smartImage.cropDeserialize(cropDefStr);
                    
                    return originalDeserialize.call(this, translatedCrop);
                };
            }
        }
    }
        }



        
        
    });
    
    
    
    // register xtype
CQ.Ext.reg('s7html5smartimage', CQ.form.S7SmartImage);


CQ.form.ImageMap.Sears = CQ.Ext.extend(CQ.form.ImageMap, {

	constructor : function(transferFieldName) {

		CQ.form.ImageMap.superclass.constructor.call(this, {
			"toolId" : "smartimageMap",
			"toolName" : CQ.I18n.getMessage("SearsMap"),
			"iconCls" : "cq-image-icon-map",
			"isCommandTool" : false,
			"userInterface" : new CQ.form.ImageMap.UI.Sears({
				"title" : CQ.I18n.getMessage("Image map tools")
			}),
			"transferFieldName" : transferFieldName

		});

	}
});

CQ.form.ImageMap.UI.pathHelper = "";

CQ.form.ImageMap.UI.Sears = CQ.Ext
		.extend(
				CQ.form.ImageMap.UI,
				{
				
					constructor : function(config) {
						var clickHandler = function(item) {
							this.toolClicked(item.itemId);
						}.createDelegate(this);
						// as Ext does only save the CQ.Ext.Elements of toolbar
						// items, we'll have to
						// keep references of the underlying buttons on our own
						this.toolbarButtons = {
							"addRect" : new CQ.Ext.Toolbar.Button({
								"itemId" : "addRect",
								"text" : CQ.I18n.getMessage("Rectangle"),
								"enableToggle" : true,
								"toggleGroup" : "mapperTools",
								"allowDepress" : false,
								"handler" : clickHandler
							}),
							"addCircle" : new CQ.Ext.Toolbar.Button({
								"itemId" : "addCircle",
								"text" : CQ.I18n.getMessage("Circle"),
								"enableToggle" : true,
								"toggleGroup" : "mapperTools",
								"allowDepress" : false,
								"handler" : clickHandler
							}),
							"addPoly" : new CQ.Ext.Toolbar.Button({
								"itemId" : "addPoly",
								"text" : CQ.I18n.getMessage("Polygon"),
								"enableToggle" : true,
								"toggleGroup" : "mapperTools",
								"allowDepress" : false,
								"handler" : clickHandler
							}),
							"editPolyPoint" : new CQ.Ext.Toolbar.Button({
								"itemId" : "editPolyPoint",
								"xtype" : "tbbutton",
								"text" : CQ.I18n.getMessage("Polygon point"),
								"enableToggle" : true,
								"toggleGroup" : "mapperTools",
								"allowDepress" : false,
								"handler" : clickHandler
							}),
							"edit" : new CQ.Ext.Toolbar.Button({
								"itemId" : "edit",
								"text" : CQ.I18n.getMessage("Edit"),
								"enableToggle" : true,
								"toggleGroup" : "mapperTools",
								"allowDepress" : false,
								"handler" : clickHandler
							})
						};

						var toolbar = new CQ.Ext.Toolbar({
							"xtype" : "toolbar",
							"items" : [ CQ.I18n.getMessage("Add") + ":",
									this.toolbarButtons["addRect"],
									this.toolbarButtons["addCircle"],
									this.toolbarButtons["addPoly"],
									this.toolbarButtons["editPolyPoint"], {
										"xtype" : "tbseparator"
									}, this.toolbarButtons["edit"], {
										"xtype" : "tbseparator"
									}, {
										"itemId" : "delete",
										"xtype" : "tbbutton",
										"text" : CQ.I18n.getMessage("Delete"),
										"handler" : function() {
											this.deleteSelection();
										}.createDelegate(this)
									} ]
						});
						var defaults = {
							"layout" : "column",
							"bodyStyle" : "padding-top: 1px; "
									+ "padding-bottom: 1px; "
									+ "padding-left: 3px; "
									+ "padding-right: 2px;",
							"width" : CQ.themes.SmartImage.Tool.MAP_TOOLS_WIDTH,
							"tbar" : toolbar,
							"items" : [
									{
										"itemId" : "col1",
										"xtype" : "panel",
										"layout" : "form",
										"border" : false,
										"columnWidth" : 0.5,
										"labelWidth" : CQ.themes.SmartImage.Tool.MAP_AREAEDITOR_LABEL_WIDTH,
										"defaults" : {
											"itemCls" : "cq-map-areaeditor-fieldlabel",
											"cls" : "cq-map-areaeditor-text",
											"fieldClass" : "cq-map-areaeditor-text",
											"width" : CQ.themes.SmartImage.Tool.MAP_AREAEDITOR_FIELD_WIDTH
										},
										"items" : [
												{
													"itemId" : "areaDefUrl",
													"name" : "url",
													"xtype" : "pathfield",
													"fieldLabel" : "HREF",
													"rootPath" : "/content/shc/sears/en_us/searsstyle/",
													"listeners" : {
														"dialogopen" : {
															"fn" : function() {
																this.__position = this
																		.getPosition();
																this.hide();
															},
															"scope" : this
														},
														"dialogclose" : {
															"fn" : function() {
															
																this.show();
																this
																		.setPosition(this.__position);
																this
																		.pathChanged(
																				this.items
																						.get("col1").items
																						.get(
																								"areaDefUrl")
																						.getValue(),
																				this.items
																						.get("col1").items
																						.get(
																								"modalContentId")
																						.getValue());
															},
															"scope" : this
														}
													}
												},

												{
													"itemId" : "modalContentId",
													"name" : "modalContent",
													"xtype" : "selection",
													"type" : "select",
													"fieldLabel" : "Content ID",
													"options" : []
												},
												
												{
													"itemId" : "sku",
													"name" : "sku",
													"xtype" : "textfield",
													"fieldLabel" : "SKU#"
												},

											
												
												{
													"itemId" : "areaDefTarget",
													"name" : "target",
													"xtype" : "selection",
													"type" : "select",
													"fieldLabel" : "Target",
													"options" : [ {
														"value" : "",
														"text" : "Same Window"
													}, {
														"value" : "_blank",
														"text" : "New Window"
													}

													]
												},
												
												
												{
													"itemId" : "omnitureId",
													"name" : "omniture",
													"xtype" : "textfield",
													"fieldLabel" : "Omniture ID"
												}

										]
									},
									{
										"itemId" : "col2",
										"xtype" : "panel",
										"layout" : "form",
										"border" : false,
										"columnWidth" : 0.5,
										"labelWidth" : CQ.themes.SmartImage.Tool.MAP_AREAEDITOR_LABEL_WIDTH,
										"defaults" : {
											"itemCls" : "cq-map-areaeditor-fieldlabel",
											"cls" : "cq-map-areaeditor-text",
											"fieldClass" : "cq-map-areaeditor-text",
											"width" : CQ.themes.SmartImage.Tool.MAP_AREAEDITOR_FIELD_WIDTH
										},
										"items" : [ {
											"itemId" : "areaDefText",
											"name" : "text",
											"xtype" : "textfield",
											"fieldLabel" : "Text"
										}, {
											"itemId" : "areaDefCoords",
											"name" : "coords",
											"xtype" : "textfield",
											"fieldLabel" : "Coordinatesx"
										},

										{
											"itemId" : "areaDefHandler",
											"name" : "handler",
											"xtype" : "selection",
											"type" : "select",
											"fieldLabel" : "Type",
											"options" : [ {
												"value" : "link",
												"text" : "Link"
											}, {
												"value" : "modal",
												"text" : "Modal Window"
											},
											
											 {
												"value" : "modal_wbackdrop",
												"text" : "Modal Window - Backdrop"
											},

											
											{
												"value" : "quickview",
												"text" : "QuickView"
											}
											

											]
										},

										{
											"itemId" : "areaDefHotSpot",
											"name" : "hotspot",
											"xtype" : "selection",
											"type" : "checkbox",
											"fieldLabel" : "HotSpot?",
											"fieldValue" : "true",
											"defaultValue" : "false"

										} ]
									} ]
						};
						
						CQ.Util.applyDefaults(config, defaults);
						
						CQ.form.ImageMap.UI.superclass.constructor.call(this,
								config);
					},

					pathChanged : function(pth, hold) {

						var refme = this;
						if (pth.length < 1 || pth == '{0}') {
							pth = defaultPath;
						}
						console.log("Path Changed");
						console.log(HotSpotOptionsPath + '.options.html?q='
								+ pth);
						$
								.ajax({
									url : HotSpotOptionsPath
											+ '.options.html?q='
											+ this.areaDefUrl.getValue(),
									error : function(xhr, status, error) {
										console
												.log(String
														.format(
																'content fetch failed: status={0}, error={1}',
																status, error));
									},
									success : function(data) {
										console.log("success");
										console.log(data);

										refme.areaDefModal.setValue("");
										refme.areaDefModal.setOptions([]);
										refme.areaDefModal
												.setOptions(eval(data));
										refme.areaDefModal.setValue(hold);
									},
									timeout : 20000
								});


					},

					/**
					 * Initializes the user interface's components.
					 */
					initComponent : function() {
						CQ.form.ImageMap.UI.superclass.initComponent.call(this);
						this.areaDefUrl = this.items.get("col1").items
								.get("areaDefUrl");
						this.areaDefTarget = this.items.get("col1").items
								.get("areaDefTarget");
						this.areaDefText = this.items.get("col2").items
								.get("areaDefText");
						this.areaDefCoords = this.items.get("col2").items
								.get("areaDefCoords");

						this.areaDefHotSpot = this.items.get("col2").items
								.get("areaDefHotSpot");
						this.areaDefHandler = this.items.get("col2").items
								.get("areaDefHandler");

						var refme = this;
						var randomnumber = Math.floor(Math.random() * 11);

						this.areaDefOmniture = this.items.get("col1").items
								.get("omnitureId");
						this.areaDefSku = this.items.get("col1").items
						.get("sku");
						this.areaDefModal = this.items.get("col1").items
								.get("modalContentId");

						this.areaDefCoords.on("specialkey", function(tf,
								keyEvent) {
							var editedArea = this.editedArea;
							if ((keyEvent.getKey() == CQ.Ext.EventObject.ENTER)
									&& (editedArea != null)) {
								if (editedArea
										.fromCoordString(this.areaDefCoords
												.getValue())) {
									// var repaintAreas = [ editedArea ];
									this.workingArea.drawImage();
								}
								this.areaDefCoords.setValue(editedArea
										.toCoordString());
							}
						}, this);
						this.setDestinationAreaEditorEnabled(false);

					},

					/**
					 * Enables or disables the destination area editor.
					 * 
					 * @param {Boolean}
					 *            isEnabled True to enable the destination area
					 *            editor
					 */
					setDestinationAreaEditorEnabled : function(isEnabled) {
						this.areaDefUrl.setDisabled(!isEnabled);
						this.areaDefTarget.setDisabled(!isEnabled);
						this.areaDefText.setDisabled(!isEnabled);
						this.areaDefCoords.setDisabled(!isEnabled);
						this.areaDefOmniture.setDisabled(!isEnabled);
						this.areaDefModal.setDisabled(!isEnabled);
						this.areaDefHandler.setDisabled(!isEnabled);
						this.areaDefHotSpot.setDisabled(!isEnabled);
						this.areaDefSku.setDisabled(!isEnabled);
					},

					/**
					 * Saves the current content of the destination area editor
					 * to the specified image area.
					 * 
					 * @param {CQ.form.ImageMap.Area}
					 *            area Area to save data to
					 */
					saveDestinationArea : function(area) {

						if (!area) {
							area = this.editedArea;
						}
						if (area) {
							var url = this.areaDefUrl.getValue();
							/*
							 * if (url.length > 0) { if (url.charAt(0) != "/") {
							 * var protocolSepPos = url.indexOf("://"); if
							 * (protocolSepPos < 0) { if (url.indexOf("mailto:") !=
							 * 0) { url = "http://" + url; } } } }
							 */
							area.destination.url = url;
							area.destination.target = this.areaDefTarget
									.getValue();
							area.destination.text = this.areaDefText.getValue();
							area.destination.omniture = this.areaDefOmniture
									.getValue();
							area.destination.sku = this.areaDefSku
							.getValue();
							area.destination.content = this.areaDefModal
									.getValue();
							area.destination.handler = this.areaDefHandler
									.getValue();

							area.destination.hotspot = this.areaDefHotSpot
									.getValue()[0] || false;

						}

					},

					/**
					 * Loads the current content of the destination area editor
					 * from the specified image area.
					 * 
					 * @param {CQ.form.ImageMap.Area}
					 *            area area to load data from; null to clear the
					 *            current content
					 */
					loadDestinationArea : function(area) {
						if (area != null) {
							this.areaDefUrl.setValue(area.destination.url);

							this.areaDefTarget
									.setValue(area.destination.target);
							this.areaDefCoords.setValue(area.toCoordString());
							this.areaDefText.setValue(area.destination.text);
							this.areaDefOmniture
									.setValue(area.destination.omniture);
							this.pathChanged(area.destination.url,
									area.destination.content);
							this.areaDefHandler
									.setValue(area.destination.handler);
							this.areaDefHotSpot
									.setValue(area.destination.hotspot);
							this.areaDefSku
							.setValue(area.destination.sku);

						} else {
							this.areaDefUrl.setValue("");
							this.areaDefTarget.setValue("");
							this.areaDefText.setValue("");
							this.areaDefCoords.setValue("");
							this.areaDefOmniture.setValue("");
							this.areaDefModal.setValue("");
							this.areaDefHandler.setValue("");
							this.areaDefHotSpot.setValue("");
							this.areaDefSku.setValue("");
						}

					}
				});
