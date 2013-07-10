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
    CQ.soco.topic = CQ.soco.topic || {};
    CQ.soco.topic.attachToCommentComposer = function(targetForm, appendTarget, stats, resourcePath) {
        var success = function(data, status, jqxhr) {
                CQ.soco.topic.numPosts += 1;
                if (CQ.soco.topic.numPosts === 1) {
                    stats.text(CQ.I18n.getMessage("{0} Reply", CQ.soco.topic.numPosts));
                } else {
                    stats.text(CQ.I18n.getMessage("{0} Replies", CQ.soco.topic.numPosts));
                }
                var newLineItem = $CQ("<li></li>");
                CQ.soco.filterHTMLFragment(data, function(node) {
                    newLineItem.append(node);
                    appendTarget.append(newLineItem);
                });
            };
        var failure = function(jqXHR, textStatus) {
                if(jqXHR.status === 302) {
                    throw new Error("Expecting client render response, recieved redirect");
                }
                throw new Error("Unknown error when creating a comment.");
            };
        CQ.soco.commons.clientSideComposer(targetForm, "listitem-template", success, failure);
    };
})(CQ, $CQ);
