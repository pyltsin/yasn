<%--
  Created by IntelliJ IDEA.
  User: Pyltsin
  Date: 07.01.2017
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Friends</title>
    <jsp:include page="getCss.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/nav2.jsp"/>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3">
            <div class="well sidebar-nav">
                <ul class="list-group">

                    <li class="list-group-item"><img
                            src="${pageContext.request.contextPath}/images/account/${sessionScope.ACCOUNT.login}.jpg"
                            height="240"
                            width="240"></li>
                    <li class="list-group-item">Имя - ${sessionScope.ACCOUNT.firstName}</li>
                    <li class="list-group-item">Отчество - ${sessionScope.ACCOUNT.middleName}</li>
                    <li class="list-group-item">Фамилия - ${sessionScope.ACCOUNT.lastName}</li>
                    <li class="list-group-item">Дата рождения - ${sessionScope.ACCOUNT.date}</li>
                    <li class="list-group-item">email - ${sessionScope.ACCOUNT.email}</li>
                </ul>
            </div>
        </div>
        <div class="span9">
            <h1>Сообщения:</h1>
            Кому:
            <select onchange="loadMessage();" id="loginTo">
                <c:forEach var="friend" items="${friends}">
                    <option>${friend.login}</option>
                </c:forEach>
            </select>
            <p><textarea name="sendsArea" id="sendsArea" style="width: 350px; height: 350px"></textarea></p>
            <p><input maxlength="25" size="40" id="sendMessage"></p>
            <p>
                <button type="button" id="buttonSend" onclick="sendMessage()" class="btn btn-lg btn-primary">Отправить
                </button>
            </p>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->


<jsp:include page="getCssEnd.jsp"/>

<script src="${pageContext.request.contextPath}/static/js/sendMessage.js"></script>
<script>
    $(document).ready(function () {
        var intervalID = setInterval(loadMessage, 5000);
    });
</script>
</body>
</html>
