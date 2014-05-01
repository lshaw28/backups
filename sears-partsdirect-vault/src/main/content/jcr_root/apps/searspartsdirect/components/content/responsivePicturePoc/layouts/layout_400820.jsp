<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:respPicPoc />
    <div class="responsivePicture">
        <span data-min="0" data-max="400" data-src="${respImage}?wid=350"></span>
        <span data-min="401" data-max="820" data-src="${respImage}?wid=550" data-default="true"></span>
        <span data-min="821" data-max="13600" data-src="${respImage}?wid=800"></span>
    </div>