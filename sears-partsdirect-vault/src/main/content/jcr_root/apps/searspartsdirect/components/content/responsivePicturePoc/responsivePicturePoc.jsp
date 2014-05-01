<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div  class="hot-spottable margin-able">
<spd:respPicPoc />
    Responsive image scene7 url ${respImage}
<spd:getBlurb />
    <c:catch><cq:include script="layouts/layout_${layout}.jsp"/></c:catch>
</div>
