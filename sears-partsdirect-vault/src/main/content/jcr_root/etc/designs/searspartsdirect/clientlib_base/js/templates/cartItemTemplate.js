/*global window:true, $:true, Class:true, mainSitePath:true */
/**
 * Shared template for addToCart items
 * @param {object} item Cart item to render
 * @param {string} quantity Optional secondary quantity value
 * @return {object} jQuery element representation of the cart item
 */
var cartItemTemplate = function (item, quantity) {
	"use strict";

	// Properties
	var su = window.SPDUtils,
		li = $('<li />'),
		description = su.validString(item.description),
		partNumber = su.validString(item.partNumber),
		partUrl = su.validString(item.partUrl),
		productGroupId = su.validNumber(item.productGroupId),
		supplierId = su.validNumber(item.supplierId),
		renewel = su.validNumber(item.renewalPeriod),
		subNote = '';

	// Data validation
	if (su.validNumber(quantity) === 0) {
		quantity = su.validNumber(item.quantity);
	}
	if (description.length > 17) {
		description = description.substring(0, 17) + '...';
	}
	if (partUrl === '' && productGroupId > 0 && supplierId > 0) {
		partUrl = '/partsdirect/part-number/' + partNumber + '/' + productGroupId + '/' + supplierId;
	}
	if (renewel !== 0) {
		subNote = '<div class="cart-sub"><span class="svg-icon-truck"></span><span>Automatic Reorder</span></div><div class="cart-sub-freq"><span>Set: every ' + renewel + ' months</span></div>';
	}

	li.addClass('cart-item');
	li.html('<span class="cart-part"><a href="' + mainSitePath + partUrl + '">' + partNumber + '</a>' + (description !== '' ? '<br />' + description : '') + '</span><span class="cart-quantity">' + quantity + '</span>' + subNote);

	return li;
};