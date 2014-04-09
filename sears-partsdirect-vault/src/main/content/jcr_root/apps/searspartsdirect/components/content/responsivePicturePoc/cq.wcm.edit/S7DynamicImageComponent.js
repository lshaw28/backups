CQ.scene7 = CQ.scene7 || {};
CQ.scene7.dynamicImageHelper = {};

/**
 * Mark a flag if the unsharp mask fields need to be disabled after submitting the empty values
 */
CQ.scene7.dynamicImageHelper.reDisableUnsharpMaskFieldsAfterSubmission = false;

/**
 * Enables the size, image format and sharpening fields before submit if an image preset is selected
 *  so their empty values would get submitted
 * 
 */
CQ.scene7.dynamicImageHelper.enableFieldsForSubmission = function(dialog) {
    if (!dialog) {
        return;
    }
    
    // always submit the unsharp values
    var fieldNames = ["./unsharpMaskAmount", "./unsharpMaskRadius", "./unsharpMaskThreshold"];
    
    // if an image preset is selected, enable the fields so they'll get submitted
    var selectedImagePreset = CQ.scene7.dynamicImageHelper.getChildBy(dialog, "name", "./s7ImagePreset");
    if (selectedImagePreset && "" != selectedImagePreset.getValue()) {
        fieldNames.push("./width");
        fieldNames.push("./height");
        fieldNames.push("./outputFormat");
        fieldNames.push("./sharpeningMode");
    }
    
    for (var fieldNameIdx = 0 ; fieldNameIdx < fieldNames.length ; fieldNameIdx++) {
        var field = CQ.scene7.dynamicImageHelper.getChildBy(dialog, "name", fieldNames[fieldNameIdx]);
        if (field) {
            
            if (fieldNames[fieldNameIdx] == "./unsharpMaskAmount" && field.disabled) {
                CQ.scene7.dynamicImageHelper.reDisableUnsharpMaskFieldsAfterSubmission = true;
            }
            
            field.setDisabled(false);
        }
    }
};

/**
 * Disables the size, image format and sharpening fields after submission if an image preset is selected 
 */
CQ.scene7.dynamicImageHelper.disableFieldsAfterSubmission = function(dialog) {
    var selectedImagePreset = CQ.scene7.dynamicImageHelper.getChildBy(dialog, "name", "./s7ImagePreset");
    if (selectedImagePreset && "" != selectedImagePreset.getValue()) {
        CQ.scene7.dynamicImageHelper.updatePresetPrecedence(dialog, selectedImagePreset.getValue());
    }
    
    // re-disable unsharp mask fields, if needed
    if (CQ.scene7.dynamicImageHelper.reDisableUnsharpMaskFieldsAfterSubmission) {
        CQ.scene7.dynamicImageHelper.reDisableUnsharpMaskFieldsAfterSubmission = false;
        
        var unsharpMaskFieldNames = ["./unsharpMaskAmount", "./unsharpMaskRadius", "./unsharpMaskThreshold"];
        for (var fieldNameIdx = 0 ; fieldNameIdx < unsharpMaskFieldNames.length ; fieldNameIdx++) {
            var field = CQ.scene7.dynamicImageHelper.getChildBy(dialog, "name", unsharpMaskFieldNames[fieldNameIdx]);
            if (field) {
                field.setDisabled(true);
            }
        }
    }
};

/**
 * Handler for the "beforesubmit" event of the component edit dialog
 */
