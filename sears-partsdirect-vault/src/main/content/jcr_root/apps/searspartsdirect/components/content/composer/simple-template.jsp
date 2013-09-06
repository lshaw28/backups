<%@ page session="false" import="com.adobe.cq.social.commons.CollabUser,
	com.adobe.cq.social.commons.Comment,
	com.adobe.cq.social.commons.CommentSystem,
	com.day.cq.i18n.I18n,
	com.day.cq.wcm.api.WCMMode,
	com.day.cq.wcm.foundation.forms.FormsHelper,
	java.util.List,
	java.util.Locale,
	java.util.ResourceBundle,
	org.apache.sling.api.resource.ResourceUtil" %><%
%><%@taglib uri="http://www.day.com/taglibs/cq/personalization/1.0" prefix="personalization" %><%
%><%@include file="/libs/social/commons/commons.jsp"%><%
	final String targetResourceType = resourceResolver.resolve(resource.getPath()).getResourceType();
	CommentSystem cs = resource.adaptTo(CommentSystem.class);
	// @Todo - find out how to do this without a request attribute.
	//boolean isRenderByForm  = request.getAttribute("isRenderByForm")==null ? false : ((Boolean)request.getAttribute("isRenderByForm")).booleanValue();
	final Boolean allowFileUploads = cs.getProperty("allowFileUploads", Boolean.class) != null ?
	   cs.getProperty("allowFileUploads", Boolean.class) :false;

	Resource commentResource = resource;
	if (cs == null) {
		// form called directly
		final List<Resource> editResources = FormsHelper.getFormEditResources(slingRequest);
		if (null != editResources && editResources.size() > 0) {
			cs = editResources.get(0).adaptTo(CommentSystem.class);
			commentResource = (null != cs) ? cs.getResource() : commentResource;
		} else {
			cs = resource.adaptTo(CommentSystem.class);
		}
	}
	if (cs == null) {
		log.error("failed to retrieve comment system for " + resource.getPath());
		return;
	}

	String defaultMessage = cs.getProperty("defaultMessage", i18n.get("Type your comment here."));
	String id = cs.getId();

	if (request.getParameter("idPrefix") != null) {
		id = xssAPI.getValidJSToken(request.getParameter("idPrefix"), "XSS");
	}

	String composerCount = request.getParameter("composerCount");
	if (composerCount != null) {
		id += "_" + composerCount;
	}

	String formId = id + "-form";
	String textId = id +  "-text";
	String nameId = id + "-" + CollabUser.PROP_NAME;
	String mailId = id +"-" + CollabUser.PROP_EMAIL;
	String webId = id + "-" + CollabUser.PROP_WEBSITE;
	String formAction = commentResource.getPath() + ".social.createcomment.html";
	boolean requireLogin = false;
	String authorizableId = loggedInUserID;
	boolean anonymous = isAnonymous;
	String commentBlockClass = "comment-block-author";

	if(anonymous){
		commentBlockClass = "comment-block-publish";
	}

	if (allowFileUploads) { %><script type="text/javascript">
		  function cancelPostFileUpload() {
			  var formID = "#"+ "formFileUploadDiv";
			  var div = $CQ(formID).html();
			  $CQ(formID).html($CQ(formID).html());
		  }
		  </script><% } %>
<script type="text/javascript">
	var commentUserDisplayName = (NS('shc.pd').firstName + ' ' + NS('shc.pd').lastName.substring(0, 1)).trim();

	function validateSubmitWrapper<%= formId.replaceAll("[^a-zA-Z0-9]","") %>(id) {
		var validationResult = CQ_collab_comments_validateSubmit(id);
		if (validationResult && CQ_Analytics.Sitecatalyst) {
			CQ_Analytics.record({
				event: 'postComment',
				values: {
					commenterName: commentUserDisplayName
				},
				componentPath: '<%=resource.getResourceType()%>'
			});
		}
		return <% if (WCMMode.fromRequest(request).equals(WCMMode.DISABLED)) { %>validationResult<% } else { %>(CQ_Analytics.ClickstreamcloudUI.isVisible() ? false : validationResult)<% } %>;
	}
	function recordPostCommentEvent(name, user, component, category) {
		CQ_Analytics.record({
			event: 'postComment',
			values: {
				commenterName: commentUserDisplayName,
				topic: name,
				category: category
			},
			componentPath: component
		});
	}
