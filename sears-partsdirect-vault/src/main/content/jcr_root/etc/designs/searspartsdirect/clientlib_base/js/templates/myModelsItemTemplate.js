/*global window:true, $:true, Class:true, mainSitePath:true */
/**
 * Shared template for my models items
 * @param {object} item Model item to render
 * @return {object} jQuery element representation of the model item
 */
var myModelsItemTemplate = function (item) {
	"use strict";

	// Properties
	var su = window.SPDUtils,
		span = $('<span />'),
		modelId = su.validString(item.id),
		modelNumber = su.validString(item.modelNumber),
		modelBrandName = su.validString(item.brandName),
		modelCategoryName = su.validString(item.categoryName),
		modelDescription = su.validString(item.modelDescription),
		modelItemUrl = su.validString(item.itemURL);

	span.addClass('cartModelItem');
	span.html('<input type="checkbox" value="' + modelId + '" /><a href="' + mainSitePath + modelItemUrl + '">' + modelBrandName + ' ' + modelCategoryName + ' model #' + modelNumber + '</a>');

	return span;
};