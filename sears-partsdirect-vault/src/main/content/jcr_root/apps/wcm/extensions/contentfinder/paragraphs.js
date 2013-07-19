{
    "tabTip": CQ.I18n.getMessage("Paragraphs"),
    "id": "cfTab-Paragraphs",
    "iconCls": "cq-cft-tab-icon paragraphs",
    "xtype": "contentfindertab",
    "ranking": 40,
    "allowedPaths": [
        "/content/*",
        "/etc/spdAssets/*",
        "/etc/scaffolding/*"
    ],
    "items": [
        CQ.wcm.ContentFinderTab.getQueryBoxConfig({
            "id": "cfTab-Paragraphs-QueryBox",
            "items": [
                CQ.wcm.ContentFinderTab.getSuggestFieldConfig({"url": "/bin/wcm/contentfinder/suggestions.json/content"})
            ]
        }),
        CQ.wcm.ContentFinderTab.getResultsBoxConfig({
            "itemsDDGroups": [CQ.wcm.EditBase.DD_GROUP_PARAGRAPH],
            "itemsDDNewParagraph": {
                "path": "foundation/components/reference",
                "propertyName": "./path"
            },
            "disableContinuousLoading": true,
            "items": {
                "cls": "cq-cft-dataview cq-cft-paragraphview",
                "tpl":
                    '<tpl for=".">' +
                        '<div class="cq-cft-paragraph">' +
                            '<div class="cq-cft-paragraph-ddproxy-wrapper">{excerpt}</div>' +
                            '<div class="cq-cft-paragraph-separator"></div>' +
                        '</div>' +
                    '</tpl>',
                "itemSelector":  ".cq-cft-paragraph"
            },
            "tbar": [
                CQ.wcm.ContentFinderTab.REFRESH_BUTTON
            ]
        },{
            "url": "/bin/wcm/foundation/paragraphlist.json"
        }, {
            "baseParams": {
                "type": "cq:Page"
            }
        })
    ]
}
