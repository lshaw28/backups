NS('shc.pd.base.widgets').ResponsiveTable = (function () {
	'use strict';
	
	var TD_LABEL_CLASSNAME = 'visible-phone';
	
	return {
		/**
		 * @param {jQuery} tables
		 * @returns {undefined}
		 */
		init: function (tables) {
			
			tables.each(function () {
				var tableHeaders = [];
				
				// get headers
				$('thead th', this).each(function () {
					tableHeaders.push($(this).text());
				});
				
				// insert on each content td
				$('tbody td', this).each(function () {
					$(this).prepend('<div class="column-label ' + TD_LABEL_CLASSNAME +'">' + tableHeaders[$(this).index()] + '</div>');
				});
			});
		}
	};
}());