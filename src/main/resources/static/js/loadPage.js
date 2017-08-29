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
    $("#account_picture").attr("src", data.picture);

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

    $("#account_addFriends").click(function () {
        getData("friendsRest/addFriends?login=" + login);
        loadIsFriends();
    });
    $("#account_deleteFriends").click(function () {
        getData("friendsRest/deleteFriends?login=" + login);
        loadIsFriends();
    });
}

function loadIsFriends() {
    getData("isFriendsRest?login=" + login, setIsFriends);
}

function loadAllForAccount(param) {
    login = param;
    loadAccount();
    loadInfoAccount();
    loadIsFriends();
}

function setFriends(data) {
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
        data: data
    });
}

function loadAllFriends() {
    getData("friends", setFriends)
}

function loadAllForFriends() {
    loadAllForAccount("");
    loadAllFriends();
    loadIsFriends();
}

function formatLogin(value) {
    var out = "<a href='#' onclick='loadAllForAccount(\"" + value + "\"" + ")'>" + value + "</a>";
    return out;
}

$(document).ready(function () {
    loadAllForAccount(login);
});