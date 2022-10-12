<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="data_passed" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadStudentPassedTestsOutput"/>
<jsp:useBean id="data_unpassed" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadStudentUnpassedTestsOutput"/>
<jsp:useBean id="data_unchecked" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadStudentUncheckedTestsOutput"/>
<jsp:useBean id="data_unstarted" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadStudentUnsolvedTestsOutput"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>KnowTest</title>
    <link rel="stylesheet" href="../css/cssStudent.css">
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body onload="onLoad()">
<header>
    <div id="logo" onclick="slowScroll('#top')">
        <span>KnowTest</span>
    </div>
    <div id="about">
        <!--<a href="#" title="Ваши тесты" onclick="slowScroll('#main')">Ваши тесты</a>-->
        <li><a href="#" title="Ваши тесты" class="down" onclick="slowScroll('#aboutus')">Ваши тесты</a>
            <ul class="submenu">
                <li><a href="#" onclick="slowScroll('#aboutus1')">Пройденные успешно</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus2')">Пройденные неуспешно</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus3')">Неоцененные</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus4')">Не начатые</a></li>
            </ul>
        </li>
        <a href="#" title="Пройти тест" onclick="slowScroll('#contact')"  id="button"><aa>Пройти тест</aa></a>

    </div>
</header>

<div id="top">
    <h1>KnowTest</h1>
    <h3><p id = "top3">Автоматизированная система тестирования знаний РГРТУ</p></h3>
    <h5><p id = "auth">Вы авторизованы как студент</p><p id = "authInfo">${sessionScope.sur_name} ${sessionScope.first_name} ${sessionScope.third_name}</p>
        <form class="logout-form tabletop-offset" action="/controller" method="get">
            <input type="hidden" name="command" value="logout">
            <input type="submit" value="Выйти">
        </form>
    </h5>

</div>

<div id="aboutus">
    <div class="intro">
        <h2>Тесты</h2>
        <span>Основная информация</span>
    </div>
    <div class="text">
        <p id="aboutus1">Тесты, пройденные успешно:</p>
        <table>
            <tr><th>Наименование теста</th><th>Ваша оценка</th></tr>
            <c:forEach var="test" items="${data_passed.passedTestList}">
            <tr>
                <th>${test.testName}</th>
                <th>${test.assessment}</th>
            </tr>
            </c:forEach>
        </table>
        <p id="aboutus2">Тесты, пройденные неуспешно:</p>
        <table>
            <tr><th>Наименование теста</th><th>Ваша оценка</th></tr>
            <c:forEach var="test" items="${data_unpassed.passedTestList}">
            <tr>
                <th>${test.testName}</th>
                <th>${test.assessment}</th>
            </tr>
            </c:forEach>
        </table>
        <p id="aboutus3">Тесты, которые еще не оценены:</p>
        <table>
            <tr><th>Id теста</th><th>Тест</th></tr>
            <c:forEach var="test" items="${data_unchecked.uncheckedTestList}">
            <tr>
                <th>${test.testId}</th>
                <th>${test.testName}</th>
            </tr>
            </c:forEach>
        </table>
        <p id="aboutus4">Тесты, к выполнению которых вы еще не приступили:</p>
        <table>
            <tr><th>Id теста</th><th>Тест</th></tr>
            <c:forEach var="test" items="${data_unstarted.uncheckedTestList}">
            <tr>
                <th>${test.testId}</th>
                <th>${test.testName}</th>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div id="contact">
    <div class="intro">
        <h2>Пройти тест </h2>
        <span></span>
    </div>
    <div>
        <form name="AddUser" action="/controller" method="post">
            <input type="hidden" name="command" value="student_page_solve_test">
        <p>
            Введите ID теста:
            <br>
            <input type="text" size="72" name="test_id" value="${sessionScope.test_id2}">
        </p>
        <input type="submit" value="Пройти тест">
            <p>${wrongTest}</p>
        <br>
        <br>

        <%--<p>Описание теста:
            <br>
            <textarea name="test_description" cols="70" rows="5">${sessionScope.test_description2}</textarea>
        </p>
        <br>

            <p>Задание:</p>
            <p>
                <textarea name="test_task" cols="70" rows="7">${sessionScope.test_task2}</textarea>

            </p>
        </form>
        <form name="AddUser" action="/controller" method="post">
            <input type="hidden" name="command" value="student_page_add_solution">
        <p>Решение:</p>
        <p>
            <textarea name="test_solution" cols="70" rows="7"></textarea>
            <input type="submit" value="Отправить">
        </p>--%>
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
            slowScroll('#contact');

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
                <li><a href="#" onclick="slowScroll('#aboutus')">Ваши тесты</a></li>
                <li><a href="#" onclick="slowScroll('#contact')">Пройти тест</a></li>
            </ul>
        </nav>
    </div>
</footer>

</html>
