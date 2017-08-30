<%--
  Created by IntelliJ IDEA.
  User: Pyltsin
  Date: 07.01.2017
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<nav class="navbar navbar-inverse navbar-fixed-top" style="z-index: 0">
    <div class="container">
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a onclick="loadAllForAccount('')" href="#">Я

                    </a></li>

                <li><a onclick="loadAllForFriends()" href="#">Друзья</a></li>
                <li><a onclick="loadAllForSends()" href="#">Сообщения
                </a></li>
                <li>
                    <div class="navbar-form navbar-left">

                        <div class="form-group ui-widget">
                            <input id="searchAjax2" class="form-control" name="search" placeholder="Поиск по сайту">
                        </div>
                        <button onclick="loadAllForFind()" class="btn btn-default">Найти</button>
                    </div>
                </li>
                <li><a href="#" onclick="loadAllForChange()">Настройка</a></li>

                <li><a href="${pageContext.request.contextPath}/signout">Выход</a></li>
                <li>Сегодня: <%= new java.util.Date() %>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

