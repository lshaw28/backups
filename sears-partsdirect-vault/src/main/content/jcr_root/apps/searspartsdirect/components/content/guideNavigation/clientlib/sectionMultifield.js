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
            labelWidth: 75,
            layout: 'form'
        };
        config = CQ.Util.applyDefaults(config, defaults);
        sectionMultifieldWidget.superclass.constructor.call(this, config);
    },

    //overriding CQ.Ext.Component#initComponent
    initComponent: function () {
        sectionMultifieldWidget.superclass.initComponent.call(this);

        console.log('getting components');
        
        console.log('Page is '+CQ.Page);
        console.log('this.path is '+this.path);
        console.dir(CQ.WCM.getComponentList(null).allowedComponents);
        var cl = CQ.WCM.getComponentList(null)
        var rawAllowedComponents = cl.allowedComponents;
        var actualList = [];
        for (var cPath in rawAllowedComponents) {
        	var interior = {};
        	interior.value = cPath;
        	var cInfo = cl.getComponent(cPath);
        	interior.text = cInfo.title;
        	interior.qtip = cInfo.description;
        	actualList.push(interior);
        }
        console.dir(actualList);
        
        // Hidden field
        this.hiddenField = new CQ.Ext.form.Hidden({
            name: './sections'
        });
        this.add(this.hiddenField);

        this.resType = new CQ.form.Selection({
    		cls: 'cls-resType-1',
    		type: 'select',
    		fieldLabel: 'Resource type: ',
    		allowBlank: false,
    		width: 275,
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
			fieldDescription: 'Text of link, leave blank for default.',
            maxLength: 80,
            maxLengthText: 'A maximum of 80 characters is allowed for the Link Text.',
            allowBlank: true,
            width: 275,
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