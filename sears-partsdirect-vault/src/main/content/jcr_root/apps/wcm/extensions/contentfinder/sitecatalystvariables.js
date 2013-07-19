
{
    "tabTip": CQ.I18n.getMessage("SiteCatalyst Variables"),
    "id": "cfTab-SiteCatalystVars",
    "iconCls": "cq-cft-tab-icon sitecatalystvars",
    "xtype": "contentfindertab",
    "ranking": 60,
    "allowedPaths": [
        "/etc/cloudservices/sitecatalyst/.*",
        "/etc/cloudservices/tagmanager/.*"
    ],
    "items": [
        CQ.wcm.ContentFinderTab.getQueryBoxConfig({
            "id": "cfTab-SCVariables-QueryBox",
            "items": [
                CQ.wcm.ContentFinderTab.getSuggestFieldConfig({
                    "minTermLength": 1000, /*disable search*/
                    "url": CQ.shared.HTTP.externalize("/libs/cq/analytics/sitecatalyst/variables.json"),
                    "search": function() {
                        var tab = this.findParentByType("contentfindertab");
                        var query = tab.getQueryValue();
                        tab.dataView.store.filterBy(function(rec){
                            var filter = tab.dataView.store.baseParams.filter;
                            for(var i=0; i<filter.length; i++){
                                if(rec.get('type').indexOf(filter[i]) > -1) {
                                    if(query && query.length > 0) {
                                        var regex = new RegExp('.*?'+query+'.*?','i');
                                        var matchesTitle = (rec.get('title').match(regex));
                                        var matchesName = (rec.get('name').match(regex));
                                        console.log("Match title", matchesTitle, "Match name", matchesName);
                                        return (matchesTitle || matchesName); 
                                    }
                                    return true;
                                }
                            }
                            return false;
                        });
                    }                                     
                })
            ]
        }),
        CQ.wcm.ContentFinderTab.getResultsBoxConfig(
            {
                "twoParametersPagination": true,
                "itemsDDGroups": ["sitecatalystvars"],
                "itemsDDNewParagraph": {
                    "path": "cq/analytics/components/sitecatalystvars",
                    "propertyName": "./variables"
                },
                "disableContinuousLoading": true,
                "items": {
                    "cls": "cq-cft-dataview cq-cft-sitecatalystvars-view",
                    "loadingText": CQ.I18n.getMessage("Loading variables..."),
                    "emptyText": CQ.I18n.getMessage("No SiteCatalyst variables found."),
                    "tpl": new CQ.Ext.XTemplate(
                        '<tpl for=".">'
                            + '<div class="cq-cft-sitecatalystvars-item cq-cft-sitecatalystvars-ddproxy-wrapper">'
                                + '<div class="cq-cft-sitecatalystvars-thumb"><img src="'+CQ.HTTP.externalize("/libs/cq/ui/widgets/themes/default/widgets/wcm/ContentFinderTab/")+'{type}.png" /></div>'
                                + '<div ext:qtip="{[ this.getHtmlTitle(values.title, true) ]}" class="cq-cft-sitecatalystvars-title">{[ this.getHtmlTitle(values.title) ]}'
                                + '<div class="cq-cft-sitecatalystvars-description">{name}</div></div>'
                                + '<div class="cq-cft-participant-separator"></div>'
                            + '</div>'
                        + '</tpl>', {
                            getHtmlTitle: function(jsonStr, tooltip) {
                                var arr = CQ.Ext.util.JSON.decode(jsonStr);
                                if (arr.length == 1)
                                    return arr[0].name;
                                var title = [];
                                CQ.Ext.each(arr, function(elem) {
                                    title.push(elem.name + ' (' + elem.rsid + ')');
                                });
                                return title.join('<br>');
                            }
                        }),
                    "itemSelector":  ".cq-cft-sitecatalystvars-item"
                },
                "tbar": [
                     {
                         "iconCls": "cq-siteadmin-refresh",
                         "handler": function(button,event) {
                             var tab = CQ.Ext.getCmp("cfTab-SiteCatalystVars");
                             var tb = button.findParentByType("toolbar");                            
                             /*reset toggles*/
                             var btns = tb.findByType("button");
                             for(var i=0; i<btns.length; i++) {
                                 if(btns[i].enableToggle) {
                                     btns[i].toggle(true);
                                 }
                             }
                             /*force reload*/
                             var store = tab.dataView.store;
                             store.baseParams.force = true;
                             store.removeAll();
                             store.reload();
                          },
                          "tooltip": CQ.I18n.getMessage("Refresh")
                     },
                     "->",
                     {
                         "xtype": "button",
                         "id": "traffic-btn",
                         "cls": "cq-btn-thumbs cq-cft-dataview-btn",
                         "iconCls":"cq-cft-dataview-traffic",
                         "tooltip": {
                             "text": CQ.I18n.getMessage("Traffic")
                         },
                         "enableToggle": true,
                         "pressed": false,
                         "toggleHandler": function(button, pressed) {
                             var tab = this.findParentByType("contentfindertab");
                             var suggest = tab.findByType("suggestfield")[0];
                             tab.dataView.store.baseParams.filter = (pressed) ? ["traffic"] : ["traffic", "conversion", "event"];
                             suggest.search();
                             CQ.Ext.getCmp('conversion-btn').toggle(false, true);
                             CQ.Ext.getCmp('event-btn').toggle(false, true);
                         }
                     },
                     {
                         "xtype": "button",
                         "id": "conversion-btn",
                         "cls": "cq-btn-thumbs cq-cft-dataview-btn",
                         "iconCls":"cq-cft-dataview-conversion",
                         "tooltip": {
                             "text": CQ.I18n.getMessage("Conversion")
                         },
                         "enableToggle": true,
                         "pressed": false,
                         "toggleHandler": function(button, pressed) {
                             var tab = this.findParentByType("contentfindertab");
                             var suggest = tab.findByType("suggestfield")[0];
                             tab.dataView.store.baseParams.filter = (pressed) ? ["conversion"] : ["traffic", "conversion", "event"];
                             suggest.search();
                             CQ.Ext.getCmp('traffic-btn').toggle(false, true);
                             CQ.Ext.getCmp('event-btn').toggle(false, true);
                         }
                     },
                     {
                         "xtype": "button",
                         "id": "event-btn",
                         "cls": "cq-btn-thumbs cq-cft-dataview-btn",
                         "iconCls":"cq-cft-dataview-event",
                         "tooltip": {
                             "text": CQ.I18n.getMessage("Event")
                         },
                         "enableToggle": true,
                         "pressed": false,
                         "toggleHandler": function(button, pressed) {
                             var tab = this.findParentByType("contentfindertab");
                             var suggest = tab.findByType("suggestfield")[0];
                             tab.dataView.store.baseParams.filter = (pressed) ? ["event"] : ["traffic", "conversion", "event"];
                             suggest.search();                    
                             CQ.Ext.getCmp('conversion-btn').toggle(false, true);
                             CQ.Ext.getCmp('traffic-btn').toggle(false, true);
                         }
                     }
                ]
            },
            {
                "url": CQ.shared.HTTP.externalize("/libs/cq/analytics/sitecatalyst/variables.json")
            },
            {
                "baseParams": {
                    "path": CQ.HTTP.getAnchor(CQ.WCM.getContentUrl()),
                    "filter": ["traffic", "conversion", "event"]
                },
                "autoDestroy": true,
                "autoLoad":true,
                "reader": new CQ.Ext.data.JsonReader({
                    "root": "variables",
                    "fields": [
                        "uid", "no", "name", "title", "path", "type"
                    ],
                    "id": "uid"
                })
            }
        )    
    ]
}
