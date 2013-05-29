// declare namespace
CQ.Ext.namespace('Shc.components.extsrc');

/**
 * @const
 * @type {String}
 */
Shc.components.extsrc.PAGE_MAPPER_XTYPE = 'pagemapper';
/**
 * @const
 * @type {String}
 */
Shc.components.extsrc.PAGE_MAPPER_GROUPDD = Shc.components.extsrc.PAGE_MAPPER_XTYPE + 'dd';
/**
 * @const
 * @type {Number}
 */
Shc.components.extsrc.PAGE_MAPPER_HEIGHT = 200;

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
	 * Default if a name isn't provided
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
	 * @type {CQ.Ext.tree.TreePanel}
	 */
	treePanel: null,
	/**
	 * @type {CQ.Ext.grid.GridPanel}
	 */
	gridPanel: null,
	/**
	 * @type {CQ.Ext.Panel}
	 */
	containerPanel: null,
	/**
	 * @type {String}
	 */
	treeRootPath: '/content',
	/**
	 * @type {CQ.Ext.data.Store}
	 * Model reference
	 */
	parentStore: null,
	/**
	 * @type {CQ.Ext.data.Store}
	 * PageMapper model
	 */
	dataStore: null,
	/**
	 * Initializer event
	 * @return {undefined}
	 */
	initComponent: function () {
		// instantiate parent constructor
		Shc.components.extsrc.PageMapper.superclass.initComponent.call(this);
		
		// get parent reference
		this.parentDialog = this.findParentByType('dialog');
		
		// form key resolver
		if (typeof this.getName().toString() === 'string') {
			this.formName = this.getName();
		}
		
		// set type to to store as String[] on JCR property
		this.setDataType('String[]');
		
		// wrapper to store components
		this.containerPanel = new CQ.Ext.Panel({
			layout: 'column'
		});
		
		// create colletions model
		this.dataStore = new CQ.Ext.data.Store({
			writer: new CQ.Ext.data.JsonWriter({
				encode: true
			}),
			proxy: new CQ.Ext.data.MemoryProxy(),
			reader: new CQ.Ext.data.ArrayReader({}, CQ.Ext.data.Record.create([{
				name: 'path'
			}]))
		});
		// spin up proxy setting
		this.dataStore.load();
		
		// tree configs
		this.initTreePanel();
		
		// grid configs
		this.initGridPanel();
		
		// append container to main
		this.add(this.containerPanel);
	},
	/**
	 * Initialize {TreePanel
	 * @private
	 * @return {undefined}
	 */
	initTreePanel: function () {
		var _this = this;
		
		this.treePanel = new CQ.Ext.tree.TreePanel({
			ddGroup: Shc.components.extsrc.PAGE_MAPPER_GROUPDD,
			width: 240,
			height: Shc.components.extsrc.PAGE_MAPPER_HEIGHT,
			border: false,
			autoScroll: true,
			containerScroll: true,
			// prevent annoying grey background default
			bodyStyle: 'background: none',
			// hide parent
			rootVisible: false,
			enableDrag: true,
			loader: {
				// data retriever
				dataUrl: CQ.HTTP.externalize('/bin/wcm/siteadmin/tree.json'),
				requestMethod: 'GET',
				// request params
				baseParams: {
					_charset_: 'utf-8'
				},
				// change request params before loading
				listeners: {
					beforeload: function(loader, node) {
						// housekeeping to keep the tree not from exploding when it trys to read nodes
						this.baseParams.path = node.getPath();
						// you'd think enabling the drag feature on the parent would cascade down to the nodes :\
						this.baseAttrs.draggable = true;
					}
				},
				// attributes for all nodes created by the loader
				baseAttrs: {
					singleClickExpand: true,
					// folders look bad, this has a familiar experience (at least in 5.5...)
					iconCls: 'page',
					// more dragging configs!
					draggable: true
				}
			},

			// CQ.Ext.tree.TreeNode config
			root: {
				nodeType: 'async',
				// we don't show the root, no need to have text
				text: '',
				name: _this.treeRootPath,
				expanded: true,
				// no root dragging (its not like u can see it when its disabled anyways)
				draggable: false
			}
		});
		
		// append to wrapper
		this.containerPanel.add(this.treePanel);
	},
	/**
	 * Initialize Grid Panel
	 * @private
	 * @return {undefined}
	 */
	initGridPanel: function () {
		var _this = this;
		
		this.gridPanel = new CQ.Ext.grid.GridPanel({
			border: false,
			columnWidth: 1,
			style: 'min-height: ' + Shc.components.extsrc.PAGE_MAPPER_HEIGHT + 'px;',
			autoHeight: true,
			loadMask: true,
			stripeRows: true,
			autoExpandColumn: 'path',
			enableColumnMove: false,
			enableDragDrop: false,
			// collections model
			store: _this.dataStore,
			enableHdMenu: false,
			sm: new CQ.Ext.grid.RowSelectionModel({
				singleSelect: true
			}),
			cm: new CQ.Ext.grid.ColumnModel([
			{
				id: 'path',
				header: 'Content Path',
				dataIndex: 'path',
				sortable: true
			}
			])
		});
		
		// enable drop configs and actions
		this.gridPanel.on('render', function () {
			return new CQ.Ext.dd.DropTarget(this.getEl(), {
				ddGroup: Shc.components.extsrc.PAGE_MAPPER_GROUPDD,
				grid: this,
				// drop event callback
				notifyDrop: function(dd, e, data) {
					// clean up path
					var path = data.node.attributes.loader.baseParams.path.toString().substr(1) +
						'/' + data.node.attributes.name;

					// record new item
					_this.dataStore.add(new _this.dataStore.recordType({
						path: path
					}, CQ.Ext.id()));

					// add field
					_this.addValue(path);
				}
			});
		});
		
		this.containerPanel.add(this.gridPanel);
	},
	/**
	 * Sets type to save on JCR property
	 * @param {String} type Specified type
	 * @return {undefined}
	 */
	setDataType: function (type) {
		// name of field
		var name = this.formName;
		
		// field config
		this.typeField = new CQ.Ext.form.Hidden({
			name: name + CQ.Sling.TYPEHINT_SUFFIX,
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
		
		// render
		this.doLayout();
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
		
		// render
		this.doLayout();
	}
});

// register xtype
CQ.Ext.reg(Shc.components.extsrc.PAGE_MAPPER_XTYPE, Shc.components.extsrc.PageMapper);