CQ.scene7.dynamicImageHelper.beforeDialogSubmit = function(dialog) {
    
    if (!dialog) {
        return;
    }
    
    // check the success and failure handlers
    var oldSuccessHandler = dialog.success;
    var oldFailureHandler = dialog.failure;
    
    if (typeof dialog.dynamicImageHandlers == "undefined") {
        // update handlers
        dialog.success = function(form, dialogResponse) {
            // disable the fields if needed
            CQ.scene7.dynamicImageHelper.disableFieldsAfterSubmission(dialog);
            
            // call old handler
            if (oldSuccessHandler) {
                oldSuccessHandler.call(this, form, dialogResponse);
            } else if (dialog.responseScope && dialog.responseScope.success) {
                dialog.responseScope.success.call(this, form, dialogResponse);
            }
        };
        
        dialog.failure = function(form, dialogResponse) {
            // disable the fields if needed
            CQ.scene7.dynamicImageHelper.disableFieldsAfterSubmission(dialog);
            
            // call old handler
            if (oldFailureHandler) {
                oldFailureHandler.call(this, form, dialogResponse);
            } else if (dialog.responseScope && dialog.responseScope.failure) {
                dialog.responseScope.failure.call(this, form, dialogResponse);
            }
        };
        
        dialog.dynamicImageHandlers = true;
    }
    
    // enable the fields
    CQ.scene7.dynamicImageHelper.enableFieldsForSubmission(dialog);
};

/**
 * Construct a config object based on the current field values in the edit dialog
 */
CQ.scene7.dynamicImageHelper.getImageConfig = function(component) {
    var tabPanel = component.findParentByType("tabpanel");
    var imgConfig = {};
    if (tabPanel) {
        var configMapArray = [
        {
            componentName: "./width",
            objectKey: "width"
        },
        {
            componentName: "./height",
            objectKey: "height"
        },
        {
            componentName: "./s7ImagePreset",
            objectKey: "s7ImagePreset"
        },
        {
            componentName: "./outputFormat",
            objectKey: "outputFormat"
        },
        {
            componentName: "./jpegQuality",
            objectKey: "jpegQuality"
        },
        {
            componentName: "./sharpeningMode",
            objectKey: "sharpeningMode"
        },
        {
            componentName: "./unsharpMaskAmount",
            objectKey: "unsharpMaskAmount"
        },
        {
            componentName: "./unsharpMaskRadius",
            objectKey: "unsharpMaskRadius"
        },
        {
            componentName: "./unsharpMaskThreshold",
            objectKey: "unsharpMaskThreshold"
        },
        {
            componentName: "./urlModifiers",
            objectKey: "urlModifiers"
        }
        ];
        
        for (var idx = 0 ; idx < configMapArray.length ; idx ++) {
            var componentSearchResult = tabPanel.find("name", configMapArray[idx].componentName);
            if (componentSearchResult && componentSearchResult.length > 0) {
                imgConfig[configMapArray[idx].objectKey] = componentSearchResult[0].getValue();
            }
        }
    }
    
    return imgConfig;
};

CQ.scene7.dynamicImageHelper.s7DropUpdateFormParams = function(comp) {
    // if we have valid dimension, update the advanced tab
    var tabPanel = comp.findParentByType("tabpanel");
    if (tabPanel) {
        var advancedTabSearch = tabPanel.find("title", "Advanced");
        var advancedTab = undefined;
        if (advancedTabSearch && advancedTabSearch.length > 0) {
            advancedTab = advancedTabSearch[0];
        }
        if (advancedTab) {
            var imageWidthFormField = CQ.scene7.dynamicImageHelper.getChildBy(advancedTab, "name", "./imageWidth");
            if (imageWidthFormField && comp.imageWidth > 0) {
                imageWidthFormField.setValue(comp.imageWidth);
            }
            
            var imageHeightFormField = CQ.scene7.dynamicImageHelper.getChildBy(advancedTab, "name", "./imageHeight");
            if (imageHeightFormField && comp.imageHeight > 0) {
                imageHeightFormField.setValue(comp.imageHeight);
            }
        }
    }
};

/**
 * Loads the SPS image presets for a given S7 config and updates the image presets combo with the new values
 * @param {String} s7ConfigPath - the Scene7 configuration for which the presets need to be fetched
 * @param {String} currentImagePreset - the selected image preset for this dynamic image
 * @param {CQ.form.Selection} presetSelectWidget - the image preset combo that will be updated with the preset values
 * 
 */
