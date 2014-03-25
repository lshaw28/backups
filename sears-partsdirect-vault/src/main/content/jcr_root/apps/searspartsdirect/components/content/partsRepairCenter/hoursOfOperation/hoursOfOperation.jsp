<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div class="hoursOfOperationContainer">
    <h4 class="bizHeader">Hours of Operation</h4>
    <ul class="bizHours">
        <li><span>Mon </span><%=properties.get("mon", "Closed")%></li>
        <li><span>Tue </span><%=properties.get("tue", "Closed")%></li>
        <li><span>Wed </span><%=properties.get("wed", "Closed")%></li>
        <li><span>Thu </span><%=properties.get("thu", "Closed")%></li>
        <li><span>Fri </span><%=properties.get("fri", "Closed")%></li>
        <li><span>Sat </span><%=properties.get("sat", "Closed")%></li>
        <li><span>Sun </span><%=properties.get("sun", "Closed")%></li>
        <li class="optionalMsg"><%=properties.get("optionalmessage", "")%></li>
    </ul>
</div>