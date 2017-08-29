<%--@elvariable id="account" type="com.getjavajob.training.web1609.pyltsinm.common.Account"--%>
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

                    <li class="list-group-item"><img
                            src="${pageContext.request.contextPath}/images/account/${account.login}.jpg" height="240"
                            width="240"></li>
                    <li class="list-group-item">Имя - ${account.firstName}</li>
                    <li class="list-group-item">Отчество - ${account.middleName}</li>
                    <li class="list-group-item">Фамилия - ${account.lastName}</li>
                    <li class="list-group-item">Дата рождения - ${account.birthday}</li>
                    <li class="list-group-item">email - ${account.email}</li>
                    <li class="list-group-item list-group-item-success">
                        Телефоны

                    </li>
                    <c:forEach var="phone" items="${account.telephones}">
                        <li class="list-group-item">
                            Тип: ${phone.typeRus}, Телефон: ${phone.telephone}
                        </li>
                    </c:forEach>


                    <li class="list-group-item list-group-item-success">Действия</li>
                    <c:if test="${! accountEquals}">

                        <c:if test="${! isFriends}">

                            <li>
                                <a class="btn btn-primary"
                                   href="${pageContext.request.contextPath}/friends/addFriends?login=${account.login}">Добавить
                                    в
                                    друзья</a></li>

                        </c:if>

                        <c:if test="${requestScope.isFriends}">

                            <li><a class="btn btn-primary"
                                   href="${pageContext.request.contextPath}/friends/deleteFriends?login=${account.login}">Удалить
                                из
                                друзей</a></li>
                        </c:if>

                    </c:if>
                    <li><a class="btn btn-primary"
                           href="${pageContext.request.contextPath}/createXML"> Сохранить XML </a></li>
                    <li>
                        <div class="form-group">
                            Загрузить XML
                            <form action="${pageContext.request.contextPath}/importXML" method="post"
                                  enctype="multipart/form-data"
                                  id="upload">
                                <input type="file" class="form-control-file" name="fileXML"
                                       onchange="document.getElementById('upload').submit()">
                            </form>
                        </div>
                    </li>

                </ul>
            </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
            <h1>Hello, world!</h1>
            <p>Это Место для всякого разного</p>

        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->


<jsp:include page="getCssEnd.jsp"/>

</body>
</html>
