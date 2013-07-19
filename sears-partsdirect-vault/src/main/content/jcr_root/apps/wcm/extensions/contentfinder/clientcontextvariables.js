{
    "tabTip": CQ.I18n.getMessage("ClientContext Variables"),
    "id": "cfTab-ClientContextVars",
    "iconCls": "cq-cft-tab-icon clientcontextvars",
    "xtype": "contentfindertab",
    "ranking": 70,
    "allowedPaths": [
        "/etc/cloudservices/sitecatalyst/.*",
        "/etc/cloudservices/tagmanager/.*"
    ],
    "items": [
        CQ.wcm.ContentFinderTab.getQueryBoxConfig({
            "id": "cfTab-CCVariables-QueryBox",
            "items": [
                CQ.wcm.ContentFinderTab.getSuggestFieldConfig({
                    "minTermLength": 1000,
                    "url": "/bin/wcm/contentfinder/suggestions.json/content",
                    "search": function() {
                        var tab = this.findParentByType("contentfindertab");
                        var query = tab.getQueryValue();
                        tab.dataView.store.filterBy(function(rec){
                            if(query && query.length > 0) {
                                return (rec.get('title').match(new RegExp('.*?'+query+'.*?','i')));
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
                "itemsDDGroups": ["clientcontextvars"],
                "itemsDDNewParagraph": {
                    "path": "cq/analytics/components/clientcontextvars",
                    "propertyName": "./variables"
                },
                "disableContinuousLoading": true,
                "items": {
                    "cls": "cq-cft-dataview cq-cft-clientcontextvars-view",
                    "loadingText": CQ.I18n.getMessage("Loading variables..."),
                    "emptyText": CQ.I18n.getMessage("No ClientContext variables found."),
                    "tpl": new CQ.Ext.XTemplate(
                        '<tpl for=".">'
                            + '<div class="cq-cft-clientcontextvars-item cq-cft-clientcontextvars-ddproxy-wrapper">'
                                + '<div class="cq-cft-clientcontextvars-thumb"><img src="'+CQ.HTTP.externalize("/libs/cq/ui/widgets/themes/default/icons/16x16/clientcontext.png")+'" /></div>'
                                + '<div ext:qtip="{[CQ.shared.Util.htmlEncode(values[\"title\"])]}" class="cq-cft-clientcontextvars-title">{title}</div>'
                                + '<div ext:qtip="{store}" class="cq-cft-clientcontextvars-store">{[CQ.I18n.getMessage(\"Store\")]}: {store}</div>'
                                + '<div ext:qtip="{name}" class="cq-cft-clientcontextvars-property">{[CQ.I18n.getMessage(\"Property\")]}: {name}</div>'
                                + '<div class="cq-cft-participant-separator"></div>'
                            + '</div>'
                        + '</tpl>'),
                    "itemSelector":  ".cq-cft-clientcontextvars-item"
                },
                "listeners": {  
                    "afterrender": function(comp) {                     
                        var wnd = CQ.WCM.getContentWindow();    

                        var data = [];
                        
                        wnd.CQ_Analytics.CCM.addListener("storesinitialize",function(e) {    
                            var stores = wnd.CQ_Analytics.StoreRegistry.getStores();
                            for(var name in stores) {
                                var store = wnd.CQ_Analytics.StoreRegistry.getStore(name);
                                if( store ) {
                                    var props = store.getPropertyNames();
                                    for(var i = 0; i < props.length; i++) {
                                        var value = props[i];
                                        if( !CQ.shared.XSS.KEY_REGEXP.test(value) ) {
                                            var id = name + "." + value;
                                            data.push({
                                                "id": id, 
                                                "store": name, 
                                                "title":id, 
                                                "name":value, 
                                                "value":store.getProperty(value)
                                            });
                                        }
                                    }
                                }   
                            }
                        });
                        
                        var tab = CQ.Ext.getCmp("cfTab-ClientContextVars");
                        var cfstore = tab.dataView.store;
                        
                        var reloadStore = function(data) {
                            cfstore.proxy.data = {"variables": data};
                            cfstore.reload();
                            cfstore.filterBy(function(record) {
                                var compFwPath = record.get('componentFrameworkPath');
                                if (CQ.Ext.isArray(compFwPath)) {
                                    compFwPath = compFwPath[0];
                                }
                                var contentPath = CQ.WCM.getPagePath();
                                return !(contentPath && compFwPath.indexOf(contentPath) != 0);
                            });
                        };
                        
                        var provider = wnd.provider;
                        if (provider) {
                            provider.onAvailable('cqVars', function(newValue) {
                                reloadStore(data.concat(newValue));
                            });
                        } else {
                            reloadStore(data);
                        }
                    }
                }
            },
            {},/* no http proxy */
            {
                "autoLoad":true,
                "proxy": new CQ.Ext.data.MemoryProxy({"variables":[]}),
                "reader": new CQ.Ext.data.JsonReader({
                    "root": "variables",
                    "fields": [
                        "id", "store", "title", "name", "value", "componentFrameworkPath", "componentName", "componentIcon"
                    ],
                    "id": "id"
                })
            }            
        )    
    ]
}
