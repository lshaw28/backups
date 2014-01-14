<script type="text/javascript" src="/etc/designs/searspartsdirect/clientlib_base/js/modelSearchResults.js" ></script>


<%
    String imageUrl = (request.getParameter("imageUrl") != null) ? request.getParameter("imageUrl") : "";
    %>
     <script type="text/javascript" charset="utf-8">
        window.print();
        $('document').ready(function() {
            if(navigator.userAgent.toLowerCase().indexOf('msie 6') != -1) {
                $('#diagram').height('800px');
                $('#diagram').width('600px');
            }
            preparePrint();
            //window.close();
        });
    </script>
	<style type="text/css">
		img{
			height: 100% !important;
			width: 100% !important;
		}
		@media print {
			div.controlBar { display:none; }
			div#diagramBox { overflow:visible; border:0 none;}
			div#diagramBox img { height:50% !important; width:50% !important; }
		}
	</style>	 
	
	
<div>
	<img id="diagram" class="fullSize" src="<%= imageUrl%>"/>		
</div>
