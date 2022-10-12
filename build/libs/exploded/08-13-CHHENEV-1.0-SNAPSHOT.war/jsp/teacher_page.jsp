<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="data" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadAllTestsOutput"/>
<jsp:useBean id="data_passed" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadPassedUsersListOutput"/>
<jsp:useBean id="data_unpassed" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadUnpassedUsersListOutput"/>
<jsp:useBean id="data_unstarted" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadUnstartedStudentsListOutput"/>
<jsp:useBean id="data_unwatched" scope="request" class="ru.rsreu.CHHENEV0813.command.content.output.LoadInvaluableTestsListOutput"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>KnowTest</title>
    <link rel="stylesheet" href="../css/cssTeacher.css">
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body onload="onLoad()">
<header>
    <div id="logo" onclick="slowScroll('#top')">
        <span>KnowTest</span>
    </div>
    <div id="about">
        <a href="#" title="Ваши тесты" onclick="slowScroll('#main')">Ваши тесты</a>
        <li><a href="#" title="Товары" class="down" onclick="slowScroll('#aboutus')">Студенты</a>
            <ul class="submenu">
                <li><a href="#" onclick="slowScroll('#aboutus1')">Прошедшие контроль</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus2')">Не прошедшие контроль</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus3')">Ожидающие оценивания</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus4')">Не приступившие</a></li>
            </ul>
        </li>
        <a href="#" title="Создать тест" onclick="slowScroll('#contact')"  id="button"><aa>Создать тест</aa></a>
        <a href="#" title="Проверить тест" onclick="slowScroll('#contact2')"  id="button2"><aa>Проверить тест</aa></a>
    </div>
</header>

<div id="top">
    <h1>KnowTest</h1>
    <h3><p id = "top3">Автоматизированная система тестирования знаний РГРТУ</p></h3>
    <h5><p id = "auth">Вы авторизованы как преподаватель</p><p id = "authInfo">${sessionScope.sur_name} ${sessionScope.first_name} ${sessionScope.third_name}</p>
        <form class="logout-form tabletop-offset" action="/controller" method="get">
            <input type="hidden" name="command" value="logout">
            <input type="submit" value="Выйти">
        </form>
    </h5>
</div>
<form id="user-list" action="/controller" method="post">
    <input type="hidden" name="command" value="teacher_page_load_tests">


<div id="main">
    <div class="intro">
        <h2>Тесты</h2>
        <span>Список всех тестов</span>
    </div>


    <div class="text">
        <table>
            <tr><th>Наименование</th><th>Описание</th></tr>
            <c:forEach var="test" items="${data.teachersTests}">
                <tr>
                    <th>${test.testName}</th>
                    <th>${test.description}</th>
                </tr>
            </c:forEach>

        </table>
        <p class="intro34">${wrongTestData}</p>
    </div>

</div>

<div id="aboutus">
    <div class="intro">
        <h2>Студенты</h2>
        <span>Основная информация</span>
    </div>

    <div class="text">



        <input type="hidden" name="command" value="teacher_page_load_passed">
        <p id="aboutus1">Студенты, прошедшие контроль знаний</p>
        <table>
            <tr><th>Фамилия</th><th>Имя</th><th>Отчество</th><th>Тест</th><th>Оценка</th></tr>
            <c:forEach var="test" items="${data_passed.passedStudent}">
            <tr>
                <th>${test.name.surName}</th>
                <th>${test.name.firstName}</th>
                <th>${test.name.thirdName}</th>
                <th>${test.testName}</th>
                <th>${test.assessment}</th>
            </tr>
            </c:forEach>
        </table>



        <input type="hidden" name="command" value="teacher_page_load_unpassed">
        <p id="aboutus2">Студенты, не прошедшие контроль знаний</p>
        <table>
            <tr><th>Фамилия</th><th>Имя</th><th>Отчество</th><th>Тест</th><th>Оценка</th></tr>
            <c:forEach var="test" items="${data_unpassed.passedStudent}">
            <tr>
                <th>${test.name.surName}</th>
                <th>${test.name.firstName}</th>
                <th>${test.name.thirdName}</th>
                <th>${test.testName}</th>
                <th>${test.assessment}</th>
            </tr>
            </c:forEach>
        </table>

        <input type="hidden" name="command" value="teacher_page_load_invaluable">
        <p id="aboutus3">Студенты, ожидающие оценивания знаний</p>
        <table>
            <tr><th>Фамилия</th><th>Имя</th><th>Отчество</th><th>ID студента</th><th>Тест</th><th>ID теста</th></tr>
            <c:forEach var="test" items="${data_unwatched.invaluableSolutions}">
            <tr>
                <th>${test.name.surName}</th>
                <th>${test.name.firstName}</th>
                <th>${test.name.thirdName}</th>
                <th>${test.userId}</th>
                <th>${test.testName}</th>
                <th>${test.testId}</th>
            </tr>
            </c:forEach>
        </table>
        <input type="hidden" name="command" value="teacher_page_load_unstarted">
        <p id="aboutus4">Студенты, не приступившие к прохождению контроля знаний</p>
        <table>
            <tr><th>Фамилия</th><th>Имя</th><th>Отчество</th><th>ID студента</th><th>Тест</th><th>ID теста</th></tr>
            <c:forEach var="test" items="${data_unstarted.unstartedSolutions}">
            <tr>
                <th>${test.name.surName}</th>
                <th>${test.name.firstName}</th>
                <th>${test.name.thirdName}</th>
                <th>${test.userId}</th>
                <th>${test.testName}</th>
                <th>${test.testId}</th>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>
