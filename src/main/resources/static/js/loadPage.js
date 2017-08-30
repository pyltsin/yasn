var sendInterval;

function getData(s, f) {
    $.getJSON(s,
        function (data) {
            if (f !== null) {
                f(data);
            }
            console.log(data)
        });
}

function setAccountData(data) {
    $("#account_firstName").html(data.firstName);
    $("#account_middleName").html(data.middleName);
    $("#account_lastName").html(data.lastName);
    $("#account_birthday").html(data.date);
    $("#account_email").html(data.email);
    $("#account_picture").attr("src", data.picture + "#" + new Date().getTime());

    $("#account_phones").empty();

    for (var i = 0; i < data.telephones.length; i++) {
        var findItems = data.telephones[i];

        $("#account_phones").append('<li class="list-group-item">' +
            'Тип: ' + findItems.typeRus + 'Телефон: ' + findItems.telephone +
            '</li>'
        );
    }
}

function loadAccount() {
    getData("accountRest?login=" + login, setAccountData);
}

function setInfoAccount(data) {
    $("#wrap_info").empty();
    $("#wrap_info").append("<div id='info'></div>");
    $("#info").html(data.message);
}

function loadInfoAccount(param) {
    getData("accountInfoRest?login=" + login, setInfoAccount);
}

function setIsFriends(data) {
    if (data == 0) {
        $("#account_addFriends").css("visibility", "hidden");
        $("#account_deleteFriends").css("visibility", "hidden");
    } else if (data == 1) {
        $("#account_addFriends").css("visibility", "hidden");
        $("#account_deleteFriends").css("visibility", "visible ");
    } else if (data == -1) {
        $("#account_addFriends").css("visibility", "visible");
        $("#account_deleteFriends").css("visibility", "hidden ");
    }
}

function functAddFriends() {
    getData("friendsRest/addFriends?login=" + login, setIsFriends);
}

function functDeleteFriends() {
    getData("friendsRest/deleteFriends?login=" + login, setIsFriends);
}

function loadIsFriends() {
    getData("isFriendsRest?login=" + login, setIsFriends);
}


function setFriends(data) {
    if (data !== null && data.length != 0) {
        $("#wrap_info").empty();
        $("#wrap_info").append("<div id='info'></div>");
        $("#info").bootstrapTable({
            columns: [{
                field: 'id',
                title: 'id'
            }, {
                field: 'login',
                title: 'login',
                formatter: formatLogin
            }, {
                field: 'lastName',
                title: 'Last Name'
            }, {
                field: 'firstName',
                title: 'First Name'
            }, {
                field: 'email',
                title: 'email'
            }, {
                field: 'date',
                title: 'Birthday'
            }],
            data: data,
            pagination: true,
            pageSize: 4,
            locale: "ru-RU"
        });

    } else {
        $("#wrap_info").empty();
        $("#wrap_info").append("<div id='info'>У вас нет друзей. Все плохо</div>");
    }
}

function out() {
    clearInterval(sendInterval);
}


function loadFriends(f) {
    getData("friends", f)
}

function loadAllForFriends() {
    out();
    loadAllForAccount("");
    loadFriends(setFriends);
    loadIsFriends();
}

function loadAllForAccount(param) {
    out();
    login = param;
    loadAccount();
    loadInfoAccount();
    loadIsFriends();
}

function loadAllForSends() {
    out();
    $("#wrap_info").empty();
    $("#wrap_info").append("<div id='info'>" +
        "            <h1>Сообщения:</h1>\n" +
        "            Кому:\n" +
        "            <select onchange=\"loadMessage();\" id=\"loginTo\">\n" +
        "            </select>\n" +
        "            <p><textarea name=\"sendsArea\" id=\"sendsArea\" style=\"width: 350px; height: 350px\"></textarea></p>\n" +
        "            <p><input maxlength=\"25\" size=\"40\" id=\"sendMessage\"></p>\n" +
        "            <p>\n" +
        "                <button type=\"button\" id=\"buttonSend\" onclick=\"sendMessage()\" class=\"btn btn-lg btn-primary\">Отправить\n" +
        "                </button>\n" +
        "            </p>\n" +
        "</div>");

    loadFriends(function (data) {
        for (var i = 0; i < data.length; i++) {
            var obj = data[i];
            $("#loginTo").append("<option>" + obj.login + "</option>")
        }
    });
    sendInterval = setInterval(loadMessage, 5000);
}


function formatLogin(value) {
    var out = "<a href='#' onclick='loadAllForAccount(\"" + value + "\"" + ")'>" + value + "</a>";
    return out;
}

function loadFind(find) {
    getData("searchAll?textFind=" + find, setFinds);
}

function setFinds(data) {

    $("#wrap_info").empty();
    $("#wrap_info").append("<div id='info'></div>");
    $("#info").bootstrapTable({
        columns: [{
            field: 'id',
            title: 'id',
        }, {
            field: 'login',
            title: 'login',
            formatter: formatLogin,
        }, {
            field: 'lastName',
            title: 'Last Name',
        }, {
            field: 'firstName',
            title: 'First Name',

        }, {
            field: 'email',
            title: 'email',

        }, {
            field: 'date',
            title: 'Birthday',
        }],
        data: data,
        pagination: true,
        pageSize: 4,
        locale: 'ru-RU',
    });

}

function loadAllForFind() {
    out();
    loadAllForAccount("");
    var find = $("#searchAjax2").val();
    $("#searchAjax2").val("");
    loadFind(find);
}

$(document).ready(function () {
    loadAllForAccount(login);
});