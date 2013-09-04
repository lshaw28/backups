/*global window:true, $:true, Class:true, mainSitePath:true */
/**
 * Shared template for recently viewed part items
 * @param {object} item Part item to render
 * @return {object} jQuery element representation of the part item
 */
var partItemTemplate = function (item) {
	"use strict";

	// Properties
	var su = window.SPDUtils,
		li = $('<li />'),
		partName = su.validString(item.partName),
		partDescription = su.validString(item.partDescription),
		partUrl = su.validString(item.partURL),
		partImageUrl = su.validString(item.partImageURL);

	if (partImageUrl.toLowerCase() === 'null') {
		partImageUrl = '';
	}

	li.html('<a href="' + mainSitePath + partUrl + '">' + (partImageUrl !== '' ? '<img src="' + mainSitePath + partImageUrl + '" alt="' + partDescription + '" />' : '') + partName + '<br />' + partDescription + '</a>');

	return li;
};