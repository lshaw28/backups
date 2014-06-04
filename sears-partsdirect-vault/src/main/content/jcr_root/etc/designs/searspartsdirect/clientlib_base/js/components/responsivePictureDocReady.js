/**
 * Created by aclavin on 6/4/14.
 */
var resizeTimer = false;
var pictureElements = [];
(function (window) {
    $(document).ready(function () {

        /* Debounce resize event */
        function resizeCall(){
            $(document).trigger('debounceResize');
        }

        function debounceResize(){
            if(window.resizeTimer) clearTimeout(window.resizeTimer);
            window.resizeTimer = setTimeout(resizeCall,150);
        }
        window.onresize = debounceResize;

        /* Find and map all "picture" elements */
        $('.responsivePictureInner').each(function(index, ele){
            window.pictureElements[index] = new ResponsivePicture( $(ele) );
            window.pictureElements[index]._checkSources($(window).width());
        });
    });
}(window));