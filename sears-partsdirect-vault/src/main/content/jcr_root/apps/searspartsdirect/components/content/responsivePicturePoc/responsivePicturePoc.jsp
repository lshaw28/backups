<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div  class="hot-spottable margin-able">
<spd:respPicPoc />
    Responsive image scene7 url ${respImage}
    <div class="responsivePicture">
        <span data-min="0" data-max="300" data-src="${respImage}?wid=100"></span>
        <span data-min="301" data-max="500" data-src="${respImage}?wid=300" data-default="true"></span>
        <span data-min="501" data-max="1360" data-src="${respImage}?wid=600"></span>
    </div>
<spd:getBlurb/>
    <c:catch><cq:include script="layouts/layout_${layout}.jsp"/></c:catch>
</div>