</form>
<div id="contact">
    <div class="intro">
        <h2>Создать тест </h2>
        <span></span>
    </div>
    <form name="AddUser" action="/controller" method="post">
        <input type="hidden" name="command" value="teacher_page_add_test">
    <div>
        <p>
            Название теста:
            <br>
            <input type="text" size="72" name="test_name">
        </p>
        <br>
        <p>Описание теста:
            <br>
            <textarea name="test_description" cols="70" rows="5"></textarea>
        </p>
        <br>
        <form enctype="multipart/form-data" method="post" >

            <p>Задание:</p>
            <p>
                <textarea name="test_task" cols="70" rows="7"></textarea>
                <input type="submit" value="Создать тест">

            </p></form>
    </div>
    </form>
</div>

<div id="contact2">
    <div class="intro">
        <h2>Проверить тест </h2>
        <span></span>
    </div>
    <div>

        <form name="AddUser" action="/controller" method="post">
            <input type="hidden" name="command" value="teacher_page_check_test">
        <p>
            Введите ID теста:
            <br>
            <input id="not" type="text" size="72" name="test_id" onclick="onLoad2()" value="${sessionScope.test_id}" >
        </p>
        <br>
        <p>
            Введите ID студента:
            <br>
            <input type="text" size="72" name="student_id" value="${sessionScope.student_id}" >
        </p>
        <input onclick="onLoad2()" type="submit" value="Просмотреть решение" >

        <br>
           <p class="intro34">${wrongStudentTest}</p>
        <br>
        <p>
            Решение студента:
            <br>
            <textarea  name="student_task" cols="70" rows="5" >${sessionScope.test_solution}</textarea>
        </p>
       </form>

        <br>
        <form name="AddAssessment" action="/controller" method="post">
            <input type="hidden" name="command" value="teacher_page_assess_test">
        <p>
            Оценка:
            <select id="selectGrade" name="assessment_id">
                <option value="5">Отлично</option>
                <option value="4">Хорошо</option>
                <option value="3">Удовлетворительно</option>
                <option value="2">Неудовлетворительно</option>
            </select>
            <br><br>
            <input type="submit" value="Выставить оценку" >
        </p>
        </form>
        <br>
    </div>
    <div>&nbsp</div>
    <div>
        <canvas id="myCanvas" width="300" height="300"
                style="background-color:#eee; border:1px solid #ccc;" value="${sessionScope.currentQuestion}">

        </canvas>
    </div>
    <div id="mouthValue" hidden>${sessionScope.mouthValue} </div>
    <div id="eyeValue" hidden>${sessionScope.eyeValue} </div>
    <div id="eyeBrowsValue" hidden>${sessionScope.eyeBrowsValue} </div>
    <div id="noseValue" hidden>${sessionScope.noseValue} </div>
    <div id="hairValue" hidden>${sessionScope.hairValue} </div>
    <div id="headValue" hidden>${sessionScope.headValue} </div>


