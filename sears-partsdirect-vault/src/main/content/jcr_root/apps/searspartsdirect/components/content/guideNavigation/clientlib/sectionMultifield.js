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
    name: null,

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

        // Hidden field
        this.hiddenField = new CQ.Ext.form.Hidden({
            name: this.name
        });
        this.add(this.hiddenField);

        this.name = new CQ.Ext.form.TextField({
            cls: 'cls-name-1',
            fieldLabel: 'Name: ',
            maxLength: 80,
            maxLengthText: 'A maximum of 80 characters is allowed for the Section Name.',
            allowBlank: false,
            width: 275,
            listeners: {
                change: {
                    scope: this,
                    fn: this.updateHidden
                }
            }
        });
        this.add(this.name);

        this.link = new CQ.Ext.form.TextField({
            cls: 'cls-link-1',
            fieldLabel: 'Bind to Section ID',
			fieldDescription: 'This ID should match the Section Block ID.',
            maxLength: 80,
            maxLengthText: 'A maximum of 80 characters is allowed for the Link Text.',
            allowBlank: false,
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
        this.name.processInit(path, record);
	this.openInNewWindow.processInit(path, record);

    },

    setValue: function (value) {
        var section = JSON.parse(value);
        this.link.setValue(section.link);
	this.name.setValue(section.name);
        this.hiddenField.setValue(value);
    },

    getValue: function () {
        return this.getRawValue();
    },

    getRawValue: function () {
        var section = {
            link: this.link.getValue(),
            name: this.name.getValue()
        };
        return JSON.stringify(section);
    },

    updateHidden: function () {
        this.hiddenField.setValue(this.getValue());
    }
});

CQ.Ext.reg('spd.sectionMultifield', sectionMultifieldWidget);