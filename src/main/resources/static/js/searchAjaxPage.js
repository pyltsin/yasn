/**
 * Created by Pyltsin on 26.03.2017.
 */


function fillFind(data) {
    var items = data;

    var iAccount = 0;
    var iGroup = 0;

    $("#findRow").empty();

    function addFindItem(findItems, s) {
        $(s).append(
            '<li class="list-group-item"> ' +
            '' + path + '<a href="/">' + findItems.url + '' + findItems.name + '</a>  ' +
            '</li> '
        );
    }

    for (var i = 0; i < items.length; i++) {
        var findItems = items[i];


        iAccount += 1;
        if (iAccount <= len) {
            addFindItem(findItems, "#findRow");
        }
    }


    if (by == 0) {
        $("#buttonBack").hide();
    } else {
        $("#buttonBack").show();
    }

    if (iAccount > len || iGroup > len) {
        $("#buttonForward").show();
    } else {
        $("#buttonForward").hide();
    }

    $("#tabs").tabs();

}


function loadAjax(textSearch, len, by) {

    $.getJSON("searchAjax?textFind=" + textSearch + "&len=" + (len + 1) + "&start=" + by,
        function (data) {
            fillFind(data);
            // items = data;
            console.log(data)
        });
}

function backSearch() {
    if (by - len < 0) {
        by = 0;
    } else {
        by -= len;
    }
    loadAjax(textSearch, len, by);
}


function forwardSearch() {
    by += len;
    loadAjax(textSearch, len, by);
}


$(document).ready(function () {
    loadAjax(textSearch, len, by);
});