</script>
<div class="row-fluid">
	<form class="comment" action="<%= formAction %>" onsubmit="return CQ.soco.comments.validateCommentForm(this)" method="POST" name="comment" enctype="multipart/form-data" id="<%= formId %>">
		<script type="text/javascript">
			CQ_collab_comments_defaultMessage = "<%= defaultMessage.replaceAll("\\\"", "\\\\\"") %>";
			CQ_collab_comments_requireLogin = <%= requireLogin %>;
			CQ_collab_comments_enterComment = "<%= i18n.get("Please enter a comment") %>";
			CQ_collab_comments_commentActivated = "<%= i18n.get("Comment activated") %>";
			CQ_collab_comments_unableToActivate = "<%= i18n.get("Unable to activate comment") %>";
		</script>
		<div class="comment-error" id="<%= id %>-error"></div>
		<label for="<%= textId %>" class="comment-text-label"><%= i18n.get("Comment") %></label>
		<textarea name="<%= Comment.PROP_MESSAGE %>" id="<%= textId %>" class="comment-text span12" onfocus="CQ.soco.commons.handleOnFocus(this, '<%= defaultMessage%>');" onblur="CQ.soco.commons.handleOnBlur(this, '<%= defaultMessage%>');" rows="10" cols="10"><%= defaultMessage %></textarea>
<% if (allowFileUploads) {
	String fileFilter = cs.getProperty(CommentSystem.PROP_FILE_UPLOAD_TYPES, String.class);
	fileFilter = StringUtils.isNotBlank(fileFilter) ? "accept='" + fileFilter + "'" :""; %>
		<div id="formFileUploadDiv">
			<input type="hidden" name="_charset_" value="<%= response.getCharacterEncoding() %>"/>
			<input class="submit" type="file" name="file" <%=fileFilter %> value="<%= i18n.get("Upload", "Upload a file") %>"/>
		</div>
<% } %>
		<div class="<%= commentBlockClass %>" id="<%= nameId %>-comment-block">
			<span class="comment-error" id="<%= nameId %>-displayName-error"></span>
			<label for="<%= nameId %>" class="comment-text-label"><%= i18n.get("Name", "Label for commenter's name") %></label>
			<input id="comments-userIdentifier" class="comment-text" type="text" name="userIdentifier" value="${firstNameLastInitial}"/>
		</div>
		<div class="<%= commentBlockClass %>" id="<%= mailId %>-comment-block">
			<span class="comment-error" id="<%= mailId %>-email-error"></span>
			<label for="<%= mailId %>" class="comment-text-label"><%= i18n.get("Email", "Label for commenter's email") %></label>
			<personalization:contextProfileHtmlInput id="<%= mailId %>" clazz="comment-text" type="text" name="<%= CollabUser.PROP_EMAIL %>" propertyName="email"/>
		</div>
		<div class="<%= commentBlockClass %>" id="<%= webId %>-comment-block">
			<span class="comment-error" id="<%= webId %>-url-error"></span>
			<label for="<%= webId %>" class="comment-text-label"><%= i18n.get("Website (optional)", "Label for commenter's website") %></label>
			<personalization:contextProfileHtmlInput id="<%= webId %>" clazz="comment-text" type="text" name="<%= CollabUser.PROP_WEBSITE %>" propertyName="url"/>
		</div>
		<div class="submit-block">
			<input type="hidden" name="_charset_" value="<%= response.getCharacterEncoding() %>"/>
			<input class="submit btn" type="submit" name="submit" id="<%= id %>-submit" value="<%= i18n.get("Post Comment", "Form submit action button") %>" onclick="recordPostCommentEvent('<%= commentResource.getPath() %>', (NS('shc.pd').firstName + ' ' + NS('shc.pd').lastName.substring(0, 1)).trim(), 'social/commons/components/composer', 'forum')" />
		</div>
	</form>
</div>