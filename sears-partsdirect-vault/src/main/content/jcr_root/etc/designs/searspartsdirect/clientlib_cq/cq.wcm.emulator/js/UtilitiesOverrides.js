/*global $:true, window:true, Class:true */
(function (window) {
	"use strict";
	if (CQ.wcm.mobile.simulator.DeviceSimulator.currentDevice !== null) {
		window.SPDUtils.isMobileBreakpoint = function () {
			var currentWidth = parseInt($('#viewport').width(), 10);

			if (currentWidth < 768) {
				return true;
			} else {
				return false;
			}
		}
		window.SPDUtils.isTabletBreakpoint = function () {
			var currentWidth = parseInt($('#viewport').width(), 10);

			if (currentWidth > 767 && currentWidth < 1025) {
				return true;
			} else {
				return false;
			}
		}
		console.log('Simulator active, current device:', CQ.wcm.mobile.simulator.DeviceSimulator.currentDevice);
	} else {
		console.log('Simulator inactive, current device:', CQ.wcm.mobile.simulator.DeviceSimulator.currentDevice);
	}
}(window));