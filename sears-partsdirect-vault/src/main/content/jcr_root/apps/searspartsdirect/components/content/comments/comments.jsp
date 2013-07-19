

<%--

 ADOBE CONFIDENTIAL
 __________________

  Copyright 2012 Adobe Systems Incorporated
  All Rights Reserved.

 NOTICE:  All information contained herein is, and remains
 the property of Adobe Systems Incorporated and its suppliers,
 if any.  The intellectual and technical concepts contained
 herein are proprietary to Adobe Systems Incorporated and its
 suppliers and are protected by trade secret or copyright law.
 Dissemination of this information or reproduction of this material
 is strictly forbidden unless prior written permission is obtained
 from Adobe Systems Incorporated.

  ==============================================================================

  Default comments component

  Includes all child resources (comments).

--%>

<%@ page session="false" import="java.util.List,
                     com.adobe.cq.social.commons.Comment,
                     com.adobe.cq.social.commons.CommentSystem,
                     com.adobe.cq.social.commons.CollabUtil,
                     com.day.cq.rewriter.linkchecker.LinkCheckerSettings,
                     com.day.cq.wcm.api.WCMMode,
                     com.day.cq.wcm.api.components.IncludeOptions,
                     com.day.cq.wcm.foundation.forms.FormsHelper,
                     com.day.cq.i18n.I18n,
                     java.util.Locale,
                     java.util.Iterator,
                     java.util.ResourceBundle,
                     org.apache.sling.api.resource.ResourceUtil,
                     org.apache.commons.lang3.StringEscapeUtils" %><%
%><%@taglib uri="http://www.day.com/taglibs/cq/personalization/1.0" prefix="personalization" %>
<%@include file="/libs/social/commons/commons.jsp"%>
	<%

    final List<Resource> editResources = FormsHelper.getFormEditResources(slingRequest);
    final Resource editComment = (null != editResources && editResources.size() > 0) ? editResources.get(0) : null;
    Resource childComment = null;
    boolean isRenderByForm = false;
    if(editComment!=null && !ResourceUtil.isA(editComment, Comment.RESOURCE_TYPE)){
        //search for the first comments from child list
        childComment = resource.getResourceResolver().getResource(editComment, "comments") ;
        isRenderByForm = true;
    }
    final Resource csResource = (null != childComment ) ? childComment : resource;
    CommentSystem cs = csResource.adaptTo(CommentSystem.class);
    final Resource commentResource = resourceResolver.getResource(cs.getPath());

    if (cs == null) {
        log.error("failed to retrieve comment system for " + csResource.getPath());
        return;
    }

    final ValueMap values = cs.getResource().adaptTo(ValueMap.class);
    final Boolean isRTEenabled = (values != null) ? values.get("rteEnabled", false) : false;

    if(isRenderByForm ){
        cs.setPath(CollabUtil.getPagePath(editComment));
        request.setAttribute("isRenderByForm", isRenderByForm);
        request.setAttribute("commentsRoot", childComment.getPath());
    }

    request.setAttribute("cs", cs);

    String formattedName = "";
    String authorizableId = resourceAuthorID;
    formattedName = resourceAuthorName;
    if (StringUtils.isBlank(formattedName)) {
        formattedName = authorizableId;
    }

    String signedInText = cs.getProperty("signedInText", i18n.get("You are signed in as:"));

    if (WCMMode.fromRequest(request) == WCMMode.EDIT) {
          %><cq:includeClientLib categories="cq.social.author"/><%
    }
%><div id="<%= cs.getId() %>">
<div class="articleComments-wrapper span9">
	<div class="articleComments-loader">
	    <h2>3 Article Comments</h2>
	    <button type="button" class="new-btn" data-path="<%=currentPage.getPath()%>">Load Comments</button>
	</div>
	<div class="comments-target"></div>

<div class="articleComments-form" >

	<c:choose>
		<c:when test="${not empty firstName}">
		<h2>Got Something to Say?</h2>
		    <div class="comment-signed-in-text" id="<%= cs.getId() %>-signed-in-text"><%= signedInText %><span class="comment-signed-in-user" id="<%= cs.getId() %>-signed-in-user"><%= StringEscapeUtils.escapeHtml4(formattedName) %></span></div>
		    <%
		    if (!cs.isClosed() && CollabUtil.canAddNode(resourceResolver.adaptTo(Session.class), cs.getRootPath())) {
		    %><sling:include resourceType="searspartsdirect/components/content/composer" replaceSelectors="simple-template"/><%
		    }
		    if(!CollabUtil.canAddNode(resourceResolver.adaptTo(Session.class), cs.getRootPath())) {
		        %><p><%=i18n.get("You are not allowed to post here, please sign in or join")%></p><%
		    }%>
	    </c:when>
	    <c:otherwise>
			<h2>Got Something to Say?</h2>
			<p>
				<a data-toggle="modal" data-target="#loginModal">Sign in with your Sears ID</a>
			</p>
		</c:otherwise>
	</c:choose>
</div>

</div>
</div>
<script>
$CQ(function(){
    var refreshCommentCount = function (target, count) {
        if (count === 1) {
            target.text(CQ.I18n.getMessage("{0} comment", count));
        } else if (count === 0) {
            target.text(CQ.I18n.getMessage("No comments yet", count));
        } else {
            target.text(CQ.I18n.getMessage("{0} comments", count));
        }
    };

    var commentCount = <%=cs.countComments(WCMMode.fromRequest(request))%>;
    <%if( isRTEenabled) {%>
    CQ.soco.commons.activateRTE($CQ("#<%=cs.getId()%>").find("form").first());
    <%}%>
    CQ.soco.comments.attachToComposer($CQ("#<%=cs.getId()%>").find("form").first(), $CQ(".comments-target"), "comment");
    $CQ("#<%=cs.getId()%>").bind(CQ.soco.comments.events.ADDED, function(event){
            CQ_Analytics.record({event: 'postComment',
                    values: {commenterName: '<%=loggedInUserID%>',
                            componentPath: '<%=resource.getResourceType()%>'
                            }
            });
            commentCount += 1;
            refreshCommentCount($CQ("#<%=cs.getId()%>-count"), commentCount);
    });
    $CQ("#<%=cs.getId()%>").bind(CQ.soco.comments.events.DELETE, function(event) {
        $CQ.post($CQ(event.target).closest("form").attr("action"), function(data, textStatus, jqXHR) {
            var parentComment = $CQ(event.target).closest(".comment").parent();
            var numReplies = +(parentComment.data("numreplies") || 0);
            parentComment.data("numreplies", (numReplies - 1));
            commentCount -= 1;
            refreshCommentCount($CQ("#<%=cs.getId()%>-count"), commentCount);
            $CQ(event.target).closest(".comment").remove();
        });
       });

    CQ.soco.comments.bindOnAdded($CQ("#<%=cs.getId()%>"));
    $CQ(".comment").bind(CQ.soco.comments.events.DELETE, CQ.soco.comments.removeHandler);
    $CQ(".comment").bind(CQ.soco.comments.events.ADDED, CQ.soco.comments.addHandler);
});
</script>

<%@ include file="/apps/searspartsdirect/global.jsp" %>