CQ.scene7.dynamicImageHelper.loadImagePresets = function(s7ConfigPath, currentImagePreset, presetSelectWidget) {
    
    if (!s7ConfigPath
            || !presetSelectWidget) {
        return;
    }
    
    // do a hit to load the presets
    CQ.HTTP.get(s7ConfigPath + "/jcr:content.imagepresets.json", function(options, success, xhr, response) {
        var newPresetOptions = [{
            text: 'None',
            value: ''}];
        
        if (success) {
            var jsonResponse = JSON.parse(xhr.responseText);
            if (jsonResponse && jsonResponse.length) {
                for (var imgPresetIdx = 0 ; imgPresetIdx < jsonResponse.length ; imgPresetIdx++) {
                    var imgPresetJson = jsonResponse[imgPresetIdx];
                    if (imgPresetJson.presetName) {
                        newPresetOptions.push({
                            text: imgPresetJson.presetName,
                            value: imgPresetJson.presetName});
                    }
                }
            }
        }

        presetSelectWidget.setOptions(newPresetOptions);
        
        if (currentImagePreset) {
            presetSelectWidget.setValue(currentImagePreset);
        }
        
    }, this);
};

/**
 * Selection change handler for the dynamic image output format field
 */
CQ.scene7.dynamicImageHelper.outputFormatSelectionChange = function(selectWidget, value, isChecked) {
    
    if (!selectWidget) {
        return;
    }
    
    // search for the container panel
    var parentPanel = selectWidget.findParentByType("panel");
    
    if(parentPanel) {
        // call the enable/disable jpeg quality method
        CQ.scene7.dynamicImageHelper.enableDisableJpegQuality(parentPanel, "jpeg" == value);
    }
};

/**
 * Selection change handler for the sharpening mode selector
 */
CQ.scene7.dynamicImageHelper.sharpeningModeSelectionChange = function(selectWidget, value, isChecked) {
    if (!selectWidget) {
        return;
    }
    
    // search for the container panel
    var parentPanel = selectWidget.findParentByType("panel");
    
    // update unsharp masking selectors status
    CQ.scene7.dynamicImageHelper.enableDisableUnsharpMask(parentPanel, ("unsharpMask" == value));
    
};

/**
 * Enable or disable the unsharp mask selectors based on the received boolean parameter
 * @parma tabPanel {CQ.Ext.Panel}
 *          panel widget under which unsharp mask sliders will be searched
 * @param {Boolean} enabled
 *          flag indicating if the sliders need to be enabled or not
 */
CQ.scene7.dynamicImageHelper.enableDisableUnsharpMask = function(tabPanel, enabled) {
    if (tabPanel) {
        
        var sharpeningModeFields = tabPanel.find("name", "./sharpeningMode");
        var selectedSharpeningMode = "";
        if (sharpeningModeFields && sharpeningModeFields.length > 0) {
            var sharpeningModeSelectWidget = sharpeningModeFields[0];
            selectedSharpeningMode = sharpeningModeSelectWidget.getValue();
        }
        
        enabled = enabled && "unsharpMask" == selectedSharpeningMode;
        
        var amountField = CQ.scene7.dynamicImageHelper.getChildBy(tabPanel, "name", "./unsharpMaskAmount");
        if (amountField) {
            // update amount slider widget state
            if (!enabled) {
                amountField.setValue(0);
            }
            CQ.scene7.dynamicImageHelper.updateSlider(amountField, !enabled, true);
        }
        
        var radiusField = CQ.scene7.dynamicImageHelper.getChildBy(tabPanel, "name", "./unsharpMaskRadius");
        if (radiusField) {
            // update radius slider widget state
            if (!enabled) {
                radiusField.setValue(0);
            }
            CQ.scene7.dynamicImageHelper.updateSlider(radiusField, !enabled, true);
        }
        
        var thresholdField = CQ.scene7.dynamicImageHelper.getChildBy(tabPanel, "name", "./unsharpMaskThreshold");
        if (thresholdField) {
            // update threshold slider widget state
            if (!enabled) {
                thresholdField.setValue(0);
            }
            CQ.scene7.dynamicImageHelper.updateSlider(thresholdField, !enabled, true);
        }
    }
};

