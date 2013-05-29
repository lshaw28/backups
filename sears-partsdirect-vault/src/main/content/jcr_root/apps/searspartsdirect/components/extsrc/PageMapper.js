// declare namespace
CQ.Ext.namespace('Shc.components.extsrc');

/**
 * @const
 * @type {String}
 */
Shc.components.extsrc.PAGE_MAPPER_XTYPE = 'pagemapper';

/**
 * @class Shc.components.extsrc.PageMapper
 * @extends CQ.form.CompositeField
 * Component that stores paths chosen by a content author using
 * drag and drop from a Tree Panel to a Grid Panel.
 * @constructor
 * Creates a new PageMapper.
 * @param {Object} config The config object
 * @xtype pagemapper
 */
Shc.components.extsrc.PageMapper = CQ.Ext.extend(CQ.form.CompositeField, {
	/**
	 * @type {String}
	 * Appear as a panel but act like a form!
	 */
	hideLabel: true,
	/**
	 * @type {CQ.Ext.form.Hidden}
	 */
	typeField: null,
	/**
	 * @type {String}
	 * Default is a name isn't provided
	 */
	formName: './pageMapper',
	/**
	 * @type {Array}
	 * List of {CQ.Ext.form.Hidden} for values
	 */
	hiddenFields: [],
	/**
	 * @type {CQ.Dialog}
	 */
	parentDialog: null,
	/**
	 * @type {}
	 */
	treePanel: null,
	/**
	 * @type {}
	 */
	gridPanel: null,
	/**
	 * @type {}
	 */
	containerPanel: null,
	/**
	 * Initializer event
	 * @return {undefined}
	 */
	initComponent: function () {
		// instantiate parent constructor
		Shc.components.extsrc.PageMapper.superclass.initComponent.call(this);
		
		// get parent refernece
		this.parentDialog = this.findParentByType('dialog');
		
		// form key resolver
		if (typeof this.getName().toString() === 'string') {
			this.formName = this.getName();
		}
		
		// set type to to store as String[] on JCR property
		this.setDataType('String[]');
		
		// wrapper to store components
		this.containerPanel = new CQ.Ext.Panel({
			"layout": "column"
		});
		
		// append container to main
		this.add(this.containerPanel);
	},
	/**
	 * @private
	 */
	initTreePanel: function () {
		
	},
	/**
	 * @private
	 */
	initGridPanel: function () {
		
	},
	/**
	 * Sets type to save on JCR property
	 * @param {String} type Specified type
	 * @return {undefined}
	 */
	setDataType: function (type) {
		this.typeField = new CQ.Ext.form.Hidden({
			name: this.getName() + CQ.Sling.TYPEHINT_SUFFIX,
			value: type
		});
		
		// append to form to be collected on dialog submit
		this.add(this.typeField);
	},
	/**
	 * Creates a hidden field with a new value and appends to component
	 * @param {String} value
	 * @return {undefined}
	 */
	addValue: function (value) {
		// name of field
		var name = this.formName,
			// hidden form to store value
			field = new CQ.Ext.form.Hidden({
				name: name,
				value: value
			});
		
		// save reference
		this.hiddenFields.push(field);
		
		// add to component
		this.add(field);
	},
	/**
	 * Removes specified hidden field from component
	 * @param {String} value
	 * @return {undefined}
	 */
	removeValue: function (value) {
		var i;
		
		// itertate over fields
		for (i = 0; i < this.hiddenFields.length; ++i) {
			// match?
			if (this.hiddenFields[i].getValue() === value) {
				// remove!
				this.remove(this.hiddenFields[i].getId(), true);
			}
		}
	}
});

// register xtype
CQ.Ext.reg(Shc.components.extsrc.PAGE_MAPPER_XTYPE, Shc.components.extsrc.PageMapper);