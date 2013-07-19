{
    "tabTip": CQ.I18n.getMessage("Products"),
    "id": "cfTab-Products",
    "iconCls": "cq-cft-tab-icon products",
    "xtype": "contentfindertab",
    "ranking": 27,
    "allowedPaths": [
        "/content/*"
    ],
    "items": [
    CQ.wcm.ContentFinderTab.getQueryBoxConfig({
        "id": "cfTab-Products-QueryBox",
        "items": [
            CQ.wcm.ContentFinderTab.getSuggestFieldConfig({"url": "/bin/wcm/contentfinder/suggestions.json/content/products"})
        ]
    }),
    CQ.wcm.ContentFinderTab.getResultsBoxConfig({
        "itemsDDGroups": [CQ.wcm.EditBase.DD_GROUP_PRODUCT],
        "items": {
            "tpl":
                '<tpl for=".">' +
                    '<div class="cq-cft-search-item" title="{pathEncoded}" ondblclick="CQ.wcm.ContentFinder.loadContentWindow(\'{[CQ.HTTP.encodePath(values.path)]}.html\');">' +
                    '<div class="cq-cft-search-thumb-top" style="background-image:url(\'{[CQ.shared.HTTP.getXhrHookedURL(CQ.HTTP.externalize(CQ.shared.XSS.getXSSValue(CQ.HTTP.encodePath(values.thumbnail))))]}\');"></div>' +
                    '<div class="cq-cft-search-text-wrapper">' +
                    '<div class="cq-cft-search-title">{values.title}</div>' +
                    '<div>{values.sku}</div>' +
                    '</div>' +
                    '<div class="cq-cft-search-separator"></div>' +
                    '</div>' +
                    '</tpl>',
            "itemSelector": CQ.wcm.ContentFinderTab.DETAILS_ITEMSELECTOR
        },
        "tbar": [
            CQ.wcm.ContentFinderTab.REFRESH_BUTTON
        ]
    },{
        "url": "/bin/wcm/contentfinder/product/view.json/etc/commerce/products"
    },{
        "reader": new CQ.Ext.data.JsonReader({
            "totalProperty": "results",
            "root": "hits",
            "fields": [
                "name", "path", "thumbnail", "title", "sku", "excerpt"
            ],
            "id": "path"
        }),
        "baseParams": {
        }
    })
]

}