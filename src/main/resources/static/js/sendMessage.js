function fillText(items) {

    var text = "";
    for (var i = 0; i < items.length; i++) {
        var findItems = items[i];
        text += findItems.from + " " +
            findItems.localDateTime + ": " + findItems.message + "\n";
    }
    $("#sendsArea").val(text);
}

function loadMessage() {
    var loginTo = $("#loginTo").val();
    console.log(loginTo);

    $.getJSON("getMessages", {"loginTo": loginTo},
        function (json) {
            fillText(json);
        }
    );
}

function sendMessage() {
    var loginTo = $("#loginTo").val();
    // console.log(loginTo);
    var message = $("#sendMessage").val();
    // console.log(message);
    $.post("sendMessage", {"loginTo": loginTo, "message": message});
    $("#sendMessage").val("");
    loadMessage();
}