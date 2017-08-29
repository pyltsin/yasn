<%--
  Created by IntelliJ IDEA.
  User: Pyltsin
  Date: 07.01.2017
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Account</title>
    <jsp:include page="/WEB-INF/jsp/getCss.jsp"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>
<body>
<jsp:include page="/WEB-INF/jsp/nav2.jsp"/>

<div class="container">


    <div class="starter-template">
        <h1>Измените данные аккуанта</h1>
        <form modelAttribute="account" name="change" action="${pageContext.request.contextPath}/ChangeServlet"
              method="post"
              enctype="multipart/form-data">

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password" value=""
                       path="password" name="password"/>
            </div>

            <div class="form-group">
                <label for="firstName">Имя</label>
                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Введите имя"
                       value="${sessionScope.ACCOUNT.firstName}" required>
            </div>

            <div class="form-group">
                <label for="middleName">Отчество</label>
                <input type="text" class="form-control" id="middleName" name="middleName" placeholder="Введите отчество"
                       value="${sessionScope.ACCOUNT.middleName}" required>
            </div>

            <div class="form-group">
                <label for="lastName">Фамилия</label>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Введите фамилию"
                       value="${sessionScope.ACCOUNT.lastName}" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Введите email"
                       value="${sessionScope.ACCOUNT.email}" required>
            </div>

            <div class="form-group">
                <label for="datepicker">Выберите дату рождения yyyy-DD-mm</label>
                <input type="text" class="form-control" id="datepicker" name="date"
                <%--value="${sessionScope.ACCOUNT.date}"--%>
                >
            </div>

            <div class="form-group">
                <label for="picture">Фотография</label>
                <input type="file" id="picture" name="foto" accept="image/jpeg">
            </div>


            <div class="form-group">
                <label for="phone">Phone</label>
                <div class="phone-list" id="phone">
                    <c:set var="i" scope="page" value="${0}"/>
                    <c:forEach var="phone" items="${sessionScope.ACCOUNT.telephones}">
                        <c:set var="i" scope="page" value="${i+1}"/>

                        <div class="input-group phone-input" style="z-index: 0">
						<span class="input-group-btn">
							<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                    aria-expanded="false"><span class="type-text">${phone.typeRus}</span> <span
                                    class="caret"></span></button>
							<ul class="dropdown-menu" role="menu">
								<li><a class="changeType" href="javascript:"
                                       data-type-value="UNDEFINED">Не указан</a></li>
								<li><a class="changeType" href="javascript:" data-type-value="WORK">WORK</a></li>
								<li><a class="changeType" href="javascript:" data-type-value="HOME">HOME</a></li>
								<li><a class="changeType" href="javascript:" data-type-value="MOBILE">MOBILE</a></li>
							</ul>
						</span>
                            <input type="hidden"
                                   name="<c:out value="telephones[${i}].type"/>"
                                   path="<c:out value="telephones[${i}].type"/>"
                                   class="type-input" value="${phone.type}"/>
                            <input
                                    name="<c:out value="telephones[${i}].telephone"/>"
                                    path="<c:out value="telephones[${i}].telephone"/>"
                                    class="form-control"
                                    value="${phone.telephone}" pattern="[0-9\-]{5,15}" placeholder="1-999-999-99-99"/>
                            <span class="input-group-btn">
                        <button class="btn btn-danger btn-remove-phone" type="button"><span
                                class="glyphicon glyphicon-remove"></span></button>
                        </span>
                        </div>
                    </c:forEach>

                </div>


                <button type="button" class="btn btn-success btn-sm btn-add-phone"><span
                        class="glyphicon glyphicon-plus"></span> Add Phone
                </button>

            </div>

            <%--<p><b>Телефон 1:</b>--%>
            <%--<input type="tel" name="phone1" size="40" value="${sessionScope.ACCOUNT.phone1}">--%>
            <%--</p>--%>

            <%--<p><b>Телефон 2:</b>--%>
            <%--<input type="tel" name="phone2" size="40" value="${sessionScope.ACCOUNT.phone2}">--%>
            <%--</p>--%>

            <button type="submit" class="btn btn-primary" onclick="return destroy();">Изменить</button>

        </form>
    </div>

</div><!-- /.container -->

<jsp:include page="getCssEnd.jsp"/>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
    var i = ${i};
    $(function () {
        $("#datepicker").datepicker();
        $("#datepicker").datepicker("option", "dateFormat", "yy-mm-dd");
        $("#datepicker").datepicker("setDate", "${sessionScope.ACCOUNT.birthday}");
    });

</script>

<script src="${pageContext.request.contextPath}/static/js/sendSubmit.js"></script>
<script src="${pageContext.request.contextPath}/static/js/addtel.js"></script>

<%--<script src="${pageContext.request.contextPath}/js/datepicker.js"></script>--%>


</body>
</html>
