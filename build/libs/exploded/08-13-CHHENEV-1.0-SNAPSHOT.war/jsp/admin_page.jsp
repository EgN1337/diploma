<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="data" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadUserListForAdminContent"/>
<!DOCTYPE html>
<html lang=ru.rsreu.CHHENEV0813.command.content.input.AddUserFromAdminToDefineInput"en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>KnowTest</title>
    <link rel="stylesheet" href="../css/Admin.css">
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
                <li><a href="#" onclick="slowScroll('#contact')">Добавить пользователя</a></li>
                <%--<li><a href="#" onclick="slowScroll('#contact2')">Редактирование пользователя</a></li>--%>
                <li><a href="#" onclick="slowScroll('#contact3')">Удалить пользователя</a></li>
            </ul>
        </li>

    </div>
</header>


<div id="top">
    <h1>KnowTest</h1>
    <h3><p id = "top3">Автоматизированная система тестирования знаний РГРТУ</p></h3>
    <h5><p id = "auth">Вы авторизованы как администратор</p><p id = "authInfo">${sessionScope.sur_name} ${sessionScope.first_name} ${sessionScope.third_name}</p>
        <form class="logout-form" action="/controller" method="get">
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
        <form id="user-list" action="/controller" method="post">
            <input type="hidden" name="command" value="admin_page_load_users">
        <table>
            <tr><th>Фамилия</th><th>Имя</th><th>Отчество</th><th>Роль</th><th>Логин</th><th>Онлайн</th><th></th></tr>
            <c:forEach var="user" items="${data.allUsersInsteadAdmin}">
                <tr>
                    <th>${user.userName.surName}</th>
                    <th>${user.userName.firstName}</th>
                    <th>${user.userName.thirdName}</th>
                    <th>${user.role}</th>
                    <th>${user.login}</th>
                    <th>
                        <c:if test="${user.logged eq 0}" >Не в сети</c:if>
                        <c:if test="${user.logged eq 1}" >В сети</c:if>
                    </th>
                    <%--<th>
                        <form action="/controller" method="post">
                            &lt;%&ndash;<input type="hidden" name="command" value="admin_page_edit_user">&ndash;%&gt;
                            <input type="hidden" name="user_id" value="${user.login}">
                            <input type="hidden" name="sur_name" value="${user.userName.surName}">
                            <input type="hidden" name="first_name" value="${user.userName.firstName}">
                            <input type="hidden" name="third_name" value="${user.userName.thirdName}">
                            <input type="hidden" name="password" value="${user.password}">
                            &lt;%&ndash;<input type="hidden" name="blocked" value="${user.blocked}">&ndash;%&gt;
                                <div id="okno">
                                    Всплывающее окошко!<br>
                                    <a href="#" class="close">Закрыть окно</a>
                                </div>
                                <a href="#okno">Вызвать всплывающее окно</a>
                            &lt;%&ndash;<button class="button" onclick="onLoad2()">
                                Удалить
                            </button>&ndash;%&gt;
                        </form>
                    </th>--%>
                    <th>
                        <form action="/controller" method="post">
                            <input type="hidden" name="command" value="admin_page_delete_user">
                            <input type="hidden" name="user_id" value="${user.login}">
                            <%--<input type="hidden" name="blocked" value="${user.blocked}">--%>
                            <button class="button" onclick="onLoad2()">
                                Удалить
                            </button>
                        </form>
                    </th>
                </tr>


          </c:forEach>
        </table>
            <p class="error-message">
                ${wrongInputData}
            </p>
        </form>
    </div>
</div>


<div id="contact">
    <div class="intro">
        <h2>Добавить пользователя</h2>
        <span></span>
    </div>

    <div>
        <form name="AddUser" action="/controller" method="post">
            <input type="hidden" name="command" value="admin_page_add_user">
            <input type="hidden" name="command" value="admin_page_load_users">
            <p>
                Фамилия:
                <br>
                <input type="text" size="75" name="sur_name"><br>
            </p>
            <p>
                Имя:
                <br>
                <input type="text" size="75" name="first_name"><br>
            </p>
            <p>
                Отчество:
                <br>
                <input type="text" size="75" name="third_name"><br>
            </p><br>

            <p>Роль пользователя:</p>

                <input type="radio" id="radioButton2" value="1" name="user_role">
                <label for="radioButton2">Модератор</label><br>
                <input type="radio" id="radioButton3" value="2" name="user_role">
                <label for="radioButton3">Преподаватель</label><br>
                <input type="radio" id="radioButton4" value="3" name="user_role">
                <label for="radioButton4">Студент</label><br>
             <br>
            <p>
                Введите логин данному пользователю:
            <br>
            <input type="text" size="75" name="user_id"><br>
            </p>
            <p>
                Введите пароль данному пользователю:
                <br>
                <input type="text" size="75" name="password">
            </p>
            <input type="submit" value="Добавить" onclick="onLoad2()">
            <%--<p class="error-message">
                ${wrongInputData}
            </p>--%>
        </form>
    </div>
</div>


<%--<div id="contact2">
    <div class="intro">
        <h2>Редактирование пользователя</h2>
        <span></span>
    </div>
    <div>
        <form name="EditUser" action="/controller" method="post">
            <input type="hidden" name="command" value="admin_page_edit_user">

        <p>
            Введите логин изменяемого пользователя:
            <br>
            <input type="text" size="75" name="user_id">
        </p>

        <p>
            Фамилия:
            <br>
            <input type="text" size="75" name="sur_name">
        </p>
        <p><br>
            Имя:
            <br>
            <input type="text" size="75" name="first_name">
        </p>
        <p><br>
            Отчество:
            <br>
            <input type="text" size="75" name="third_name">
        </p><br>
        <p>
            Введите пароль данному пользователю:
            <br>
            <input type="text" size="75" name="password">
        </p>
            <input type="submit" value="Редактировать">
        </form>
    </div>
</div>--%>

<div id="contact3">
    <div class="intro">
        <h2>Удалить пользователя</h2>
        <span></span>
    </div>
    <div>
        <form name="DeleteUser" action="/controller" method="post">
            <input type="hidden" name="command" value="admin_page_delete_user">
        <p>
            Введите логин удаляемого пользователя:
            <br>
            <input type="text" size="75" name="user_id">
        </p>
        <input type="submit" value="Удалить">
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

    function openForm() {

        const button = document.querySelector("button");
        const form = document.querySelector("#blablabla");
        const popup = document.querySelector(".popup");

        button.addEventListener('click', () => {
            form.classList.add('open');
            popup.classList.add('popup_open');
        });

    }
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
