// declare namespace
CQ.Ext.namespace('Shc.components.extsrc');

/**
 * @const
 * @type {String}
 */
Shc.components.extsrc.SCAFFOLDING_PAGE_MAPPER_XTYPE = 'scaffoldingpagemapper';

/**
 * @class Shc.components.extsrc.ScaffoldingPageMapper
 * @extends Shc.components.extsrc.PageMapper
 * @constructor
 * @param {Object} config The config object
 * @xtype scaffoldingpagemapper
 */
Shc.components.extsrc.ScaffoldingPageMapper = CQ.Ext.extend(Shc.components.extsrc.PageMapper, {
	/**
	 * @Override
	 * Change the primary source of the parent collections model
	 * @return {undefined}
	 */
	parentStoreBootup: function () {
		var _this = this;
		
		// this is a fake dialog, expressed in the body.jsp JavaScript code
		this.parentDialog = this.findParentByType('container').ownerCt;
		
		// get content path, we're about to do sling call
		var contentNodePath = this.parentDialog.url;
		
		// only attempt to get existing data if node path is correct
		if (/\*/g.test(contentNodePath) === false) {
			// create a new parent store because the scaffolding JS never exposed it
			this.parentStore = new CQ.data.SlingStore({
				url: CQ.HTTP.externalize(contentNodePath) + CQ.Sling.SELECTOR_INFINITY + CQ.HTTP.EXTENSION_JSON
			});
			
			// call it!
			this.parentStore.load({
				callback: function () {
					// finally... push this data to where it belongs
					_this.loadDataFromParent();
				}
			});
		} else {
			// Its expressed in body.jsp that if a node path is invalid, its the spawner page.
			// We need to reset the data on creation
			this.parentDialog.getForm().on('actioncomplete', function () {
				_this.clearData();
			});
		}
	}
});

// register xtype
CQ.Ext.reg(Shc.components.extsrc.SCAFFOLDING_PAGE_MAPPER_XTYPE, Shc.components.extsrc.ScaffoldingPageMapper);