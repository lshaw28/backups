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

  Comment component

  Draws a comment.

--%><%@ page session="false" import="java.text.DateFormat,
					 com.adobe.cq.social.commons.Comment,
                     com.adobe.cq.social.commons.CommentSystem,
                     com.day.cq.wcm.api.WCMMode,
                     com.day.cq.commons.date.DateUtil" %><%
%><%@include file="/libs/social/commons/commons.jsp"%><%


    final Comment comment = resource.adaptTo(Comment.class);
    final CommentSystem cs = comment.getCommentSystem();
	final DateFormat defaultFormat = new SimpleDateFormat("h:m a 'on' MMM d, yyyy");
    
    String authorName = resourceAuthorName;
    DateFormat dateFormat = DateUtil.getDateFormat(cs.getProperty("dateFormat", String.class), defaultFormat, pageLocale);
    String date = dateFormat.format(comment.getDate());
    date = date.replace("AM","am").replace("PM","pm");
    String message = comment.getMessage();
    // #26898 - line breaks in comments
    // xss protection will remove line breaks before we can format them
    // for the output, so it is done here a a workaround:
    // first remove the CR, then replace the LF with <BR>
    message = message.replaceAll("\\r", "");
    message = message.replaceAll("\\n", "<br>");

    %>
    <div class="articleComments-item">
		<div class="item-header">
			<%--<div class="avatar">
				<img src="/content/dam/searspartsdirect/avatar-sample.jpg" alt="" />
			</div>--%>
			<a href="" class="name"><%= xssAPI.encodeForHTML(authorName) %></a><br />
			<%= xssAPI.encodeForHTML(date) %>
		</div>
		<div class="item-body">
			<%= xssAPI.filterHTML(message) %>
		</div>
	</div>
	
    <%
    if (!cs.isClosed()) {
        %><cq:include script="replies.jsp"/><%
    }

    if (WCMMode.fromRequest(request) == WCMMode.EDIT) {
        %><script type="text/javascript">
            CQ.WCM.onEditableReady("<%= comment.getPath() %>",
                function(editable) {
                    editable.refreshCommentSystem = function() {
                        <%-- todo: does't work as expected yet
                        var cs = CQ.WCM.getEditable("<%= cs.getResource().getPath() %>");
                        if (cs) {
                            console.log("cs", cs.path);
                            cs.refresh();
                        } else {
                        --%>
                            CQ.wcm.EditBase.refreshPage();
                        <%-- } --%>
                    };
                }
            );
        </script><%
    }
%>
