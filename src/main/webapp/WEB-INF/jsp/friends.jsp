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
            <h1>Друзья</h1>
            <ul class="list-group col-md-4">

                <c:forEach var="friend" items="${friends}">
                    <li class="list-group-item"><a
                            href="${pageContext.request.contextPath}/account?login=${friend.login}">

                        <img
                                src="${pageContext.request.contextPath}/images/account/${friend.login}.jpg"
                                height="50"
                                width="50">
                            ${friend.name}
                    </a>
                    </li>
                </c:forEach>
            </ul>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->


<%--<div id="container">--%>
<%--<div id="sidebar">--%>
<%--Кратко о себе:--%>

<%--<P>Имя - ${sessionScope.ACCOUNT.firstName}--%>
<%--</P>--%>
<%--<P>Отчество - ${sessionScope.ACCOUNT.middleName}--%>
<%--</P>--%>
<%--<P>Фамилия - ${sessionScope.ACCOUNT.lastName}--%>

<%--</div>--%>

<%--<article>--%>
<%--<p>--%>
<%--<strong> Мои друзья: </strong>--%>
<%--</p>--%>
<%--<p>--%>
<%--<ul>--%>
<%--<c:forEach var="friend" items="${friends}">--%>
<%--<li><a href="${pageContext.request.contextPath}/account?login=${friend.login}">${friend.name}</a></li>--%>
<%--</c:forEach>--%>
<%--</ul>--%>
<%--</p>--%>

<%--</article>--%>
<%--</div>--%>

<jsp:include page="getCssEnd.jsp"/>


</body>
</html>