/**
 * 
 * Enables or disables the jpeg quality field, based on the value of the received boolean
 * @parma tabPanel {CQ.Ext.Panel}
 *          panel widget under which the jpeg quality will be searched
 * @param {Boolean} jpegQualityEnabled
 *          if true the field will be enabled, if false it will be disabled
 */
CQ.scene7.dynamicImageHelper.enableDisableJpegQuality = function(tabPanel, jpegQualityEnabled) {
    
    if (!tabPanel) {
        return;
    }
    
    // enable or disable the jpeg quality field based on the selected format
    var outputFormatFieldsSearchResults = tabPanel.find("name", "./outputFormat");
    var selectedFormat = "";
    if (outputFormatFieldsSearchResults && outputFormatFieldsSearchResults.length > 0) {
        var outputFormatWidget = outputFormatFieldsSearchResults[0];
        selectedFormat = outputFormatWidget.getValue();
    }
    
    jpegQualityEnabled = jpegQualityEnabled && "jpeg" == selectedFormat;
    
    var jpegQualityFields = tabPanel.find("name", "./jpegQuality");
    
    if (jpegQualityFields && jpegQualityFields.length > 0) {
        var jpegQualityWidget = jpegQualityFields[0];
        
        // update slider widget state
        CQ.scene7.dynamicImageHelper.updateSlider(jpegQualityWidget, !jpegQualityEnabled, true);
    }
};

/**
 * Helper method to update the state of a {CQ.Ext.form.SliderField} widget
 * @param {CQ.Ext.form.SliderField} sliderWidget - the slider
 * @param {Boolean} disabled - boolean indicating if the slider needs to be disabled or not
 * @param {Boolean} updateValue - boolean indicating if the underlying slider will need to be updated (the thumb does not always reflect the current value...)
 */
CQ.scene7.dynamicImageHelper.updateSlider = function(sliderWidget, disabled, updateValue) {
    if (!sliderWidget) {
        return;
    }
    
    // update disabled state
    sliderWidget.setDisabled(disabled);
    
    var slider = sliderWidget.slider;
    if (slider && updateValue == true) {
        // update the slider thumb
        var currentValue = sliderWidget.getValue();
        slider.setValue(currentValue, false);
        slider.moveThumb(0, slider.translateValue(currentValue), false);
    }
};

/**
 * Searches a parent component for a child having a given property name and value
 * @param {CQ.Ext.Container} parent - container component under which the search will be performed
 * @param {String} childSearchPropName - property name what will be searched in the children
 * @param {String} childSearchPropValue - property value that will be searched in the children
 * @return {CQ.Ext.Component} the first child component that has childSearchPropName = childSearchPropValue or undefined if no match is found
 */
CQ.scene7.dynamicImageHelper.getChildBy = function(parent, childSearchPropName, childSearchPropValue) {
    if (parent && parent.find) {
        var childSearchResultArray = parent.find(childSearchPropName, childSearchPropValue);
        
        if (childSearchResultArray && childSearchResultArray.length > 0) {
            return childSearchResultArray[0];
        }
    }
    
    return undefined;
};

/**
 * Updates the values and states for the size, image format and image sharpness based on wether a preset is selected or not
 * Preset has precedence, so if one is selected the size, format and sharpness are disabled
 */
CQ.scene7.dynamicImageHelper.updatePresetPrecedence = function(dialog, selectedPreset) {
    var disableFields = "" != selectedPreset;
    
    if (dialog) {
        
        var fieldNames = ["./width", "./height", "./outputFormat", "./sharpeningMode"];
        for (var fieldNameIdx = 0 ; fieldNameIdx < fieldNames.length ; fieldNameIdx++) {
            var field = CQ.scene7.dynamicImageHelper.getChildBy(dialog, "name", fieldNames[fieldNameIdx]);
            if (field) {
                if (disableFields) {
                    field.setValue("");
                }
                field.setDisabled(disableFields);
            }
        }
    }
};

CQ.scene7.dynamicImageHelper.activateAdvancedTab = function(panel) {
    var dialog = panel.findParentByType("dialog");
    if (dialog) {
        var selectedImagePreset = CQ.scene7.dynamicImageHelper.getChildBy(dialog, "name", "./s7ImagePreset");
        if (selectedImagePreset) {
            CQ.scene7.dynamicImageHelper.updatePresetPrecedence(dialog, selectedImagePreset.getValue());
        }
    }
};

