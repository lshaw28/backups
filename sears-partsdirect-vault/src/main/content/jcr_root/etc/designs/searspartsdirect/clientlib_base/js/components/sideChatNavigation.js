

/*global window:true, $:true, Class:true, mainSitePath:true */
var PCChat = Class.extend(function() {
    "use strict";
    
    return {
        /**
        * model section
        * 
        * init: On page load events to fire
        */
        init : function() {          
            this.sideNavChat();
       },
        /**
        * toggle model section
        * 
        * @return {void}
        */
        sideNavChat : function() {
            $('#persistentChat_sidePanel #pC_call_id').toggle(function show() {
                $('#persistentChat_sidePanel').animate({
                    width: 400,
                    marginLeft: 1,
                    marginRight: 340,
                    display: 'toggle'
                }, 'slow');
            }, function hide() {
                $('#persistentChat_sidePanel').animate({
                    width: 400,
                    marginLeft: 0,
                    marginRight:0,
                    display: 'toggle'
                }, 'slow');
            });
            
        }
    }
}());

