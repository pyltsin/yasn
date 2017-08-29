$(document).ready(function () {

    $('#searchAjax2').autocomplete({
        source: function (request, response) {
            $.getJSON("searchAjax?textFind=" + request.term,
                function (data) {
                    response($.map(data, function (v, key) {
                        return {
                            label: v.name,
                            value: v.name,
                            url: v.url
                        };
                    }));
                });
        },
        minLength: 2,
        delay: 100,
        select: function (event, ui) {
            document.location.href = ui.item.url;
        }
    });

});

