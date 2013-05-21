/**
* @class apps.redcross_main.MultiLinkWidget
* @extends CQ.form.CompositeField
* This is a custom path field with link text and target
* @param {Object} config the config object
*/

/**
* @class Ejst.CustomWidget
* @extends CQ.form.CompositeField
* This is a custom widget based on {@link CQ.form.CompositeField}.
* @constructor
* Creates a new CustomWidget.
* @param {Object} config The config object
*/

MultiLinkWidget = CQ.Ext.extend(CQ.form.CompositeField, {

	/**
	* @private
	* @type CQ.Ext.form.TextField
	*/
	hiddenField: null,

	/**
	* @private
	* @type CQ.Ext.form.TextField
	*/
	linkText: null,

	/**
	* @private
	* @type CQ.Ext.form.TextField
	*/
	linkURL: null,

	/**
	* @private
	* @type CQ.Ext.form.CheckBox
	*/
	openInNewWindow: null,

	/**
	* @private
	* @type CQ.Ext.form.FormPanel
	*/
	formPanel: null,
	
	innerConfig: {},

	constructor: function (config) {
		config = config || {};
		var defaults = {
			"border": true,
			"labelWidth": 75,
			"layout": "form",
			"linkLabel":"Link Text:",
			"urlLabel":"Link URL:",
			"rootPath":"/content",
			"openInNewWindowOption":false
			//"columns":6
		};
				

		config = CQ.Util.applyDefaults(config, defaults);
		innerConfig = config;
		
		MultiLinkWidget.superclass.constructor.call(this, config);
	},

	//overriding CQ.Ext.Component#initComponent
	initComponent: function () {
		MultiLinkWidget.superclass.initComponent.call(this);

		// Hidden field
		this.hiddenField = new CQ.Ext.form.Hidden({
			name: this.name
		});
		this.add(this.hiddenField);

		// Link text
		this.add(new CQ.Ext.form.Label({
			cls: "customwidget-label",
			text: innerConfig.linkLabel
		}));
		
		this.linkText = new CQ.Ext.form.TextField({
			cls: "customwidget-1",
			fieldLabel: innerConfig.linkLabel,
			maxLength: 80,
			maxLengthText: "A maximum of 80 characters is allowed for the Link Text.",
			allowBlank: true,
			width:325,
			listeners: {
				change: {
					scope: this,
					fn: this.updateHidden
				}
			}
		});
		this.add(this.linkText);

		// Link URL
		this.add(new CQ.Ext.form.Label({
			cls: "customwidget-label",
			text: innerConfig.urlLabel
		}));
		this.linkURL = new CQ.form.PathField({
			cls: "customwidget-2",
			fieldLabel: innerConfig.urlLabel,
			width: 325,
			rootPath: innerConfig.rootPath,
			listeners: {
				change: {
					scope: this,
					fn: this.updateHidden
				},
				dialogclose: {
					scope: this,
					fn: this.updateHidden
				}
			}
		});
		this.add(this.linkURL);

		// Link openInNewWindow
		this.openInNewWindow = new CQ.Ext.form.Checkbox({
			cls: "customwidget-3",
			boxLabel: "New window",
			listeners: {
				change: {
					scope: this,
					fn: this.updateHidden
				},
				check: {
					scope: this,
					fn: this.updateHidden
				}
			}
		});
		
		if(innerConfig.openInNewWindowOption) {
			this.add(this.openInNewWindow);
		}
	},

	processInit: function (path, record) {
		this.linkText.processInit(path, record);
		this.linkURL.processInit(path, record);
		this.openInNewWindow.processInit(path, record);
	},

	setValue: function (value) {
		var link = JSON.parse(value);
		this.linkText.setValue(link.text);
		this.linkURL.setValue(link.url);
		this.hiddenField.setValue(value);
	},

	getValue: function () {
		return this.getRawValue();
	},

	getRawValue: function () {
		var link = {
			"url": this.linkURL.getValue(),
			"text": this.linkText.getValue(),
			"openInNewWindow": this.openInNewWindow.getValue()
		};
		return JSON.stringify(link);
	},

	updateHidden: function () {
		this.hiddenField.setValue(this.getValue());
	}
});

CQ.Ext.reg("multilink", MultiLinkWidget);
