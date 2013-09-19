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
				var tableHeaders = [],
                    uniqueID;

				// get headers
				$('thead th', this).each(function () {
					tableHeaders.push($(this).text());
				});
				
				// insert on each content td
				$('tbody td', this).each(function () {
                    // if the first td, it will be shown - the others are collapsed
                    if ($(this).index() === 0) {
                        uniqueID = window.SPDUtils.getGUID();
                        $(this).attr('id', uniqueID);
                    } else {
                        $(this).addClass('td-hideable hidden-phone');
                    };
                    $(this).prepend('<div class="column-label ' + TD_LABEL_CLASSNAME +'">' + tableHeaders[$(this).index()] + '</div>');
				});
				
				// check if rows shouldn't be hidden
				if ($(this).hasClass('alwaysOpen')) {
					$('.alwaysOpen td:eq(0)').addClass('td-open');
					$('.alwaysOpen .td-hideable').removeClass('hidden-phone');
				// if not...
				} else {
					// add hide/show toggle for collapsible elements
					$('tbody').on('click','td',function(){
						$(this).toggleClass('td-open');
						$('#'+this.id+' ~ .td-hideable').toggleClass('hidden-phone');
					});
				}
				
			});
		}
     };
}());