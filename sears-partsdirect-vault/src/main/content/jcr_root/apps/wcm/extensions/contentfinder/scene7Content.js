{
    "id":"cfTab-S7Browse",
        "xtype":"contentfindertab",
        "tabTip":CQ.I18n.getMessage("Scene7 Media Browser"),
        "iconCls":"cq-cft-tab-icon scene7Content",
        "allowedPaths": [
    "/content/*"
],
        "items":[
    {
        xtype:"panel",
        region:"north",
        width:"auto",

        "items":[
             {
                 xtype:"panel",
                 region:"center",
                 padding: 5,
                 width:"auto",
                 items:[
                        {
                        	"xtype": "label",
                        	"text": CQ.I18n.getMessage("Configuration"),
                        	"cls": "x-form-field x-form-item-label"
                        },
                        {
                            "xtype": "scene7cloudservicescombo",
                            "fieldLabel": CQ.I18n.getMessage("Configuration"),
                            "rootPath": "/etc/cloudservices/scene7",
                            "name": "S7ConfigSelector",
                            "selectFirst": true,
                            "clearEnabled": false,
                            "valueNotFoundText": CQ.I18n.getMessage("None"),
                            "id": "cfTab-S7Browse-S7ConfigSelector",
                            "tpl":new CQ.Ext.XTemplate(
                                    '<tpl for=".">',
                                    '<div class="workflow-model-item x-combo-list-item">',
                                        '<div class="workflow-model-title">{title:this.formatStr}</div>',
                                        '<div style="clear:both"></div>',
                                    '</div>',
                                    '</tpl>',
                                    '<div style="height:5px;overflow:hidden"></div>',
                                    {
                                        formatStr:function(v) {
                                            return (v!== null) ? v : "";
                                        }
                                    }
                                ),
                           "listeners": {
                                select: function (combo, record, index ) {
                                    var browseTree = CQ.Ext.getCmp("cfTab-S7Browse-Tree");
                                    var newTreeRootConfig = {
                                        "name": "content",
                                        "text": combo.getCqRootPath(),
                                        "draggable": false,
                                        "expanded":true
                                    };

                                    var newRootNode = new CQ.Ext.tree.AsyncTreeNode(newTreeRootConfig);
                                    
                                    browseTree.setRootNode(newRootNode);
                                }
                            }
                        }   
                 ]
             },
            CQ.wcm.ContentFinderTab.getBrowseTree({
                "treeRoot":{
                    "text": CQ.S7.getCqRootPath(CQ.S7.getSelectedConfigPath())
                },
                "treeLoader":{
                    "dataUrl":CQ.S7.getSelectedConfigPath() + "/jcr:content.foldertree.json",
                    "listeners": {
                        "beforeload": function(loader, node) {
                            loader.dataUrl = CQ.S7.getSelectedConfigPath() + "/jcr:content.foldertree.json";
                        	var browseTree = CQ.Ext.getCmp("cfTab-S7Browse-Tree");
                        	browseTree.getRootNode().setText(CQ.S7.getCqRootPath(CQ.S7.getSelectedConfigPath()));
                            this.baseParams.path = node.getPath();
                            this.baseParams.configPath = CQ.S7.getSelectedConfigPath();
                        }
                    }
                },
                "selectionchangeListener":function(selModel, node){
                    var store = CQ.Ext.getCmp("cfTab-S7Browse-resultBox").items.get(0).store;
                    CQ.S7.setSearchQualifiers(store);
                    store.reload();
                }

            }, "cfTab-S7Browse-Tree", "cfTab-S7Browse-resultBox"),
            {
                xtype:"panel",
                region:"center",
                padding: 5,
                width:"auto",
                items:[
                    CQ.wcm.ContentFinderTab.getSuggestFieldConfig(
                            {"id":"cfTab-S7Browse-searchField",
                                "store": {
                                    "url": CQ.S7.getSelectedConfigPath() + "/jcr:content.search.json",
                                    "listeners" : {
                                        "beforeload" : function(store, params) {
                                            store.proxy.setUrl(CQ.S7.getSelectedConfigPath() + "/jcr:content.search.json", true);
                                        }
                                    }
                                },
                                "emptyText": CQ.I18n.getMessage("Search by name"),
                                "search": function() {
                                    var store = CQ.Ext.getCmp("cfTab-S7Browse-resultBox").items.get(0).store;
                                    CQ.S7.setSearchQualifiers(store);
                                    store.reload();
                                }

                            }),
                    {
                        xtype: "spacer",
                        height: 5
                    },
                    {
                        xtype: "combo",
                        id: "cfTab-S7Browse-assetType",
                        triggerAction:"all",
                        mode: "local",
                        store: [["None",CQ.I18n.getMessage("None")],
                            ["AdjustedView",CQ.I18n.getMessage("AdjustedView")],
                            ["Flash",CQ.I18n.getMessage("Flash")],
                            ["Image",CQ.I18n.getMessage("Image")],
                            ["Fxg",CQ.I18n.getMessage("FXG")],
                            ["Template",CQ.I18n.getMessage("Template")],
                            ["Video",CQ.I18n.getMessage("Video")],
                            ["MbrSet",CQ.I18n.getMessage("Adaptive Video Set")]],
                        editable: false,
                        emptyText: CQ.I18n.getMessage("Filter by asset type"),
                        listeners: {
                            select: function (combo, record, index ) {
                                var store = CQ.Ext.getCmp("cfTab-S7Browse-resultBox").items.get(0).store;
                                CQ.S7.setSearchQualifiers(store);
                                store.reload();
                            }
                        }
                    }
                ]
            }
        ]
    },
    CQ.wcm.ContentFinderTab.getResultsBoxConfig({
                "id":"cfTab-S7Browse-resultBox",
                "disableContinuousLoading":true,
                "width": "auto",
                "tbar":[
                    CQ.wcm.ContentFinderTab.REFRESH_BUTTON,
                    "->",
                    {
                        "toggleGroup":"cfTab-S7Browse-TG",
                        "enableToggle":true,
                        "toggleHandler":function(button, pressed) {
                            var tab = CQ.Ext.getCmp("cfTab-S7Browse");
                            if (pressed) {
                                tab.dataView.tpl = new CQ.Ext.XTemplate(CQ.S7.THUMBS_TEMPLATE);
                                tab.dataView.itemSelector = CQ.wcm.ContentFinderTab.THUMBS_ITEMSELECTOR;
                            }
                            if (tab.dataView.store != null) {
                                tab.dataView.refresh();
                            }
                        },
                        "pressed":true,
                        "allowDepress":false,
                        "cls": "cq-btn-thumbs cq-cft-dataview-btn",
                        "iconCls":"cq-cft-dataview-mosaic",
                        "tooltip":{
                            "text":CQ.I18n.getMessage("Mosaic View"),
                            "autoHide":true
                        }
                    },{
                        "toggleGroup":"cfTab-S7Browse-TG",
                        "enableToggle":true,
                        "toggleHandler":function(button, pressed) {
                            var tab = CQ.Ext.getCmp("cfTab-S7Browse");
                            if (pressed) {
                                tab.dataView.tpl = new CQ.Ext.XTemplate(CQ.S7.DETAILS_TEMPLATE);
                                tab.dataView.itemSelector = CQ.wcm.ContentFinderTab.DETAILS_ITEMSELECTOR;
                            }
                            if (tab.dataView.store != null) {
                                tab.dataView.refresh();
                            }
                        },
                        "pressed":false,
                        "allowDepress":false,
                        "cls": "cq-btn-details cq-cft-dataview-btn",
                        "iconCls":"cq-cft-dataview-list",
                        "tooltip":{
                            "text":CQ.I18n.getMessage("List View"),
                            "autoHide":true
                        }
                    }
                ],
                "items": {"tpl": CQ.S7.THUMBS_TEMPLATE}
            },
            {
                "url": CQ.S7.getSelectedConfigPath() + ".search.json",
                "listeners" : {
                    "beforeload" : function(proxy, params) {
                        proxy.setUrl(CQ.S7.getSelectedConfigPath() + "/jcr:content.search.json", true);
                    }
                }
            },
            {
                "autoLoad":false,
                "baseParams": CQ.S7.getDefaultSearchQualifiers(),
                "reader": new CQ.Ext.data.JsonReader({
                    "totalProperty": "results",
                    "root": "hits",
                    "fields": [
                        "name", "thumb", "path", "type", "lastModified", "nanos", "imageWidth", "imageHeight", "templateParams", "ddGroups", "cqContent", "mimeType"
                    ],
                    "id": "name"
                })
            })
]
}
