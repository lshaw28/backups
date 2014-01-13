<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div id="estimatedDeliveryModal" class="modal hide fade" role="dialog" aria-labelledby="afEstimatedDeliveryModalLabel" aria-hidden="true">
    <div class="contentModal">
        <h1>In stock: <span class="bold">Shipping options</span></h1>
        <p>If you place your order today, your part(s) will arrive by:</p>
        <ul>
            <li><span id="standardLabel" class="bold">Standard: </span> <span id="standardDelivery">Monday, January 13, 2014</span></li>
            <li><span id="expeditedLabel" class="bold">Expedited: </span> <span id="expeditedDelivery">Friday, January 10, 2014</span></li>
            <li><span id="priorityLabel" class="bold">Priority: </span> <span id="priorityDelivery">Wednesday, January 8, 2014</span></li>
        </ul>
        <p class="bold">You'll be able to choose a shipping method during checkout.</p>
        <div class="pull-right">
            <button type="button" class="new-btn" data-dismiss="modal" data-cancel="true"aria-hidden="true">Close</button>
        </div>
    </div>
</div>