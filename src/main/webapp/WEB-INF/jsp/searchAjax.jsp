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
    <title>Search</title>
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
            <h1>Результаты поиска</h1>
            <div id="searchItem">
                <p>
                <ul class="list-group col-md-4" id="findRow">
                </ul>
                </p>
            </div>
            <div id="buttonSearch">
                <p>
                    <button type="button" id="buttonBack" onclick="backSearch()" class="btn btn-lg btn-primary">Назад
                    </button>
                    <button type="button" id="buttonForward" onclick="forwardSearch()" class="btn btn-lg btn-primary">
                        Вперед
                    </button>
                </p>
            </div>
        </div>


        <!--/row-->
    </div><!--/span-->
</div><!--/row-->


<jsp:include page="getCssEnd.jsp"/>


<script>
    var textSearch = "${textSearch}";
    var len = 3;
    var by = 0;
    var path = "${pageContext.request.contextPath}";
</script>

<script src="${pageContext.request.contextPath}/js/searchAjaxPage.js"></script>


</body>
</html>
