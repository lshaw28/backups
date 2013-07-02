var carouselMultifieldWidget = CQ.Ext.extend(CQ.form.CompositeField, {

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
        carouselMultifieldWidget.superclass.constructor.call(this, config);
    },

    //overriding CQ.Ext.Component#initComponent
    initComponent: function () {
    	carouselMultifieldWidget.superclass.initComponent.call(this);

        // Hidden field
        this.hiddenField = new CQ.Ext.form.Hidden({
            name: this.name
        });
        this.add(this.hiddenField);

        this.linkText = new CQ.Ext.form.TextField({
            cls: 'cls-linkText-1',
            fieldLabel: 'Image Name: ',
            maxLength: 80,
            maxLengthText: 'A maximum of 80 characters is allowed for the image name.',
            allowBlank: false,
            width: 275,
            listeners: {
                change: {
                    scope: this,
                    fn: this.updateHidden
                }
            }
        });
        this.add(this.linkText);

        

    },

    processInit: function (path, record) {
        this.linkText.processInit(path, record);
    },

    setValue: function (value) {
        var link = JSON.parse(value);
        this.linkText.setValue(link.text);
        this.hiddenField.setValue(value);
    },

    getValue: function () {
        return this.getRawValue();
    },

    getRawValue: function () {
        var link = {
            text: this.linkText.getValue()
        };
        return JSON.stringify(link);
    },

    updateHidden: function () {
        this.hiddenField.setValue(this.getValue());
    }
});

CQ.Ext.reg('spd.carouselMultifield', carouselMultifieldWidget);
