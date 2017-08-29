$(function () {
    $(document.body).on('click', '.changeType', function () {
        $(this).closest('.phone-input').find('.type-text').text($(this).text());
        $(this).closest('.phone-input').find('.type-input').val($(this).data('type-value'));
    });

    $(document.body).on('click', '.btn-remove-phone', function () {
        $(this).closest('.phone-input').remove();
    });


    $('.btn-add-phone').click(function () {
        i = i + 1;
        $('.phone-list').append('' +
            '<div class="input-group phone-input">' +
            '<span class="input-group-btn">' +
            '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="type-text">Тип телефона</span> <span class="caret"></span></button>' +
            '<ul class="dropdown-menu" role="menu">' +
            '<li><a class="changeType" href="javascript:;" data-type-value="UNDEFINED">UNDEFINED</a></li>' +
            '<li><a class="changeType" href="javascript:;" data-type-value="WORK">WORK</a></li>' +
            '<li><a class="changeType" href="javascript:;" data-type-value="HOME">HOME</a></li>' +
            '<li><a class="changeType" href="javascript:;" data-type-value="MOBILE">MOBILE</a></li>' +
            '</ul>' +
            '</span>' +
            '<input type="text" ' +
            'name="telephones[' + i + '].telephone" ' +
            'path="telephones[' + i + '].telephone" ' +

            'class="form-control" placeholder="1-999-999-99-99"  pattern="[0-9\-]{5,15}"/>' +
            '<input type="hidden" ' +
            'name="telephones[' + i + '].type" ' +
            'path="telephones[' + i + '].type" ' +
            'class="type-input" value="" />' +
            '<span class="input-group-btn">' +
            '<button class="btn btn-danger btn-remove-phone" type="button"><span class="glyphicon glyphicon-remove"></span></button>' +
            '</span>' +
            '</div>'
        );

    });
});
