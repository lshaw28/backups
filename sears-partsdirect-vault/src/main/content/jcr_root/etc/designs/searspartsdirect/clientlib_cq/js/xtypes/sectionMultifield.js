var sectionMultifieldWidget = CQ.Ext.extend(CQ.form.CompositeField, {

    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    hiddenField: null,

    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    link: null,
    resType: null,

    /**
     * @private
     * @type CQ.Ext.form.FormPanel
     */
    formPanel: null,

    constructor: function (config) {
        config = config || {};
        var defaults = {
            border: true,
            labelWidth: 100,
            layout: 'form'
        };
        config = CQ.Util.applyDefaults(config, defaults);
        config.orderable = false;
        sectionMultifieldWidget.superclass.constructor.call(this, config);
    },

    //overriding CQ.Ext.Component#initComponent
    initComponent: function () {
        sectionMultifieldWidget.superclass.initComponent.call(this);

        var cl = CQ.WCM.getComponentList(null)
        var rawAllowedComponents = cl.allowedComponents;
        // We'll need to get components that are part of the template as well.
        // We will in fact NOT be part of the parsys at all.
        var actualList = [];
        for (var cPath in rawAllowedComponents) {
        	var interior = {};
        	interior.value = cPath;
        	var cInfo = cl.getComponent(cPath);
        	interior.text = cInfo.title;
        	interior.qtip = cInfo.description;
        	actualList.push(interior);
        }
        
        // Hidden field
        this.hiddenField = new CQ.Ext.form.Hidden({
            name: './sections'
        });
        this.add(this.hiddenField);

        this.resType = new CQ.form.Selection({
    		cls: 'cls-resType-1',
    		type: 'select',
    		fieldLabel: 'Component&nbsp;type',
    		allowBlank: false,
    		width: 350,
    		listeners: {
    			selectionchanged: {
    				scope: this,
    				fn: this.updateHidden
    			}
    		},
    		options: actualList
    	});
        this.add(this.resType);

        this.link = new CQ.Ext.form.TextField({
            cls: 'cls-link-1',
            fieldLabel: 'Link text',
            maxLength: 21,
            maxLengthText: 'A maximum of 21 characters is allowed for the Link Text.',
            allowBlank: true,
            width: 350,
            listeners: {
                change: {
                    scope: this,
                    fn: this.updateHidden
                }
            }
        });
        this.add(this.link);

    },

    processInit: function (path, record) {
        this.link.processInit(path, record);
    	this.resType.processInit(path, record);
    	this.openInNewWindow.processInit(path, record);
    },

    setValue: function (value) {
        var section = JSON.parse(value);
        this.link.setValue(section.link);
		this.resType.setValue(section.resType);
        this.hiddenField.setValue(value);
    },

    getValue: function () {
        return this.getRawValue();
    },

    getRawValue: function () {
        var section = {
            link: this.link.getValue(),
        	resType: this.resType.getValue()
        };
        return JSON.stringify(section);
    },

    updateHidden: function () {
        this.hiddenField.setValue(this.getValue());
    },
    
    getTypesAvailable: null
});
CQ.Ext.reg('spd.sectionMultifield', sectionMultifieldWidget);