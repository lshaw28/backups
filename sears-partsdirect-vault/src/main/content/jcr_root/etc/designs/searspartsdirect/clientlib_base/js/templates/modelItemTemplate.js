/*global window:true, $:true, Class:true, mainSitePath:true */
/**
 * Shared template for recently viewed model items
 * @param {object} item Model item to render
 * @return {object} jQuery element representation of the model item
 */
var modelItemTemplate = function (item) {
	"use strict";

	// Properties
	var su = window.SPDUtils,
		li = $('<li />'),
		modelName = su.validString(item.modelName),
		modelDescription = su.validString(item.modelDescription),
		modelUrl = su.validString(item.modelURL);

	li.html('Model <a href="' + mainSitePath + modelUrl +'">' + modelName + '<br />' + modelDescription + '</a>');

	return li;
};