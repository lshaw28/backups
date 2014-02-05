var modelSearchResults = function (el) {

    var modelSearchResultsView = new Thorax.View({
        greeting: "Hello",
        template: Handlebars.compile('{{greeting}} World!')
    });

    modelSearchResultsView.appendTo('.modelSearchResultsMain');

}