</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>


    let canvas = document.getElementById("myCanvas"),
        context = canvas.getContext("2d"),
        mouthValue = document.getElementById("mouthValue").innerText,
        eyeValue = document.getElementById("eyeValue").innerText,
        eyeBrowsValue = document.getElementById("eyeBrowsValue").innerText,
        noseValue = document.getElementById("noseValue").innerText,
        hairValue = document.getElementById("hairValue").innerText,
        headValue = document.getElementById("headValue").innerText;

    mouthValue = parseInt(mouthValue);
    eyeValue = parseInt(eyeValue);
    eyeBrowsValue = parseInt(eyeBrowsValue);
    noseValue = parseInt(noseValue);
    hairValue = parseInt(hairValue);
    headValue = parseInt(headValue);

    //head
    context.beginPath();
    context.arc(150, 150, 100, 0, Math.PI * 2, false);
    if (headValue === 50) {
        context.fillStyle = "#FFFF00";
    } else if (headValue <= 10 && headValue >= -20) {
        context.fillStyle = "#fd0000";
    } else if (headValue <= 20 && headValue > 10) {
        context.fillStyle = "#ff4848";
    } else if (headValue <= 30 && headValue > 20) {
        context.fillStyle = "#f57145";
    } else if (headValue <= 40 && headValue > 30) {
        context.fillStyle = "#ff9a00";
    } else if (headValue <= 50 && headValue > 40) {
        context.fillStyle = "#ffa500";
    } else if (headValue <= 60 && headValue > 50) {
        context.fillStyle = "#edff00";
    } else if (headValue <= 70 && headValue > 60) {
        context.fillStyle = "#ebf300";
    } else if (headValue <= 80 && headValue > 70) {
        context.fillStyle = "#ddff00";
    } else if (headValue <= 90 && headValue > 80) {
        context.fillStyle = "#b2ff28";
    } else if (headValue <= 1000 && headValue > 90) {
        context.fillStyle = "#56ff00";
    }
    //context.fillStyle = "#FFFF00";
    context.fill();
    context.closePath();
    context.stroke();

    if (eyeValue < 25) {
        //eyes left
        context.beginPath();
        context.arc(55, 120 + eyeValue / 10, 15, 0, 2 * Math.PI);
        //context.fillStyle = "#333333";
        //context.fill();
        context.closePath();
        context.stroke();
    }
    else if (eyeValue < 50 && eyeValue >= 25){
        //eyes left
        context.beginPath();
        context.arc(70 + eyeValue / 10, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        //eyes right
        context.beginPath();
        context.arc(190, 120 - eyeValue / 10, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();
    } else if (eyeValue === 50){
        //eyes left
        context.beginPath();
        context.arc(120, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        //eyes right
        context.beginPath();
        context.arc(180, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();
    } else if (eyeValue < 75 && eyeValue > 50){
        //eyes left
        context.beginPath();
        context.arc(120, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        //eyes right
        context.beginPath();
        context.arc(180, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        context.beginPath();
        context.arc(120, 120 - eyeValue / 10, 5, 0, 2 * Math.PI);
        context.fillStyle = "#333333";
        context.fill();
        context.closePath();
        context.stroke();
    } else if (eyeValue >= 75){
        //eyes left
        context.beginPath();
        context.arc(120, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        //eyes right
        context.beginPath();
        context.arc(180, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        context.beginPath();
        context.arc(120, 120, 5, 0, 2 * Math.PI);
        context.fillStyle = "#333333";
        context.fill();
        context.closePath();
        context.stroke();

        context.beginPath();
        context.arc(180, 120, 5, 0, 2 * Math.PI);
        context.fillStyle = "#333333";
        context.fill();
        context.closePath();
        context.stroke();
    }

    //mouth
    context.beginPath();
    context.moveTo(120, 205);
    context.quadraticCurveTo(150, 155 + mouthValue, 180, 205);
    context.closePath();
    context.fillStyle = "red";
    context.fill();
    context.lineWidth = 1;
    context.strokeStyle = "black";
    context.stroke();


    //eyebrows left
    context.beginPath();
    context.moveTo(105, 85 + eyeBrowsValue / 10);
    context.lineTo(130, 95 - eyeBrowsValue / 10);
    context.closePath();
    context.stroke();

    //eyebrows right
    context.beginPath();
    context.moveTo(168, 95 - eyeBrowsValue / 10);
    context.lineTo(193, 85 + eyeBrowsValue / 10);
    context.closePath();
    context.stroke();


    if (noseValue <= 15) {

    } else if (noseValue <= 30 && noseValue > 15) {
        //nose
        context.beginPath();
        //context.moveTo(140, 160);
        context.arc(140, 170, 10, 0, Math.PI / 8, true);
        context.closePath();
        context.stroke();
    } else if (noseValue <= 40 && noseValue > 30) {
        //nose
        context.beginPath();
        //context.moveTo(140, 160);
        context.arc(150, 170, 10, 0, Math.PI / 4, true);
        context.closePath();
        context.stroke();
    } else if (noseValue < 50 && noseValue > 40) {
        //nose
        context.beginPath();
        //context.moveTo(140, 160);
        context.arc(145, 170, 10, 0, Math.PI / 2, true);
        context.closePath();
        context.stroke();
    } else if (noseValue === 50) {
        //nose
        context.beginPath();
        //context.moveTo(140, 160);
        context.arc(150, 170, 10, 0, Math.PI / 2, true);
        context.closePath();
        context.stroke();
    } else if (noseValue > 50 && noseValue <= 70) {
        //nose
        context.beginPath();
        context.arc(145, 170, 10, 0, Math.PI, true);
        context.closePath();
        context.stroke();
    } else if (noseValue > 70 && noseValue <= 90) {
        //nose
        context.beginPath();
        context.arc(147, 170, 10, 0, Math.PI, true);
        context.closePath();
        context.stroke();
    } else if (noseValue > 90) {
        //nose
        context.beginPath();
        context.arc(150, 170, 10, 0, Math.PI, true);
        context.closePath();
        context.stroke();
    }

    if (hairValue < 10) {
        //hair
        context.beginPath();
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.closePath();
        context.stroke();
    } else if (hairValue < 25 && hairValue >= 10) {
        //hair
        context.beginPath();
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.closePath();
        context.stroke();
    }else if (hairValue < 50 && hairValue >= 25) {
        //hair
        context.beginPath();
        context.moveTo(134, 52);
        context.lineTo(130, 33);
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.moveTo(166, 52);
        context.lineTo(170, 33);
        context.closePath();
        context.stroke();
    } else if (hairValue === 50){
        //hair
        context.beginPath();
        context.moveTo(125, 54);
        context.lineTo(120, 35);
        context.moveTo(134, 52);
        context.lineTo(130, 33);
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.moveTo(166, 52);
        context.lineTo(170, 33);
        context.moveTo(175, 54);
        context.lineTo(180, 35);
        context.closePath();
        context.stroke();
    } else if (hairValue < 70 && hairValue >= 50) {
        //hair
        context.beginPath();
        context.moveTo(116, 57);
        context.lineTo(109, 37);
        context.moveTo(125, 54);
        context.lineTo(120, 35);
        context.moveTo(134, 52);
        context.lineTo(130, 33);
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.moveTo(166, 52);
        context.lineTo(170, 33);
        context.moveTo(175, 54);
        context.lineTo(180, 35);
        context.moveTo(184, 57);
        context.lineTo(190, 37);
        context.closePath();
        context.stroke();
    }else if (hairValue < 90 && hairValue >= 70) {
        //hair
        context.beginPath();
        context.moveTo(108, 60);
        context.lineTo(100, 41);
        context.moveTo(116, 57);
        context.lineTo(109, 37);
        context.moveTo(125, 54);
        context.lineTo(120, 35);
        context.moveTo(134, 52);
        context.lineTo(130, 33);
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.moveTo(166, 52);
        context.lineTo(170, 33);
        context.moveTo(175, 54);
        context.lineTo(180, 35);
        context.moveTo(184, 57);
        context.lineTo(190, 37);
        context.moveTo(192, 60);
        context.lineTo(200, 41);
        context.closePath();
        context.stroke();
    } else if (hairValue >= 90) {
        //hair
        context.beginPath();
        context.moveTo(100, 62);
        context.lineTo(90,  45);
        context.moveTo(108, 60);
        context.lineTo(100, 41);
        context.moveTo(116, 57);
        context.lineTo(109, 37);
        context.moveTo(125, 54);
        context.lineTo(120, 35);
        context.moveTo(134, 52);
        context.lineTo(130, 33);
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.moveTo(166, 52);
        context.lineTo(170, 33);
        context.moveTo(175, 54);
        context.lineTo(180, 35);
        context.moveTo(184, 57);
        context.lineTo(190, 37);
        context.moveTo(192, 60);
        context.lineTo(200, 41);
        context.moveTo(200, 62);
        context.lineTo(210, 45);
        context.closePath();
        context.stroke();
    }

</script>
<script>
    function slowScroll(id){
        $('html,body').animate({
            scrollTop: $(id).offset().top
        }, 500);

    }

    function onLoad2(){
        localStorage.setItem('int', '1');
    }

    function onLoad(){
        if (localStorage.getItem('int') === '1'  ) {
            slowScroll('#contact2');
            //localStorage.setItem('int', '0');
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
                <li><a href="#" onclick="slowScroll('#main')">Ваши тесты</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus')">Студенты</a></li>
            </ul>
        </nav>
    </div>
</footer>

</html>
