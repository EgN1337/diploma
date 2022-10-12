<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="data" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadUserListFromModerOutput"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>KnowTest</title>
    <link rel="stylesheet" href="../css/Moderator.css">
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body onload="onLoad()">
<header>
    <div id="logo" onclick="slowScroll('#top')">
        <span>KnowTest</span>
    </div>
    <div id="about">
        <a href="#" title="Ваши тесты" onclick="slowScroll('#aboutus')">Список всех пользователей</a>
        <li><a href="#" title="Ваши тесты" class="down" onclick="slowScroll('#contact')">Пользователи</a>
            <ul class="submenu">
                <li><a href="#" onclick="slowScroll('#contact')">Заблокировать пользователя</a></li>

            </ul>
        </li>
    </div>
</header>

<div id="top">
    <h1>KnowTest</h1>
    <h3><p id = "top3">Автоматизированная система тестирования знаний РГРТУ</p></h3>
    <h5><p id = "auth">Вы авторизованы как модератор</p><p id = "authInfo">${sessionScope.sur_name} ${sessionScope.first_name} ${sessionScope.third_name}</p>
        <form class="logout-form tabletop-offset" action="/controller" method="get">
            <input type="hidden" name="command" value="logout">
            <input type="submit" value="Выйти">
        </form>
    </h5>

</div>

<div id="aboutus">
    <div class="intro">
        <h2>Пользователи</h2>
        <span>Основная информация</span>
    </div>
    <div class="text">
        <p id="aboutus1">Список всех пользователей:</p>
        <form id="user-list" action="/controller?command=moder_page_load_users" method="post">
            <input type="hidden" name="command" value="moder_page_load_users">
        <table>
        <tr><th>Фамилия</th><th>Имя</th><th>Отчество</th><th>Роль</th><th>Логин</th><th>Блокировка</th></tr>
            <c:forEach var="user" items="${data.userStatusList}">
             <tr>
                <th>${user.userName.surName}</th>
                <th>${user.userName.firstName}</th>
                <th>${user.userName.thirdName}</th>
                <th>${user.role}</th>
                <th>${user.login}</th>
                <th><c:if test="${user.blocked eq 0}" >Активен</c:if>
                    <c:if test="${user.blocked eq 1}" >Заблокирован</c:if></th>
                 <th>
                <form action="/controller" method="post">
                    <input type="hidden" name="command" value="moder_page_edit_user">
                    <input type="hidden" name="login" value="${user.login}">
                    <%--<input type="hidden" name="blocked" value="${user.blocked}">--%>
                    <button class="button" onclick="onLoad2()">
                        <c:if test="${user.blocked eq 0}" >Block</c:if>
                        <c:if test="${user.blocked eq 1}" >Unlock</c:if>
                    </button>
                </form>
                 </th>
             </tr>
            </c:forEach>
        </table>
        </form>
    </div>
</div>


<div id="contact">
    <div class="intro">
        <h2>Заблокировать пользователя</h2>
        <span></span>
    </div>

    <div>
        <form name="AddUser" action="/controller" method="post">
            <input type="hidden" name="command" value="moder_page_edit_user">
        <p>
            Введите логин блокируемого пользователя:
            <br>
            <input type="text" size="75" name="login">
        </p>
        <input type="submit" value="Заблокировать">
        </form>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    function slowScroll(id){
        $('html,body').animate({
            scrollTop: $(id).offset().top
        }, 500)
    }

    function onLoad2(){
        localStorage.setItem('int', '1');
    }

    function onLoad(){
        if (localStorage.getItem('int') === '1'  ) {
            slowScroll('#aboutus');

        }
        localStorage.setItem('int', '0');

    }

    $(document).on("scroll", function () {
        if ($(window).scrollTop() === 0)
            $("header").removeClass("fixed");
        else {
            $("header").attr("class","fixed");
        }
    });
</script>

</body>

<footer class="footer">
    <div class="container grid grid-3">
        <div class="text-center">
            <h1>KnowTest.rsreu</h1>
            <p>Copyright &copy; 2022</p>
        </div>
        <div></div>
        <nav class="text-center">
            <ul>
                <li><a href="#" onclick="slowScroll('#top')">KnowTest</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus')">Пользователи</a></li>

            </ul>
        </nav>
    </div>
</footer>

</html>