CQ.scene7.dynamicImageHelper.initS7ImagePanel = function(panel) {
    var imagePresetsPanel = panel.find("name", "imagePresetsHbox");
    var dialog = panel.findParentByType("dialog");
    var selectedImagePreset = "";
    if (imagePresetsPanel && imagePresetsPanel.length > 0) {
        
        imagePresetsPanel = imagePresetsPanel[0];
        imagePresetsPanel.removeAll();
        
        var selectedImagePresetArray = panel.find("name", "./s7ImagePreset");
        if (selectedImagePresetArray && selectedImagePresetArray.length > 0) {
            selectedImagePreset = selectedImagePresetArray[0].getValue();
        }
        
        var presetSelectWidget = new CQ.form.Selection({
            type: 'select',
            name: 'imagePresetCombo',
            fieldLabel: CQ.I18n.getMessage('Image Preset'),
            fieldDescription: CQ.I18n.getMessage("Image Preset to use when rendering image."),
            defaultValue: selectedImagePreset,
            listeners: {
                selectionchanged : function(select, value, isChecked ) {
                    if (selectedImagePresetArray && selectedImagePresetArray.length > 0) {
                        selectedImagePresetArray[0].setValue(value);
                        
                        // disable size, image format and sharpness selectors
                        CQ.scene7.dynamicImageHelper.updatePresetPrecedence(dialog, value);
                        
                        CQ.scene7.dynamicImageHelper.enableDisableJpegQuality(panel, "" == value);
                        CQ.scene7.dynamicImageHelper.enableDisableUnsharpMask(panel, "" == value);
                    }
                }
            },
            options: [
                  { text: 'None', value: ''},
            ]
        });
        
        var s7CloudConfigCombo = new CQ.cloudservices.Scene7CloudConfigurationCombo({
            "fieldLabel": CQ.I18n.getMessage("Scene7 Configuration"),
            "fieldDescription": CQ.I18n.getMessage("Scene7 Configuration used to fetch the active image presets from SPS"),
            "rootPath": "/etc/cloudservices/scene7",
            "name": "S7ImpTemplateConfigSelector",
            "selectFirst": true,
            "clearEnabled": false,
            "valueNotFoundText": CQ.I18n.getMessage("None"),
            "width": 200,
            "tpl":new CQ.Ext.XTemplate(
                    '<tpl for=".">',
                    '<div class="workflow-model-item x-combo-list-item">',
                        '<div class="workflow-model-title">{title:this.formatStr}</div>',
                        '<div style="clear:both"></div>',
                    '</div>',
                    '</tpl>',
                    '<div style="height:5px;overflow:hidden"></div>',
                    {
                        formatStr:function(v) {
                            return (v!== null) ? v : "";
                        }
                    }
                ),
           "listeners": {
                select: function (combo, record, index ) {
                    var selectedConfig = combo.getValue();
                    
                    CQ.scene7.dynamicImageHelper.loadImagePresets(selectedConfig, selectedImagePreset, presetSelectWidget);
                },
                change: function(store, newValue, oldValue ) {
                    CQ.scene7.dynamicImageHelper.loadImagePresets(newValue, selectedImagePreset, presetSelectWidget);
                }
            }
        });

        imagePresetsPanel.add(s7CloudConfigCombo);
        imagePresetsPanel.add(presetSelectWidget);
    }
    
    // enable or disable the jpeg quality field based on the selected format
    CQ.scene7.dynamicImageHelper.enableDisableJpegQuality(panel, "" == selectedImagePreset);
    
    // update unsharp mask slider fields based on the selected sharpening option
    CQ.scene7.dynamicImageHelper.enableDisableUnsharpMask(panel, "" == selectedImagePreset);
    
    // update fields based on selected preset
    CQ.scene7.dynamicImageHelper.updatePresetPrecedence(dialog, selectedImagePreset);
    
    panel.doLayout();
};