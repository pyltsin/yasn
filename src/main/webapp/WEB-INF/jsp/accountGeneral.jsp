<%--
  Created by IntelliJ IDEA.
  User: Pyltsin
  Date: 29.08.2017
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Account</title>
    <jsp:include page="getCss.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/nav2.jsp"/>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3">
            <div class="well sidebar-nav">
                <ul class="list-group">

                    <li class="list-group-item"><img id="account_picture"
                                                     src=""
                                                     height="240"
                                                     width="240"></li>
                    <li class="list-group-item">Имя - <span id="account_firstName"> </span></li>
                    <li class="list-group-item">Отчество - <span id="account_middleName"> </span></li>
                    <li class="list-group-item">Фамилия - <span id="account_lastName"> </span></li>
                    <li class="list-group-item">Дата рождения - <span id="account_birthday"> </span></li>
                    <li class="list-group-item">email - <span id="account_email"> </span></li>
                    <li class="list-group-item list-group-item-success">
                        Телефоны

                    </li>
                    <div id="account_phones"></div>

                    <li class="list-group-item list-group-item-success">Действия</li>

                    <li>
                        <a class="btn btn-primary" id="account_addFriends" onclick="functAddFriends()">Добавить
                            в
                            друзья</a></li>


                    <li><a class="btn btn-primary" id="account_deleteFriends" onclick="functDeleteFriends()">Удалить
                        из
                        друзей</a></li>

                </ul>
            </div><!--/.well -->
        </div><!--/span-->
        <div class="span7">
            <div id="wrap_info" style="width: 60%; height: 460px">
                <div id="info">
                    <h1>Hello, world!</h1>
                    <p>Это Место для всякого разного</p>
                </div>
            </div>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->


<jsp:include page="getCssEnd.jsp"/>
<script src="${pageContext.request.contextPath}/js/loadPage.js"></script>
<script src="${pageContext.request.contextPath}/js/loadPageChange.js"></script>
<script src="${pageContext.request.contextPath}/js/sendMessage.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-table-ru-RU.js"></script>
<script src="${pageContext.request.contextPath}/js/sendSubmit.js"></script>
<script src="${pageContext.request.contextPath}/js/addtel.js"></script>

<script>
    var login = "${login}";
</script>


</body>
</html>
