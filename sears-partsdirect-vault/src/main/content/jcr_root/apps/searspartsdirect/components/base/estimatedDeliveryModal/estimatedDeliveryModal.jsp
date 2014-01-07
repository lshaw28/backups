<%@ include file="/apps/searspartsdirect/global.jsp"%><%
%><div id="estimatedDeliveryModal" class="modal hide fade" role="dialog" aria-labelledby="afEstimatedDeliveryModalLabel" aria-hidden="true">
    <h1>In stock: <strong>Shipping options</strong></h1>
    <p>If you place your order today, your part(s) will arrive by:</p>
    <table>
        <tbody>
            <tr>
                <td><strong>Shipping method</strong></td>
                <td><strong>Estimated arrival date</strong></td>
            </tr>
            <tr>
                <td>Standard</td>
                <td><span id="standardDelivery">Monday, January 13, 2014</span></td>
            </tr>
            <tr>
                <td>Expedited</td>
                <td><span id="expeditedDelivery">Friday, January 10, 2014</span></td>
            </tr>
            <tr>
                <td>Priority</td>
                <td><span id="priorityDelivery">Wednesday, January 8, 2014</span></td>
            </tr>
        </tbody>
    </table>

          <p><strong>You'll be able to choose a shipping method during checkout.</strong></p>
    <div class="pull-right">
        <button type="button" class="new-btn" data-dismiss="modal" data-cancel="true"aria-hidden="true">Close</button>
    </div>
</div>