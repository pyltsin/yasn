<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pyltsin
  Date: 14.01.2017
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
    <jsp:include page="getCss.jsp"/>
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <form class="form-signin" name="auth" action="${loginProcessingUrl}" method="post">
        <h2 class="form-signin-heading">Войти в сеть</h2>
        <c:if test="${error}">
            <div class="alert alert-danger" role="alert">
                <strong>Ошибка!</strong> Логин/пароль не корректны.
            </div>

        </c:if>


        <label for="inputLogin" class="sr-only">Login</label>
        <input type="text" id="inputLogin" name="username" class="form-control" placeholder="login" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="remember-me" value="yes"> Сохранить данные?
            </label>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

        <h2><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/register.html">Регистрация</a>
        </h2>
    </form>

</div> <!-- /container -->

<jsp:include page="getCssEnd.jsp"/>


</body>
</html>
