var numberPhone = 0;

function uploadXML() {
    var formData = new FormData();
    formData.append("fileXML", $("#fileXML")[0].files[0]);
    $.ajax({
        type: 'POST',
        url: 'importXML',
        data: formData,
        success: function () {
            loadAllForAccount("");
            console.log("Signup was successful");
        },
        error: function () {
            console.log("Signup was unsuccessful");
        },
        processData: false,  // Important!
        contentType: false,
        enctype: 'multipart/form-data',
        cache: false,
    });

    loadAllForAccount("");
}

function loadAllForChange() {
    login = "";
    out();
    loadIsFriends();
    loadChangeAccount();
}

function getHtmlPhone(data) {
    var txtStart =
        '            <div class="form-group">\n' +
        '                <label for="phone">Phone</label>\n' +
        '                <div class="phone-list" id="phone">\n'

    var txtEnd = '\n' +
        '                </div>\n';

    var txtIter = "";
    for (var i = 0; i < data.telephones.length; i++) {
        var obj = data.telephones[i];
        txtIter += '<div class="input-group phone-input" style="z-index: 0">' +
            '<span class="input-group-btn">' +
            '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"' +
            'aria-expanded="false"><span class="type-text">'

            + obj.typeRus

            + '</span> <span class="caret"></span></button>' +
            '<ul class="dropdown-menu" role="menu">' +
            '<li><a class="changeType" href="javascript:"' +
            'data-type-value="UNDEFINED">Не указан</a></li>' +
            '<li><a class="changeType" href="javascript:" data-type-value="WORK">WORK</a></li>' +
            '<li><a class="changeType" href="javascript:" data-type-value="HOME">HOME</a></li>' +
            '<li><a class="changeType" href="javascript:" data-type-value="MOBILE">MOBILE</a></li>' +
            '</ul>' +
            '</span><input type="hidden" name="telephones[' + i + '].type" path="telephones[' + i + '].type" ' +
            'class="type-input" value="' + obj.type + '"/>' +
            '<input name="telephones[' + i + '].telephone" path="telephones[' + i + '].telephone" class="form-control"' +
            'value="' + obj.telephone + '" pattern="[0-9\\\\-]{5,15}" placeholder="1-999-999-99-99"/>' +
            '<span class="input-group-btn">\n' +
            '<button class="btn btn-danger btn-remove-phone" type="button"><span\n' +
            'class="glyphicon glyphicon-remove"></span></button>\n' +
            '</span>\n' +
            '</div>'
    }

    return txtStart + txtIter + txtEnd;
}

function setChangeAccountData(data) {

    numberPhone = data.telephones.length;


    $("#wrap_info").empty();
    $("#wrap_info").append("<div id='info'></div>");

    var htmlPhone = getHtmlPhone(data);
    $("#info").html('    <div class="starter-template">\n' +
        '        <h1>Измените данные аккуанта</h1>\n' +
        '        <form modelAttribute="account" name="change" id="change" enctype="multipart/form-data" ' +
        '              method="post"\n' +
        '              enctype="multipart/form-data">\n' +
        '\n' +
        '            <div class="form-group">\n' +
        '                <label for="firstName">Имя</label>\n' +
        '                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Введите имя"\n' +
        '                       value="' + data.firstName + '" required>\n' +
        '            </div>\n' +
        '\n' +
        '            <div class="form-group">\n' +
        '                <label for="middleName">Отчество</label>\n' +
        '                <input type="text" class="form-control" id="middleName" name="middleName" placeholder="Введите отчество"\n' +
        '                       value="' + data.middleName + '" required>\n' +
        '            </div>\n' +
        '\n' +
        '            <div class="form-group">\n' +
        '                <label for="lastName">Фамилия</label>\n' +
        '                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Введите фамилию"\n' +
        '                       value="' + data.lastName + '" required>\n' +
        '            </div>\n' +
        '\n' +
        '            <div class="form-group">\n' +
        '                <label for="email">Email</label>\n' +
        '                <input type="email" class="form-control" id="email" name="email" placeholder="Введите email"\n' +
        '                       value="' + data.email + '" required>\n' +
        '            </div>\n' +
        '\n' +
        '            <div class="form-group">\n' +
        '                <label for="datepicker">Выберите дату рождения yyyy-DD-mm</label>\n' +
        '                <input type="text" class="form-control" id="datepicker" name="date"\n' +
        '            </div>\n' +
        '\n' +
        '            <div class="form-group">\n' +
        '                <label for="picture">Фотография</label>\n' +
        '                <input type="file" id="picture" name="foto" accept="image/jpeg">\n' +
        '            </div>\n' +
        '\n' +
        '\n' +
        htmlPhone +
        '\n' +
        '\n' +
        '                <button type="button" class="btn btn-success btn-sm btn-add-phone"><span\n' +
        '                        class="glyphicon glyphicon-plus"></span> Add Phone\n' +
        '                </button>\n' +
        '\n' +
        '            </div>\n' +
        '\n' +
        '            <button type="submit" class="btn btn-primary" id="signup">Изменить</button>\n' +
        '\n' +
        '        </form>\n' +
        '    </div>');

    $(function () {
        $("#datepicker").datepicker();
        $("#datepicker").datepicker("option", "dateFormat", "yy-mm-dd");
        $("#datepicker").datepicker("setDate", data.date);
    });
}

function setAllChangeAccountData(data) {
    setAccountData(data);
    setChangeAccountData(data);
}

function loadChangeAccount() {
    getData("accountRest?login=" + login, setAllChangeAccountData);
}
