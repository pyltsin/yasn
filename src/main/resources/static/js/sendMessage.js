function fillText(items) {
    // console.log("items: " + items);

    var text = "";
    for (var i = 0; i < items.length; i++) {
        var findItems = items[i];
        text += findItems.from + " " +
            findItems.localDateTime + ": " + findItems.message + "\n";
    }
    // $("#sends").innerHTML=text;
    // $("#sends").html(text);
    $("#sendsArea").val(text);
}

function loadMessage() {
    var loginTo = $("#loginTo").val();
    console.log(loginTo);

    $.getJSON("getMessages", {"loginTo": loginTo},
        function (json) {
            // console.log("JSON Data: " + json);
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