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

MultiCustomWidget = CQ.Ext.extend(CQ.form.CompositeField, {

	/**
	* @private
	* @type CQ.Ext.form.TextField
	*/
	hiddenField: null,
	
	/**
	*@private
	*@type: mutilipy xtypes object that we can use
	*/
	xTypes:{
		"textarea":CQ.Ext.form.TextArea,
		"textfield":CQ.Ext.form.TextField,
		"pathfield":CQ.form.PathField,
		"checkbox":CQ.Ext.form.Checkbox,
		"datefield":CQ.Ext.form.DateField,
		"datetime":CQ.form.DateTime,
		"numberfield":CQ.Ext.form.NumberField
	},

	getTypeFromXType: function(type) {
		if (type == "datefield" || type == "datetime")
		{
			return Date;
		}
		if (type == "numberfield")
		{
			return Number;
		}
		return String;
	},

	constructor: function (config) {
		config = config || {};
		var defaults = {
			"border": true,
			"labelWidth": 75,
			"layout": "form"
			//"columns":6
		};				

		config = CQ.Util.applyDefaults(config, defaults);
		this.xtypeListeners={
			"textarea": {change:{scope:this, fn: this.updateHidden}},
			"textfield":{change:{scope: this,fn: this.updateHidden}},
			"pathfield":{change:{scope: this,fn: this.updateHidden},dialogclose:{scope: this,fn: this.updateHidden}},
			"checkbox":{change:{scope: this,fn: this.updateHidden},check:{scope: this,fn: this.updateHidden}},
			"datefield":{change:{scope: this,fn: this.updateHidden},select:{scope: this,fn: this.updateHidden}},
			"datetime":{change:{scope: this,fn: this.updateHidden}}
		};		
		MultiCustomWidget.superclass.constructor.call(this, config);		
	},

	
	//overriding CQ.Ext.Component#initComponent
	initComponent: function () {
		MultiCustomWidget.superclass.initComponent.call(this);
		
		var allItems = new Array();
		
		// Hidden field
		this.hiddenField = new CQ.Ext.form.Hidden({
			name: this.name
		});
		this.add(this.hiddenField);
				
		// Go in reverse order, because we are removing items from this list, don't want to get index out of bounds
		for(var i = this.items.items.length-1; i >= 0; i--) {
			var currentItem = this.items.items[i];		
			var initialConfig = currentItem.initialConfig;
			initialConfig["listeners"] = {
				"change": {
					scope: this,
					fn: this.updateHidden
				}
			};
			
			// We don't want these names to submit
			initialConfig["submitValue"] = false;
											
			var currentField;
			if(currentItem.getXType() == 'textfield') {
				currentField = new CQ.Ext.form.TextField(initialConfig);								
			} else if(currentItem.getXType() == 'pathfield') {
				initialConfig.listeners["dialogClose"] = {scope: this,fn: this.updateHidden};
				currentField = new CQ.form.PathField(initialConfig);							
			} else if(currentItem.getXType() == 'checkbox') {
				initialConfig.listeners["check"] = {scope: this,fn: this.updateHidden};
				currentField = new CQ.Ext.form.Checkbox(initialConfig);				
			} else if(currentItem.getXType() == 'textarea') {
				currentField = new CQ.Ext.form.TextArea(initialConfig);											
			} else if(currentItem.getXType() == 'datefield') {
				initialConfig.listeners["select"] = {scope: this,fn: this.updateHidden};
				currentField = new CQ.Ext.form.DateField(initialConfig);											
			} else if(currentItem.getXType() == 'datetime') {
				currentField = new CQ.form.DateTime(initialConfig);											
			} else if(currentItem.getXType() == 'numberfield') {
				currentField = new CQ.Ext.form.NumberField(initialConfig);											
			}
			
			if(currentField) {
				allItems.unshift(currentField);
				this.remove(currentItem);
			}
		}
		
		for(var j = 0; j < allItems.length; j++) {
			this.add(allItems[j]);
		}
			
	},
	
	updateHidden: function () {
		this.hiddenField.setValue(this.getValue());
	},
	
	getValue: function() {
		return this.getRawValue();
	},
	
	getRawValue: function () {
		var items = this.items.items;
		
		var link = {};
		
		for(var i = 0; i < items.length; i++) {
			var currentItem = items[i];
			var name = currentItem.getName();
			var value = currentItem.getValue();
			if(value && value != ''  && name != this.hiddenField.name) {
				link[name] = String(value);
			} else {
				link[name] = "";
			}
		}

		return JSON.stringify(link);
	
	},
	
	
	setValue: function (value) {					
		var jsonValue = JSON.parse(value);
		for(var i=0;i<this.items.items.length;i++){
			var currentItem = this.items.items[i];
			var type = this.getTypeFromXType(currentItem.getXType());
			currentItem.setValue(new type(jsonValue[currentItem.getName()]));
		} 
		this.hiddenField.setValue(value);
	},
	
	//select xtype
	xTypeSelector:function(xtype){
		//making a javascript object to mask the field name and the object so whenever if there are different type all we need to do is modify this object and the config for different fieldtype
		return this.xTypes[xtype];
	}

});

CQ.Ext.reg("multicustom", MultiCustomWidget);
