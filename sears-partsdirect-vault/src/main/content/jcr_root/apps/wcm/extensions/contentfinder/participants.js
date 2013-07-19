{
    "tabTip": CQ.I18n.getMessage("Participants"),
    "id": "cfTab-Participants",
    "iconCls": "cq-cft-tab-icon participants",
    "xtype": "contentfindertab",
    "ranking": 60,
    "allowedPaths": [
        "/etc/workflow/models/*"
    ],
    "items": [
        CQ.wcm.ContentFinderTab.getQueryBoxConfig({
            "id": "cfTab-Participants-QueryBox",
            "items": [
                CQ.wcm.ContentFinderTab.getSuggestFieldConfig({"url": "/bin/wcm/contentfinder/suggestions.json/home"})
            ]
        }),
        CQ.wcm.ContentFinderTab.getResultsBoxConfig(
            {
                "twoParametersPagination": true,
                "itemsDDGroups": ["participant"],
                "itemsDDNewParagraph": {
                    "path": "cq/workflow/components/model/participant",
                    "propertyName": "./metaData/PARTICIPANT"
                },
                "disableContinuousLoading": false,
                "items": {
                    "cls": "cq-cft-dataview cq-cft-participants-view",
                    "tpl": new CQ.Ext.XTemplate(
                        '<tpl for=".">'
                            + '<div class="cq-cft-participant-item cq-cft-participants-ddproxy-wrapper">'
                                + '<div class="cq-cft-participant-thumb"><img src="{[CQ.HTTP.externalize(values[\"image\"])]}"></div>'
                                + '<div ext:qtip="{[CQ.shared.Util.htmlEncode(values[\"name\"])]}" class="cq-cft-participant-title">{name}</div>'
                                + '<div ext:qtip="{email}" class="cq-cft-participant-description">{email}</div>'
                                + '<div class="cq-cft-participant-separator"></div>'
                            + '</div>'
                        + '</tpl>'),
                    "itemSelector":  ".cq-cft-participant-item"
                }
            },
            {
                "url": "/libs/cq/workflow/authorizables.json"
            },
            {
                "baseParams": {
                    "start": 0,
                    "limit": 20
                },
                "autoLoad":false,
                "reader": new CQ.Ext.data.JsonReader({
                    "totalProperty": "results",
                    "root": "authorizables",
                    "fields": [
                        "path", "id", "name", "image", "email"
                    ],
                    "id": "home"
                })
            }
        )
    ]
}
