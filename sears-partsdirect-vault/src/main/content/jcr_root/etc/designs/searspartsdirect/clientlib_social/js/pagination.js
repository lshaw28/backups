/*
 *
 * ADOBE CONFIDENTIAL
 * __________________
 *
 *  Copyright 2012 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 */
(function(CQ, $CQ) {
    "use strict";
    CQ.soco = CQ.soco || {};
    CQ.soco.commons = CQ.soco.commons || {};

    CQ.soco.commons.attachToPagination = function(paginationUITarget, pageTarget, from, limit, resourcePath) {
        var currentFrom = from;
        var pageFunction = function(offset) {
                $CQ.ajax(resourcePath + ".pagination.html?from=" + (currentFrom + offset), {
                    success: function(data, status, jqxhr) {
                        currentFrom += offset;
                        CQ.soco.filterHTMLFragment(data, function(node) {
                            pageTarget.html(node);
                        });
                    },
                    type: "GET",
                    headers: {
                        "Accept": "text/html"
                    }
                });
            };
        paginationUITarget.find(".nextPage").on("click", function(event) {
            event.preventDefault();
            pageFunction(limit);

        });
        paginationUITarget.find(".prevPage").on("click", function(event) {
            event.preventDefault();
            pageFunction(-1 * limit);

        });
    };
    CQ.soco.commons.configurePagination = function(paginationTarget, currentPageNum, limit, numPages) {
        var currentPage = currentPageNum,
            urlPrefix = CQ.shared.HTTP.getPath() + CQ.shared.HTTP.EXTENSION_HTML + "?from=",
            render = function() {
                paginationTarget.find(".message").text(CQ.I18n.getMessage("Page {0} of {1}", [currentPage, numPages]));
                paginationTarget.find(".nextPage").attr('href', urlPrefix + ((currentPage) * limit));
                paginationTarget.find(".prevPage").attr('href', urlPrefix + ((currentPage - 2) * limit));

                if (currentPage >= numPages) {
                    paginationTarget.find(".nextPage").hide();
                } else {
                    paginationTarget.find(".nextPage").show();
                }
                if (currentPage <= 1) {
                    paginationTarget.find(".prevPage").hide();
                } else {
                    paginationTarget.find(".prevPage").show();
                }
            };
        paginationTarget.find(".nextPage").on("click", function(event) {
            event.preventDefault();
            currentPage += 1;
            render();
        });
        paginationTarget.find(".prevPage").on("click", function(event) {
            event.preventDefault();
            currentPage -= 1;
            render();

        });
    };
})(CQ, $CQ);
